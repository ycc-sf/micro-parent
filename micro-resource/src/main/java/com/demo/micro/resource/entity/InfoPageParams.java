package com.demo.micro.resource.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "InfoPageParams", description = "信息分页参数")
public class InfoPageParams {

	@ApiModelProperty("标题")
	private String title;
	
	@ApiModelProperty("分类")
	private int infoType;
	
	@ApiModelProperty("信息发布者id")
	private Long userId;
	
}
