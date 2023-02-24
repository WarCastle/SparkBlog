package com.castle.blog.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author YuLong
 * Date: 2023/2/24 15:13
 * @Classname UserApplication
 * @Description 用户服务启动类
 */

@Slf4j
@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
        log.info("用户服务启动成功");
    }

}
