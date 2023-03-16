package com.castle.common.utils;

import org.springframework.util.StringUtils;

/**
 * @author YuLong
 * Date: 2023/3/5 21:30
 * @Classname RegexUtils
 * @Description 验证工具类
 */
public class RegexUtils {

    /**
     * 是否是无效手机格式
     *
     * @param phone 要校验的手机号
     * @return true:符合，false：不符合
     */
    public static boolean isPhoneInvalid(String phone) {
        return mismatch(phone, RegexPatterns.PHONE_REGEX);
    }

    /**
     * 是否是无效邮箱格式
     *
     * @param email 要校验的邮箱
     * @return true:符合，false：不符合
     */
    public static boolean isEmailInvalid(String email) {
        return mismatch(email, RegexPatterns.EMAIL_REGEX);
    }

    /**
     * 是否是无效验证码格式
     *
     * @param code 要校验的验证码
     * @return true:符合，false：不符合
     */
    public static boolean isCodeInvalid(String code) {
        return mismatch(code, RegexPatterns.VERIFY_CODE_REGEX);
    }

    /**
     * 校验是否不符合正则格式
     *
     * @param str   待检验字符串
     * @param regex 正则匹配
     * @return true:符合，false：不符合
     */
    private static boolean mismatch(String str, String regex) {
        if (!StringUtils.hasText(str)) {
            return true;
        }
        return !str.matches(regex);
    }

}
