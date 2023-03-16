package com.castle.common.utils;

import org.apache.commons.lang.RandomStringUtils;

/**
 * @author YuLong
 * Date: 2023/3/5 20:28
 * @Classname RandomUtil
 * @Description 随机工具类
 */
public class RandomUtil {

    private static final String LOWERCASE_STR = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE_NUM_STR = "abcdefghijklmnopqrstuvwxyz1234567890";
    private static final String UPPERCASE_NUM_STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public static String randomNumber(int count) {
        return RandomStringUtils.random(count, false, true);
    }

    public static String randomLetter(int count) {
        return RandomStringUtils.random(count, true, false);
    }

    public static String randomLowercase(int count) {
        return RandomStringUtils.random(count, LOWERCASE_STR);
    }

    public static String randomUppercase(int count) {
        return RandomStringUtils.random(count, UPPERCASE_STR);
    }

    public static String randomNumLetter(int count) {
        return RandomStringUtils.random(count, true, true);
    }

    public static String randomNumLowercase(int count) {
        return RandomStringUtils.random(count, LOWERCASE_NUM_STR);
    }

    public static String randomNumUppercase(int count) {
        return RandomStringUtils.random(count, UPPERCASE_NUM_STR);
    }
}
