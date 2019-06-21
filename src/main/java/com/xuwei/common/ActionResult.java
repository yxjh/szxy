package com.xuwei.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by 许伟 on 2018/1/28.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActionResult {
    private static final long serialVersionUID=1L;

    private int responseCode;
    private String message;
    private Object content;
    private Object tag;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    /**
     * 构造函数
     * @param responseCode 返回值代码
     * @param message 返回信息
     * @param content 返回内容
     * @param tag 附加内容
     */
    public ActionResult(int responseCode, String message, Object content, Object tag) {
        this.responseCode = responseCode;
        this.message = message;
        this.content = content;
        this.tag = tag;
    }
}
