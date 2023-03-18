package com.castle.common.utils;

import lombok.AllArgsConstructor;

/**
 * @author YuLong
 * Date: 2023/3/16 15:13
 * @Classname StatusCode
 * @Description 状态码
 */
@AllArgsConstructor
@SuppressWarnings("all")
public enum StatusCode {

    OK(200, "请求成功"),
    USER_AUTHENTICATION_FAIL(401, "用户认证失败，请登录账号"),
    PAYMENT_REQUIRED(402, "需要付款"),
    USER_UN_PERMISSIONS(403, "用户权限不足，无法访问"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "服务器繁忙，请稍后再试，或联系管理员"),
    USERNAME_NULL(1001, "用户名为空"),
    USERNAME_DUPLICATE(1002, "用户名已存在"),
    REGISTER_INFO_PARTIAL(1003, "注册信息不全"),
    USER_REGISTER_SUCCESS(1004, "用户注册成功"),
    USER_LOGIN_PASSWORD_ERROR(1005, "登录失败，用户名或密码错误"),
    TOKEN_PARSE_FAIL(1007, "token解析失败"),


    ;
    private Integer code;
    private String message;

    public int value() {
        return this.code;
    }

    public String msg() {
        return this.message;
    }
}
