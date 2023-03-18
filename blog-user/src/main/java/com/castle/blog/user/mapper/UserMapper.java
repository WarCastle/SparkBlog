package com.castle.blog.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.castle.blog.user.domain.UserDTO;
import com.castle.blog.user.entity.User;

/**
 * @author YuLong
 * @Description 针对表【sys_user(用户表)】的数据库操作Mapper
 * Date 2023-03-02 20:37:09
 * @Entity com.castle.blog.user.entity.User
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询用户
     *
     * @param userName 用户名
     * @return user
     */
    User selectUserByUserName(String userName);

    /**
     * 根据手机号查询用户
     *
     * @param phone 手机号
     * @return User
     */
    User selectUserByPhone(String phone);

    /**
     * 保存用户
     *
     * @param user 用户
     */
    void insertUser(User user);

    /**
     * 用户名注册
     *
     * @param userDTO 用户 DTO
     * @return ResponseResult
     */
    Integer insertByUserName(UserDTO userDTO);
}




