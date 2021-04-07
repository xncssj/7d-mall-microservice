package com.dunshan.mall.member.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @description: MyBatis配置文件
 * @author: 李文
 * @create: 2020-11-15 18:11
 **/
@Configuration
@EnableTransactionManagement
@MapperScan({"com.dunshan.mall.mapper","com.dunshan.mall.member.dao"})
public class MyBatisConfig {
}

