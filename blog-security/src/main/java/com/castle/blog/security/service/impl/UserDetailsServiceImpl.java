package com.castle.blog.security.service.impl;

import com.castle.blog.feign.clients.user.UserServiceClient;
import com.castle.blog.security.domain.LoginUserDetails;
import com.castle.blog.security.mapper.MenuMapper;
import com.castle.blog.security.mapper.RoleMapper;
import com.castle.blog.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author YuLong
 * Date: 2023/3/10 15:28
 * @Classname UserDetailsServiceImpl
 * @Description 用户详细信息业务实现类
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserServiceClient userServiceClient;
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询用户信息
        log.info("username = {}", username);
        User user = userServiceClient.findUserByUserName(username);
        // 如果查询不到用户数据就通过抛出异常来给出提示
        log.info("待查找的用户名称为：{}, 在数据库中user = {}", username, user);
        if (Objects.isNull(user)) {
            throw new RuntimeException("数据库中不存在该用户");
        }
        // 根据用户查询权限信息 添加到 LoginUser中
        List<String> permissions = menuMapper.selectPermissionsByUserId(user.getUserId());
        List<String> roles = roleMapper.selectRolesByUserId(user.getUserId());
        // 封装成UserDetails对象返回
        return new LoginUserDetails(user, permissions, roles);
    }
}
