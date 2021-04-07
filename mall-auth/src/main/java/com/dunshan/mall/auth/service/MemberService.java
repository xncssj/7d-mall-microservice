package com.dunshan.mall.auth.service;

import com.dunshan.mall.common.domain.UserDto;
import com.dunshan.mall.model.UmsMember;

public interface MemberService {

    /**
     * 根据用户名获取会员
     */
    UmsMember getByUsername(String username);

    /**
     * 根据会员编号获取会员
     */
    UmsMember getById(Long id);

    /**
     * 获取当前登录会员
     */
    UmsMember getCurrentMember();

    /**
     * 获取用户信息
     */
    UserDto loadUserByUsername(String username);


}
