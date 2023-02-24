package com.castle.blog.article;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author YuLong
 * Date: 2023/2/24 17:34
 * @Classname ArticleApplication
 * @Description 文章服务启动类
 */

@Slf4j
@SpringBootApplication
public class ArticleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArticleApplication.class, args);
        log.info("文章服务启动成功");
    }

}
