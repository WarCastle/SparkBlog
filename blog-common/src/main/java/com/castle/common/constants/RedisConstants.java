package com.castle.common.constants;

/**
 * @author YuLong
 * Date: 2023/3/5 21:07
 * @Classname RedisConstants
 * @Description Redis常量类
 */
public class RedisConstants {
    public static final String TOKEN = "token";
    public static final String USER_LOGIN_KEY = "login:user:";
    public static final String LOGIN_USER_CODE_KEY = "login:user:code";
    public static final Integer SMS_CODE_EXPIRE = 2 * 60;
    public static final String USER_PREFIX_TOKEN = "system:user:";
}
