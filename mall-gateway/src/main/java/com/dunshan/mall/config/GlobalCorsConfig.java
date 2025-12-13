package com.dunshan.mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.Arrays;

/**
 * 全局跨域配置
 * 注意：前端从网关进行调用时需要配置
 * Created by dunshan on 2019/7/27.
 */
@Configuration
public class GlobalCorsConfig {

    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedMethod("*");
        config.setAllowedOrigins(Arrays.asList( // ✅ 明确指定允许的源
            "http://localhost:8201",
            "http://127.0.0.1:8201",
            "http://localhost:8080",
            "http://127.0.0.1:8080",
            "http://192.168.31.79:8201", // 您的内网地址
            "http://10.211.55.15:8201"
        ));
        config.addAllowedHeader("*");
        config.setAllowCredentials(true);
        config.setMaxAge(3600L); // 预检请求的缓存时间
        config.setExposedHeaders(Arrays.asList("Authorization", "X-Total-Count", "Link")); // 暴露自定义响应头
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }

}
