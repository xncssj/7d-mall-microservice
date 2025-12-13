#!/bin/bash
# 设置 JDK 1.8 环境变量并启动项目
# 使用方法: ./start-with-jdk8.sh [module-name]
# 例如: ./start-with-jdk8.sh mall-auth

export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home
export PATH=$JAVA_HOME/bin:$PATH

echo "========================================="
echo "JDK Version:"
java -version
echo "========================================="
echo "Maven Version:"
mvn -version | head -3
echo "========================================="

if [ -z "$1" ]; then
    echo "Usage: ./start-with-jdk8.sh [module-name]"
    echo "Example: ./start-with-jdk8.sh mall-auth"
    exit 1
fi

MODULE=$1
echo "Starting module: $MODULE"
cd $MODULE
mvn spring-boot:run

