package com.xuwei.common;

/**
 * Created by 许伟 on 2018/1/29.
 */
public enum ReturnEnum {

    SUCCESS(1000, "成功"),
    SYSTEMERROR(9999, "系统异常"),
    CUSTOMERROR(2001, "服务异常");

    ReturnEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    //返回代码
    private int code;
    //返回信息
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
