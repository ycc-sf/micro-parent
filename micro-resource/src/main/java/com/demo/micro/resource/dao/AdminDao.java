package com.demo.micro.resource.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.demo.micro.common.domain.PageRequestParams;
import com.demo.micro.resource.entity.Role;
import com.demo.micro.resource.entity.UserPageParams;
import com.demo.micro.resource.entity.UserRole;

public interface AdminDao {
	
	/**
	 * 修改用户密码
	 * @param userId
	 * @param password
	 * @return
	 */
	int updateUserPassword(@Param("userId") Long userId, @Param("password") String password );
	
	/**
	 * 修改用户角色
	 * @param id
	 * @param role
	 * @return
	 */
	int updateUserRoleById(@Param("userId") Long userId, @Param("roleId") Long roleId);
	
	/**
	 * 通过id删除用户
	 * @param id
	 * @return
	 */
	int deleteUserById(Long id);
	
	/**
	 * 用户信息分页总条数查询
	 * @param params
	 * @return
	 */
	Long countUserByConditions(@Param("queryParams") UserPageParams params);
	
	/**
	 * 用户信息分页
	 * @param pageParams
	 * @param queryParams
	 * @return
	 */
	List<UserRole> pageUser(@Param("pageParams") PageRequestParams pageParams, @Param("queryParams") UserPageParams queryParams);
	
	/**
	 * 获取所有角色
	 * @return
	 */
	List<Role> selectAllRole();
}
