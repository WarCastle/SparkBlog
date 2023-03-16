package com.castle.blog.security.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.castle.blog.user.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author YuLong
 * Date: 2023/3/6 8:55
 * @Classname LoginUserDetails
 * @Description 用户登录类
 */
@Data
@NoArgsConstructor
public class LoginUserDetails implements UserDetails {

    private User user;

    private List<String> permissions;

    private List<String> roles;

    @JSONField(serialize = false)
    private List<GrantedAuthority> authorities;

    public LoginUserDetails(User user, List<String> permissions, List<String> roles) {
        this.user = user;
        this.permissions = permissions;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 若 authorities不为空，则直接返回
        if (Objects.nonNull(authorities)) {
            return authorities;
        }
        // 把 permissions 中 String类型的权限信息封装成 SimpleGrantedAuthority对象
        authorities = permissions.stream()
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
