package com.dunshan.mall.cart.config;

import com.dunshan.mall.common.config.BaseSwaggerConfig;
import com.dunshan.mall.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2API文档的配置
 * Created by dunshan on 2020/11/16.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.dunshan.mall.cart.controller")
                .title("mall-购物车系统")
                .description("mall购物车相关接口文档")
                .contactName("dunshan")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
