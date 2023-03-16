package com.castle.blog.security.expression;

import com.castle.blog.security.domain.LoginUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author YuLong
 * Date: 2023/3/10 9:00
 * @Classname UserSecurityExpressionRoot
 * @Description 用户自定义权限注解表达式
 */
@Component("userExpress")
public class UserSecurityExpressionRoot {
    public boolean hasAuthority(String authority) {
        // 获取当前用户的权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUserDetails userDetails = (LoginUserDetails) authentication.getPrincipal();
        List<String> permissions = userDetails.getPermissions();
        // 判断用户权限集合中是否存在 authority
        return permissions.contains(authority);
    }
}
