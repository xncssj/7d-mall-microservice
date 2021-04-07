package com.dunshan.mall.member.service.imp;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.json.JSONUtil;
import com.dunshan.mall.common.api.CommonResult;
import com.dunshan.mall.common.api.ResultCode;
import com.dunshan.mall.common.constant.AuthConstant;
import com.dunshan.mall.common.domain.UserDto;
import com.dunshan.mall.common.exception.Asserts;
import com.dunshan.mall.mapper.UmsMemberLevelMapper;
import com.dunshan.mall.mapper.UmsMemberLoginLogMapper;
import com.dunshan.mall.mapper.UmsMemberMapper;
import com.dunshan.mall.mapper.UmsMemberReceiveAddressMapper;
import com.dunshan.mall.member.feign.AuthService;
import com.dunshan.mall.member.service.MemberCacheService;
import com.dunshan.mall.member.service.MemberService;
import com.dunshan.mall.member.utils.MD5Util;
import com.dunshan.mall.model.*;
import com.nimbusds.jose.JWSObject;
import java.text.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @description: 会员实现类
 * @author: 李文
 * @create: 2020-11-15 14:16
 **/
@Service
public class MemberServiceImpl implements MemberService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MemberServiceImpl.class);
    @Autowired
    private UmsMemberMapper memberMapper;
    @Autowired
    private UmsMemberLevelMapper memberLevelMapper;
    @Autowired
    private MemberCacheService memberCacheService;
    @Value("${redis.key.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Autowired
    private AuthService authService;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UmsMemberReceiveAddressMapper addressMapper;

    @Autowired
    MemberService memberService;

    @Autowired
    UmsMemberLoginLogMapper umsMemberLoginLog;


    @Override
    public Long addCache() {
        long count = memberMapper.countByExample(null);
        int size = 1000;
        int page = (int) (count / size) + 1;
        for (int i = 0; i < page; i++) {
            long start = i * size;
            long end = start + size;

            List<UmsMember> members = memberMapper.selectLimit(start, new Long(size));
            members.stream().forEach(x -> {
                memberCacheService.setMember(x);
            });
            LOGGER.info("addCache pageSize={} start={} end={}", size, start, end);
        }
        return count;
    }


    @Override
    public UmsMember getByUsername(String username) {

        UmsMember member = memberCacheService.getMemberByUsername(username);
        if (member != null)
            return member;
        member = getByUsernameFromDB(username);
        if (member != null)
            memberCacheService.setMember(member);
        return member;
    }

    public UmsMember getByUsernameFromDB(String username) {
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsMember> memberList = memberMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(memberList)) {
            return memberList.get(0);
        }
        return null;
    }

    @Override
    public UmsMember getById(Long id) {

        return memberMapper.selectByPrimaryKey(id);
    }

    @Override
    public void register(String username, String password, String telephone, String authCode) {
        //验证验证码
        if (!verifyAuthCode(authCode, telephone)) {
            Asserts.fail("验证码错误");
        }
        //查询是否已有该用户
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andUsernameEqualTo(username);
        example.or(example.createCriteria().andPhoneEqualTo(telephone));
        List<UmsMember> umsMembers = memberMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(umsMembers)) {
            Asserts.fail("该用户已经存在");
        }
        //没有该用户进行添加操作
        UmsMember umsMember = new UmsMember();
        umsMember.setUsername(username);
        umsMember.setPhone(telephone);
        umsMember.setPassword(MD5Util.toMD5(password));
        umsMember.setCreateTime(new Date());
        umsMember.setStatus(1);
        umsMember.setBirthday(new Date());
        //获取默认会员等级并设置
        UmsMemberLevelExample levelExample = new UmsMemberLevelExample();
        levelExample.createCriteria().andDefaultStatusEqualTo(1)  ;
        List<UmsMemberLevel> memberLevelList = memberLevelMapper.selectByExample(levelExample);
        if (!CollectionUtils.isEmpty(memberLevelList)) {
            umsMember.setMemberLevelId(memberLevelList.get(0).getId());
        }
        memberMapper.insert(umsMember);
        umsMember.setPassword(null);
    }



    @Override
    public String generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        memberCacheService.setAuthCode(telephone, sb.toString());
        return sb.toString();
    }

    @Override
    public void updatePassword(String telephone, String password, String authCode) {
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andPhoneEqualTo(telephone);
        List<UmsMember> memberList = memberMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(memberList)) {
            Asserts.fail("该账号不存在");
        }
        //验证验证码
        if (!verifyAuthCode(authCode, telephone)) {
            Asserts.fail("验证码错误");
        }
        UmsMember umsMember = memberList.get(0);
        umsMember.setPassword(BCrypt.hashpw(password));
        memberMapper.updateByPrimaryKeySelective(umsMember);
        memberCacheService.delMember(umsMember.getId());
    }

    @Override
    public UmsMember getCurrentMember() {
        String userStr = request.getHeader(AuthConstant.USER_TOKEN_HEADER);
        if (StrUtil.isEmpty(userStr)) {
            userStr = transFromToken();
            if (StrUtil.isEmpty(userStr)) {
                LOGGER.info("获取用户信息失败！{}", userStr);
                Asserts.fail(ResultCode.UNAUTHORIZED);
            }
        }
        UserDto userDto = JSONUtil.toBean(userStr, UserDto.class);
        UmsMember member = memberCacheService.getMember(userDto.getId());
        if (member != null) {
            return member;
        } else {
            member = getById(userDto.getId());
            memberCacheService.setMember(member);
            return member;
        }
    }

    private String transFromToken() {
        String token = request.getHeader(AuthConstant.JWT_TOKEN_HEADER);
        if (StrUtil.isEmpty(token)) {
            return "";
        }
        String realToken = token.replace(AuthConstant.JWT_TOKEN_PREFIX, "");
        JWSObject jwsObject = null;
        try {
            jwsObject = JWSObject.parse(realToken);
        } catch (ParseException e) {
            LOGGER.error("transFromToken error", e);
            return "";
        }
        String userStr = jwsObject.getPayload().toString();
        return userStr;
    }

    @Override
    public void updateIntegration(Long id, Integer integration) {
        UmsMember record = new UmsMember();
        record.setId(id);
        record.setIntegration(integration);
        memberMapper.updateByPrimaryKeySelective(record);
        memberCacheService.delMember(id);


    }

    @Override
    public UserDto loadUserByUsername(String username) {
        UmsMember member = getByUsername(username);
        if (member != null) {
            UserDto userDto = new UserDto();
            BeanUtil.copyProperties(member, userDto);
            userDto.setRoles(CollUtil.toList("前台会员"));
            return userDto;
        }
        return null;
    }

    @Override
    public CommonResult login(String username, String password) {
        if (StrUtil.isEmpty(username) || StrUtil.isEmpty(password)) {
            Asserts.fail("用户名或密码不能为空！");
        }
        Map<String, String> params = new HashMap<>();
        params.put("client_id", AuthConstant.PORTAL_CLIENT_ID);
        params.put("client_secret", "123456");
        params.put("grant_type", "password");
        params.put("password", password);
        params.put("username", username);
        CommonResult restResult = authService.getAccessToken(params);
        if (ResultCode.SUCCESS.getCode() == restResult.getCode() && restResult.getData() != null) {
//            updateLoginTimeByUsername(username);
            insertLoginLog(username);
        }
        if (restResult == null) {
            return CommonResult.failed("认证失败:" + restResult);
        }
        return restResult;
    }

    /**
     * 添加登录记录
     *
     * @param username 用户名
     */
    private void insertLoginLog(String username) {
        UmsMember memberName = getByUsername(username);
        if (memberName == null) {
            return;
        }
        UmsMemberLoginLog loginLog = new UmsMemberLoginLog();
        loginLog.setMemberId(memberName.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(request.getRemoteAddr());
        umsMemberLoginLog.insert(loginLog);
    }

    @Override
    public List<UmsMemberReceiveAddress> list() {
        UmsMember currentMember = memberService.getCurrentMember();
        UmsMemberReceiveAddressExample example = new UmsMemberReceiveAddressExample();
        example.createCriteria().andMemberIdEqualTo(currentMember.getId());
        return addressMapper.selectByExample(example);
    }


    //对输入的验证码进行校验
    private boolean verifyAuthCode(String authCode, String telephone) {
        if (StringUtils.isEmpty(authCode)) {
            return false;
        }
        String realAuthCode = memberCacheService.getAuthCode(telephone);
        return authCode.equals(realAuthCode);
    }

//    public static void main(String[] args) {
//        long count = 10001L;
//        int size = 1000;
//        int page = (int) (count / size) + 1;
//        for (int i = 0; i < page; i++) {
//            int start = i * size;
//            int end = start + size;
//            System.out.println(start + "," + end);
//        }
//    }
}

