package com.xuwei.config.mybatis;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.MapWrapper;

import java.util.Map;

/**
 * 驼峰处理
 * @author 许伟
 * @date 2017/10/24
 */
public class MyMapWrapper extends MapWrapper {
    public MyMapWrapper(MetaObject metaObject, Map<String, Object> map) {
        super(metaObject, map);
    }

    @Override
    public String findProperty(String name, boolean useCamelCaseMapping) {
        if (useCamelCaseMapping && (name.charAt(0) >= 'A' && name.charAt(0) <= 'Z' || name.indexOf("_") >= 0)) {
            return underlineToCamelCase(name);
        }
        return name;
    }

    /**
     * 将下划线转化为驼峰风格
     *
     * @param inputString
     * @author LiuWang
     * @date 2017/10/23 15:20
     */
    public String underlineToCamelCase(String inputString) {
        StringBuilder sb = new StringBuilder();
        boolean nextUpperCase = false;
        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);
            if (c == '_') {
                if (sb.length() > 0) {
                    nextUpperCase = true;
                }
            } else {
                if (nextUpperCase) {
                    sb.append(Character.toUpperCase(c));
                    nextUpperCase = false;
                } else {
                    sb.append(Character.toLowerCase(c));
                }
            }
        }
        return sb.toString();
    }
}
