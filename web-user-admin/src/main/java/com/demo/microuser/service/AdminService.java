package com.demo.microuser.service;

import java.util.List;

import com.demo.microuser.model.Info;

/**
 * 用户服务 
 */
public interface AdminService {
	public Object findInfoList(Long infoType,String title, Double x, Double y, Double range, Integer number);
}
