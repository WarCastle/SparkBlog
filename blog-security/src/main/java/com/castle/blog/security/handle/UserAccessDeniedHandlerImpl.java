package com.castle.blog.security.handle;

import com.alibaba.fastjson.JSONObject;
import com.castle.common.utils.ResponseResult;
import com.castle.common.utils.StatusCode;
import com.castle.common.utils.WebUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author YuLong
 * Date: 2023/3/3 8:37
 * @Classname UserAccessDeniedHandlerImpl
 * @Description 用户拒绝访问处理实现类
 */
@Component
public class UserAccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        ResponseResult<String> result = ResponseResult
                .error(StatusCode.USER_UN_PERMISSIONS.value(), StatusCode.USER_UN_PERMISSIONS.msg());
        String json = JSONObject.toJSONString(result);
        // 处理异常
        WebUtil.renderString(response, json);
    }
}
