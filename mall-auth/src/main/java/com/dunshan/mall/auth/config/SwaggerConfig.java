package com.dunshan.mall.auth.config;

import com.dunshan.mall.common.config.BaseSwaggerConfig;
import com.dunshan.mall.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger API文档相关配置
 * Created by dunshan on 2018/4/26.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.dunshan.mall.auth.controller")
                .title("mall认证中心")
                .description("mall认证中心相关接口文档")
                .contactName("dunshan")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
