package com.dunshan.mall.member.service;

import com.dunshan.mall.member.domain.CartPromotionItem;
import com.dunshan.mall.member.domain.SmsCouponHistoryDetail;
import com.dunshan.mall.model.SmsCoupon;
import com.dunshan.mall.model.SmsCouponHistory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MemberCouponService {
    /**
     * 会员添加优惠券
     */
    @Transactional
    void add(Long couponId);

    /**
     * 获取优惠券历史列表
     */
    List<SmsCouponHistory> listHistory(Integer useStatus);

    /**
     * 根据购物车信息获取可用优惠券
     */
    List<SmsCouponHistoryDetail> listCart(List<CartPromotionItem> cartItemList, Integer type);

    /**
     * 获取当前商品相关优惠券
     */
    List<SmsCoupon> listByProduct(Long productId);

    /**
     * 获取用户优惠券列表
     */
    List<SmsCoupon> list(Integer useStatus);
}
