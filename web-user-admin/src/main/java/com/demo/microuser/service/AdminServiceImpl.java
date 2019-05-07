package com.demo.microuser.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.micro.common.domain.RestResponse;
import com.demo.microuser.domain.ApplicationException;
import com.demo.microuser.domain.HttpRequestMethedEnum;
import com.demo.microuser.domain.WebErrorCode;
import com.demo.microuser.model.CommentPageParams;
import com.demo.microuser.model.Info;
import com.demo.microuser.model.InfoPageParams;
import com.demo.microuser.model.ReportPageParams;
import com.demo.microuser.model.SubscriptionPageParams;
import com.demo.microuser.model.UserInfo;
import com.demo.microuser.model.UserPageParams;
import com.demo.microuser.model.UserRole;
import com.demo.microuser.util.CommonUtil;
import com.demo.microuser.util.DTOUtil;
import com.demo.microuser.util.HttpClientUtil;

@Service
//@Transactional
public class AdminServiceImpl implements AdminService {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
	
	@Override
	public void resetUserPassword(Long userId){
		String uri = "/admin/resetUserPassword";
		//query参数
		Map<String, Object> urlParams = new HashMap<>();
		urlParams.put("userId", userId);
	    RestResponse restResponse = HttpClientUtil.myReqMethod(uri, HttpRequestMethedEnum.HttpPut, urlParams, null);
	    logger.info("-->{}", restResponse);
	    if(restResponse.getCode() != 0){
	    	throw new ApplicationException(WebErrorCode.CUSTOM, restResponse.getMsg());
	    }
		return ;
	}
	
	@Override
	public void modifyUserRoleById(Long userId, Long roleId){
		String uri = "/admin/modifyUserRoleById";
		//query参数
		Map<String, Object> urlParams = new HashMap<>();
		urlParams.put("userId", userId);
		urlParams.put("roleId", roleId);
	    RestResponse restResponse = HttpClientUtil.myReqMethod(uri, HttpRequestMethedEnum.HttpPut, urlParams, null);
	    logger.info("-->{}", restResponse);
	    if(restResponse.getCode() != 0){
	    	throw new ApplicationException(WebErrorCode.CUSTOM, restResponse.getMsg());
	    }
		return ;
	}
	
	@Override
	public void removeUserById(Long id) {
		String uri = "/admin/removeUserById";
		//query参数
		Map<String, Object> urlParams = new HashMap<>();
		urlParams.put("id", id);
	    RestResponse restResponse = HttpClientUtil.myReqMethod(uri, HttpRequestMethedEnum.HttpDelete, urlParams, null);
	    logger.info("-->{}", restResponse);
	    if(restResponse.getCode() != 0){
	    	throw new ApplicationException(WebErrorCode.CUSTOM, restResponse.getMsg());
	    }
		return;
	}
	
	@Override
	public Object pageUser(int pageNo, int pageSize, UserPageParams queryParams) {
		String uri = "/admin/pageUser";
		//query参数
		Map<String, Object> urlParams = new HashMap<>();
		urlParams.put("pageNo", pageNo);
		urlParams.put("pageSize", pageSize);
	    RestResponse restResponse = HttpClientUtil.myReqMethod(uri, HttpRequestMethedEnum.HttpPost, urlParams, queryParams);
	    logger.info("-->{}", restResponse);
	    if(restResponse.getCode() != 0){
	    	throw new ApplicationException(WebErrorCode.CUSTOM, restResponse.getMsg());
	    }
		return restResponse.getResult();
	}
	
	@Override
	public void updateReportById(Long id){
		String uri = "/admin/updateReportById";
		//query参数
		Map<String, Object> urlParams = new HashMap<>();
		urlParams.put("id", id);
	    RestResponse restResponse = HttpClientUtil.myReqMethod(uri, HttpRequestMethedEnum.HttpPut, urlParams, null);
	    logger.info("-->{}", restResponse);
	    if(restResponse.getCode() != 0){
	    	throw new ApplicationException(WebErrorCode.CUSTOM, restResponse.getMsg());
	    }
		return ;
	}
	
	@Override
	public Object pageReport(int pageNo, int pageSize, ReportPageParams queryParams) {
		String uri = "/admin/pageReport";
		//query参数
		Map<String, Object> urlParams = new HashMap<>();
		urlParams.put("pageNo", pageNo);
		urlParams.put("pageSize", pageSize);
	    RestResponse restResponse = HttpClientUtil.myReqMethod(uri, HttpRequestMethedEnum.HttpPost, urlParams, queryParams);
	    logger.info("-->{}", restResponse);
	    if(restResponse.getCode() != 0){
	    	throw new ApplicationException(WebErrorCode.CUSTOM, restResponse.getMsg());
	    }
		return restResponse.getResult();
	}
	
	@Override
	public void removeSubscriptionById(Long id) {
		String uri = "/admin/removeSubscriptionById";
		//query参数
		Map<String, Object> urlParams = new HashMap<>();
		urlParams.put("id", id);
	    RestResponse restResponse = HttpClientUtil.myReqMethod(uri, HttpRequestMethedEnum.HttpDelete, urlParams, null);
	    logger.info("-->{}", restResponse);
	    if(restResponse.getCode() != 0){
	    	throw new ApplicationException(WebErrorCode.CUSTOM, restResponse.getMsg());
	    }
		return;
	}
	
