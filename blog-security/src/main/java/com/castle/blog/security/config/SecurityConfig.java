package com.castle.blog.security.config;

import com.castle.blog.security.filter.UserJwtAuthenticationTokenFilter;
import com.castle.blog.security.handle.UserAccessDeniedHandlerImpl;
import com.castle.blog.security.handle.UserAuthenticationEntryPointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @author YuLong
 * Date: 2023/3/2 20:46
 * @Classname SecurityConfig
 * @Description Security安全配置类
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Resource
    private UserJwtAuthenticationTokenFilter userJwtAuthenticationTokenFilter;
    @Resource
    private UserAccessDeniedHandlerImpl userAccessDeniedHandler;
    @Resource
    private UserAuthenticationEntryPointImpl userAuthenticationEntryPoint;

    /**
     * 创建 BCryptPasswordEncoder注入容器
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 创建 AuthenticationManager注入容器
     *
     * @param authenticationConfiguration 身份验证配置
     * @return AuthenticationManager
     * @throws Exception 异常
     */
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 关闭csrf（前后端分离项目需关闭）[跨站请求伪造，web常见的攻击之一]
                .csrf().disable()
                // 不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口 仅匿名访问
                .antMatchers("/verify/user/login").anonymous()
                .antMatchers("/sms/user/send/code").anonymous()
                .antMatchers("/sms/user/login").anonymous()
                // 对于 hello接口 登录用户和匿名都可访问
                .antMatchers("/test/hello").permitAll()
                // 对于 testCors接口 需要具备权限才能访问
                .antMatchers("/testCors").hasAuthority("system:dept:list")
                // 除上面外的所有请求全部需要鉴权认证（任何经过身份验证的用户都可以访问）
                .anyRequest().authenticated()
                .and()
                .formLogin();
        // 在 UsernamePasswordAuthenticationFilter之前添加 UserJwtAuthenticationTokenFilter过滤器
        http.addFilterBefore(userJwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // 配置异常处理器
        http.exceptionHandling()
                // 配置用户授权失败处理器
                .accessDeniedHandler(userAccessDeniedHandler)
                // 配置用户认证失败处理器
                .authenticationEntryPoint(userAuthenticationEntryPoint);
        // 允许跨域
        http.cors();
        return http.build();
    }
}
