# 7d-mall


## 项目介绍

`7d-mall`是一套微服务商城系统，采用了 Spring Cloud Hoxton & Alibaba、Spring Boot 2.3、Oauth2、MyBatis、Docker、Elasticsearch等核心技术。`7d-mall`在电商业务的基础集成了注册中心、配置中心、监控中心、网关等系统功能。。 

## 系统架构图

![系统架构图](https://uploader.shimo.im/f/Mfo8gDbIqbaKPwsw.jpg!thumbnail)

## 组织结构

``` lua
mall
├── mall-common -- 工具类及通用代码模块
├── mall-mbg -- MyBatisGenerator生成的数据库操作代码模块
├── mall-auth -- 基于Spring Security Oauth2的统一的认证中心
├── mall-gateway -- 基于Spring Cloud Gateway的微服务API网关服务
├── mall-monitor -- 基于Spring Boot Admin的微服务监控中心
├── mall-member -- 会员系统服务
├── mall-order -- 订单系统服务
├── mall-cart -- 购物车系统服务
├── mall-admin -- 后台管理系统服务
├── mall-search -- 基于Elasticsearch的商品搜索系统服务
├── mall-portal -- 商城后台系统服务
├── mall-demo -- 微服务远程调用测试服务
└── config -- 配置中心存储的配置
```


## 系统技术栈


![系统技术栈](https://uploader.shimo.im/f/K0NoLNzFOEumQs9r.jpg!thumbnail)

## 环境搭建

![部署架构图](https://img-blog.csdnimg.cn/20210330185951870.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3p1b3pld2Vp,size_16,color_FFFFFF,t_70)

## 运行效果展示


## 公众号

电商项目全套学习教程连载中，关注公众号「**7DGroup**」第一时间获取。

加微信群交流，公众号后台回复「**性能工程**」即可。

![公众号图片](https://img-blog.csdnimg.cn/20190304100509555.jpg)

## 许可证

[Apache License 2.0](https://gitee.com/perfmance/mall-swarm-new/raw/master/LICENSE)

Copyright (c) 2020-2022 7DGroup