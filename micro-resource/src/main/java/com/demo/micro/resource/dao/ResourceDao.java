package com.demo.micro.resource.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.demo.micro.common.domain.PageRequestParams;
import com.demo.micro.resource.entity.Comment;
import com.demo.micro.resource.entity.CommentPageParams;
import com.demo.micro.resource.entity.Info;
import com.demo.micro.resource.entity.InfoPageParams;
import com.demo.micro.resource.entity.InfoType;
import com.demo.micro.resource.entity.Report;
import com.demo.micro.resource.entity.ReportPageParams;
import com.demo.micro.resource.entity.Role;
import com.demo.micro.resource.entity.Subscription;
import com.demo.micro.resource.entity.SubscriptionPageParams;
import com.demo.micro.resource.entity.UserInfo;

public interface ResourceDao {
	
	/**
	 * 修改举报状态为解决
	 * @param id
	 * @return
	 */
	int updateReport(@Param("id") Long id);
	
	/**
	 * 添加举报
	 * @param Report
	 * @return
	 */
	int insertReport(Report report);
	
	/**
	 * 举报分页的数据总数
	 * @param params
	 * @return
	 */
	Long countReportByConditions(@Param("queryParams") ReportPageParams params);
	
	/**
	 * 举报分页
	 * @param pageParams
	 * @param queryParams
	 * @return
	 */
	List<Report> pageReport(@Param("pageParams") PageRequestParams pageParams, @Param("queryParams") ReportPageParams queryParams);  
	
	
	/**
	 * 获取所有信息类型
	 * @return
	 */
	List<InfoType> selectInfoType();
	
	/**
	 * 删除订阅
	 * @param id
	 * @return
	 */
	int deleteSubscription(@Param("id") Long id);
	
	/**
	 * 添加订阅
	 * @param subscription
	 * @return
	 */
	int insertSubscription(Subscription subscription);
	
	/**
	 * 信息分页的数据总数
	 * @param params
	 * @return
	 */
	Long countSubscriptionByConditions(@Param("queryParams") SubscriptionPageParams params);
	
	/**
	 * 信息分页
	 * @param pageParams
	 * @param queryParams
	 * @return
	 */
	List<Subscription> pageSubscription(@Param("pageParams") PageRequestParams pageParams, @Param("queryParams") SubscriptionPageParams queryParams);  
	
	/**
	 * 信息分页的数据总数
	 * @param params
	 * @return
	 */
	Long countCommentByConditions(@Param("queryParams") CommentPageParams params);
	
	/**
	 * 信息分页
	 * @param pageParams
	 * @param queryParams
	 * @return
	 */
	List<Comment> pageComment(@Param("pageParams") PageRequestParams pageParams, @Param("queryParams") CommentPageParams queryParams);
	
	/**
	 * 删除留言
	 * @param id
	 * @return
	 */
	int deleteComment(Long id);
	
	/**
	 * 新增留言
	 * @param 
	 * @return
	 */
	int insertComment(Comment comment);

	/**
	 * 通过id删除信息
	 * @param id
	 * @return
	 */
	int deleteInfoById(@Param("id") Long id);
	
	/**
	 * 通过id获取信息
	 * @param id
	 * @return
	 */
	Info selectInfoById(Long id);
	
	/**
	 * 新增信息
	 * @param info
	 * @return
	 */
	int insertInfo(Info info);
	
	/**
	 * 信息分页的数据总数
	 * @param params
	 * @return
	 */
	Long countInfoByConditions(@Param("queryParams") InfoPageParams params);
	
	/**
	 * 信息分页
	 * @param pageParams
	 * @param queryParams
	 * @return
	 */
	List<Info> pageInfo(@Param("pageParams") PageRequestParams pageParams, @Param("queryParams") InfoPageParams queryParams);
	
    /**
     * 根据位置获取指定范围内，指定数量的信息
     * @param x
     * @param y
     * @param range
     * @param number
     * @return
     */
    List<Info> selectRangeInfo(@Param("infoType") Long infoType, @Param("title") String title,
                               @Param("x") double x, @Param("y") double y,
                               @Param("range") double range, @Param("number") int number);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    int updateUserById(UserInfo user);
    
    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    List<UserInfo> findUserByUsername(@Param("username") String username);
    
    /**
     * 通过角色id查找角色
     * @param id
     * @return
     */
    Role findRoleById(@Param("id") Long id);
}
