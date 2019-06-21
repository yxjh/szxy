package com.xuwei.config.mybatis;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiuWang
 * @date 2017/10/24
 */
@Configuration
public class MyBatisConfig {

    /**
     * 定制mybatis配置(加载Map结果驼峰配置)
     * @author LiuWang
     * @date 2017/10/24 15:21
     */
    @Bean
    public ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return config -> {
            config.getTypeAliasRegistry().registerAliases("com.sailing.zunyi.entity");
            config.setMapUnderscoreToCamelCase(true);
            config.setObjectWrapperFactory(new MapWrapperFactory());
        };
    }
}
