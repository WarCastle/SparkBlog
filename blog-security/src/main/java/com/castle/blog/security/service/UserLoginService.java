package com.castle.blog.security.service;

import com.castle.blog.user.domain.UserDTO;
import com.castle.blog.user.entity.User;
import com.castle.common.utils.ResponseResult;

import java.util.Map;

/**
 * @author YuLong
 * Date: 2023/3/10 9:13
 * @Classname UserLoginService
 * @Description 用户登录业务接口
 */
public interface UserLoginService {

    /**
     * 用户注册时判断输入用户名是否已存在于数据库
     *
     * @param userName 用户名
     * @return ResponseResult
     */
    ResponseResult<Map<String, String>> duplicateUserNameJudge(String userName);

    /**
     * 用户名注册
     *
     * @param userDTO 用户 DTO
     * @return ResponseResult
     */
    ResponseResult<Map<String, String>> registerByUserName(UserDTO userDTO);

    /**
     * 用户密码登录
     *
     * @param user 用户
     * @return ResponseResult
     */
    ResponseResult<Map<String, String>> loginByPassword(User user);

    /**
     * 用户退出登录
     * @return ResponseResult
     */
    ResponseResult<String> logout();
}
