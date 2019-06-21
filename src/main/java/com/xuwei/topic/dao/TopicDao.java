package com.xuwei.topic.dao;

import com.xuwei.entity.ReplyEntity;
import com.xuwei.entity.TopicEntity;
import com.xuwei.topic.dao.provider.TopicDaoProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * Created by 许伟 on 2018/3/16 0016.
 */
@Mapper
public interface TopicDao {

    /**
    * @Description: 查询所有主贴
    * @author: 许伟
    * @date: 2018/3/16 0016 16:47
    */
    @SelectProvider(type = TopicDaoProvider.class,method = "findAllTopic")
    List<Map> findAllTopic(String key);
    /**
    * @Description: 发帖
    * @author: 许伟
    * @date: 2018/3/16 0016 17:06
    */
    @SelectProvider(type = TopicDaoProvider.class,method = "startTopic")
    void startTopic(TopicEntity topic);

    /**
    * @Description: 统计
    * @author: 许伟
    * @date: 2018/3/16 0016 18:01
    */
    @SelectProvider(type = TopicDaoProvider.class,method = "num")
    Map num(String yesterday, String today);

    /**
    * @Description: 回帖
    * @author: 许伟
    * @date: 2018/3/16 0016 19:50
    */
    @SelectProvider(type = TopicDaoProvider.class,method = "reply")
    void reply(ReplyEntity reply);

    @SelectProvider(type = TopicDaoProvider.class,method = "findHtl")
    Map findHtl(Integer xh);

    @SelectProvider(type = TopicDaoProvider.class,method = "findZhhtr")
    Map findZhhtr(Integer xh);
}
