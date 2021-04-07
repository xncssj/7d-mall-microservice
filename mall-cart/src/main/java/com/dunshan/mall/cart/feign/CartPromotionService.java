package com.dunshan.mall.cart.feign;

import com.dunshan.mall.cart.domain.CartPromotionItem;
import com.dunshan.mall.model.OmsCartItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author LiWen
 * @version 1.0
 * @Date: 2020-11-25 16:58
 * @Description: 调用商城数据
 */
@FeignClient("mall-portal")
public interface CartPromotionService {

    /**
     * 计算购物车中的促销活动信息
     *
     * @param cartItemList
     * @return
     */
    @PostMapping("/promotion/cart/CartPromotion")
    List<CartPromotionItem> calcCartPromotion(@RequestBody List<OmsCartItem> cartItemList);
}
