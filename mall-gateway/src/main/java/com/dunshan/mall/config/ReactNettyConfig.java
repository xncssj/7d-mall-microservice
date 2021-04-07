package com.dunshan.mall.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorResourceFactory;

/**
 * @author xuxinwei
 * @create 2020-12-27
 */
@Configuration
public class ReactNettyConfig {

    @Value("${reactor.netty.ioWorkerCount}")
    private String ioWorkerCount;

    @Bean
    public ReactorResourceFactory reactorClientResourceFactory() {

        System.setProperty("reactor.netty.ioWorkerCount", ioWorkerCount);
        return new ReactorResourceFactory();
    }

}
