package com.castle.blog.tag;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author YuLong
 * Date: 2023/2/24 17:42
 * @Classname TagApplication
 * @Description 标签服务启动类
 */

@Slf4j
@SpringBootApplication
public class TagApplication {

    public static void main(String[] args) {
        SpringApplication.run(TagApplication.class, args);
        log.info("标签服务启动成功");
    }

}
