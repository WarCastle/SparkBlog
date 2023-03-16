package com.castle.common.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author YuLong
 * Date: 2023/3/6 9:55
 * @Classname WebUtil
 * @Description web工具类
 */
public class WebUtil {
    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string   待渲染的字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
