package com.demo.micro.resource.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;

import com.demo.micro.common.domain.PageRequestParams;
import com.demo.micro.resource.entity.Role;
import com.demo.micro.resource.entity.UserInfo;
import com.demo.micro.resource.entity.UserPageParams;
import com.demo.micro.resource.entity.UserRole;

/**
 * 资源服务
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
	Page<UserRole> pageUser(int pageNo, int pageSize, UserPageParams queryParams);
	
	/**
	 * 获取所有角色
	 * @return
	 */
	List<Role> getAllRole();
}
