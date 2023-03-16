package com.castle.blog.security.controller;

import com.castle.common.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YuLong
 * Date: 2023/3/12 16:15
 * @Classname HelloController
 * @Description 你好测试控制类
 */
@Slf4j
@RequestMapping("/test")
@RestController
public class HelloController {

    @GetMapping("/hello")
    public ResponseResult<String> hello() {
        log.info("hello");
        return ResponseResult.success(HttpStatus.OK.value(), "哈喽哈喽");
    }

    @GetMapping("/token")
    public ResponseResult<String> tokenTest() {
        log.info("token测试成功");
        return ResponseResult.success(HttpStatus.OK.value(), "token测试成功");
    }
}
