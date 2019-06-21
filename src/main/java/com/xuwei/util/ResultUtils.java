package com.xuwei.util;


import com.xuwei.common.ActionResult;
import com.xuwei.common.ReturnEnum;

/**
 * Created by 许伟 on 2018/1/29.
 * 返回结果工具类
 */
public class ResultUtils {

    /*
     * 返回成功
     */
    public static ActionResult success() {
        return new ActionResult(
                ReturnEnum.SUCCESS.getCode(),
                ReturnEnum.SUCCESS.getMessage(),
                null,
                null
        );
    }

    public static ActionResult success(Object content) {
        return new ActionResult(
                ReturnEnum.SUCCESS.getCode(),
                ReturnEnum.SUCCESS.getMessage(),
                content,
                null
        );
    }

    public static ActionResult success(Object content, Object tag) {
        return new ActionResult(
                ReturnEnum.SUCCESS.getCode(),
                ReturnEnum.SUCCESS.getMessage(),
                content,
                tag);
    }

    /*
     * 系统异常
     */
    public static ActionResult systemError(ReturnEnum systemError) {
        return new ActionResult(
                ReturnEnum.SYSTEMERROR.getCode(),
                ReturnEnum.SYSTEMERROR.getMessage(),
                null,
                null);
    }

    /*
     * 用户异常
     */
    public static ActionResult customError(String message) {
        return new ActionResult(
                ReturnEnum.CUSTOMERROR.getCode(),
                message,
                null,
                null);
    }

}
