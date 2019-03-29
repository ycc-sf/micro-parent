package com.demo.microuser.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**  
 * @author 杨小波
 */
@Data
@ApiModel(value = "PageParams", description = "分页参数")
public class PageParams {
    
    @ApiModelProperty("页码")
    private Integer pageNo;
    @ApiModelProperty("单页数量")
    private Integer pageSize;
    
    public PageParams(Integer pageNo, Integer pageSize) {
        super();
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }
    public PageParams() {
        super();
    }
}
