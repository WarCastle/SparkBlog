package com.castle.blog.security;

import com.castle.blog.feign.clients.user.UserServiceClient;
import com.castle.blog.feign.config.DefaultFeignConfig;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author YuLong
 * Date: 2023/3/1 13:54
 * @Classname SecurityApplication
 * @Description 安全服务启动类
 */
@Slf4j
@MapperScan({"com.castle.blog.security.mapper", "com.castle.blog.user.mapper"})
@EnableFeignClients(clients = UserServiceClient.class, defaultConfiguration = DefaultFeignConfig.class)
@SpringBootApplication(scanBasePackages = {"com.castle.blog.security", "com.castle.common"})
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
        log.info("安全服务启动成功");
    }

}

