package com.demo.microuser.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.micro.common.domain.RestResponse;
import com.demo.microuser.model.UserInfo;
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
    @Autowired
    private ResourceService resourceService;
	
    
    
    
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
	public RestResponse<Nullable> pageApplicationByConditions(@RequestParam String str){
		System.out.println("Hi! This is web-user." + str);
		return RestResponse.success();
	}
	
}
