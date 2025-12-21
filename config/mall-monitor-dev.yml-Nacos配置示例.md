# mall-monitor-dev.yml Nacos 配置示例

## 在 Nacos 配置中心创建/更新配置

**配置位置：**
- Data ID: `mall-monitor-dev.yml`
- Group: `DEFAULT_GROUP`
- 配置格式: `YAML`

## 完整配置内容

```yaml
# Spring Boot Admin 监控配置
spring:
  boot:
    admin:
      discovery:
        enabled: true  # 启用服务发现（从 Nacos 自动发现服务）
        ignored-services: ${spring.application.name}  # 忽略监控服务自身
        # 配置服务实例转换器，使用 IP 地址而不是主机名
        converter:
          prefer-ip: true  # 优先使用 IP 地址访问服务实例

# 禁用 Redis 健康检查（监控模块不需要 Redis）
management:
  health:
    redis:
      enabled: false
```

## 配置说明

### Spring Boot Admin 服务发现配置
- `enabled: true` - 启用从 Nacos 自动发现服务
- `ignored-services` - 忽略监控服务自身，避免自己监控自己
- `prefer-ip: true` - 使用 IP 地址而不是主机名访问服务实例

### Management 健康检查配置
- `redis.enabled: false` - 禁用 Redis 健康检查（因为 mall-monitor 不需要 Redis）

## 操作步骤

1. 访问 Nacos 控制台：`http://10.211.55.15:8848/nacos`
2. 进入"配置管理" → "配置列表"
3. 找到或创建 `mall-monitor-dev.yml` 配置
4. 将上面的配置内容复制到配置中
5. 点击"发布"保存配置
6. 重启 `mall-monitor` 服务使配置生效

## 注意事项

- 如果配置已存在，会与现有配置合并
- 支持配置动态刷新（如果 bootstrap-dev.yml 中配置了 `refresh: true`）
- 配置优先级：Nacos 配置 > 本地 application-dev.yml > application.yml

