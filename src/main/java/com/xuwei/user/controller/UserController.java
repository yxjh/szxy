package com.xuwei.user.controller;

import com.xuwei.common.ActionResult;
import com.xuwei.entity.UserEntity;
import com.xuwei.user.service.UserService;
import com.xuwei.util.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 许伟 on 2018/3/21 0021.
 */
@Api(tags = "登陆模块")
@RestController
@RequestMapping("user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;
    /**
    * @Description: 登陆
    * @author: 许伟
    * @date: 2018/3/21 0021 10:56
    */
    @GetMapping("login")
    @ApiOperation(value = "登陆功能")
    public ActionResult login(UserEntity userEntity){
        return ResultUtils.success(userService.login(userEntity));
    }

    /**
    * @Description: 用户注册
    * @author: 许伟
    * @date: 2018/3/21 0021 11:08
    */
    @GetMapping("register")
    @ApiOperation(value = "注册功能")
    public ActionResult register(UserEntity userEntity){
        UserEntity entity=userService.register(userEntity);
        return ResultUtils.success(entity);
    }

}
