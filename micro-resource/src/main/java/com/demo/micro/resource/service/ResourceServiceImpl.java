package com.demo.micro.resource.service;

import java.sql.Blob;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.micro.common.domain.BusinessException;
import com.demo.micro.common.domain.ErrorCode;
import com.demo.micro.common.domain.PageRequestParams;
import com.demo.micro.resource.dao.ResourceDao;
import com.demo.micro.resource.entity.Comment;
import com.demo.micro.resource.entity.CommentPageParams;
import com.demo.micro.resource.entity.Info;
import com.demo.micro.resource.entity.InfoPageParams;
import com.demo.micro.resource.entity.Role;
import com.demo.micro.resource.entity.Subscription;
import com.demo.micro.resource.entity.SubscriptionPageParams;
import com.demo.micro.resource.entity.UserInfo;
import com.demo.micro.resource.entity.UserRole;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceDao resourceDao;
	
	private static final Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);
	
	
	
	
	public Page<Subscription> pageSubscription(int pageNo, int pageSize, SubscriptionPageParams queryParams){
		//计算分页参数
        PageRequestParams pageRequest = PageRequestParams.of(pageNo, pageSize);
        //条件查询
        List<Subscription> list = resourceDao.pageSubscription(pageRequest, queryParams);
        //查询符合条件的总数
        Long total = resourceDao.countSubscriptionByConditions(queryParams);
        //封装pageable
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        //封装page
        Page<Subscription> page = new PageImpl<>(list,pageable,total);
        return page;
	}
	
	@Override
	public Page<Comment> pageComment(int pageNo, int pageSize, CommentPageParams queryParams) {
		//计算分页参数
        PageRequestParams pageRequest = PageRequestParams.of(pageNo, pageSize);
        //条件查询
        List<Comment> commentList = resourceDao.pageComment(pageRequest, queryParams);
        //查询符合条件的总数
        Long total = resourceDao.countCommentByConditions(queryParams);
        //封装pageable
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        //封装page
        Page<Comment> pageComment = new PageImpl<>(commentList,pageable,total);
        return pageComment;
	}
	
	public void removeComment(Long id){
		int deleteComment = resourceDao.deleteComment(id);
		if(deleteComment == 0){
			throw new BusinessException(ErrorCode.E_102002);
		}
	}
	
	public Long addComment(Comment comment){
		//将详情转为blob格式
		String detail = comment.getCommDetailString();
		Blob detailBlob = null;
		try {
			detailBlob = new SerialBlob(detail.getBytes());
			comment.setCommDetail(detailBlob);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(ErrorCode.E_102001);
		}
		resourceDao.insertComment(comment);
		return comment.getId();
	}
	
	public UserInfo getUserInfo(String username){
        List<UserInfo> users = resourceDao.findUserByUsername(username);
        logger.info("[service-]用户名：{}。获取到用户：{}", username, users);
        if(users == null || users.size() == 0){
            throw new BusinessException(ErrorCode.E_101003);
        }
        if(users.size() > 1){
            throw new BusinessException(ErrorCode.E_101004);
        }
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
	
	public void removeInfoById(Long id){
		int deleteInfoById = resourceDao.deleteInfoById(id);
		if(deleteInfoById == 0){
			throw new BusinessException(ErrorCode.E_102002);
		}
	}
	
	public Info getInfoById(Long id){
		logger.info("[begin]id:{}",id);
		Info info = resourceDao.selectInfoById(id);
		if(info == null){
			throw new BusinessException(ErrorCode.E_102002);
		}
		Object detailBlob = info.getInfoDetail();
		logger.info("[runing]detailBlob:{}", detailBlob);
		if(detailBlob != null){
			String detailString = new String((byte[])detailBlob);
			info.setInfoDetailString(detailString);
			logger.info("[runing]detailString:{}", detailString);
		}
		
		return info;
	}
	
	@Override
	public Long addInfo(Info info) {
		//将详情转为blob格式
		String detail = info.getInfoDetailString();
		Blob detailBlob = null;
		try {
			detailBlob = new SerialBlob(detail.getBytes());
			info.setInfoDetail(detailBlob);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(ErrorCode.E_102001);
		}
		resourceDao.insertInfo(info);
		return info.getId();
	}
	
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
	public Page<Info> pageInfo(int pageNo, int pageSize, InfoPageParams queryParams) {
		//计算分页参数
        PageRequestParams pageRequest = PageRequestParams.of(pageNo, pageSize);
        //条件查询
        List<Info> infoList = resourceDao.pageInfo(pageRequest, queryParams);
        //查询符合条件的总数
        Long total = resourceDao.countInfoByConditions(queryParams);
        //封装pageable
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        //封装page
        Page<Info> pageInfo = new PageImpl<>(infoList,pageable,total);
        return pageInfo;
	}

}
