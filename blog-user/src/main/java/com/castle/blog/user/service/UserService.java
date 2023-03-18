package com.castle.blog.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.castle.blog.user.domain.UserDTO;
import com.castle.blog.user.entity.User;

/**
 * @author YuLong
 * @Description 针对表【sys_user(用户表)】的数据库操作Service
 * Date 2023-03-02 20:37:09
 */
public interface UserService extends IService<User> {


    /**
     * 根据用户名查询用户
     *
     * @param userName 用户名
     * @return User
     */
    User queryUserByUserName(String userName);

    /**
     * 根据手机号查询用户
     *
     * @param phone 手机号
     * @return User
     */
    User queryUserByPhone(String phone);

    /**
     * 保存用户
     *
     * @param user 用户
     */
    void saveUser(User user);

    /**
     * 用户名注册
     *
     * @param userDTO 用户 DTO
     * @return ResponseResult
     */
    boolean saveByUserName(UserDTO userDTO);
}
