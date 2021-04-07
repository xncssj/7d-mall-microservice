package com.dunshan.mall.cart.controller;

import com.alibaba.fastjson.JSON;
import com.dunshan.mall.cart.domain.CartProduct;
import com.dunshan.mall.cart.domain.CartPromotionItem;
import com.dunshan.mall.cart.feign.MemberService;
import com.dunshan.mall.cart.service.CartItemService;
import com.dunshan.mall.common.api.CommonResult;
import com.dunshan.mall.model.OmsCartItem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 购物车服务
 * @author: 李文
 * @create: 2020-11-15 13:59
 **/
@Log4j2
@Controller
@Api(tags = "CartItemController", description = "购物车管理")
@RequestMapping("/cart")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private MemberService memberService;


    @ApiOperation("添加商品到购物车")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult add(@RequestBody OmsCartItem cartItem) {
        int count = cartItemService.add(cartItem);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("添加商品到购物车")
    @RequestMapping(value = "/addNew", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addNew(String productSkuCode, Integer quantity) {
        int count = cartItemService.addCart(productSkuCode, quantity);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取某个会员的购物车列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<OmsCartItem>> list() {
        Long id = memberService.getCurrentMember().getId();
        List<OmsCartItem> cartItemList = cartItemService.list(id);
        return CommonResult.success(cartItemList);
    }

//    @ApiOperation("获取某个会员的购物车列表,包括促销信息")
//    @RequestMapping(value = "/list/promotion/{memberId}", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult<List<CartPromotionItem>> listPromotion(@PathVariable("memberId") Long memberId, @RequestBody List<Long> cartIds) {
//        List<CartPromotionItem> cartPromotionItemList = cartItemService.listPromotion(memberId, cartIds);
//
//        log.info("查询结果：{}", JSON.toJSON(cartPromotionItemList));
//        return CommonResult.success(cartPromotionItemList);
//    }

    @ApiOperation("获取某个会员的购物车列表,包括促销信息")
    @RequestMapping(value = "/list/promotionNew/{memberId}", method = RequestMethod.POST)
    @ResponseBody
    public List<CartPromotionItem> listPromotionnew(@PathVariable("memberId") Long memberId, @RequestBody List<Long> cartIds) {
        List<CartPromotionItem> cartPromotionItemList = cartItemService.listPromotion(memberId, cartIds);
        log.info("查询结果：{}", JSON.toJSON(cartPromotionItemList));
        return cartPromotionItemList;
    }


    @ApiOperation("修改购物车中某个商品的数量")
    @RequestMapping(value = "/update/quantity", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult updateQuantity(@RequestParam Long id,
                                       @RequestParam Integer quantity) {
        int count = cartItemService.updateQuantity(id, memberService.getCurrentMember().getId(), quantity);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取购物车中某个商品的规格,用于重选规格")
    @RequestMapping(value = "/getProduct/{productId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CartProduct> getCartProduct(@PathVariable Long productId) {
        CartProduct cartProduct = cartItemService.getCartProduct(productId);
        return CommonResult.success(cartProduct);
    }

    @ApiOperation("修改购物车中商品的规格")
    @RequestMapping(value = "/update/attr", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateAttr(@RequestBody OmsCartItem cartItem) {
        int count = cartItemService.updateAttr(cartItem);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("删除购物车中的某个商品")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = cartItemService.delete(memberService.getCurrentMember().getId(), ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("清空购物车")
    @RequestMapping(value = "/clear", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult clear() {
        int count = cartItemService.clear(memberService.getCurrentMember().getId());
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


}

