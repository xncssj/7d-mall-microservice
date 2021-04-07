package com.dunshan.mall.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 购物车
 * @author: 李文
 * @create: 2020-11-15 22:30
 **/
@EnableFeignClients
@EnableDiscoveryClient
@RestController
@SpringBootApplication(scanBasePackages = "com.dunshan.mall.cart")
public class MallCartApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallCartApplication.class, args);
    }
}

