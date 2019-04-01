package com.demo.microuser.model;

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
    @ApiModelProperty("评论详情")
    private String commDetailString;
    @ApiModelProperty("评论详情（Blob）")
    private Object commDetail;
    @ApiModelProperty("时间")
    private Timestamp createDate;
}
