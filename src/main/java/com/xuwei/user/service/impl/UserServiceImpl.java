package com.xuwei.user.service.impl;

import com.xuwei.entity.UserEntity;
import com.xuwei.user.dao.UserDao;
import com.xuwei.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by 许伟 on 2018/3/21 0021.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserEntity login(UserEntity userEntity) {
        return userDao.findUserByNameAndPass(userEntity);
    }

    @Override
    public UserEntity register(UserEntity userEntity) {
        return userDao.register(userEntity);
    }


}
