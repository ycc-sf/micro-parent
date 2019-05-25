package com.demo.microuser.service;

import com.demo.microuser.model.CommentPageParams;
import com.demo.microuser.model.InfoPageParams;
import com.demo.microuser.model.ReportPageParams;
import com.demo.microuser.model.SubscriptionPageParams;
import com.demo.microuser.model.UserInfo;
import com.demo.microuser.model.UserPageParams;

/**
 * 用户服务 
 */
public interface AdminService {
	
	/**
	 * 修改用户密码
	 * @param userId
	 * @param password
	 * @return
	 */
	void resetUserPassword(Long userId);
	
	/**
	 * 修改用户角色
	 * @param id
	 * @param role
	 * @return
	 */
	void modifyUserRoleById(Long userId, Long roleId);
	
	/**
	 * 通过id删除用户
	 * @param id
	 * @return
	 */
	void removeUserById(Long id);
	
	/**
	 * 用户信息分页
	 * @param pageParams
	 * @param queryParams
	 * @return
	 */
	Object pageUser(int pageNo, int pageSize, UserPageParams queryParams);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Object getInfoById(Long id);
	
	/**
	 * 信息分页
	 * @param pageNo
	 * @param pageSize
	 * @param queryParams
	 * @return
	 */
	Object pageInfo(int pageNo, int pageSize, InfoPageParams queryParams);
	
	/**
	 * 获取所有角色
	 * @return
	 */
	Object getAllRole();
	
	/**
     * 登录
     * @return
     */
    UserInfo login(UserInfo userInfo);

	void removeInfoById(Long id);

	Object pageComment(int pageNo, int pageSize, CommentPageParams queryParams);

	void removeComment(Long id);

	Object pageSubscription(int pageNo, int pageSize, SubscriptionPageParams queryParams);

	void removeSubscriptionById(Long id);

	Object pageReport(int pageNo, int pageSize, ReportPageParams queryParams);

	void updateReportById(Long id);

}
