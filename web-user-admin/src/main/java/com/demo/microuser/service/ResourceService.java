package com.demo.microuser.service;

import com.demo.microuser.model.UserInfo;
import com.demo.microuser.model.UserRole;

/**
 * 用户服务 
 */
public interface ResourceService {
	
	public Object findInfoList(Long infoType,String title, Double x, Double y, Double range, Integer number);
    /**
     * 登录
     * @return
     */
    UserInfo login(UserInfo userInfo);
}
