package com.castle.blog.security.filter;

import com.castle.blog.security.domain.LoginUserDetails;
import com.castle.blog.security.utils.JwtUtil;
import com.castle.common.utils.RedisUtils;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static com.castle.common.utils.MessageConstants.TOKEN_PARSE_FAIL;
import static com.castle.common.utils.MessageConstants.USER_UN_LOGIN;
import static com.castle.common.utils.RedisConstants.TOKEN;
import static com.castle.common.utils.RedisConstants.USER_LOGIN_KEY;

/**
 * @author YuLong
 * Date: 2023/3/6 8:44
 * @Classname UserJwtAuthenticationTokenFilter
 * @Description 用户身份验证令牌过滤器
 */
@Component
public class UserJwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private RedisUtils redisUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        String token = request.getHeader(TOKEN);
        if (!StringUtils.hasText(token)) {
            // 为空直接放行，由于在SpringSecurity过滤器链中的链末端会有FilterSecurityInterceptor来判断是否处于认证状态
            filterChain.doFilter(request, response);
            return;
        }

        // 解析token
        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            throw new RuntimeException(TOKEN_PARSE_FAIL);
        }

        // 从 redis中获取用户信息
        String redisKey = USER_LOGIN_KEY + userId;
        LoginUserDetails userDetails = redisUtils.getRedisObject(redisKey);
        if (Objects.isNull(userDetails)) {
            throw new RuntimeException(USER_UN_LOGIN);
        }

        /*
            存入SecurityContextHolder
            获取权限信息，封装到 authentication中
         */
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 放行
        filterChain.doFilter(request, response);
    }
}
