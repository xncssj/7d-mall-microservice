package com.dunshan.mall.member.service;

import com.dunshan.mall.common.api.CommonResult;
import com.dunshan.mall.common.domain.UserDto;
import com.dunshan.mall.model.UmsMember;
import com.dunshan.mall.model.UmsMemberReceiveAddress;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MemberService {

    /**
     * 加缓存
     *
     * @return
     */
    Long addCache();

    /**
     * 根据用户名获取会员
     */
    UmsMember getByUsername(String username);

    /**
     * 根据会员编号获取会员
     */
    UmsMember getById(Long id);

    /**
     * 用户注册
     */
    @Transactional
    void register(String username, String password, String telephone, String authCode);

    /**
     * 生成验证码
     */
    String generateAuthCode(String telephone);

    /**
     * 修改密码
     */
    @Transactional
    void updatePassword(String telephone, String password, String authCode);

    /**
     * 获取当前登录会员
     */
    UmsMember getCurrentMember();

    /**
     * 根据会员id修改会员积分
     */
    void updateIntegration(Long id, Integer integration);


    /**
     * 获取用户信息
     */
    UserDto loadUserByUsername(String username);

    /**
     * 登录后获取token
     */
    CommonResult login(String username, String password);

    /**
     * 获取用户地址
     *
     * @return
     */
    List<UmsMemberReceiveAddress> list();
}
