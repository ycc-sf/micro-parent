package com.demo.microuser.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel(value = "Report", description = "举报")
public class Report {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("举报人id")
    private Long userId;
    @ApiModelProperty("举报详情")
    private String reportDetail;
    @ApiModelProperty("信息id")
    private int infoId;
    @ApiModelProperty("时间")
    private Timestamp createDate;
    @ApiModelProperty("是否处理.0:未处理，1：已处理")
    private int isDeal;
}
