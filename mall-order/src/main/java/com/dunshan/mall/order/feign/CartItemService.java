package com.dunshan.mall.order.feign;

import com.dunshan.mall.common.api.CommonResult;
import com.dunshan.mall.order.domain.CartPromotionItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LiWen
 * @version 1.0
 * @Date: 2020-11-16 17:15
 * @Description: 购物车
 */
@FeignClient("mall-cart")
public interface CartItemService {
    /**
     * 获取包含促销活动信息的购物车列表
     */
    @RequestMapping(value = "/cart/list/promotionNew/{memberId}", method = RequestMethod.POST)
    List<CartPromotionItem> listPromotionnew(@PathVariable("memberId") Long memberId, @RequestBody List<Long> cartIds);


    /**
     * 批量删除购物车中的商品
     */
    @RequestMapping(value = "/cart/delete", method = RequestMethod.POST)
    public CommonResult delete(@RequestParam("ids") List<Long> ids);

}
