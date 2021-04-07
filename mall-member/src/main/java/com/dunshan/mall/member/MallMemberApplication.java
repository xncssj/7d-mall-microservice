package com.dunshan.mall.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description: 会员服务系统
 * @author: 李文
 * @create: 2020-11-15 22:31
 * ,exclude = { MongoRepositoriesAutoConfiguration.class }
 **/

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.dunshan.mall.member")
public class MallMemberApplication {

    public static void main(String[] args) {

        SpringApplication.run(MallMemberApplication.class, args);
    }
}

