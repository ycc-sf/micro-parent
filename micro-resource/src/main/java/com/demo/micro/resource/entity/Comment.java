package com.demo.micro.resource.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel(value = "Comment", description = "评论")
public class Comment {
    @ApiModelProperty("id" )
    private Long id;
    @ApiModelProperty("对应info的id")
    private Long infoId;
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("用户真实姓名")
    private String realName;
    @ApiModelProperty("评论详情")
    private String commDetailString;
    @ApiModelProperty("评论详情（Blob）")
    private Object commDetail;
    @ApiModelProperty("时间")
    private Timestamp createDate;
}
