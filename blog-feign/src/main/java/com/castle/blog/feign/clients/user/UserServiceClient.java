package com.castle.blog.feign.clients.user;

import com.castle.blog.user.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author YuLong
 * Date: 2023/3/6 19:33
 * @Classname UserServiceClient
 * @Description 用户服务调用接口
 */
@FeignClient(value = "blog-user", path = "/user")
public interface UserServiceClient {

    /**
     * 根据 id查找用户
     *
     * @param id 用户 id
     * @return User
     */
    @GetMapping("/id")
    User findById(@RequestParam("id") Long id);

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return user
     */
    @GetMapping("/find/name")
    User findUserByUserName(@RequestParam("username") String username);

    /**
     * 根据手机号查找用户
     *
     * @param phone 手机号
     * @return User
     */
    @GetMapping("/query")
    User findUserByPhone(@RequestParam("phone") String phone);

    /**
     * 保存用户信息
     *
     * @param user 用户
     */
    @PostMapping("/save")
    void saveUser(@RequestBody User user);
}
