package com.xuwei.home.dao;

import com.xuwei.entity.ProductEntity;
import com.xuwei.entity.UserEntity;
import com.xuwei.home.dao.provider.HomeDaoProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * Created by 许伟 on 2018/3/26 0026.
 */
@Mapper
public interface HomeDao {

    @SelectProvider(type = HomeDaoProvider.class,method = "myWp")
    List<Map> myWp(Integer xh);

    @Select("SELECT * FROM XW_USER WHERE XH=#{xh}")
    Map myInfo(Integer xh);

    @SelectProvider(type = HomeDaoProvider.class,method = "myTopic")
    List<Map> myTopic(Integer xh);

    @SelectProvider(type = HomeDaoProvider.class,method = "myReply")
    List<Map> myReply(Integer xh);

    @SelectProvider(type = HomeDaoProvider.class,method = "myLyFromMe")
    List<Map> myLyFromMe(Integer xh);

    @SelectProvider(type = HomeDaoProvider.class,method = "myLyToOther")
    List<Map> myLyToOther(int xh);

    @SelectProvider(type = HomeDaoProvider.class,method = "insert")
    void insert(ProductEntity productEntity);

    @SelectProvider(type = HomeDaoProvider.class,method = "headNum")
    Map headNum(Integer xh);

    @SelectProvider(type = HomeDaoProvider.class,method = "editMyInfo")
    void editMyInfo(UserEntity userEntity);

    @Select("UPDATE XW_SCWP SET JYZT='2' WHERE XH=#{topic_xh}")
    void updateState(int topic_xh);
}
