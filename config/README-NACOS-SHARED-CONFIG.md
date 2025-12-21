# Nacos 配置中心共享配置说明

## 方案一：使用 Nacos 配置中心（推荐）

### 1. 在 Nacos 配置中心创建共享配置

访问 Nacos 控制台：`http://10.211.55.15:8848/nacos`

**配置信息：**
- **Data ID**: `shared-dev.yml`
- **Group**: `DEFAULT_GROUP`
- **配置格式**: `YAML`
- **配置内容**: 见 `config/nacos-shared-dev.yml` 文件

### 2. 配置内容

```yaml
spring:
  cloud:
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

### 3. 各服务配置

在各服务的 `bootstrap-dev.yml` 中添加：

```yaml
spring:
  cloud:
    nacos:
      config:
        shared-configs:
          - data-id: shared-dev.yml
            group: DEFAULT_GROUP
            refresh: true
```

### 优点
- ✅ 统一管理，一处修改，所有服务生效
- ✅ 支持动态刷新，无需重启服务
- ✅ 配置与代码分离，便于运维管理

---

## 方案二：使用环境变量（最简单）

### 配置方式

在各服务的 `bootstrap-dev.yml` 中只保留：

```yaml
spring:
  cloud:
    nacos:
      discovery:
        ip: ${NACOS_IP:}  # 环境变量为空时，使用 Spring Cloud 默认行为
```

### 启动时设置环境变量

```bash
# 方式1：export 环境变量
export NACOS_IP=10.211.55.15
java -jar mall-auth.jar

# 方式2：启动时指定
NACOS_IP=10.211.55.15 java -jar mall-auth.jar

# 方式3：在启动脚本中
#!/bin/bash
NACOS_IP=10.211.55.15
java -jar mall-auth.jar
```

### 优点
- ✅ 配置最简单，只需一行
- ✅ 灵活，不同环境可以设置不同的 IP
- ✅ 不需要修改代码

### 缺点
- ⚠️ 需要确保所有服务启动时都设置了环境变量
- ⚠️ 如果环境变量未设置，可能获取到错误的 IP

---

## 方案三：在 mall-common 模块中创建公共配置

### 1. 创建公共配置文件

在 `mall-common/src/main/resources/` 下创建 `application-cloud.yml`：

```yaml
spring:
  cloud:
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

### 2. 各服务自动继承

所有依赖 `mall-common` 的服务会自动加载这个配置。

### 优点
- ✅ 配置在代码中，版本可控
- ✅ 所有服务自动继承

### 缺点
- ⚠️ 修改配置需要重新编译和部署
- ⚠️ 不够灵活，不同环境需要不同配置

---

## 推荐方案

**推荐使用方案一（Nacos 配置中心）**，因为：
1. 项目已经使用了 Nacos 配置中心
2. 支持动态刷新，无需重启服务
3. 配置统一管理，便于维护
4. 不同环境可以创建不同的共享配置（shared-dev.yml, shared-test.yml, shared-prod.yml）

