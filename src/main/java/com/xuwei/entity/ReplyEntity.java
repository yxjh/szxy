package com.xuwei.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * Created by 许伟 on 2018/3/16 0016.
 */
@Data
@ApiModel(value = "回帖的实体",reference = "回帖")
public class ReplyEntity {
    @ApiParam(value = "回帖序号",required = true)
    private int xh;
    @ApiParam(value = "回帖内容")
    private String htnr;
    @ApiParam(value = "回帖时间")
    private String htsj;
    @ApiParam(value = "用户序号",required = true)
    private int user_xh;
    @ApiParam(value = "主题帖序号",required = true)
    private int topic_xh;

}
