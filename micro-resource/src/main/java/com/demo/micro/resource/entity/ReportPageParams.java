package com.demo.micro.resource.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel(value = "ReportPageParams", description = "举报查询条件")
public class ReportPageParams {
	@ApiModelProperty("信息id")
	private int infoId;
	@ApiModelProperty("用户id")
	private long userId;
    @ApiModelProperty("举报详情")
    private String reportDetail;
    @ApiModelProperty("文章标题")
    private String infoTitle;
    @ApiModelProperty("时间")
    private Timestamp createDate;
    @ApiModelProperty("是否处理.0:未处理，1：已处理")
    private int isDeal;
}
