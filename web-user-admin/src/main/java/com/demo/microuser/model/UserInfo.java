package com.demo.microuser.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@ApiModel(value = "UserInfo", description = "用户")
public class UserInfo {
    @ApiModelProperty("用户id")
    private Long id;
    @ApiModelProperty("真实姓名")
    private String realName;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("角色id")
    private Long roleId;

}
