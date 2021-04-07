package com.dunshan.mall.search.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 * Created by dunshan on 2019/4/8.
 */
@Configuration
@MapperScan({"com.dunshan.mall.mapper","com.dunshan.mall.search.dao"})
public class MyBatisConfig {
}
