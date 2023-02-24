package com.castle.blog.chatroom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author YuLong
 * Date: 2023/2/24 14:40
 * @Classname ChatRoomApplication
 * @Description 聊天室房间服务启动类
 */

@Slf4j
@SpringBootApplication
public class ChatRoomApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatRoomApplication.class, args);
        log.info("聊天室房间服务启动成功");
    }

}