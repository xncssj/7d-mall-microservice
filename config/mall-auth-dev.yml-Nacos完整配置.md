# mall-auth-dev.yml Nacos 配置中心完整配置

## 配置信息
- **Data ID**: `mall-auth-dev.yml`
- **Group**: `DEFAULT_GROUP`
- **配置格式**: `YAML`

## 完整配置内容

```yaml
spring:
  datasource:
    url: jdbc:mysql://10.211.55.15:3306/mall?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&queryInterceptors=brave.mysql8.TracingQueryInterceptor&exceptionInterceptors=brave.mysql8.TracingExceptionInterceptor
    username: reader
    password: 123456
    driver-class-name: com.mysql.cj.jdbc.Driver
    druid:
      initial-size: 10 #连接池初始化大小
      min-idle: 10 #最小空闲连接数
      max-active: 20 #最大连接数
      web-stat-filter:
        exclusions: "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*" #不统计这些请求数据
      stat-view-servlet: #访问监控网页的登录用户名和密码
        login-username: druid
        login-password: druid

  redis:
    host: 10.211.55.15 # Redis服务器地址
    database: 0 # Redis数据库索引（默认为0）
    port: 6379 # Redis服务器连接端口
    password: 123456 # Redis服务器连接密码
    timeout: 3000ms # 连接超时时间（毫秒）
  rabbitmq:
    host: 10.211.55.15
    port: 5672 # RabbitMQ AMQP 端口（与 docker-compose.yml 一致）
    virtual-host: /mall
    username: admin # RabbitMQ 默认用户（与 docker-compose.yml 一致）
    password: 123456 # RabbitMQ 默认密码（与 docker-compose.yml 一致）
  zipkin:
    base-url: http://10.211.55.15:9411 # Zipkin 服务器地址
    enabled: true
    discovery-client-enabled: false
    sender:
      type: web
    service:
      name: ${spring.application.name}
    compression:
      enabled: true
  sleuth:
    propagation:
      tag:
        whitelisted-keys:
          - dunshan
        enabled: true
      keys:
        - dunshan
    sampler: #采样器
      probability: 1.0 #采样率，采样率是采集Trace的比率，默认0.1
      rate: 10000 #每秒数据采集量，最多n条/秒Trace

mybatis:
  mapper-locations:
    - classpath:dao/*.xml
    - classpath*:com/**/mapper/*.xml
management: #开启SpringBoot Admin的监控
  endpoints:
    web:
      exposure:
        include: '*'
  endpoint:
    health:
      show-details: always
  health:
    rabbit:
      enabled: false # 禁用 RabbitMQ 健康检查（如果 RabbitMQ 未配置或不可用）

# 自定义redis key
redis:
  database: mall
  key:
    authCode: 'ums:authCode'
    orderId: 'oms:orderId'
    member: 'ums:member'
  expire:
    authCode: 90 # 验证码超期时间
    common: 86400 # 24小时

# 消息队列定义
rabbitmq:
  queue:
    name:
      cancelOrder: cancelOrderQueue
      cart: OneTwoThree
feign:
  okhttp:
    enabled: true
ribbon:
  ConnectTimeout: 3000 #服务请求连接超时时间（毫秒）
  ReadTimeout: 3000 #服务请求处理超时时间（毫秒）

# 认证配置
auth:
  matchSwitch: 1 # 密码匹配开关，1=严格匹配MD5，0=允许所有密码
  cacheSwitch: 1 # 缓存开关，1=启用缓存，0=禁用缓存（直接从数据库获取）
```

## 当前 Nacos 配置的问题

从 Nacos 中获取的配置存在以下问题：
1. ❌ RabbitMQ 端口错误：`30993` → 应该是 `5672`
2. ❌ RabbitMQ 用户名密码错误：`mall/mall` → 应该是 `admin/123456`
3. ❌ 缺少 `sleuth` 配置
4. ❌ 缺少 `management` 配置
5. ❌ 缺少 `mybatis` 配置
6. ❌ 缺少 `auth` 配置
7. ❌ Zipkin 配置不完整

## 操作步骤

1. 访问 Nacos 控制台：`http://10.211.55.15:8848/nacos`
2. 进入"配置管理" → "配置列表"
3. 找到 `mall-auth-dev.yml` 配置
4. 点击"编辑"
5. 将上面的完整配置内容复制粘贴进去
6. 点击"发布"保存配置
7. 重启 `mall-auth` 服务使配置生效

## 注意事项

- 配置发布后会自动刷新（如果 bootstrap-dev.yml 中配置了 `refresh: true`）
- 或者需要重启服务才能生效
- 确保所有配置项都正确，特别是 RabbitMQ 和 Zipkin 的配置

