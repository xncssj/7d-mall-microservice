package com.dunshan.mall.auth.feign;

import com.dunshan.mall.common.domain.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by dunshan on 2019/10/18.
 */
@FeignClient("mall-admin")
public interface AdminFeign {

    @GetMapping("/admin/loadByUsername")
    UserDto loadUserByUsername(@RequestParam String username);
}
