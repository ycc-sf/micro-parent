package com.demo.micro.resource.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;

import com.demo.micro.resource.entity.Comment;
import com.demo.micro.resource.entity.CommentPageParams;
import com.demo.micro.resource.entity.Info;
import com.demo.micro.resource.entity.InfoPageParams;
import com.demo.micro.resource.entity.InfoType;
import com.demo.micro.resource.entity.Subscription;
import com.demo.micro.resource.entity.SubscriptionPageParams;
import com.demo.micro.resource.entity.UserInfo;
import com.demo.micro.resource.entity.UserRole;

/**
 * 资源服务
 */
public interface ResourceService {
	
	
	/**
     * 修改用户信息
     * @param user
     * @return
     */
    void updateUserById(UserInfo user);
	
	/**
	 * 获取所有信息类型
	 * @return
	 */
	List<InfoType> getInfoType();
	
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
	Page<Subscription> pageSubscription(int pageNo, int pageSize, SubscriptionPageParams queryParams);
	
	/**
	 * 分页条件查询评论  
	 * @param pageNo
	 * @param pageSize
	 * @param queryParams
	 * @return
	 */
	Page<Comment> pageComment(int pageNo, int pageSize, CommentPageParams queryParams);
	
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
	Long addInfo(Info info);
	/**
	 * 分页条件查询
	 * @param pageNo
	 * @param pageSize
	 * @param queryParams
	 * @return
	 */
	public Page<Info> pageInfo(int pageNo, int pageSize, InfoPageParams queryParams); 
	
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
