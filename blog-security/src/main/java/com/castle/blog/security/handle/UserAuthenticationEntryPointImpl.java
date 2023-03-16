package com.castle.blog.security.handle;

import com.alibaba.fastjson.JSONObject;
import com.castle.common.utils.ResponseResult;
import com.castle.common.utils.WebUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.castle.common.utils.MessageConstants.USER_AUTHENTICATION_FAIL;

/**
 * @author YuLong
 * Date: 2023/3/6 9:16
 * @Classname UserAuthenticationEntryPointImpl
 * @Description 用户身份验证入口处理实现类
 */
@Component
public class UserAuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        ResponseResult<String> result = ResponseResult
                .error(HttpStatus.FORBIDDEN.value(), USER_AUTHENTICATION_FAIL);
        String json = JSONObject.toJSONString(result);
        // 处理异常
        WebUtil.renderString(response, json);
    }
}
