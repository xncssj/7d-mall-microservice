package com.dunshan.mall.member.config;

import com.dunshan.mall.common.config.BaseSwaggerConfig;
import com.dunshan.mall.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description: 会员管理系统Swagger2 Api管理文档
 * @author: 李文
 * @create: 2020-11-15 17:20
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.dunshan.mall.member.controller")
                .title("mall-会员管理前台系统")
                .description("mall会员相关接口文档")
                .contactName("dunshan")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
