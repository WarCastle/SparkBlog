package com.castle.blog.user;

import com.castle.blog.user.entity.User;
import com.castle.blog.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author YuLong
 * Date: 2023/3/6 18:16
 * @Classname ServiceTest
 * @Description 服务层测试类
 */
@Slf4j
@SpringBootTest
public class ServiceTest {


    @Resource
    private UserService userService;

    @Test
    public void testUserQueryByPhone() {
        String phone = "15915638361";
        User user = userService.queryUserByPhone(phone);
        log.info("user = {}", user);
    }

    @Test
    public void testUserSave() {
        Long userId = RandomUtils.nextLong();
        User user = new User();
        user.setUserId(userId);
        user.setUserName("castle");
        user.setNickName("城堡");
        user.setPassword("123456");
        user.setPhone("15915638361");
        user.setSex("0");
        // 保存用户到数据库
        userService.saveUser(user);
        log.info("用户数据保存到数据库成功，user = {}", user);
    }

}
