package com.xuwei.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * Created by 许伟 on 2018/3/16 0016.
 */
@Data
@ApiModel(value = "主题帖的实体",reference = "主题帖")
public class TopicEntity {
    @ApiParam(value = "主题帖序号",required = true)
    private int xh;
    @ApiParam(value = "发帖内容")
    private String ftnr;
    @ApiParam(value = "发帖时间")
    private String ftsj;
    @ApiParam(value = "用户序号",required = true)
    private int user_xh;
}
