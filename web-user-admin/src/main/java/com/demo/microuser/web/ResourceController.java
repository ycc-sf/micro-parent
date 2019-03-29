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
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.micro.common.domain.RestResponse;
import com.demo.microuser.domain.HttpRequestMethedEnum;
import com.demo.microuser.model.UserInfo;
import com.demo.microuser.model.UserRole;
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
    public RestResponse<Object> findInfoList(@RequestParam(value="infoType", required=false) Long infoType,
                                                 @RequestParam(value="title", required=false) String title,
                                                 @RequestParam("x") Double x, @RequestParam("y") Double y,
                                          @RequestParam("range") Double range, @RequestParam("number") Integer number){
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
        httpSession.setAttribute("userInfo", userInfo);
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
	public RestResponse<Nullable> pageApplicationByConditions(@RequestParam String str) throws UnsupportedEncodingException{
    	logger.info("Hi! This is web-user." + str);
		String url = "http://127.0.0.1:53010/resource/hi?str=" + str;
	    // 存储相关的header值
	    Map<String,String> header = new HashMap<String, String>();
	    //username:password--->访问的用户名，密码,并使用base64进行加密，将加密的字节信息转化为string类型，encoding--->token
//	    String encoding = DatatypeConverter.printBase64Binary("kermit:kermit".getBytes("UTF-8"));
//	    header.put("Authorization", "Basic " + encoding);
	    String response = HttpClientUtil.sendHttp(HttpRequestMethedEnum.HttpGet,url, null,header);
	    logger.info("请求成功，返回信息：" + JSON.toJSONString(JSONObject.parseObject(response),true));
		return RestResponse.success();
	}
}