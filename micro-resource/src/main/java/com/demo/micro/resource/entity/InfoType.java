package com.demo.micro.resource.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "InfoType", description = "信息类型")
public class InfoType {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("类型名称")
    private String typeName;
}
