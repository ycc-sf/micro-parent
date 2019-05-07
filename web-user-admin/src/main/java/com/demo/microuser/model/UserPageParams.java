package com.demo.microuser.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "UserPageParams", description = "用户分页参数")
public class UserPageParams {

	@ApiModelProperty("用户名")
	private String username;
	
	@ApiModelProperty("真实姓名")
	private String realName;
	
	@ApiModelProperty("角色id")
	private Long roleId;
	
	@ApiModelProperty("手机号码")
	private String phonenum;
	
}
