package com.xuwei.common;

import com.xuwei.util.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 许伟 on 2018/1/29.
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ActionResult customerErrorHandler(HttpServletRequest req, Exception e) {
        e.printStackTrace();
        // 记录日志信息
        logger.error(String.format("系统错误,%s,%s", e.getMessage(), req.getServletPath()));
        // 返回通用数据结构
        return ResultUtils.systemError(ReturnEnum.SYSTEMERROR);
    }

}
