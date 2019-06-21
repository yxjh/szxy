package com.xuwei.topic.controller;

import com.xuwei.common.ActionResult;
import com.xuwei.entity.ReplyEntity;
import com.xuwei.entity.TopicEntity;
import com.xuwei.topic.service.TopicService;
import com.xuwei.util.CommUtils;
import com.xuwei.util.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 许伟 on 2018/3/16 0016.
 */
@Api(tags = "论坛模块")
@RequestMapping("topic")
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.OPTIONS})
public class TopicController {

    @Autowired
    private TopicService topicService;

    /**
    * @Description: 查询所有的主贴
    * @author: 许伟
    * @date: 2018/3/16 0016 16:44
    */
    @GetMapping("allTopic")
    @ApiOperation(value = "所有的主题帖")
    public ActionResult findAllTopic(@RequestParam(value = "pageIndex",defaultValue = "1") int pageIndex,
                                     @RequestParam(value = "pageSize",defaultValue = "15") int pageSize,
                                     String key){
        return ResultUtils.success(topicService.findAllTopic(pageIndex,pageSize,key));
    }

    /**
    * @Description: 发帖
    * @author: 许伟
    * @date: 2018/3/16 0016 17:03
    */
    @GetMapping("startTopic")
    @ApiOperation(value = "发帖")
    public ActionResult startTopic(TopicEntity topic){
        return ResultUtils.success(topicService.startTopic(topic));
    }
    /**
    * @Description: 回帖
    * @author: 许伟
    * @date: 2018/3/16 0016 19:47
    */
    @GetMapping("reply")
    @ApiOperation(value = "回帖")
    public ActionResult reply(ReplyEntity reply){
        return ResultUtils.success(topicService.reply(reply));
    }
    /**
    * @Description: 统计头表数字
    * @author: 许伟
    * @date: 2018/3/16 0016 17:35
    */
    @GetMapping("num")
    @ApiOperation(value = "统计头部数字")
    public ActionResult num(){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Date time1= new Date();
        Date time=new Date(time1.getTime()-1000*60*60*24);
        String yesterday=format.format(time);
        String today=CommUtils.dateToStr(time1,"yyyy-MM-dd 00:00:00");
        return ResultUtils.success(topicService.num(yesterday,today));
    }


}
