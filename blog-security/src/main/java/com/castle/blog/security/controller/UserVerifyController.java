package com.castle.blog.security.controller;

import com.castle.blog.security.service.UserLoginService;
import com.castle.blog.user.entity.User;
import com.castle.common.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author YuLong
 * Date: 2023/3/1 14:27
 * @Classname UserVerifyController
 * @Description 用户认证控制器
 */
@Slf4j
@RestController
@RequestMapping("/verify/user")
public class UserVerifyController {

    @Resource
    private UserLoginService userLoginService;

    /**
     * 用户密码登录
     *
     * @param user 用户信息
     * @return ResponseResult
     */
    @PostMapping("/login")
    public ResponseResult<Map<String, String>> loginByPassword(@RequestBody User user) {
        log.info("进入登录controller");
        return userLoginService.loginByPassword(user);
    }

    /**
     * 用户退出登录
     *
     * @return ResponseResult
     */
    @GetMapping("/logout")
    public ResponseResult<String> logout() {
        return userLoginService.logout();
    }
}
