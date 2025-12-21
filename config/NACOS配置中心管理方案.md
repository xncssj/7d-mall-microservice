# Nacos 配置中心统一管理方案

## 当前情况
- 各服务配置都在 Nacos 配置中心
- 配置格式：`{服务名}-dev.yml`（如 `mall-monitor-dev.yml`）
- 配置地址：`http://10.211.55.15:8848/nacos`

---

## 方案一：创建共享配置（推荐）⭐

### 步骤 1：在 Nacos 配置中心创建共享配置

访问：`http://10.211.55.15:8848/nacos/index.html#/configeditor`

**创建新配置：**
- **Data ID**: `shared-dev.yml`
- **Group**: `DEFAULT_GROUP`
- **配置格式**: `YAML`
- **配置内容**:

```yaml
spring:
  cloud:
    # 配置网络接口选择规则，自动排除 Docker 网络接口
    inetutils:
      preferred-networks:
        - 10.211  # 优先选择 10.211.x.x 网段
        - 192.168  # 其次选择 192.168.x.x 网段
        - 172.16   # 再次选择 172.16.x.x 网段（排除 172.17, 172.18 等 Docker 网段）
      ignored-interfaces:
        - docker0    # 忽略 Docker 默认网桥
        - veth.*     # 忽略 Docker 虚拟网卡
      use-only-site-local-interfaces: false  # 允许使用非本地回环接口
```

### 步骤 2：在各服务的 bootstrap-dev.yml 中添加共享配置引用

确保各服务的 `bootstrap-dev.yml` 中包含：

```yaml
spring:
  cloud:
    nacos:
      config:
        shared-configs:
          - data-id: shared-dev.yml
            group: DEFAULT_GROUP
            refresh: true  # 支持动态刷新
```

### 步骤 3：验证

重启服务后，所有服务都会自动加载共享配置中的 `inetutils` 设置。

**优点：**
- ✅ 一处修改，所有服务生效
- ✅ 支持动态刷新，无需重启
- ✅ 配置统一管理

---

## 方案二：在各服务的 Nacos 配置中添加（简单直接）

### 操作步骤

对于每个服务的 Nacos 配置（如 `mall-monitor-dev.yml`、`mall-auth-dev.yml` 等），在配置文件中添加：

```yaml
spring:
  cloud:
    # 配置网络接口选择规则，自动排除 Docker 网络接口
    inetutils:
      preferred-networks:
        - 10.211
        - 192.168
        - 172.16
      ignored-interfaces:
        - docker0
        - veth.*
      use-only-site-local-interfaces: false
```

### 需要修改的配置文件

在 Nacos 配置中心修改以下配置：
- `mall-monitor-dev.yml`
- `mall-auth-dev.yml`
- `mall-demo-dev.yml`
- `mall-member-dev.yml`
- `mall-gateway-dev.yml`
- `mall-admin-dev.yml`
- `mall-order-dev.yml`
- `mall-portal-dev.yml`
- `mall-cart-dev.yml`
- `mall-search-dev.yml`

**优点：**
- ✅ 简单直接，不需要创建共享配置
- ✅ 配置都在 Nacos 中，便于查看和管理

**缺点：**
- ⚠️ 需要在每个配置文件中都添加（但都在 Nacos 中，管理也方便）
- ⚠️ 修改时需要更新多个配置文件

---

## 方案三：使用环境变量（最简单）

### 操作步骤

在各服务的 Nacos 配置中，只需要添加：

```yaml
spring:
  cloud:
    nacos:
      discovery:
        ip: ${NACOS_IP:}  # 环境变量为空时，使用 Spring Cloud 默认行为
```

然后在启动服务时设置环境变量：

```bash
export NACOS_IP=10.211.55.15
java -jar mall-auth.jar
```

**优点：**
- ✅ 配置最简单
- ✅ 灵活，不同环境可以设置不同的 IP

**缺点：**
- ⚠️ 需要确保所有服务启动时都设置了环境变量

---

## 推荐方案

**推荐使用方案一（共享配置）**，因为：
1. 配置统一管理，一处修改，所有服务生效
2. 支持动态刷新，无需重启服务
3. 符合微服务配置管理最佳实践

---

## 快速操作指南

### 如果选择方案一（共享配置）：

1. **在 Nacos 创建共享配置**
   - 访问：`http://10.211.55.15:8848/nacos`
   - 创建配置：`shared-dev.yml`
   - 内容：使用上面的 YAML 配置

2. **验证 bootstrap-dev.yml**
   - 确保各服务的 `bootstrap-dev.yml` 包含 `shared-configs` 配置
   - 如果还没有，需要添加（参考上面的配置）

3. **重启服务验证**
   - 重启服务后，检查 Nacos 中的服务实例 IP 是否正确

### 如果选择方案二（各服务配置中添加）：

1. **批量修改 Nacos 配置**
   - 在 Nacos 配置中心打开每个服务的配置文件
   - 添加 `inetutils` 配置
   - 保存并发布

2. **验证**
   - 配置会自动刷新（如果设置了 `refresh: true`）
   - 或重启服务验证

