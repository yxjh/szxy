package com.xuwei.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 许伟 on 2018/3/17 0017.
 */
@Configuration
@ConfigurationProperties(prefix = "pathconfig")
public class ImgConfig {
    public static String PATH;
    public static String URL;

    public static String getPath(){
        return PATH;
    }

    public static void setPath(String path){
        PATH=path;
    }

    public static String getUrl(){
        return URL;
    }

    public static void setUrl(String url){
        URL=url;
    }
}
