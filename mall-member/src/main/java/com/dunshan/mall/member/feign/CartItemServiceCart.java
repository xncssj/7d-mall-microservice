package com.dunshan.mall.member.feign;


import com.dunshan.mall.common.api.CommonResult;
import com.dunshan.mall.member.domain.CartPromotionItem;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author liwen
 */
@FeignClient("mall-cart")
public interface CartItemServiceCart {

    /**
     * 获取包含促销活动信息的购物车列表
     */
    @RequestMapping(value = "/list/promotionNew/{memberId}", method = RequestMethod.POST)
    List<CartPromotionItem> listPromotionnew(@PathVariable("memberId") Long memberId, @RequestBody List<Long> cartIds);


}
