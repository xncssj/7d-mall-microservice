package com.dunshan.mall.order.feign;

import com.dunshan.mall.common.api.CommonResult;
import com.dunshan.mall.model.UmsMember;
import com.dunshan.mall.model.UmsMemberReceiveAddress;
import com.dunshan.mall.order.domain.CartPromotionItem;
import com.dunshan.mall.order.domain.OmsCartItemVo;
import com.dunshan.mall.order.domain.SmsCouponHistoryDetail;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @author LiWen
 * @version 1.0
 * @Date: 2020-11-16 17:14
 * @Description: 远程调用用户
 */
@FeignClient(value = "mall-member")
public interface MemberService {

    /**
     * 获取当前登录会员
     */
    @GetMapping(value = "/sso/feign/info")
    UmsMember getCurrentMember();

    /**
     * 根据会员编号获取会员
     */
    @GetMapping(value = "/sso/getId/{id}")
    UmsMember getById(@PathVariable(value = "id") Long id);

    /**
     * 返回当前用户的收货地址
     */
    @RequestMapping(value = "/sso/feign/info/address", method = RequestMethod.GET)
    List<UmsMemberReceiveAddress> listAddress();

    @GetMapping(value = "/feign/info/address1")
    CommonResult listAddress1();

    /**
     * 获取地址详情
     *
     * @param id 地址id
     */
    @GetMapping(value = "/sso/address")
    UmsMemberReceiveAddress getItem(@RequestParam(value = "id") String id);

    /**
     * 根据会员id修改会员积分
     */
    @GetMapping(value = "/sso/update/{id}/{integration}")
    void updateIntegration(@PathVariable(value = "id") Long id, @PathVariable(value = "integration") Integer integration);

    /**
     * 根据购物车信息获取可用优惠券
     */
    @PostMapping(value = "/member/coupon/cartList")
    List<SmsCouponHistoryDetail> listCart(@RequestBody OmsCartItemVo omsCartItemVo);

}
