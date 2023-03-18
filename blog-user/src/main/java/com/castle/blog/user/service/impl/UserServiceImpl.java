package com.castle.blog.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.castle.blog.user.domain.UserDTO;
import com.castle.blog.user.entity.User;
import com.castle.blog.user.mapper.UserMapper;
import com.castle.blog.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author YuLong
 * @Description 针对表【sys_user(用户表)】的数据库操作Service实现
 * Date 2023-03-02 20:37:09
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 根据用户名查询用户
     *
     * @param userName 用户名
     * @return User
     */
    @Override
    public User queryUserByUserName(String userName) {
        log.info("进入userServiceImpl");
        return userMapper.selectUserByUserName(userName);
    }

    /**
     * 根据手机号查询用户
     *
     * @param phone 手机号
     * @return User
     */
    @Override
    public User queryUserByPhone(String phone) {
        return userMapper.selectUserByPhone(phone);
    }

    /**
     * 保存用户
     *
     * @param user 用户
     */
    @Override
    public void saveUser(User user) {
        userMapper.insertUser(user);
    }

    /**
     * 用户名注册
     *
     * @param userDTO 用户 DTO
     * @return ResponseResult
     */
    @Override
    public boolean saveByUserName(UserDTO userDTO) {
        log.info("进入userService...saveByUserName");
        Integer num = userMapper.insertByUserName(userDTO);
        return num > 0;
    }
}




