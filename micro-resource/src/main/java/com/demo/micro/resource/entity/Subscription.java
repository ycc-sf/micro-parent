package com.demo.micro.resource.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel(value = "Subscription", description = "订阅")
public class Subscription {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("用户姓名")
    private String realName;
    @ApiModelProperty("信息id")
    private Long infoId;
    @ApiModelProperty("信息标题")
    private String infoTitle;
    @ApiModelProperty("1:订阅中；2：已完成")
    private int status;
    @ApiModelProperty("时间")
    private Timestamp createDate;
}
