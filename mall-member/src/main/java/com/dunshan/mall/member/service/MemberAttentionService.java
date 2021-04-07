package com.dunshan.mall.member.service;

import com.dunshan.mall.member.domain.MemberBrandAttention;
import org.springframework.data.domain.Page;

public interface MemberAttentionService {
    /**
     * 添加关注
     */
    int add(MemberBrandAttention memberBrandAttention);

    /**
     * 取消关注
     */
    int delete(Long brandId);

    /**
     * 获取用户关注列表
     */
    Page<MemberBrandAttention> list(Integer pageNum, Integer pageSize);

    /**
     * 获取用户关注详情
     */
    MemberBrandAttention detail(Long brandId);

    /**
     * 清空关注列表
     */
    void clear();
}
