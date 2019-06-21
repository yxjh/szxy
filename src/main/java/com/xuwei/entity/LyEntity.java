package com.xuwei.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.Valid;

/**
 * Created by 许伟 on 2018/3/28 0028.
 */
@Data
@ApiModel(value = "留言的实体",reference = "ly")
public class LyEntity {

    @ApiParam(value = "序号",required = true)
    private int xh;
    @ApiParam(value = "留言内容")
    private String lynr;
    @ApiParam(value = "留言时间")
    private String lysj;
    @ApiParam(value = "用户序号",required = true)
    private int user_xh;
    @ApiParam(value = "物品序号",required = true)
    private int wp_xh;
}
