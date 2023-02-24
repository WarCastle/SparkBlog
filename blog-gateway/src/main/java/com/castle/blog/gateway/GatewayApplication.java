package com.castle.blog.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author YuLong
 * Date: 2023/2/23 15:28
 * @Classname GatewayApplication
 * @Description 网关服务启动类
 * 1.开启服务注册发现
 * 配置 nacos的注册中心地址
 */

@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
        log.info("网关API服务启动成功");
    }

}
