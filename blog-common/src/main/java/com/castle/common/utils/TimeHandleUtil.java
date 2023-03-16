package com.castle.common.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author YuLong
 * Date: 2023/3/6 18:31
 * @Classname TimeHandleUtil
 * @Description 时间处理工具类
 */
public class TimeHandleUtil {

    public static LocalDateTime localCurrentTime() {
        ZoneId zone = ZoneId.of("Asia/Shanghai");
        return LocalDateTime.now().atZone(zone).toLocalDateTime();
    }
}
