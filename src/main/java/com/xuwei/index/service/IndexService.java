package com.xuwei.index.service;

import com.github.pagehelper.PageInfo;
import com.xuwei.entity.LyEntity;
import com.xuwei.entity.SearchEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by 许伟 on 2018/3/15 0015.
 */
public interface IndexService {
    /**
    * @Description: 查询所有的物品(按照条件查找实现分页)
    * @author: 许伟
    * @date: 2018/3/15 0015 14:34
    */
    PageInfo findAll(SearchEntity searchEntity);

    /**
     * 图片轮播的接口
     * @return
     */
    List<Map> photoList();

    /**
    * @Description: 热点公告的接口
    * @author: 许伟
    * @date: 2018/3/15 0015 20:17
    */
    List<Map> titleList();

    /**
    * @Description: 饼图，交易状态
    * @author: 许伟
    * @date: 2018/3/15 0015 20:45
    */
    List<Map> complete();

    /**
    * @Description: 折线图统计
    * @author: 许伟
    * @date: 2018/3/16 0016 9:17
    */
    Map totalPercent(String weekTime);

    /**
    * @Description: 根据物品xh查询详细信息
    * @author: 许伟
    * @date: 2018/3/19 0019 13:31
    */
    Map findByXh(int user_xh,int xh);

    /**
    * @Description: 添加留言
    * @author: 许伟
    * @date: 2018/3/28 0028 14:32
    */
    int addLy(LyEntity lyEntity);
}
