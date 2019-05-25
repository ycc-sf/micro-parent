package com.demo.microuser.web;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.micro.common.domain.RestResponse;
import com.demo.microuser.model.CommentPageParams;
import com.demo.microuser.model.InfoPageParams;
import com.demo.microuser.model.ReportPageParams;
import com.demo.microuser.model.SubscriptionPageParams;
import com.demo.microuser.model.UserInfo;
import com.demo.microuser.model.UserPageParams;
import com.demo.microuser.service.AdminService;
import com.demo.microuser.service.ResourceService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
	
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    
    @Autowired
    private AdminService adminService;
    
	@ApiOperation("重置用户密码为（12345）")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value="用户id", required=true, dataType="Long", paramType="query")
	})
	@PutMapping("/resetUserPassword")
	public RestResponse<Nullable> resetUserPassword(@RequestParam Long userId){
		logger.info("[begin]参数:userId:{}",userId);
        adminService.resetUserPassword(userId);
        logger.info("[end]结果。");
        return RestResponse.success();
	}
	
	@ApiOperation("修改用户角色")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value="用户id", required=true, dataType="Long", paramType="query")
		,@ApiImplicitParam(name = "roleId", value="角色id", required=true, dataType="Long", paramType="query")
	})
	@PutMapping("/modifyUserRoleById")
	public RestResponse<Nullable> modifyUserRoleById(@RequestParam Long userId, @RequestParam Long roleId){
		logger.info("[begin]参数:userId:{}, roleId:{}",userId, roleId);
        adminService.modifyUserRoleById(userId, roleId);
        logger.info("[end]结果。");
        return RestResponse.success();
	}
	
	@ApiOperation("通过id删除用户")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value="用户id", required=true, dataType="Long", paramType="query")
	})
	@DeleteMapping("/removeUserById")
	public RestResponse<Nullable> removeUserById(@RequestParam Long id){
		logger.info("[begin]参数:{}",id);
        adminService.removeUserById(id);
        logger.info("[end]结果。");
        return RestResponse.success();
	}
	
	@ApiOperation(value="分页条件查询用户")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "pageNo", value  = "请求页码", required = true, dataType = "int", paramType="query"),
		@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = true, dataType = "int", paramType="query"),
		@ApiImplicitParam(name = "queryParams", value = "查询条件", required = false, dataTypeClass = UserPageParams.class, paramType = "body")
    })
    @PostMapping(value = "/pageUser")
    public RestResponse<Object> pageUser(@RequestBody UserPageParams queryParams
    		,@RequestParam(name="pageNo", required=false, defaultValue="1") Integer pageNo, @RequestParam(name="pageSize", required=false, defaultValue="20") Integer pageSize){
        logger.info("[begin]pageNo:{} pageSize:{} params:{}",pageNo, pageSize, queryParams);
        Object page = adminService.pageUser(pageNo, pageSize, queryParams);
        logger.info("[end]成功。{}", page);
        return RestResponse.success(page);
    }
	
	@ApiOperation("根据id修改举报状态为解决")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value="举报id", required=true, dataType="Long", paramType="query")
	})
	@PutMapping("/updateReportById")
	public RestResponse<Nullable> updateReportById(@RequestParam Long id){
		logger.info("[begin]{}" , id);
        adminService.updateReportById(id);
        logger.info("[end]结果。");
        return RestResponse.success();
	}
	
	@ApiOperation(value="分页条件查询举报")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "pageNo", value  = "请求页码", required = true, dataType = "int", paramType="query"),
		@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = true, dataType = "int", paramType="query"),
		@ApiImplicitParam(name = "queryParams", value = "查询条件", required = false, dataTypeClass = ReportPageParams.class, paramType = "body")
    })
    @PostMapping(value = "/pageReport")
    public RestResponse<Object> pageReport(@RequestBody ReportPageParams queryParams
    		,@RequestParam(name="pageNo", required=false, defaultValue="1") Integer pageNo, @RequestParam(name="pageSize", required=false, defaultValue="20") Integer pageSize){
        logger.info("[begin]pageNo:{} pageSize:{} params:{}",pageNo, pageSize, queryParams);
        Object page = adminService.pageReport(pageNo, pageSize, queryParams);
        logger.info("[end]成功。{}", page);
        return RestResponse.success(page);
    }
	
	@ApiOperation("通过id删除订阅")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value="订阅id", required=true, dataType="Long", paramType="query")
	})
	@DeleteMapping("/removeSubscriptionById")
	public RestResponse<Nullable> removeSubscriptionById(@RequestParam Long id){
		logger.info("[begin]参数:{}",id);
        adminService.removeSubscriptionById(id);
        logger.info("[end]结果。");
        return RestResponse.success();
	}	
	
	@ApiOperation(value="分页条件查询订阅")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "pageNo", value = "请求页码", required = true, dataType = "int", paramType="query"),
		@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = true, dataType = "int", paramType="query"),
		@ApiImplicitParam(name = "queryParams", value = "查询条件（userId、infoId、status）", required = false, dataTypeClass = SubscriptionPageParams.class, paramType = "body")
    })
    @PostMapping(value = "/pageSubscription")
    public RestResponse<Object> pageSubscription(@RequestBody SubscriptionPageParams queryParams
    		,@RequestParam(name="pageNo", required=false, defaultValue="1") Integer pageNo, @RequestParam(name="pageSize", required=false, defaultValue="20") Integer pageSize){
        logger.info("[begin]pageNo:{} pageSize:{} params:{}",pageNo, pageSize, queryParams);
        Object list = adminService.pageSubscription(pageNo, pageSize, queryParams);
        logger.info("[end]成功。{}", list);
        return RestResponse.success(list);
    }

	@ApiOperation("通过id删除评论")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value="评论id", required=true, dataType="Long", paramType="query")
	})
	@DeleteMapping("/removeCommentById")
	public RestResponse<Nullable> removeCommentById(@RequestParam Long id){
		logger.info("[begin]参数:{}",id);
        adminService.removeComment(id);
        logger.info("[end]结果。");
        return RestResponse.success();
	}
	
	@ApiOperation(value="分页条件查询评论")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "pageNo", value = "请求页码", required = true, dataType = "int", paramType="query"),
		@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = true, dataType = "int", paramType="query"),
		@ApiImplicitParam(name = "queryParams", value = "查询条件（userId和infoId）", required = false, dataTypeClass = CommentPageParams.class, paramType = "body")
    })
    @PostMapping(value = "/pageComment")
    public RestResponse<Object> pageComment(@RequestBody CommentPageParams queryParams
    		,@RequestParam(name="pageNo", required=false, defaultValue="1") Integer pageNo, @RequestParam(name="pageSize", required=false, defaultValue="20") Integer pageSize){
        logger.info("[begin]pageNo:{} pageSize:{} params:{}",pageNo, pageSize, queryParams);
        Object list = adminService.pageComment(pageNo, pageSize, queryParams);
        logger.info("[end]成功。{}", list);
        return RestResponse.success(list);
    }
	
	@ApiOperation("通过id删除信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value="信息id", required=true, dataType="Long", paramType="query")
	})
	@DeleteMapping("/removeInfoById")
	public RestResponse<Nullable> removeInfoById(@RequestParam Long id){
		logger.info("[begin]参数:{}",id);
        adminService.removeInfoById(id);
        logger.info("[end]结果。");
        return RestResponse.success();
	}
	
	@ApiOperation("通过id查询信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value="信息id", required=true, dataType="Long", paramType="query")
	})
	@GetMapping("/getInfoById")
	public RestResponse<Object> getInfoById(@RequestParam Long id){
		logger.info("[begin]参数:{}",id);
        Object info = adminService.getInfoById(id);
        logger.info("[end]结果。{}", info);
        return RestResponse.success(info);
	}
	
	@ApiOperation(value="分页条件查询信息")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "pageNo", value = "请求页码", required = true, dataType = "int", paramType="query"),
		@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = true, dataType = "int", paramType="query"),
		@ApiImplicitParam(name = "queryParams", value = "查询条件（title和infoType）", required = false, dataTypeClass = InfoPageParams.class, paramType = "body")
    })
    @PostMapping(value = "/pageInfo")
    public RestResponse<Object> pageInfo(@RequestBody InfoPageParams queryParams
    		,@RequestParam(name="pageNo", required=false, defaultValue="1") Integer pageNo, @RequestParam(name="pageSize", required=false, defaultValue="20") Integer pageSize){
        logger.info("[begin]pageNo:{} pageSize:{} params:{}",pageNo, pageSize, queryParams);
        Object infoList = adminService.pageInfo(pageNo, pageSize, queryParams);
        logger.info("[end]成功。{}", infoList);
        return RestResponse.success(infoList);
    }
	
	@ApiOperation("获取所有角色")
	@ApiImplicitParams({
	})
	@GetMapping("/getAllRole")
	public RestResponse<Object> getAllRole(){
		logger.info("[begin]");
        Object list = adminService.getAllRole();
        logger.info("[end]结果。{}", list);
        return RestResponse.success(list);
	}
    
    @ApiOperation(value="登录")
    @ApiImplicitParam(name = "userInfo", value = "用户名/密码", required = true, dataTypeClass = UserInfo.class, paramType="body")
    @PostMapping(value = "/login")
    public RestResponse<Object> login(@RequestBody UserInfo userInfo, HttpServletResponse response,
                                        HttpServletRequest request, HttpSession httpSession){
        logger.info("[web-begin]登录：{}", userInfo);
        UserInfo user = adminService.login(userInfo);
        httpSession.setAttribute("userInfo", user);
        Cookie cookie = new Cookie("realName", user.getRealName());
        cookie.setPath("/");
        response.addCookie(cookie);
        logger.info("[web-end]登陆成功。{}", user);
        return RestResponse.success(user);
    }
    
	@ApiOperation("hi")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "str", value = "str", required = false, dataType = "String", paramType="query")
	})
	@GetMapping(value = "/hi")
	public RestResponse<Nullable> pageApplicationByConditions(@RequestParam String str){
		System.out.println("Hi! This is web-user." + str);
		return RestResponse.success();
	}
	
}
