package com.castle.blog.security;

import com.castle.blog.feign.clients.user.UserServiceClient;
import com.castle.blog.security.entity.Role;
import com.castle.blog.security.service.RoleService;
import com.castle.blog.user.entity.User;
import com.castle.common.utils.RandomUtil;
import com.castle.common.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.castle.common.utils.RedisConstants.USER_PREFIX_TOKEN;

/**
 * @author YuLong
 * Date: 2023/3/5 20:36
 * @Classname UtilTest
 * @Description 工具测试类
 */
@Slf4j
@SpringBootTest
public class UtilTest {

    @Resource
    private BCryptPasswordEncoder passwordEncoder;
    @Resource
    private UserServiceClient userServiceClient;
    @Resource
    private RoleService roleService;
    @Resource
    private RedisUtils redisUtils;
    private final int count = 50;

    @Test
    public void testRedisUtil() throws IOException {
        String userName = "castle";
        String userKey = USER_PREFIX_TOKEN + userName;
        User user = new User();
        Long userId = RandomUtils.nextLong();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setNickName("城堡");
        user.setPassword("123456");
        user.setPhone("15915638361");
        user.setSex("0");
        redisUtils.setRedisObject(userKey, user, 1L, TimeUnit.MINUTES);
        Object redisObject = redisUtils.getRedisObject(userKey);
        log.info("redisObject = {}", redisObject);
    }

    @Test
    public void testRandom1() {
        String s1 = RandomUtil.randomNumber(count);
        String s2 = RandomUtil.randomLetter(count);
        String s3 = RandomUtil.randomNumLetter(count);
        log.info("{}\n{}\n{}", s1, s2, s3);
    }

    @Test
    public void testRandom2() {
        String s1 = RandomUtil.randomLowercase(count);
        String s2 = RandomUtil.randomNumLowercase(count);
        String s3 = RandomUtil.randomUppercase(count);
        String s4 = RandomUtil.randomNumUppercase(count);
        log.info("{}\n{}\n{}\n{}", s1, s2, s3, s4);
    }

    @Test
    public void testFindUserByPhone() {
        String phone = "15915638361";
        User user = userServiceClient.findUserByPhone(phone);
        log.info("user = {}\n", user);
    }

    @Test
    public void testFindUserByUserName() {
        String userName = "castle";
        User user = userServiceClient.findUserByUserName(userName);
        log.info("user = {}\n", user);
    }

    @Test
    public void testSaveRole() {
        Role role = new Role();
        role.setRoleId(Long.valueOf(RandomUtil.randomNumber(19)));
        role.setRoleName("管理员");
        role.setRoleCode("super");
        role.setStatus("1");
        role.setRemark("暂无");
        roleService.saveRole(role);
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        Long userId = RandomUtils.nextLong();
        user.setUserId(userId);
        user.setUserName("castle");
        user.setNickName("城堡");
        user.setPassword("123456");
        user.setPhone("15915638361");
        user.setSex("0");
        // 加密用户输入的密码
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        // 保存用户到数据库
        userServiceClient.saveUser(user);
        log.info("用户数据保存到数据库成功，user = {}", user);
    }
}
