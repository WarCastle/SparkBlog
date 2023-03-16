package com.castle.blog.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @author YuLong
 * Date: 2023/3/9 17:11
 * @Classname DefaultFeignConfig
 * @Description 默认Feign配置
 */
public class DefaultFeignConfig {
    @Bean
    public Logger.Level logLevel() {
        return Logger.Level.BASIC;
    }
}
