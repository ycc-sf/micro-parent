package com.demo.micro.resource.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "CommentPageParams", description = "评论分页参数")
public class CommentPageParams {

	@ApiModelProperty("信息id")
	private Long infoId;
	
	@ApiModelProperty("用户id")
	private Long userId;
	
}
