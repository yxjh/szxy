package com.xuwei.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * Created by 许伟 on 2018/3/21 0021.
 */
@Data
@ApiModel(value = "User的实体",reference = "user")
public class UserEntity {

    @ApiParam(value = "序号",required = true)
    private int xh;
    @ApiParam(value = "姓名")
    private String name;
    @ApiParam(value = "密码")
    private String password;
    @ApiParam(value = "邮箱")
    private String email;
    @ApiParam(value = "生日")
    private String birthday;
    @ApiParam(value = "昵称")
    private String nc;
    @ApiParam(value = "发布路径")
    private String url;


}