	@Override
	public Object pageSubscription(int pageNo, int pageSize, SubscriptionPageParams queryParams) {
		String uri = "/admin/pageSubscription";
		//query参数
		Map<String, Object> urlParams = new HashMap<>();
		urlParams.put("pageNo", pageNo);
		urlParams.put("pageSize", pageSize);
	    RestResponse restResponse = HttpClientUtil.myReqMethod(uri, HttpRequestMethedEnum.HttpPost, urlParams, queryParams);
	    logger.info("-->{}", restResponse);
	    if(restResponse.getCode() != 0){
	    	throw new ApplicationException(WebErrorCode.CUSTOM, restResponse.getMsg());
	    }
		return restResponse.getResult();
	}
	
	@Override
	public void removeComment(Long id) {
		String uri = "/admin/removeCommentById";
		//query参数
		Map<String, Object> urlParams = new HashMap<>();
		urlParams.put("id", id);
	    RestResponse restResponse = HttpClientUtil.myReqMethod(uri, HttpRequestMethedEnum.HttpDelete, urlParams, null);
	    logger.info("-->{}", restResponse);
	    if(restResponse.getCode() != 0){
	    	throw new ApplicationException(WebErrorCode.CUSTOM, restResponse.getMsg());
	    }
		return;
	}
	
	@Override
	public Object pageComment(int pageNo, int pageSize, CommentPageParams queryParams) {
		String uri = "/admin/pageComment";
		//query参数
		Map<String, Object> urlParams = new HashMap<>();
		urlParams.put("pageNo", pageNo);
		urlParams.put("pageSize", pageSize);
	    RestResponse restResponse = HttpClientUtil.myReqMethod(uri, HttpRequestMethedEnum.HttpPost, urlParams, queryParams);
	    logger.info("-->{}", restResponse);
	    if(restResponse.getCode() != 0){
	    	throw new ApplicationException(WebErrorCode.CUSTOM, restResponse.getMsg());
	    }
		return restResponse.getResult();
	}
	
	@Override
	public void removeInfoById(Long id) {
		String uri = "/admin/removeInfoById";
		//query参数
		Map<String, Object> urlParams = new HashMap<>();
		urlParams.put("id", id);
	    RestResponse restResponse = HttpClientUtil.myReqMethod(uri, HttpRequestMethedEnum.HttpDelete, urlParams, null);
	    logger.info("-->{}", restResponse);
	    if(restResponse.getCode() != 0){
	    	throw new ApplicationException(WebErrorCode.CUSTOM, restResponse.getMsg());
	    }
		return;
	}
	
	@Override
	public Object getInfoById(Long id) {
		String uri = "/admin/getInfoById";
		//query参数
		Map<String, Object> urlParams = new HashMap<>();
		urlParams.put("id", id);
	    RestResponse restResponse = HttpClientUtil.myReqMethod(uri, HttpRequestMethedEnum.HttpGet, urlParams, null);
	    logger.info("-->{}", restResponse);
	    if(restResponse.getCode() != 0){
	    	throw new ApplicationException(WebErrorCode.CUSTOM, restResponse.getMsg());
	    }
	    
		return restResponse.getResult();
	}

	@Override
	public Object pageInfo(int pageNo, int pageSize, InfoPageParams queryParams) {
		String uri = "/admin/pageInfo";
		//query参数
		Map<String, Object> urlParams = new HashMap<>();
		urlParams.put("pageNo", pageNo);
		urlParams.put("pageSize", pageSize);
	    RestResponse restResponse = HttpClientUtil.myReqMethod(uri, HttpRequestMethedEnum.HttpPost, urlParams, queryParams);
	    logger.info("-->{}", restResponse);
	    if(restResponse.getCode() != 0){
	    	throw new ApplicationException(WebErrorCode.CUSTOM, restResponse.getMsg());
	    }
		return restResponse.getResult();
	}
	
	public Object getAllRole(){
		String uri = "/admin/getAllRole";
	    RestResponse restResponse = HttpClientUtil.myReqMethod(uri, HttpRequestMethedEnum.HttpGet, null, null);
	    logger.info("-->{}", restResponse);
	    if(restResponse.getCode() != 0){
	    	throw new ApplicationException(WebErrorCode.CUSTOM, restResponse.getMsg());
	    }
		return restResponse.getResult();
	}
	
	@Override
	public UserInfo login(UserInfo userInfo) {
		StringBuilder url = new StringBuilder(CommonUtil.GATEWAY_URL + "/resource/login");
		//存储相关的header值
	    Map<String,String> header = new HashMap<String, String>();
		// 相关请求参数
	    Map<String,Object> params = new HashMap<String, Object>();
	    params.put("username", userInfo.getUsername());
	    params.put("password", userInfo.getPassword());
	    String response = HttpClientUtil.sendHttp(HttpRequestMethedEnum.HttpPost,url.toString(), params,header);
	    RestResponse restResponse = DTOUtil.convertJSONStrToObject(response);
	    logger.info("-->{}", restResponse);
	    if(restResponse.getCode() != 0){
	    	throw new ApplicationException(WebErrorCode.CUSTOM, restResponse.getMsg());
	    }
	    UserInfo userRole = JSON.toJavaObject((JSON)restResponse.getResult(), UserRole.class);
	    logger.info("userRole:{}", userRole);
	    //判断是否管理员
	    if(userRole.getRoleId() > 1){
	    	throw new ApplicationException(WebErrorCode.E_130105);
	    }
	    return userRole;
	}

}
