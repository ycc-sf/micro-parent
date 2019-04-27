package com.demo.microuser.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.demo.microuser.model.UserInfo;

public class MyInterceptor implements HandlerInterceptor {

    /**
     * controller 执行之前调用
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
    	HttpSession session = request.getSession();
        String requestURI = request.getRequestURI();
        Object user = session.getAttribute("userInfo");
        
        //判断，如果接口访问登录页面或者登录接口或者静态资源，则直接放行
        if(requestURI.contains("/hi")
        		||requestURI.indexOf("/login.html") == 0
        		|| requestURI.indexOf("/resource/login") == 0
        		|| requestURI.indexOf("/html/admin/login.html") == 0
        		|| requestURI.indexOf("/admin/login") == 0
        		|| requestURI.indexOf("/error") == 0
        		|| requestURI.indexOf("/css") == 0
        		|| requestURI.indexOf("/images") == 0
        		|| requestURI.indexOf("/js") == 0
        		|| requestURI.indexOf("/plugin") == 0){
        	return true;
        }
        System.out.println("【preHandle】" + requestURI + "【user】" + user);
        
        //如果未登录且访问前台接口，则返回登录页面
        if(user == null && requestURI.indexOf("/resource") == 0){
        	response.sendRedirect("/login.html");
        	return false;
        }else if(user == null && requestURI.indexOf("/admin") == 0){//如果未登录且访问前后台接口，则返回登录页面
        	response.sendRedirect("/admin/login.html");
        	return false;
        }else if(user == null && requestURI.indexOf("/index.html") == 0){//如果未登录且访问前台主页，则返回登录页面
        	response.sendRedirect("/login.html");
        	return false;
        }else if(user == null && requestURI.indexOf("/html/admin/index.html") == 0){//如果未登录且访问后台主页，则返回登录页面
        	response.sendRedirect("/html/admin/login.html");
        	return false;
        }else if(user == null){
        	response.sendRedirect("/login.html");
        	return false;
        }
        UserInfo userInfo = (UserInfo)user;
        request.setAttribute("userInfo", userInfo);
        return true;
    }

    /**
     * controller 执行之后，且页面渲染之前调用
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
//        System.out.println("------postHandle-----");
    }

    /**
     * 页面渲染之后调用，一般用于资源清理操作
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
//        System.out.println("------afterCompletion-----");

    }

}
