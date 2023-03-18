package com.castle.blog.security.service.impl;

import com.castle.blog.feign.clients.user.UserServiceClient;
import com.castle.blog.security.domain.LoginUserDetails;
import com.castle.blog.security.service.UserLoginService;
import com.castle.blog.security.utils.JwtUtil;
import com.castle.blog.user.domain.UserDTO;
import com.castle.blog.user.entity.User;
import com.castle.common.utils.RandomUtil;
import com.castle.common.utils.RedisUtil;
import com.castle.common.utils.ResponseResult;
import com.castle.common.utils.StatusCode;
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

import static com.castle.common.constants.DigitConstants.ID_LENGTH;
import static com.castle.common.constants.MessageConstants.LOGIN_SUCCESS;
import static com.castle.common.constants.MessageConstants.LOGOUT_SUCCESS;
import static com.castle.common.constants.RedisConstants.TOKEN;
import static com.castle.common.constants.RedisConstants.USER_LOGIN_KEY;

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
    private UserServiceClient userServiceClient;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private RedisUtil redisUtil;

    /**
     * 用户注册时判断输入用户名是否已存在于数据库
     *
     * @param userName 用户名
     * @return ResponseResult
     */
    @Override
    public ResponseResult<Map<String, String>> duplicateUserNameJudge(String userName) {
        User user = userServiceClient.findUserByUserName(userName);
        if (Objects.isNull(user)) {
            return ResponseResult.success();
        }
        return ResponseResult.error(StatusCode.USERNAME_DUPLICATE.value(), StatusCode.USERNAME_DUPLICATE.msg());
    }

    /**
     * 用户名注册
     *
     * @param userDTO 用户 DTO
     * @return ResponseResult
     */
    @Override
    public ResponseResult<Map<String, String>> registerByUserName(UserDTO userDTO) {
        // 为防止用户绕过前端校验，通过接口直接发送注册信息，这里须对 userDTO中的用户名进行再次校验
        ResponseResult<Map<String, String>> response = duplicateUserNameJudge(userDTO.getUserName());
        // 若在数据库中，该用户名已存在，则直接返回注册失败结果
        if (!response.getCode().equals(HttpStatus.OK.value())) {
            return response;
        }
        // 随机生成用户id
        userDTO.setUserId(RandomUtil.randomNumber(ID_LENGTH));
        boolean flag = userServiceClient.saveByUserName(userDTO);
        // 判断保存用户是否成功
        if (flag) {
            // 成功，返回注册用户成功信息
            return ResponseResult.success(StatusCode.USER_REGISTER_SUCCESS.value(),
                    StatusCode.USER_REGISTER_SUCCESS.msg());
        }
        // 否则，则返回服务器繁忙信息
        return ResponseResult.error(StatusCode.INTERNAL_SERVER_ERROR.value(),
                StatusCode.INTERNAL_SERVER_ERROR.msg());
    }

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
            log.info(StatusCode.USER_LOGIN_PASSWORD_ERROR.msg());
            throw new RuntimeException(StatusCode.USER_LOGIN_PASSWORD_ERROR.msg());
        }
        // 如果认证通过了，使用 userId生成一个jwt，jwt存入ResponseResult返回
        LoginUserDetails userDetails = (LoginUserDetails) authenticate.getPrincipal();
        String userId = userDetails.getUser().getUserId().toString();
        String jwt = JwtUtil.createJWT(userId);
        Map<String, String> map = new HashMap<>(1);
        map.put(TOKEN, jwt);
        // 把完整的用户信息存入 redis，userId作为 token中的 key
        log.info("userDetails = {}", userDetails);
        redisUtil.setRedisObject(USER_LOGIN_KEY + userId, userDetails, 30L, TimeUnit.MINUTES);
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
        redisUtil.delete(USER_LOGIN_KEY + userId);
        log.info(LOGOUT_SUCCESS);
        return ResponseResult.success(HttpStatus.OK.value(), LOGOUT_SUCCESS);
    }
}
