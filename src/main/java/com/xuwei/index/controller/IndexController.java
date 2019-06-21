package com.xuwei.index.controller;

import com.github.pagehelper.PageInfo;
import com.xuwei.common.ActionResult;
import com.xuwei.entity.LyEntity;
import com.xuwei.entity.SearchEntity;
import com.xuwei.index.service.IndexService;
import com.xuwei.util.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 许伟 on 2018/3/15 0015.
 */
@Api(tags = "首页模块")
@RequestMapping("index")
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.OPTIONS,RequestMethod.POST})
public class IndexController {

    @Autowired
    private IndexService indexService;

    /**
    * @Description:查询所有的闲置物品
    * @author: 许伟
    * @date: 2018/3/15 0015 14:31
    */
    @GetMapping("findAll")
    @ApiOperation(value = "查询所有的闲置物品")
    public ActionResult findAll(SearchEntity searchEntity){
            String time=searchEntity.getBeginTime();
            if(StringUtils.isNotBlank(searchEntity.getBeginTime())){
                String t[]=time.split("-");
                searchEntity.setBeginTime(dealTime(t[0])+" 00:00:00");
                searchEntity.setEndTime(dealTime(t[1])+" 23:59:59");
            }
            PageInfo result=indexService.findAll(searchEntity);
            return ResultUtils.success(result);
    }

    /**
    * @Description: 首页接口合并(饼图，轮播图，热点话题)
    * @author: 许伟
    * @date: 2018/3/15 0015 20:00
    */
    @GetMapping("indexAll")
    @ApiOperation(value = "查询闲置物品信息，公告及图片")
    public ActionResult indexAll(){
        Map result =new HashMap();
        result.put("photo",indexService.photoList());
        result.put("title",indexService.titleList());
        return ResultUtils.success(result);
    }
    /**
    * @Description: 饼图，统计交易状态
    * @author: 许伟
    * @date: 2018/3/15 0015 20:45
    */
    @GetMapping("complete")
    @ApiOperation(value = "饼图统计")
    public ActionResult complete(){
        return ResultUtils.success(indexService.complete());
    }

    /**
    * @Description: 折现周统计
    * @author: 许伟
    * @date: 2018/3/16 0016 9:17
    */
    @PostMapping("totalPercent")
    @ApiOperation(value = "折线图统计")
    public ActionResult totalPercent(){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        String weekTime=format.format(calendar.getTime());
        return ResultUtils.success(indexService.totalPercent(weekTime));
    }

    /**
    * @Description: 根据物品xh查询物品详细信息
    * @author: 许伟
    * @date: 2018/3/19 0019 13:30
    */
    @GetMapping("findByXh")
    @ApiOperation(value = "查询物品详细信息")
    public ActionResult findByXh(int user_xh,int topic_xh){
        return ResultUtils.success(indexService.findByXh(user_xh,topic_xh));
    }

    /**
    * @Description: 增加留言
    * @author: 许伟
    * @date: 2018/3/28 0028 14:23
    */
    @GetMapping("addLy")
    @ApiOperation(value = "添加留言")
    public ActionResult addLy(LyEntity lyEntity){
        return ResultUtils.success(indexService.addLy(lyEntity));
    }
    /**
    * @Description: 处理时间
    * @author: 许伟
    * @date: 2018/3/15 0015 19:51
    */
    private String dealTime(String time){
        String s1="";
        String s2="";
        String[] str=time.split("/");
        if(Integer.parseInt(str[1])<10 ){
            s1="0"+str[1];
        }else {
            s1=str[1];
        }
        if(Integer.parseInt(str[2])<10){
            s2="0"+str[2];
        }else{
            s2=str[2];
        }
        return str[0]+"-"+s1+"-"+s2;
    }
}
