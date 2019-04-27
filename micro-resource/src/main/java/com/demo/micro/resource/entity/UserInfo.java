package com.demo.micro.resource.entity;

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
    @ApiModelProperty("性别（1：保密；2：男；3：女）")
    private Integer gender;
    @ApiModelProperty("手机号")
    private String phonenum;
    @ApiModelProperty("邮箱")
    private String email;

}
