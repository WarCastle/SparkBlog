package com.castle.blog.chatmessage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author YuLong
 * Date: 2023/2/24 14:47
 * @Classname ChatMessageApplication
 * @Description 聊天室消息服务启动类
 */

@Slf4j
@SpringBootApplication
public class ChatMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatMessageApplication.class, args);
        log.info("聊天室消息服务启动成功");
    }

}
