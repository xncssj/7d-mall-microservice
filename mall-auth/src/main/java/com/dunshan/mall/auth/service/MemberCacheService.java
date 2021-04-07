package com.dunshan.mall.auth.service;


import com.dunshan.mall.model.UmsMember;

public interface MemberCacheService {
    /**
     * 删除会员用户缓存
     */
    void delMember(Long memberId);

    /**
     * 获取会员用户缓存
     */
    UmsMember getMember(Long memberId);

    /**
     * 获取会员用户缓存
     */
    UmsMember getMemberByUsername(String username);
    /**
     * 设置会员用户缓存
     */
    void setMember(UmsMember member);

    /**
     * 设置验证码
     */
    void setAuthCode(String telephone, String authCode);

    /**
     * 获取验证码
     */
    String getAuthCode(String telephone);
}
