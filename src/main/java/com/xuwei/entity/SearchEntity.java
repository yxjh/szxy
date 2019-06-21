package com.xuwei.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * Created by 许伟 on 2018/3/15 0015.
 */
@Data
@ApiModel(value = "查询条件的实体",reference = "查询")
public class SearchEntity {
    @ApiParam(value = "分页首页")
    private int pageIndex=1;
    @ApiParam(value = "分页大小")
    private int pageSize=7;
    @ApiParam(value = "开始时间")
    private String beginTime;
    @ApiParam(value = "结束时间")
    private String endTime;
    @ApiParam(value = "物品类型")
    private String wplx;
    @ApiParam(value = "关键字")
    private String keys;
}
