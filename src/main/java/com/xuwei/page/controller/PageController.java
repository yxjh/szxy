package com.xuwei.page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 许伟 on 2018/3/15 0015.
 */
@Controller
@CrossOrigin(origins = "*")
public class PageController {

    /**
    * @Description: 跳转到页面
    * @author: 许伟
    * @date: 2018/3/15 0015 14:24
    */
    @RequestMapping("index")
    public String loginPage(){
        return "index";
    }
}
