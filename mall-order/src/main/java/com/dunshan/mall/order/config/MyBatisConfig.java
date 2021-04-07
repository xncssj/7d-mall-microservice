package com.dunshan.mall.order.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis配置类
 * Created by dunshan on 2019/4/8.
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.dunshan.mall.mapper","com.dunshan.mall.order.dao"})
public class MyBatisConfig {
}
