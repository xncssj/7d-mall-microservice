package com.dunshan.mall.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.dunshan.mall.portal")
public class MallPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallPortalApplication.class, args);
    }

}


//-javaagent:/Users/liwen/evn/skywalkinag/agent/skywalking-agent.jar
//-Dskywalking.agent.service_name=mall-admin
//-Dskywalking.collector.backend_service=localhost:11800

/**
 http://localhost:8181/topology


 -server -XX:PermSize=200M -XX:MaxPermSize=200M
 -javaagent:/Users/liwen406/evn/skywalking/agent/skywalking-agent.jar
 -Dskywalking.agent.service_name=mall-admin
 -Dskywalking.collector.backend_service=localhost:11800


 -server -XX:PermSize=200M -XX:MaxPermSize=200M
 -javaagent:/Users/liwen406/evn/skywalking/agent/skywalking-agent.jar
 -Dskywalking.agent.service_name=mall-auth
 -Dskywalking.collector.backend_service=localhost:11800

 -server -XX:PermSize=200M -XX:MaxPermSize=200M
 -javaagent:/Users/liwen406/evn/skywalking/agent/skywalking-agent.jar
 -Dskywalking.agent.service_name=mall-cart
 -Dskywalking.collector.backend_service=localhost:11800

 -server -XX:PermSize=200M -XX:MaxPermSize=200M
 -javaagent:/Users/liwen406/evn/skywalking/agent/skywalking-agent.jar
 -Dskywalking.agent.service_name=mall-demo
 -Dskywalking.collector.backend_service=localhost:11800

 -server -XX:PermSize=200M -XX:MaxPermSize=200M
 -javaagent:/Users/liwen406/evn/skywalking/agent/skywalking-agent.jar
 -Dskywalking.agent.service_name=mall-gateway
 -Dskywalking.collector.backend_service=localhost:11800

 -server -XX:PermSize=200M -XX:MaxPermSize=200M
 -javaagent:/Users/liwen406/evn/skywalking/agent/skywalking-agent.jar
 -Dskywalking.agent.service_name=mall-member
 -Dskywalking.collector.backend_service=localhost:11800

 -server -XX:PermSize=200M -XX:MaxPermSize=200M
 -javaagent:/Users/liwen406/evn/skywalking/agent/skywalking-agent.jar
 -Dskywalking.agent.service_name=mall-monitor
 -Dskywalking.collector.backend_service=localhost:11800

 -server -XX:PermSize=200M -XX:MaxPermSize=200M
 -javaagent:/Users/liwen406/evn/skywalking/agent/skywalking-agent.jar
 -Dskywalking.agent.service_name=mall-portal
 -Dskywalking.collector.backend_service=localhost:11800

 -server -XX:PermSize=200M -XX:MaxPermSize=200M
 -javaagent:/Users/liwen406/evn/skywalking/agent/skywalking-agent.jar
 -Dskywalking.agent.service_name=mall-search
 -Dskywalking.collector.backend_service=localhost:11800

 mall-gateway：8201
 mall-admin: 8080
 mall-auth : 8401
 mall-demo : 8082
 mall-monitor: 8101
 mall-portal: 8085
 mall-search:8081
 mall-member 8083
 mall-cart 8084
 *
 */