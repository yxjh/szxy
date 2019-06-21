package com.xuwei.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * Created by 许伟 on 2018/3/26 0026.
 */
@Data
@ApiModel(value = "闲置物品的实体",reference = "闲置物品")
public class ProductEntity {
    @ApiParam(value = "闲置物品序号",required = true)
    private int xh;
    @ApiParam(value = "上传物品名")
    private String scwp;
    @ApiParam(value = "物品描述")
    private String wpms;
    @ApiParam(value = "物品类型")
    private String wplx;
    @ApiParam(value = "意向交换物品")
    private String yxjhwp;
    @ApiParam(value = "上传时间")
    private String scsj;
    @ApiParam(value = "用户序号",required = true)
    private int user_xh;
    @ApiParam(value = "发布路径")
    private String url;
    @ApiParam(value = "上传地点")
    private String scdd;
    @ApiParam(value = "备注")
    private String bz;
    @ApiParam(value = "交易状态")
    private String jyzt;
}
