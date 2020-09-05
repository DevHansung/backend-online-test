package com.hansung.web.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hansung.web.service.UserService;
import com.hansung.web.vo.User;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private UserService userService;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	String name = request.getHeader("name");
        User user = userService.authenticateUser(name);
        request.setAttribute("user", user);
		return true;
    }
}