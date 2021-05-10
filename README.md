# 7d-mall

## 项目介绍

`7d-mall-microservice` 是一套微服务电商，其是在开源电商项目 [mall-swarm](https://github.com/macrozheng/mall-swarm) 上进行了改造，采用了 Spring Cloud Hoxton & Alibaba、Spring Boot 2.3、Oauth2、MyBatis、Docker、k8s、Elasticsearch 等核心技术。
`7d-mall-microservice`在电商业务的基础集成了注册中心、配置中心、监控中心、网关等系统功能。。 

## 组织结构

```lua
7d-mall
├── mall-common  -- 工具类及通用代码模块
├── mall-mbg     -- MyBatisGenerator生成的数据库操作代码模块
├── mall-auth    -- 基于Spring Security Oauth2的统一的认证中心
├── mall-gateway -- 基于Spring Cloud Gateway的微服务API网关服务
├── mall-monitor -- 基于Spring Boot Admin的微服务监控中心
├── mall-member  -- 会员系统服务
├── mall-order   -- 订单系统服务
├── mall-cart    -- 购物车系统服务
├── mall-admin   -- 后台管理系统服务
├── mall-search  -- 基于Elasticsearch的商品搜索系统服务
├── mall-portal  -- 商城后台系统服务
├── mall-demo    -- 微服务远程调用测试服务
└── config       -- 配置中心存储的配置
```

## 系统技术栈



| 技术                   | 说明                 | 官网                                                 |
| ---------------------- | -------------------- |:---------------------------------------------------- |
| Spring Cloud           | 微服务框架           | https://spring.io/projects/spring-cloud              |
| Spring Cloud Alibaba   | 微服务框架           | https://github.com/alibaba/spring-cloud-alibaba      |
| Spring Boot            | 容器+MVC框架         | https://spring.io/projects/spring-boot               |
| Spring Security Oauth2 | 认证和授权框架       | https://spring.io/projects/spring-security-oauth     |
| MyBatis                | ORM框架              | http://www.mybatis.org/mybatis-3/zh/index.html       |
| MyBatisGenerator       | 数据层代码生成       | http://www.mybatis.org/generator/index.html          |
| PageHelper             | MyBatis物理分页插件  | http://git.oschina.net/free/Mybatis_PageHelper       |
| Knife4j                | 文档生产工具         | https://github.com/xiaoymin/swagger-bootstrap-ui     |
| Elasticsearch          | 搜索引擎             | https://github.com/elastic/elasticsearch             |
| RabbitMq               | 消息队列             | https://www.rabbitmq.com/                            |
| Redis                  | 分布式缓存           | https://redis.io/                                    |
| MongoDb                | NoSql数据库          | https://www.mongodb.com/                             |
| Docker                 | 应用容器引擎         | https://www.docker.com/                              |
| Druid                  | 数据库连接池         | https://github.com/alibaba/druid                     |
| OSS                    | 对象存储             | https://github.com/aliyun/aliyun-oss-java-sdk        |
| MinIO                  | 对象存储             | https://github.com/minio/minio                       |
| JWT                    | JWT登录支持          | https://github.com/jwtk/jjwt                         |
| LogStash               | 日志收集             | https://github.com/logstash/logstash-logback-encoder |
| Lombok                 | 简化对象封装工具     | https://github.com/rzwitserloot/lombok               |
| Seata                  | 全局事务管理框架     | https://github.com/seata/seata                       |
| Kubernetes             | 应用容器管理平台     | https://kubernetes.io/                               |
|Sentinel|服务保护|https://sentinelguard.io/zh-cn/|
|SkyWalking|分布式追踪、服务网格遥测分析、度量聚合和可视化一体化解决方案|http://skywalking.apache.org|


## 系统架构
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210509110259347.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3p1b3pld2Vp,size_16,color_FFFFFF,t_70#pic_center)

## 部署架构

![部署架构图](https://img-blog.csdnimg.cn/20210330185951870.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3p1b3pld2Vp,size_16,color_FFFFFF,t_70)


## 公众号

电商项目全套学习教程连载中，关注公众号「**7DGroup**」第一时间获取。

加微信群交流，公众号后台回复「**性能工程**」即可。

![公众号图片](https://img-blog.csdnimg.cn/20190304100509555.jpg)

## 许可证

[Apache License 2.0](LICENSE)

Copyright (c) 2020-2022 7DGroup
