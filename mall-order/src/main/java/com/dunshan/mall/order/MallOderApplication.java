package com.dunshan.mall.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author LiWen
 * @version 1.0
 * @Date: 2020-11-16 17:00
 * @Description: 订单服务系统
 * ,exclude = { MongoRepositoriesAutoConfiguration.class }
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.dunshan.mall.order")
public class MallOderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallOderApplication.class, args);
    }
}
