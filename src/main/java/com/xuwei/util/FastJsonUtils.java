package com.xuwei.util;

import com.alibaba.fastjson.serializer.NameFilter;

/**
 * Created by 许伟 on 2018/1/28.
 */
public class FastJsonUtils {
    public static NameFilter nameFilter = new NameFilter() {
        @Override
        public String process(Object o, String s, Object o1) {
            // 属性名转小写
            String firstChar = s.substring(0, 1);
            String result = firstChar.equals(firstChar.toUpperCase()) ? s.toLowerCase() : s;
            return result;
        }
    };
}
