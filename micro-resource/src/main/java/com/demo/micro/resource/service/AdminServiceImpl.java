package com.demo.micro.resource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.micro.common.domain.BusinessException;
import com.demo.micro.common.domain.ErrorCode;
import com.demo.micro.common.domain.PageRequestParams;
import com.demo.micro.resource.dao.AdminDao;
import com.demo.micro.resource.dao.ResourceDao;
import com.demo.micro.resource.entity.Info;
import com.demo.micro.resource.entity.Role;
import com.demo.micro.resource.entity.Subscription;
import com.demo.micro.resource.entity.UserInfo;
import com.demo.micro.resource.entity.UserPageParams;
import com.demo.micro.resource.entity.UserRole;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;
	
	public void resetUserPassword(Long userId){
		String pass = "12345";
		int influence = adminDao.updateUserPassword(userId, pass);
		if(influence == 0){
			throw new BusinessException(ErrorCode.E_102002);
		}
	}
	
	public void modifyUserRoleById(Long userId, Long roleId){
		int influence = adminDao.updateUserRoleById(userId, roleId);
		if(influence == 0){
			throw new BusinessException(ErrorCode.E_102002);
		}
	}
	
	public void removeUserById(Long id){
		int influence = adminDao.deleteUserById(id);
		if(influence == 0){
			throw new BusinessException(ErrorCode.E_102002);
		}
	}
	
	public Page<UserRole> pageUser(int pageNo, int pageSize, UserPageParams queryParams){
		//计算分页参数
        PageRequestParams pageRequest = PageRequestParams.of(pageNo, pageSize);
        //条件查询
        List<UserRole> list = adminDao.pageUser(pageRequest, queryParams);
        //查询符合条件的总数
        Long total = adminDao.countUserByConditions(queryParams);
        //封装pageable
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        //封装page
        Page<UserRole> page = new PageImpl<>(list,pageable,total);
        return page;
	}
	
	public List<Role> getAllRole(){
		List<Role> list = adminDao.selectAllRole();
		return list;
	}
}
