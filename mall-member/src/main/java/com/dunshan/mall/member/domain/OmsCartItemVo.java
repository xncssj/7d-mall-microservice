package com.dunshan.mall.member.domain;

import java.util.List;

/**
 * @author LiWen
 * @version 1.0
 * @Date: 2020-11-30 18:45
 * @Description: 根据购物车信息获取可用优惠券
 */
public class OmsCartItemVo {
    List<CartPromotionItem> cartItemList;

    public List<CartPromotionItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartPromotionItem> cartItemList) {
        this.cartItemList = cartItemList;
    }
}
