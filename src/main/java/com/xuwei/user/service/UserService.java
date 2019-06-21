package com.xuwei.user.service;

import com.xuwei.entity.UserEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by 许伟 on 2018/3/21 0021.
 */
public interface UserService {
    /**
    * @Description: 用户登陆
    * @author: 许伟
    * @date: 2018/3/21 0021 10:57
    */
    UserEntity login(UserEntity userEntity);

    /**
    * @Description: 用户注册
    * @author: 许伟
    * @date: 2018/3/21 0021 11:09
    */
    UserEntity register(UserEntity userEntity);


}
