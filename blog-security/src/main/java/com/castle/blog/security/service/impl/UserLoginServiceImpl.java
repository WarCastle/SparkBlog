package com.castle.blog.security.service.impl;

import com.castle.blog.security.domain.LoginUserDetails;
import com.castle.blog.security.service.UserLoginService;
import com.castle.blog.security.utils.JwtUtil;
import com.castle.blog.user.entity.User;
import com.castle.common.utils.RedisUtils;
import com.castle.common.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.castle.common.utils.MessageConstants.*;
import static com.castle.common.utils.RedisConstants.TOKEN;
import static com.castle.common.utils.RedisConstants.USER_LOGIN_KEY;

/**
 * @author YuLong
 * Date: 2023/3/10 9:18
 * @Classname UserLoginServiceImpl
 * @Description 用户登录业务接口
 */
@Slf4j
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private RedisUtils redisUtils;

    /**
     * 用户密码登录
     *
     * @param user 用户信息
     * @return ResponseResult
     */
    @Override
    public ResponseResult<Map<String, String>> loginByPassword(User user) {
        // AuthenticationManager authenticationManager进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 如果认证没通过，给出对应的提示
        if (Objects.isNull(authenticate)) {
            log.info(LOGIN_PASSWORD_ERROR);
            throw new RuntimeException(LOGIN_PASSWORD_ERROR);
        }
        // 如果认证通过了，使用 userId生成一个jwt，jwt存入ResponseResult返回
        LoginUserDetails userDetails = (LoginUserDetails) authenticate.getPrincipal();
        String userId = userDetails.getUser().getUserId().toString();
        String jwt = JwtUtil.createJWT(userId);
        Map<String, String> map = new HashMap<>(1);
        map.put(TOKEN, jwt);
        // 把完整的用户信息存入 redis，userId作为 token中的 key
        log.info("userDetails = {}", userDetails);
        redisUtils.setRedisObject(USER_LOGIN_KEY + userId, userDetails, 30L, TimeUnit.MINUTES);
        log.info(LOGIN_SUCCESS);
        return ResponseResult.success(HttpStatus.OK.value(), LOGIN_SUCCESS, map);
    }

    /**
     * 用户退出登录
     *
     * @return ResponseResult
     */
    @Override
    public ResponseResult<String> logout() {
        // 获取 SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken)
                SecurityContextHolder.getContext().getAuthentication();
        LoginUserDetails userDetails = (LoginUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getUser().getUserId();
        // 删除 redis中的值
        redisUtils.delete(USER_LOGIN_KEY + userId);
        log.info(LOGOUT_SUCCESS);
        return ResponseResult.success(HttpStatus.OK.value(), LOGOUT_SUCCESS);
    }
}
