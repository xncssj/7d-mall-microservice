package com.dunshan.mall.portal.controller;

import com.dunshan.mall.model.OmsCartItem;
import com.dunshan.mall.portal.domain.CartPromotionItem;
import com.dunshan.mall.portal.service.OmsPromotionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 优惠劵api
 * @author: 李文
 * @create: 2020-11-15 15:20
 **/
@Controller
@Api(tags = "CartPromotionController", description = "促销管理内容管理")
@RequestMapping("/promotion")
public class CartPromotionController {

    @Autowired
    OmsPromotionService omsPromotionService;

    /**
     * 计算购物车中的促销活动信息
     *
     * @param cartItemList
     * @return
     */
    @PostMapping("/cart/CartPromotion")
    @ResponseBody
    List<CartPromotionItem> calcCartPromotion(@RequestBody List<OmsCartItem> cartItemList) {
        List<CartPromotionItem> cartPromotionItems = omsPromotionService.calcCartPromotion(cartItemList);
        return cartPromotionItems;
    }

}

