package com.dunshan.mall.search.config;

import com.dunshan.mall.common.config.BaseSwaggerConfig;
import com.dunshan.mall.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2API文档的配置
 * Created by dunshan on 2018/4/26.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.dunshan.mall.search.controller")
                .title("mall搜索系统")
                .description("mall搜索相关接口文档")
                .contactName("7d")
                .version("1.0")
                .enableSecurity(false)
                .build();
    }
}
