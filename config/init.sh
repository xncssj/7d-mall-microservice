#!/bin/bash
set -e

# ========== 第一步：初始化Redis单机配置 ==========
echo "===== 初始化Redis单机配置 ====="
# 创建配置目录
mkdir -p /data/middleware/redis/conf

# 创建 redis-1 配置文件
cat > /data/middleware/redis/conf/redis-1.conf << EOF
port 6379
bind 0.0.0.0
protected-mode no
appendonly yes
requirepass 123456
dir /data
EOF

# 创建 redis-2 配置文件
cat > /data/middleware/redis/conf/redis-2.conf << EOF
port 6379
bind 0.0.0.0
protected-mode no
appendonly yes
requirepass 123456
dir /data
EOF

# ========== 第二步：初始化Redis集群配置 ==========
echo "===== 初始化Redis集群配置 ====="
HOST_IP=$(hostname -I | awk '{print $1}')
for port in 7001 7002 7003 7004 7005 7006; do
  mkdir -p /data/middleware/redis-cluster/$port/conf
  cat > /data/middleware/redis-cluster/$port/conf/redis.conf << EOF
port $port
bind 0.0.0.0
protected-mode no
cluster-enabled yes
cluster-config-file nodes.conf
cluster-node-timeout 5000
appendonly yes
requirepass 123456
masterauth 123456
dir /data
EOF
done

# ========== 第三步：初始化Nacos数据库 ==========
echo "===== 初始化Nacos数据库 ====="
# 创建nacos_config数据库
docker exec -it mysql mysql -uroot -p123456 -e "CREATE DATABASE IF NOT EXISTS nacos_config CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
# 导入Nacos初始化SQL
docker exec -i mysql mysql -uroot -p123456 nacos_config < <(curl -s https://raw.githubusercontent.com/alibaba/nacos/2.3.2/distribution/conf/nacos-mysql.sql)

# ========== 第四步：构建Redis集群 ==========
echo "===== 构建Redis集群 ====="
sleep 10  # 等待Redis节点启动完成
docker exec -it redis-7001 redis-cli -a 123456 --cluster create \
$HOST_IP:7001 $HOST_IP:7002 $HOST_IP:7003 \
$HOST_IP:7004 $HOST_IP:7005 $HOST_IP:7006 \
--cluster-replicas 1 --cluster-yes

echo "===== 所有初始化操作完成 ====="