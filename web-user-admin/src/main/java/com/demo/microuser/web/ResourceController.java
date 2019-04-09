package com.demo.microuser.web;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.micro.common.domain.RestResponse;
import com.demo.microuser.domain.HttpRequestMethedEnum;
import com.demo.microuser.model.Comment;
import com.demo.microuser.model.CommentPageParams;
import com.demo.microuser.model.Info;
import com.demo.microuser.model.InfoPageParams;
import com.demo.microuser.model.Subscription;
import com.demo.microuser.model.SubscriptionPageParams;
import com.demo.microuser.model.UserInfo;
import com.demo.microuser.service.ResourceService;
import com.demo.microuser.util.HttpClientUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/resource")
public class ResourceController {

    private static final Logger logger = LoggerFactory
            .getLogger(ResourceController.class);
    
    @Autowired
    private ResourceService resourceService;
    
    
    
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
    
    @ApiOperation("发布订阅")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "subscription", value="评论", required=true, dataTypeClass=Subscription.class, paramType="body")
	})
	@PostMapping("/addSubscription")
	public RestResponse<Object> addSubscription(@RequestBody Subscription subscription){
		logger.info("[begin]参数:{}",subscription);
		Object newId = resourceService.addSubscription(subscription);
        logger.info("[end]结果。{}", newId);
        return RestResponse.success(newId);
	}
	
	@ApiOperation(value="分页条件查询订阅")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "pageNo", value = "请求页码", required = true, dataType = "int", paramType="query"),
		@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = true, dataType = "int", paramType="query"),
		@ApiImplicitParam(name = "queryParams", value = "查询条件（userId、infoId、status）", required = false, dataTypeClass = SubscriptionPageParams.class, paramType = "body")
    })
    @PostMapping(value = "/pageSubscription")
    public RestResponse<Object> pageSubscription(@RequestBody SubscriptionPageParams queryParams
			,@RequestParam Integer pageNo, @RequestParam Integer pageSize){
        logger.info("[begin]pageNo:{} pageSize:{} params:{}",pageNo, pageSize, queryParams);
        Object list = resourceService.pageSubscription(pageNo, pageSize, queryParams);
        logger.info("[end]成功。{}", list);
        return RestResponse.success(list);
    }
	
	@ApiOperation(value="分页条件查询评论")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "pageNo", value = "请求页码", required = true, dataType = "int", paramType="query"),
		@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = true, dataType = "int", paramType="query"),
		@ApiImplicitParam(name = "queryParams", value = "查询条件（userId和infoId）", required = false, dataTypeClass = CommentPageParams.class, paramType = "body")
    })
    @PostMapping(value = "/pageComment")
    public RestResponse<Object> pageComment(@RequestBody CommentPageParams queryParams
			,@RequestParam Integer pageNo, @RequestParam Integer pageSize){
        logger.info("[begin]pageNo:{} pageSize:{} params:{}",pageNo, pageSize, queryParams);
        Object list = resourceService.pageComment(pageNo, pageSize, queryParams);
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
    
    @ApiOperation("发布评论")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "comment", value="评论", required=true, dataTypeClass=Comment.class, paramType="body")
	})
	@PostMapping("/addComment")
	public RestResponse<Object> addComment(@RequestBody Comment comment){
		logger.info("[begin]参数:{}",comment);
		Object newId = resourceService.addComment(comment);
        logger.info("[end]结果。{}", newId);
        return RestResponse.success(newId);
	}
    
    @ApiOperation("通过username获取用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "username", value="用户名", required=true, dataType="String", paramType="query")
	})
	@GetMapping("/getUserInfo")
	public RestResponse<UserInfo> getUserInfo(@RequestParam String username){
		logger.info("[begin]参数:{}",username);
        UserInfo userInfo = resourceService.getUserInfo(username);
        logger.info("[end]结果。{}", userInfo);
        return RestResponse.success(userInfo);
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
    
    @ApiOperation("发布信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "info", value="信息", required=true, dataTypeClass=Info.class, paramType="body")
	})
	@PostMapping("/addInfo")
	public RestResponse<Object> addInfo(@RequestBody Info info){
		logger.info("[begin]参数:{}",info);
		Object addInfoId = resourceService.addInfo(info);
        logger.info("[end]结果。{}", addInfoId);
        return RestResponse.success(addInfoId);
	}
    
    @ApiOperation(value="分页条件查询信息")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "pageNo", value = "请求页码", required = true, dataType = "int", paramType="query"),
		@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = true, dataType = "int", paramType="query"),
		@ApiImplicitParam(name = "queryParams", value = "查询条件（title和infoType）", required = false, dataTypeClass = InfoPageParams.class, paramType = "body")
    })
    @PostMapping(value = "/pageInfo")
    public RestResponse<Object> pageInfo(@RequestBody InfoPageParams queryParams
			,@RequestParam Integer pageNo, @RequestParam Integer pageSize){
        logger.info("[begin]pageNo:{} pageSize:{} params:{}",pageNo, pageSize, queryParams);
        Object pageInfo = resourceService.pageInfo(pageNo, pageSize, queryParams);
        logger.info("[end]成功。{}", pageInfo);
        return RestResponse.success(pageInfo);
    }
	
    
    @ApiOperation(value="根据位置获取指定范围内，指定数量的信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "infoType", value = "信息类型", required = false, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "title", value = "名称（模糊搜索）", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "x", value = "经度（数值较大的）", required = true, dataType = "Double", paramType = "query"),
            @ApiImplicitParam(name = "y", value = "纬度（数值较小的）", required = true, dataType = "Double", paramType = "query"),
            @ApiImplicitParam(name = "range", value = "要获取信息的半径", required = true, dataType = "Double", paramType = "query"),
            @ApiImplicitParam(name = "number", value = "要获取信息的数量", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping(value = "/findRangedInfoList")
    public RestResponse<Object> findInfoList(@RequestAttribute(value="userInfo", required=false) UserInfo user,
								@RequestParam(value="infoType", required=false) Long infoType,
                                @RequestParam(value="title", required=false) String title,
                                @RequestParam("x") Double x, @RequestParam("y") Double y,
                                @RequestParam("range") Double range, @RequestParam("number") Integer number){
    	logger.info("【【user】】" + user + "   >>" + (user == null ? "" : user.getId() ) );
        logger.info("[begin]title:{} infoType:{} x:{} y:{}",title, infoType, x, y);
        Object infoList = resourceService.findInfoList(infoType, title, x, y, range, number);
        logger.info("[end]成功。");
        return RestResponse.success(infoList);
    }
    
    
    @ApiOperation(value="登录")
    @ApiImplicitParam(name = "userInfo", value = "用户名/密码", required = true, dataTypeClass = UserInfo.class, paramType="body")
    @PostMapping(value = "/login")
    public RestResponse<Object> login(@RequestBody UserInfo userInfo, HttpServletResponse response,
                                        HttpServletRequest request, HttpSession httpSession){
        logger.info("[web-begin]登录：{}", userInfo);
        UserInfo user = resourceService.login(userInfo);
        httpSession.setAttribute("userInfo", user);
        Cookie cookie = new Cookie("realName", user.getRealName());
//        Cookie cookie = new Cookie("username", user.getUsername());
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
	public RestResponse<Nullable> pageApplicationByConditions(@RequestParam String str) throws UnsupportedEncodingException{
    	logger.info("Hi! This is web-user." + str);
		String url = "http://127.0.0.1:53010/resource/hi?str=" + str;
	    // 存储相关的header值
	    Map<String,String> header = new HashMap<String, String>();
	    String response = HttpClientUtil.sendHttp(HttpRequestMethedEnum.HttpGet,url, null,header);
	    logger.info("请求成功，返回信息：" + JSON.toJSONString(JSONObject.parseObject(response),true));
		return RestResponse.success();
	}
}
