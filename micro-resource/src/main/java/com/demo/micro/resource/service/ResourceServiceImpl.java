package com.demo.micro.resource.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.micro.common.domain.BusinessException;
import com.demo.micro.common.domain.ErrorCode;
import com.demo.micro.resource.dao.ResourceDao;
import com.demo.micro.resource.entity.Info;
import com.demo.micro.resource.entity.InfoPageParams;
import com.demo.micro.resource.entity.Role;
import com.demo.micro.resource.entity.UserInfo;
import com.demo.micro.resource.entity.UserRole;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceDao resourceDao;
	
	private static final Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);
	
	@Override
    public List<Info> findInfoList(Long infoType, String title, double x, double y, double range, int number) {
        List<Info> infoList = resourceDao.selectRangeInfo(infoType, title, x, y, range, number);
        return infoList;
    }
	
	public UserRole login(UserInfo userInfo) {
        logger.info("[service-begin]登录：{}", userInfo);
        if(userInfo == null){
            throw new BusinessException(ErrorCode.E_101002);
        }
        if(StringUtils.isBlank(userInfo.getUsername())){
            throw new BusinessException(ErrorCode.E_101002);
        }
        List<UserInfo> users = resourceDao.findUserByUsername(userInfo.getUsername());
        logger.info("[service-]用户名：{}。获取到用户：{}", userInfo.getUsername(), users);
        if(users == null || users.size() == 0){
            throw new BusinessException(ErrorCode.E_101003);
        }
        if(users.size() > 1){
            throw new BusinessException(ErrorCode.E_101004);
        }
        //校验密码
        if(!users.get(0).getPassword().equals(userInfo.getPassword())){
            throw new BusinessException(ErrorCode.E_101007);
        }
        logger.info("[service-]用户名密码校验通过。{}", users.get(0));
        //获取角色
        Role role = resourceDao.findRoleById(users.get(0).getRoleId());
        logger.info("[service-]获取角色。{}", role);
        if(role == null){
            throw new BusinessException(ErrorCode.E_101005);
        }
        UserRole userRole = new UserRole();
        userRole.setId(users.get(0).getId());
        userRole.setUsername(users.get(0).getUsername());
        userRole.setRealName(users.get(0).getRealName());
        userRole.setRoleId(users.get(0).getRoleId());
        userRole.setRoleName(role.getRoleName());
        userRole.setLevel(role.getLevel());
        logger.info("[service-end]登录。{}", userRole);
        return userRole;
    }

	@Override
	public List<Info> pageInfo(int pageNo, int pageSize, InfoPageParams queryParams) {
		// TODO Auto-generated method stub
		return null;
	}

}
