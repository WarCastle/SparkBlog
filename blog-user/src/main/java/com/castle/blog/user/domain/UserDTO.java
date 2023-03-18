package com.castle.blog.user.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author YuLong
 * Date: 2023/3/17 22:53
 * @Classname UserDTO
 * @Description 用户 DTO
 */
@Data
public class UserDTO {
    private Long userId;
    private String phone;
    private String email;
    private String userName;
    private String nickName;
    private String password;
    private String avatar;
    private String sex;
    private String tags;
    private LocalDateTime birthday;
}
