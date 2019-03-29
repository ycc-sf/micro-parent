package com.demo.micro.resource.service;

import java.util.List;

import com.demo.micro.resource.entity.Info;
import com.demo.micro.resource.entity.InfoPageParams;
import com.demo.micro.resource.entity.UserInfo;
import com.demo.micro.resource.entity.UserRole;

/**
 * 资源服务
 */
public interface ResourceService {
	/**
	 * 分页条件查询
	 * @param pageNo
	 * @param pageSize
	 * @param queryParams
	 * @return
	 */
	public List<Info> pageInfo(int pageNo, int pageSize, InfoPageParams queryParams); 
	
    /**
     * 根据位置获取指定范围内，指定数量的信息
     * @param x
     * @param y
     * @param range
     * @param number
     * @return
     */
    public List<Info> findInfoList(Long infoType, String title, double x, double y, double range, int number);
    
    /**
     * 登录
     * @return
     */
    UserRole login(UserInfo userInfo);
}
