package com.dunshan.mall.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.dunshan.mall.auth.service.MemberCacheService;
import com.dunshan.mall.auth.service.MemberService;
import com.dunshan.mall.common.api.ResultCode;
import com.dunshan.mall.common.constant.AuthConstant;
import com.dunshan.mall.common.domain.UserDto;
import com.dunshan.mall.common.exception.Asserts;
import com.dunshan.mall.mapper.UmsMemberLoginLogMapper;
import com.dunshan.mall.mapper.UmsMemberMapper;
import com.dunshan.mall.model.UmsMember;
import com.dunshan.mall.model.UmsMemberExample;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @description: 会员实现类
 * @author: 李文
 * @create: 2020-11-15 14:16
 **/
@Service
public class MemberServiceImpl implements MemberService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Resource
    private UmsMemberMapper memberMapper;

    @Resource
    private UmsMemberLoginLogMapper umsMemberLoginLog;

    @Autowired
    private MemberCacheService memberCacheService;

    @Autowired
    private HttpServletRequest request;


    @Override
    public UmsMember getByUsername(String username) {

        UmsMember member = memberCacheService.getMemberByUsername(username);
        if (member != null) {
            return member;
        }
        member = getByUsernameFromDB(username);
        if (member != null) {
            memberCacheService.setMember(member);
        }
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
    public UmsMember getCurrentMember() {
        String userStr = request.getHeader(AuthConstant.USER_TOKEN_HEADER);
        if (StrUtil.isEmpty(userStr)) {
            LOGGER.info("获取用户信息失败！{}", userStr);
            Asserts.fail(ResultCode.UNAUTHORIZED);
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


}

