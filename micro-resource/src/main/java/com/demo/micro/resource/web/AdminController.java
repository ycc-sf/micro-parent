package com.demo.micro.resource.web;

import java.util.List;

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
import com.demo.micro.resource.entity.Comment;
import com.demo.micro.resource.entity.CommentPageParams;
import com.demo.micro.resource.entity.Info;
import com.demo.micro.resource.entity.InfoPageParams;
import com.demo.micro.resource.entity.Report;
import com.demo.micro.resource.entity.ReportPageParams;
import com.demo.micro.resource.entity.Role;
import com.demo.micro.resource.entity.Subscription;
import com.demo.micro.resource.entity.SubscriptionPageParams;
import com.demo.micro.resource.entity.UserPageParams;
import com.demo.micro.resource.entity.UserRole;
import com.demo.micro.resource.service.AdminService;
import com.demo.micro.resource.service.ResourceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/admin")
@Api(value = "后台", tags = "AdminApi", description="后台支持api")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ResourceService resourceService;
	
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
    public RestResponse<Page<UserRole>> pageUser(@RequestBody UserPageParams queryParams
			,@RequestParam Integer pageNo, @RequestParam Integer pageSize){
        logger.info("[begin]pageNo:{} pageSize:{} params:{}",pageNo, pageSize, queryParams);
        Page<UserRole> page = adminService.pageUser(pageNo, pageSize, queryParams);
        logger.info("[end]成功。{}", page);
        return RestResponse.success(page);
    }
	
	@ApiOperation("获取所有角色")
	@ApiImplicitParams({
	})
	@GetMapping("/getAllRole")
	public RestResponse<List<Role>> getAllRole(){
		logger.info("[begin]");
        List<Role> list = adminService.getAllRole();
        logger.info("[end]结果。{}", list);
        return RestResponse.success(list);
	}
	
	@ApiOperation("根据id修改举报状态为解决")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value="举报id", required=true, dataType="Long", paramType="query")
	})
	@PutMapping("/updateReportById")
	public RestResponse<Nullable> updateReportById(@RequestParam Long id){
		logger.info("[begin]{}" , id);
        resourceService.updateReport(id);
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
    public RestResponse<Page<Report>> pageReport(@RequestBody ReportPageParams queryParams
			,@RequestParam Integer pageNo, @RequestParam Integer pageSize){
        logger.info("[begin]pageNo:{} pageSize:{} params:{}",pageNo, pageSize, queryParams);
        Page<Report> page = resourceService.pageReport(pageNo, pageSize, queryParams);
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
        resourceService.removeSubscription(id);
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
    public RestResponse<Page<Subscription>> pageSubscription(@RequestBody SubscriptionPageParams queryParams
			,@RequestParam Integer pageNo, @RequestParam Integer pageSize){
        logger.info("[begin]pageNo:{} pageSize:{} params:{}",pageNo, pageSize, queryParams);
        Page<Subscription> list = resourceService.pageSubscription(pageNo, pageSize, queryParams);
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
        resourceService.removeComment(id);
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
    public RestResponse<Page<Comment>> pageComment(@RequestBody CommentPageParams queryParams
			,@RequestParam Integer pageNo, @RequestParam Integer pageSize){
        logger.info("[begin]pageNo:{} pageSize:{} params:{}",pageNo, pageSize, queryParams);
        Page<Comment> list = resourceService.pageComment(pageNo, pageSize, queryParams);
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
        resourceService.removeInfoById(id);
        logger.info("[end]结果。");
        return RestResponse.success();
	}
	
	@ApiOperation("通过id查询信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value="信息id", required=true, dataType="Long", paramType="query")
	})
	@GetMapping("/getInfoById")
	public RestResponse<Info> getInfoById(@RequestParam Long id){
		logger.info("[begin]参数:{}",id);
        Info info = resourceService.getInfoById(id);
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
    public RestResponse<Page<Info>> pageInfo(@RequestBody InfoPageParams queryParams
			,@RequestParam Integer pageNo, @RequestParam Integer pageSize){
        logger.info("[begin]pageNo:{} pageSize:{} params:{}",pageNo, pageSize, queryParams);
        Page<Info> infoList = resourceService.pageInfo(pageNo, pageSize, queryParams);
        logger.info("[end]成功。{}", infoList);
        return RestResponse.success(infoList);
    }
	
	@ApiOperation("hi")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "str", value = "str", required = false, dataType = "String", paramType="query")
	})
	@GetMapping(value = "/hi")
	public RestResponse<Nullable> pageApplicationByConditions(@RequestParam String str){
		System.out.println("Hi! this is resourceServer." + str);
		return RestResponse.success();
	}

}
