package com.demo.microuser.service;

import java.util.List;

import com.demo.microuser.model.Info;
import com.demo.microuser.model.UserInfo;

/**
 * 用户服务 
 */
public interface AdminService {
	
	/**
     * 登录
     * @return
     */
    UserInfo login(UserInfo userInfo);
	
}
