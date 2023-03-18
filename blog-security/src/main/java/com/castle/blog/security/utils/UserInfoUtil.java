package com.castle.blog.security.utils;

import com.castle.blog.user.domain.UserDTO;
import org.springframework.util.StringUtils;

/**
 * @author YuLong
 * Date: 2023/3/17 23:03
 * @Classname UserInfoUtil
 * @Description 用户信息工具类
 */
public class UserInfoUtil {

    public static boolean registerCheck(UserDTO userDTO) {
        if (StringUtils.hasText(userDTO.getUserName())
                && StringUtils.hasText(userDTO.getPassword())) {
            return true;
        }
        return false;
    }

    public static UserDTO registerTrim(UserDTO userDTO) {
        userDTO.setUserName(userDTO.getUserName().trim());
        userDTO.setPassword(userDTO.getPassword().trim());
        return userDTO;
    }
}
