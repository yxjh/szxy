package com.xuwei.topic.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xuwei.entity.ReplyEntity;
import com.xuwei.entity.TopicEntity;
import com.xuwei.topic.dao.TopicDao;
import com.xuwei.topic.service.TopicService;
import com.xuwei.util.CommUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by 许伟 on 2018/3/16 0016.
 */
@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicDao topicDao;

    @Override
    public PageInfo findAllTopic(int pageIndex,int pageSize,String key) {
        PageHelper.startPage(pageIndex,pageSize);
        List<Map> list=topicDao.findAllTopic(key);
        for(int i=0;i<list.size();i++){
            Map map=list.get(i);
            BigDecimal b1= (BigDecimal) map.get("xh");
            int xh=b1.intValue();
            Map m1=topicDao.findHtl(xh);
            Map m2=topicDao.findZhhtr(xh);
            if(m2!=null){
                map.put("zhhtr",m2.get("zhhtr"));
            }else{
                map.put("zhhtr","无");
            }
            if(m1!=null){
                map.put("htl",m1.get("htl"));
            }else{
                map.put("htl",0);
            }

        }
        PageInfo result=new PageInfo(list);
        return result;
    }

    @Override
    public int startTopic(TopicEntity topic) {
        topic.setFtsj(CommUtils.dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss"));
        try {
            topicDao.startTopic(topic);
            return 1;
        }catch (Exception e){

        }
        return 0;
    }

    @Override
    public Map num(String yesterday, String today) {
        return topicDao.num(yesterday,today);
    }
    @Override
    public int reply(ReplyEntity reply){
       reply.setHtsj(CommUtils.dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss"));
        try {
            topicDao.reply(reply);
            return 1;
        }catch (Exception e){

        }
        return 0;
    }
}
