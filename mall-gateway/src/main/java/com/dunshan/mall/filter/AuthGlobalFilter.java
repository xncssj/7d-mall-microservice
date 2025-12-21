package com.dunshan.mall.filter;

import cn.hutool.core.util.StrUtil;
import com.dunshan.mall.common.constant.AuthConstant;
import com.nimbusds.jose.JWSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.ParseException;

/**
 * 将登录用户的JWT转化成用户信息的全局过滤器
 * Created by dunshan on 2020/6/17.
 */
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private static Logger LOGGER = LoggerFactory.getLogger(AuthGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst(AuthConstant.JWT_TOKEN_HEADER);
        // 获取原始请求中的dunshan头部，如果没有则使用默认值
        String dunshanValue = exchange.getRequest().getHeaders().getFirst("dunshan");
        if (dunshanValue == null || dunshanValue.isEmpty()) {
            // 默认值
            dunshanValue = "7dgroup";

        }

        ServerHttpRequest.Builder builder = exchange.getRequest().mutate()
                // 添加dunshan标识头
                .header("dunshan", dunshanValue);

        if (!StrUtil.isEmpty(token)) {
            try {
                //从token中解析用户信息并设置到Header中去
                String realToken = token.replace(AuthConstant.JWT_TOKEN_PREFIX, "");
                JWSObject jwsObject = JWSObject.parse(realToken);
                String userStr = jwsObject.getPayload().toString();
                LOGGER.info("AuthGlobalFilter.filter() user:{}", userStr);
                builder.header(AuthConstant.USER_TOKEN_HEADER, userStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        ServerHttpRequest request = builder.build();
        exchange = exchange.mutate().request(request).build();
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}