package com.demo.micro.resource.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "InfoType", description = "信息类型")
public class InfoType {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("角色名称")
    private String roleName;
    @ApiModelProperty("角色等级")
    private int level;
}
