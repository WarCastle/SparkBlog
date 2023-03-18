package com.castle.blog.security.controller;

import com.castle.blog.security.service.UserLoginService;
import com.castle.blog.security.utils.UserInfoUtil;
import com.castle.blog.user.domain.UserDTO;
import com.castle.blog.user.entity.User;
import com.castle.common.utils.ResponseResult;
import com.castle.common.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
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
     * 用户注册时判断输入用户名是否已存在于数据库
     *
     * @param userName 用户名
     * @return ResponseResult
     */
    @GetMapping("/duplicate")
    public ResponseResult<Map<String, String>> duplicateUserNameJudge(
            @RequestParam("userName") String userName) {
        log.info("userName = {}", userName);
        if (StringUtils.hasText(userName)) {
            return userLoginService.duplicateUserNameJudge(userName);
        }
        return ResponseResult.error(StatusCode.USERNAME_NULL.value(),
                StatusCode.USERNAME_NULL.msg());
    }

    /**
     * 用户名注册
     *
     * @param userDTO 用户 DTO
     * @return ResponseResult
     */
    @PostMapping("/register/username")
    public ResponseResult<Map<String, String>> userNameRegister(@RequestBody UserDTO userDTO) {
        BeanUtils.copyProperties(userDTO, UserInfoUtil.registerTrim(userDTO));
        if (!UserInfoUtil.registerCheck(userDTO)) {
            return ResponseResult.error(StatusCode.REGISTER_INFO_PARTIAL.value(),
                    StatusCode.REGISTER_INFO_PARTIAL.msg());
        }
        log.info("用户注册中，用户名为：{}", userDTO.getUserName());
        return userLoginService.registerByUserName(userDTO);
    }

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
        log.info("用户退出登录中。。。");
        return userLoginService.logout();
    }
}
