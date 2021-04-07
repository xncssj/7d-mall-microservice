package com.dunshan.mall.demo.service;

import com.dunshan.mall.common.api.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by dunshan on 2019/10/18.
 */
@FeignClient("mall-member")
public interface FeignPortalService {

    @PostMapping("/sso/login")
    CommonResult login(@RequestParam String username, @RequestParam String password);

    @GetMapping("/cart/list")
    CommonResult list();
}
