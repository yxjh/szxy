package com.xuwei.user.dao;

import com.xuwei.entity.UserEntity;
import com.xuwei.user.dao.provider.UserDaoProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * Created by 许伟 on 2018/3/21 0021.
 */
@Mapper
public interface UserDao {

    @SelectProvider(type = UserDaoProvider.class,method = "findUserByNameAndPass")
    UserEntity findUserByNameAndPass(UserEntity userEntity);

    @SelectProvider(type = UserDaoProvider.class,method = "register")
    UserEntity register(UserEntity userEntity);
}
