package com.manage.biz.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CommonSession extends HandlerInterceptorAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(CommonSession.class);
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception { 
		
		logger.info("session체크");
		HttpSession session  =  request.getSession();
		
		String member_id = (String)session.getAttribute("member_id");
		
		logger.info("현재 로그인하려는 member_id = "+ member_id);
		if ( member_id == null) {		//session check
			response.sendRedirect("loginpage.do");
			return false;
		}

		else{
			return true;
		}
		
	}

}
