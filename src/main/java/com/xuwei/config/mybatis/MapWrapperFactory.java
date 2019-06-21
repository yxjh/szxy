package com.xuwei.config.mybatis;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.*;

import java.util.Map;

/**
 * Map类型转驼峰
 * @author 许伟
 * @date 2017/10/24
 */
public class MapWrapperFactory implements ObjectWrapperFactory {

    @Override
    public boolean hasWrapperFor(Object o) {
        return o != null && o instanceof Map;
    }

    @Override
    public ObjectWrapper getWrapperFor(MetaObject metaObject, Object o) {
        return new MyMapWrapper(metaObject, (Map) o);
    }
}
