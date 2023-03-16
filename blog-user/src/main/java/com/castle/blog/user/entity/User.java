package com.castle.blog.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author YuLong
 * Date: 2023/3/1 15:05
 * @Classname LoginUser
 * @Description 用户实体类
 */
@Data
@TableName(value = "sys_user")
public class User implements Serializable {
    /**
     * 用户id
     */
    @TableId(value = "user_id")
    private Long userId;

    /**
     * 用户手机号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 用户邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 用户名
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 用户昵称
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 用户密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 用户头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 用户性别（0男，1女，2未知）
     */
    @TableField(value = "sex")
    private String sex;

    /**
     * 用户自带标签
     */
    @TableField(value = "tags")
    private String tags;

    /**
     * 用户账号状态（0正常 1停用）
     */
    @TableField(value = "account_status")
    private String accountStatus;

    /**
     * 用户登录状态(在线1，离线0)
     */
    @TableField(value = "login_status")
    private String loginStatus;

    /**
     * 用户好友id集合
     */
    @TableField(value = "friend_ids")
    private Object friendIds;

    /**
     * 用户生日
     */
    @TableField(value = "birthday")
    private LocalDateTime birthday;

    /**
     * 用户上线时间
     */
    @TableField(value = "login_time")
    private LocalDateTime loginTime;

    /**
     * 用户下线时间
     */
    @TableField(value = "last_time")
    private LocalDateTime lastTime;

    /**
     * 用户创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 用户更新时间
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    /**
     * 逻辑删除（0代表未删除，1代表已删除）
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}