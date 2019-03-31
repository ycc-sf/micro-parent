package com.demo.micro.resource.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "SubscriptionPageParams", description = "订阅分页参数")
public class SubscriptionPageParams {

	@ApiModelProperty("信息id")
	private Long infoId;
	
	@ApiModelProperty("用户id")
	private Long userId;
	
	@ApiModelProperty("订阅状态(1:订阅中；2：已完成)")
	private int status;
	
}
