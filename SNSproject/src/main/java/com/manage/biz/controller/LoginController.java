package com.manage.biz.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manage.biz.service.JoinMemberService;

public class LoginController {
	@Autowired
	JoinMemberService joinmemberService;

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value = "/login.do")
	@ResponseBody
	public String login(String id, String pw, HttpSession session){
				
		HashMap<String, String> hstParam = new HashMap<String, String>();
		hstParam.put("member_id", id);
		hstParam.put("member_password", pw);
		
		String loginIdentify = joinmemberService.loginCheck(hstParam);
		
		if (loginIdentify == "1"){
			session.setAttribute("member_id", id);
			session.setAttribute("member_password", pw);
		}
				
		return loginIdentify;
	}
}
