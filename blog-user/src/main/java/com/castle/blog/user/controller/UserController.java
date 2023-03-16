package com.castle.blog.user.controller;

import com.castle.blog.user.entity.User;
import com.castle.blog.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author YuLong
 * Date: 2023/3/6 15:54
 * @Classname UserController
 * @Description 用户控制器
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return user
     */
    @GetMapping("/find/name")
    public User findUserByUserName(@RequestParam("username") String username) {
        log.info("进入userController");
        return userService.queryUserByUserName(username);
    }

    /**
     * 根据手机号查找用户
     *
     * @param phone 手机号
     * @return User
     */
    @GetMapping("/query")
    public User queryUserByPhone(@RequestParam("phone") String phone) {
        return userService.queryUserByPhone(phone);
    }

    /**
     * 保存用户信息
     *
     * @param user 用户
     */
    @PostMapping("/save")
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }
}
