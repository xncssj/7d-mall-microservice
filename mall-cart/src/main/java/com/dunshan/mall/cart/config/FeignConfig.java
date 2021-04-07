package com.dunshan.mall.cart.config;

import com.dunshan.mall.cart.component.FeignRequestInterceptor;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiWen
 * @version 1.0
 * @Date: 2020-11-17 11:23
 * @Description: feign远程调用
 */
@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    RequestInterceptor requestInterceptor() {
        return new FeignRequestInterceptor();
    }
}