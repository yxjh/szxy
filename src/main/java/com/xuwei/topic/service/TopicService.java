package com.xuwei.topic.service;

import com.github.pagehelper.PageInfo;
import com.xuwei.common.ActionResult;
import com.xuwei.entity.ReplyEntity;
import com.xuwei.entity.TopicEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by 许伟 on 2018/3/16 0016.
 */
public interface TopicService {
    /**
    * @Description: 查询所有主贴
    * @author: 许伟
    * @date: 2018/3/16 0016 16:46
    */
    PageInfo findAllTopic(int pageIndex,int pageSize,String key);

    /**
    * @Description: 发帖
    * @author: 许伟
    * @date: 2018/3/16 0016 17:05
    */
    int startTopic(TopicEntity topic);

    /**
    * @Description: 统计贴总量
    * @author: 许伟
    * @date: 2018/3/16 0016 18:00
    */
    Map num(String yesterday, String today);

    /**
    * @Description: 回帖
    * @author: 许伟
    * @date: 2018/3/16 0016 19:48
    */
    int reply(ReplyEntity reply);
}
