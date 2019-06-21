package com.xuwei.user.dao.provider;

import com.xuwei.entity.UserEntity;
import com.xuwei.util.CommUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.Date;

/**
 * Created by 许伟 on 2018/3/21 0021.
 */
public class UserDaoProvider {

    public String findUserByNameAndPass(UserEntity userEntity){
        return new SQL(){
            {
                SELECT("*");
                FROM("XW_USER");
                WHERE("NAME='"+userEntity.getName()+"'");
                WHERE("PASSWORD='"+userEntity.getPassword()+"'");
            }
        }.toString();
    }

    public String register(UserEntity userEntity){
        return new SQL(){
            {
                INSERT_INTO("XW_USER");
                VALUES("XH","SEQ_XH.NEXTVAL");
                if(StringUtils.isNotBlank(userEntity.getName())){
                    VALUES("NAME","'"+userEntity.getName()+"'");
                }
                if(StringUtils.isNotBlank(userEntity.getPassword())){
                    VALUES("PASSWORD","'"+userEntity.getPassword()+"'");
                }
                if(StringUtils.isNotBlank(userEntity.getNc())){
                    VALUES("NC","'"+userEntity.getNc()+"'");
                }
                if(StringUtils.isNotBlank(userEntity.getUrl())){
                    VALUES("URL","'"+userEntity.getUrl()+"'");
                }
                if(StringUtils.isNotBlank(userEntity.getEmail())){
                    VALUES("EMAIL","'"+userEntity.getEmail()+"'");
                }
                if(StringUtils.isNotBlank(userEntity.getBirthday())){
                    VALUES("BIRTHDAY","'"+userEntity.getBirthday()+"'");
                }
            }
        }.toString();
    }

}
