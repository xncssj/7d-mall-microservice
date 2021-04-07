package com.dunshan.mall.member.controller;

import com.dunshan.mall.common.api.CommonResult;
import com.dunshan.mall.member.domain.CartPromotionItem;
import com.dunshan.mall.member.domain.OmsCartItemVo;
import com.dunshan.mall.member.domain.SmsCouponHistoryDetail;
import com.dunshan.mall.member.feign.CartItemServiceCart;
import com.dunshan.mall.member.service.MemberCouponService;
import com.dunshan.mall.member.service.MemberService;
import com.dunshan.mall.model.SmsCoupon;
import com.dunshan.mall.model.SmsCouponHistory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 用户优惠卷
 * @author: 李文
 * @create: 2020-11-15 17:35
 **/
@Controller
@Api(tags = "UmsMemberCouponController", description = "用户优惠券管理")
@RequestMapping("/member/coupon")
public class MemberCouponController {
    @Autowired
    private MemberCouponService memberCouponService;
    @Resource
    private CartItemServiceCart cartItemService;
    @Autowired
    private MemberService memberService;

    @ApiOperation("领取指定优惠券")
    @RequestMapping(value = "/add/{couponId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult add(@PathVariable Long couponId) {
        memberCouponService.add(couponId);
        return CommonResult.success(null, "领取成功");
    }

    @ApiOperation("获取用户优惠券历史列表")
    @ApiImplicitParam(name = "useStatus", value = "优惠券筛选类型:0->未使用；1->已使用；2->已过期",
            allowableValues = "0,1,2", paramType = "query", dataType = "integer")
    @RequestMapping(value = "/listHistory", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<SmsCouponHistory>> listHistory(@RequestParam(value = "useStatus", required = false) Integer useStatus) {
        List<SmsCouponHistory> couponHistoryList = memberCouponService.listHistory(useStatus);
        return CommonResult.success(couponHistoryList);
    }

    @ApiOperation("获取用户优惠券列表")
    @ApiImplicitParam(name = "useStatus", value = "优惠券筛选类型:0->未使用；1->已使用；2->已过期",
            allowableValues = "0,1,2", paramType = "query", dataType = "integer")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<SmsCoupon>> list(@RequestParam(value = "useStatus", required = false) Integer useStatus) {
        List<SmsCoupon> couponList = memberCouponService.list(useStatus);
        return CommonResult.success(couponList);
    }

    @ApiOperation("获取登录会员购物车的相关优惠券")
    @ApiImplicitParam(name = "type", value = "使用可用:0->不可用；1->可用",
            defaultValue = "1", allowableValues = "0,1", paramType = "query", dataType = "integer")
    @RequestMapping(value = "/list/cart/{type}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<SmsCouponHistoryDetail>> listCart(@PathVariable Integer type) {
        List<CartPromotionItem> cartPromotionItemList = cartItemService.listPromotionnew(memberService.getCurrentMember().getId(), null);
        List<SmsCouponHistoryDetail> couponHistoryList = memberCouponService.listCart(cartPromotionItemList, type);
        return CommonResult.success(couponHistoryList);
    }

    @ApiOperation("获取当前商品相关优惠券")
    @RequestMapping(value = "/listByProduct/{productId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<SmsCoupon>> listByProduct(@PathVariable Long productId) {
        List<SmsCoupon> couponHistoryList = memberCouponService.listByProduct(productId);
        return CommonResult.success(couponHistoryList);
    }

    /**
     * 根据购物车信息获取可用优惠券
     *
     * @param cartItemList
     * @return
     */
    @ApiOperation("根据购物车信息获取可用优惠券")
    @PostMapping(value = "/cartList")
    @ResponseBody
    List<SmsCouponHistoryDetail> listCart(@RequestBody OmsCartItemVo cartItemList) {
        final Integer type = 1;
        return memberCouponService.listCart(cartItemList.getCartItemList(), type);
    }

}


