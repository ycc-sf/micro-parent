package com.demo.microuser.service;

import org.apache.ibatis.annotations.Param;

import com.demo.microuser.model.Comment;
import com.demo.microuser.model.CommentPageParams;
import com.demo.microuser.model.Info;
import com.demo.microuser.model.InfoPageParams;
import com.demo.microuser.model.Subscription;
import com.demo.microuser.model.SubscriptionPageParams;
import com.demo.microuser.model.UserInfo;

/**
 * 用户服务 
 */
public interface ResourceService {
	
	
	/**
	 * 删除订阅
	 * @param id
	 * @return
	 */
	void removeSubscription(@Param("id") Long id);
	
	/**
	 * 添加订阅
	 * @param subscription
	 * @return
	 */
	Long addSubscription(Subscription subscription);
	
	/**
	 * 分页条件查询订阅
	 * @param pageNo
	 * @param pageSize
	 * @param queryParams
	 * @return
	 */
	Object pageSubscription(int pageNo, int pageSize, SubscriptionPageParams queryParams);
	
	/**
	 * 分页条件查询评论  
	 * @param pageNo
	 * @param pageSize
	 * @param queryParams
	 * @return
	 */
	Object pageComment(int pageNo, int pageSize, CommentPageParams queryParams);
	
	/**
	 * 删除留言
	 * @param id
	 * @return
	 */
	void removeComment(Long id);
	
	/**
	 * 新增留言
	 * @param 
	 * @return
	 */
	Long addComment(Comment comment);
	
	/**
	 * 通过用户名获取用户、角色信息
	 * @param username
	 * @return
	 */
	UserInfo getUserInfo(String username);
	
	/**
	 * 通过id删除信息
	 * @param id
	 * @return
	 */
	void removeInfoById(Long id);
	
	/**
	 * 通过id获取信息
	 * @param id
	 * @return
	 */
	Info getInfoById(Long id);
	
	/**
	 * 新增信息
	 * @param info
	 * @return
	 */
	Object addInfo(Info info);
	/**
	 * 分页条件查询
	 * @param pageNo
	 * @param pageSize
	 * @param queryParams
	 * @return
	 */
	Object pageInfo(int pageNo, int pageSize, InfoPageParams queryParams); 
	
    /**
     * 根据位置获取指定范围内，指定数量的信息
     * @param x
     * @param y
     * @param range
     * @param number
     * @return
     */
	Object findInfoList(Long infoType,String title, Double x, Double y, Double range, Integer number);
    /**
     * 登录
     * @return
     */
    UserInfo login(UserInfo userInfo);
}
