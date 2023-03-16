package com.castle.blog.security.service;

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
     * 用户密码登录
     *
     * @param user 用户
     * @return ResponseResult
     */
    ResponseResult<Map<String, String>> loginByPassword(User user);

    /**
     * 用户退出登录
     *
     * @return ResponseResult
     */
    ResponseResult<String> logout();
}
