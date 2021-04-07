package com.dunshan.mall.member.service;

import com.dunshan.mall.model.UmsMemberReceiveAddress;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author LiWen
 * @version 1.0
 * @Date: 2020-11-16 20:22
 * @Description: 会员收获地址信息
 */
public interface MemberReceiveAddressService {
    /**
     * 添加收货地址
     */
    int add(UmsMemberReceiveAddress address);

    /**
     * 删除收货地址
     * @param id 地址表的id
     */
    int delete(Long id);

    /**
     * 修改收货地址
     * @param id 地址表的id
     * @param address 修改的收货地址信息
     */
    @Transactional
    int update(Long id, UmsMemberReceiveAddress address);

    /**
     * 返回当前用户的收货地址
     */
    List<UmsMemberReceiveAddress> list();

    /**
     * 获取地址详情
     * @param id 地址id
     */
    UmsMemberReceiveAddress getItem(Long id);
}
