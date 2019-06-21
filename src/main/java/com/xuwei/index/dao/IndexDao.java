package com.xuwei.index.dao;

import com.xuwei.entity.LyEntity;
import com.xuwei.entity.SearchEntity;
import com.xuwei.index.dao.provider.IndexDaoProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * Created by 许伟 on 2018/3/15 0015.
 */
@Mapper
public interface IndexDao {
    String photoSql="SELECT * FROM (SELECT W.URL,W.WPMS FROM XW_SCWP W INNER JOIN XW_USER U ON U.XH=W.USER_XH ORDER BY W.SCSJ DESC)WHERE ROWNUM<5";
    String titleSql="SELECT * FROM (SELECT W.WPLX,W.WPMS FROM XW_SCWP W INNER JOIN XW_USER U ON U.XH=W.USER_XH ORDER BY W.SCSJ DESC)WHERE ROWNUM<6";
    /**
    * @Description: 根据条件查询所有的上传物品
    * @author: 许伟
    * @date: 2018/3/15 0015 14:47
    */
    @SelectProvider(type = IndexDaoProvider.class,method = "findAll")
    List<Map> findAll(SearchEntity searchEntity);

    /**
    * @Description: 图片轮播的接口
    * @author: 许伟
    * @date: 2018/3/15 0015 20:18
    */
    @Select(photoSql)
    List<Map> photoList();

    /**
    * @Description: 公告的接口
    * @author: 许伟
    * @date: 2018/3/15 0015 20:18
    */
    @Select(titleSql)
    List<Map> titleList();

    /**
    * @Description: 饼图统计的接口
    * @author: 许伟
    * @date: 2018/3/16 0016 9:18
    */
    @SelectProvider(type = IndexDaoProvider.class,method = "complete")
    List<Map> complete();

    /**
    * @Description: 折线图统计的接口
    * @author: 许伟
    * @date: 2018/3/16 0016 9:18
    */
    @SelectProvider(type = IndexDaoProvider.class,method = "totalPercent")
    List<Map> totalPercent(String weekTime);

    /**
    * @Description: 根据物品xh查询详细信息
    * @author: 许伟
    * @date: 2018/3/19 0019 13:32
    */
    @SelectProvider(type = IndexDaoProvider.class,method = "findByXh")
    Map findByXh(int user_xh,int xh);

    @SelectProvider(type = IndexDaoProvider.class,method = "addLy")
    void addLy(LyEntity lyEntity);

    @Select("update xw_scwp set jyzt='1' where xh=#{wp_xh}")
    void updateState(int wp_xh);
}
