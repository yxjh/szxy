package com.xuwei.home.controller;

import com.xuwei.common.ActionResult;
import com.xuwei.entity.ProductEntity;
import com.xuwei.entity.UserEntity;
import com.xuwei.home.service.HomeService;
import com.xuwei.util.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 许伟 on 2018/3/26 0026.
 */
@Api(tags = "我的用户模块")
@RequestMapping("home")
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.OPTIONS,RequestMethod.POST})
public class HomeController {

    @Autowired
    private HomeService homeService;

    /**
    * @Description: 头部数量统计
    * @author: 许伟
    * @date: 2018/3/27 0027 15:54
    */
    @GetMapping("headNum")
    @ApiOperation(value = "查询头部数字")
    public ActionResult headNum(Integer xh){
        return ResultUtils.success(homeService.headNum(xh));
    }

    /**
    * @Description: 上传物品
    * @author: 许伟
    * @date: 2018/3/26 0026 18:03
    */
    @PostMapping("upload")
    @ApiOperation(value = "上传闲置物品")
    public ActionResult upload(MultipartFile file, ProductEntity productEntity){
        return ResultUtils.success(homeService.upload(file,productEntity));
    }
    /**
    * @Description: 查询我上传的闲置物品信息
    * @author: 许伟
    * @date: 2018/3/26 0026 14:41
    */
    @GetMapping("myWp")
    @ApiOperation(value = "查询我上传的全部闲置物品信息")
    public ActionResult Mywp(int xh){
        return ResultUtils.success(homeService.myWp(xh));
    }


    /**
    * @Description: 我的个人信息
    * @author: 许伟
    * @date: 2018/3/27 0027 10:14
    */
    @GetMapping("myInfo")
    @ApiOperation(value = "查询我的个人信息")
    public ActionResult myInfo(int xh){
        return ResultUtils.success(homeService.myInfo(xh));
    }

    /**
    * @Description: 编辑个人信息
    * @author: 许伟
    * @date: 2018/3/27 0027 16:30
    */
    @PostMapping("editMyInfo")
    @ApiOperation(value = "编辑我的个人信息")
    public ActionResult editMyInfo(MultipartFile file,UserEntity userEntity){
        return ResultUtils.success(homeService.editMyInfo(file,userEntity));
    }
    /**
    * @Description: 导入我的物品
    * @author: 许伟
    * @date: 2018/3/26 0026 15:32
    */
    @GetMapping("excelMyWp")
    @ApiOperation(value = "将我的闲置物品导入excel表")
    public ActionResult excelMyWp(int xh,HttpServletResponse response){
        return ResultUtils.success(homeService.excelMyWp(xh,response));
    }
    /**
    * @Description: 查询我的主题
    * @author: 许伟
    * @date: 2018/3/26 0026 14:41
    */
    @GetMapping("myTopic")
    @ApiOperation(value = "我所有的主题帖")
    public ActionResult myTopic(int xh){
        return ResultUtils.success(homeService.myTopic(xh));
    }

    /**
    * @Description: 查询我的回帖
    * @author: 许伟
    * @date: 2018/3/27 0027 15:22
    */
    @ApiOperation(value = "我所有的回帖信息")
    @GetMapping("myReply")
    public ActionResult myReply(int xh){
        return ResultUtils.success(homeService.myReply(xh));
    }
    /**
    * @Description: 我的留言
    * @author: 许伟
    * @date: 2018/3/27 0027 13:35
    */
    @GetMapping("myLy")
    @ApiOperation(value = "我的留言信息")
    public ActionResult myLy(int xh){
        Map result=new HashMap();
        result.put("to",homeService.myLyToOther(xh));
        result.put("from",homeService.myLyFromMe(xh));
        return ResultUtils.success(result);
    }

    /**
    * @Description: 修改交易状态
    * @author: 许伟
    * @date: 2018/3/28 0028 16:35
    */
    @GetMapping("updateState")
    @ApiOperation(value = "修改交易状态")
    public ActionResult updateState(int topic_xh){
        return ResultUtils.success(homeService.updateState(topic_xh));
    }
}
