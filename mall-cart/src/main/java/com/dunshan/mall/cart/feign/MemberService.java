package com.dunshan.mall.cart.feign;

import com.dunshan.mall.cart.domain.CartPromotionItem;
import com.dunshan.mall.common.api.CommonResult;
import com.dunshan.mall.model.OmsCartItem;
import com.dunshan.mall.model.UmsMember;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author liwen
 * 2020/11/15
 */
@FeignClient("mall-member")
public interface MemberService {

    /**
     * 通过feig远程调用
     *
     * @return
     */
    @GetMapping(value = "/sso/feign/info")
    UmsMember getCurrentMember();

    @RequestMapping(value = "/sso/info", method = RequestMethod.GET)
    CommonResult info();


}
