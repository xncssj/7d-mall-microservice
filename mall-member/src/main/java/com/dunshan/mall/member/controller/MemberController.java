package com.dunshan.mall.member.controller;

import com.alibaba.fastjson.JSON;
import com.dunshan.mall.common.api.CommonResult;
import com.dunshan.mall.common.domain.UserDto;
import com.dunshan.mall.member.domain.CartPromotionItem;
import com.dunshan.mall.member.domain.SmsCouponHistoryDetail;
import com.dunshan.mall.member.service.MemberCacheService;
import com.dunshan.mall.member.service.MemberCouponService;
import com.dunshan.mall.member.service.MemberReceiveAddressService;
import com.dunshan.mall.member.service.MemberService;
import com.dunshan.mall.model.UmsMember;
import com.dunshan.mall.model.UmsMemberReceiveAddress;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

/**
 * @description: 会员管理
 * @author: 李文
 * @create: 2020-11-15 14:13
 **/
@Log4j2
@RefreshScope
@Controller
@Api(tags = "MemberController", description = "会员登录注册管理")
@RequestMapping("/sso")
public class MemberController {


    @Autowired
    private MemberService memberService;

    @Autowired
    MemberReceiveAddressService receiveAddressService;

    @Autowired
    private MemberCacheService memberCacheService;


    @Value("${rabbitmq.queue.name.cart}")
    private String nacosValue;

    @ApiOperation("加载全量用户缓存")
    @GetMapping("/loadUserCache")
    @ResponseBody
    public CommonResult loadUserCache() {
        Long count = memberService.addCache();

        HashMap<String, Object> map = new HashMap<>();
        map.put("ok", nacosValue);
        map.put("count", count);

        return CommonResult.success(map);
    }

    @ApiOperation("清除全量用户缓存")
    @GetMapping("/removeUserCache")
    @ResponseBody
    public CommonResult removeUserCache() {
        int count = memberCacheService.clearAllMember();

        HashMap<String, Object> map = new HashMap<>();
        map.put("ok", nacosValue);
        map.put("count", count);

        return CommonResult.success(map);
    }

    @ApiOperation("调试动态刷新")
    @GetMapping("/nacos")
    @ResponseBody
    public CommonResult nacosResult() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("ok", nacosValue);
        map.put("nacos", "验证数据");
        return CommonResult.success(map);
    }


    @ApiOperation("会员注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult register(@RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String telephone,
                                 @RequestParam String authCode) {
        memberService.register(username, password, telephone, authCode);
        return CommonResult.success(null, "注册成功");
    }

    @ApiOperation("会员登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestParam String username, @RequestParam String password) {
        return memberService.login(username, password);
    }

    @ApiOperation("获取会员信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult info() {
        UmsMember member = memberService.getCurrentMember();
        return CommonResult.success(member);
    }

    /**
     * 远程调用
     * 获取会员信息
     *
     * @return
     */
    @ApiOperation("远程调用获取会员信息")
    @RequestMapping(value = "/feign/info", method = RequestMethod.GET)
    @ResponseBody
    public UmsMember getCurrentMember() {
//        log.info("查询的结果是：{}", JSON.toJSON(currentMember));
        UmsMember currentMembe = memberService.getCurrentMember();
        List<UmsMemberReceiveAddress> list = memberService.list();
        currentMembe.setList(list);

        return currentMembe;
    }


    @ApiOperation("获取验证码")
    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAuthCode(@RequestParam String telephone) {
        String authCode = memberService.generateAuthCode(telephone);
        return CommonResult.success(authCode, "获取验证码成功");
    }

    @ApiOperation("修改密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePassword(@RequestParam String telephone,
                                       @RequestParam String password,
                                       @RequestParam String authCode) {
        memberService.updatePassword(telephone, password, authCode);
        return CommonResult.success(null, "密码修改成功");
    }

    @ApiOperation("根据用户名获取通用用户信息")
    @RequestMapping(value = "/loadByUsername", method = RequestMethod.GET)
    @ResponseBody
    public UserDto loadUserByUsername(@RequestParam String username) {
        return memberService.loadUserByUsername(username);
    }

    /**
     * 根据会员编号获取会员
     */
    @ApiOperation("根据会员编号获取会员")
    @RequestMapping(value = "/getId/{id}", method = RequestMethod.GET)
    @ResponseBody
    UmsMember getById(@PathVariable(value = "id") Long id) {
        UmsMember byId = memberService.getById(id);
        log.info("查询用户信息{}", byId);
        return memberService.getById(id);
    }

    /**
     * 返回当前用户的收货地址
     *
     * @return
     */
    @ApiOperation("返回当前用户的收货地址")
    @RequestMapping(value = "/feign/info/address", method = RequestMethod.GET)
    @ResponseBody
    List<UmsMemberReceiveAddress> listAddress() {
        List<UmsMemberReceiveAddress> list = memberService.list();
        log.info("用户地址：{}", JSON.toJSON(list));
        return list;
    }

    @ApiOperation("返回当前用户的收货地址")
    @RequestMapping(value = "/feign/info/address1", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult listAddress1() {
//        List<UmsMemberReceiveAddress> list = memberService.list();
//        log.info("用户地址：{}", JSON.toJSON(list));
        return CommonResult.success(memberService.list());
    }


    /**
     * 获取地址详情
     *
     * @param id 地址id
     */
    @ApiOperation("获取地址详情")
    @GetMapping(value = "/address")
    @ResponseBody
    UmsMemberReceiveAddress getItem(@RequestParam(value = "id") String id) {
        return receiveAddressService.getItem(Long.valueOf(id));
    }

    /**
     * 根据购物车信息获取可用优惠券
     *
     * @param id
     * @param integration
     */
    @ApiOperation("根据购物车信息获取可用优惠券")
    @GetMapping(value = "/update/{id}/{integration}")
    @ResponseBody
    public CommonResult updateIntegration(@PathVariable(value = "id") Long id, @PathVariable(value = "integration") Integer integration) {
        memberService.updateIntegration(id, integration);
        return CommonResult.success("操作成功");
    }


}

