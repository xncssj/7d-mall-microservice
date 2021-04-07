package com.dunshan.mall.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.dunshan.mall.auth.constant.MessageConstant;
import com.dunshan.mall.auth.domain.SecurityUser;
import com.dunshan.mall.auth.feign.AdminFeign;
import com.dunshan.mall.auth.service.MemberCacheService;
import com.dunshan.mall.auth.service.MemberService;
import com.dunshan.mall.common.constant.AuthConstant;
import com.dunshan.mall.common.domain.UserDto;
import com.dunshan.mall.model.UmsMember;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户管理业务类 Created by dunshan on 2020/6/19.
 */
@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private AdminFeign adminFeign;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberCacheService memberCacheService;

    @Value("${auth.cacheSwitch}")
    private String cacheSwitch;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String clientId = request.getParameter("client_id");
        UserDto userDto = null;
        if (AuthConstant.ADMIN_CLIENT_ID.equals(clientId)) {
            userDto = adminFeign.loadUserByUsername(username);
        } else {
            if ("1".equals(cacheSwitch)) {
                userDto = getMemberFromCache(username);
            }
            if (userDto == null) {
                userDto = memberService.loadUserByUsername(username);
            }
        }
        if (userDto == null) {
            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }
        userDto.setClientId(clientId);
        SecurityUser securityUser = new SecurityUser(userDto);
        if (!securityUser.isEnabled()) {
            throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
        } else if (!securityUser.isAccountNonLocked()) {
            throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
        } else if (!securityUser.isAccountNonExpired()) {
            throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
        } else if (!securityUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
        }
        return securityUser;
    }

    private UserDto getMemberFromCache(String username) {
        UmsMember member = memberCacheService.getMemberByUsername(username);
        if (member != null) {
            UserDto userDto = new UserDto();
            BeanUtil.copyProperties(member, userDto);
            userDto.setRoles(CollUtil.toList("前台会员"));
            return userDto;
        }
        return null;
    }

}
