package com.dunshan.mall.demo.config;

import com.dunshan.mall.common.config.BaseSwaggerConfig;
import com.dunshan.mall.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger相关配置
 * Created by dunshan on 2019/4/8.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfigDome extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.dunshan.mall.demo.controller")
                .title("mall-demo系统")
                .description("SpringCloud版本中的一些示例")
                .contactName("dunshan")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }

}
