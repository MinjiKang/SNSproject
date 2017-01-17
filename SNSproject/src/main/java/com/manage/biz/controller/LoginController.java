package com.manage.biz.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.manage.biz.service.JoinMemberService;

public class LoginController {
	@Autowired
	JoinMemberService joinmemberService;

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	// id, password 체크 후 맞으면 세션 등록
	@RequestMapping(value = "/login.do")
	@ResponseBody
	public int login(String id, String pw, HttpSession session){
				
		HashMap<String, String> hstParam = new HashMap<String, String>();
		hstParam.put("member_id", id);
		hstParam.put("member_password", pw);
		
		int loginIdentify = joinmemberService.loginCheck(hstParam);
		if (loginIdentify == 1){
			session.setAttribute("member_id", id);
			session.setAttribute("member_password", pw);
		}
				
		return loginIdentify;
	}
	
	//로그인 되어있으면 메인페이지로, 로그인되어있지 않으면 로그인페이지로 이동
	@RequestMapping(value = "/loginpage.do")
	public ModelAndView loginpage(HttpSession session){
		
		ModelAndView mav = new ModelAndView();
		
		String member_id = (String)session.getAttribute("member_id");
		
		if ( member_id != null) {		//session check
			
			mav.setViewName("redirect:/Intro");
			return mav;
		}
		
		mav.setViewName("/sns/loginpage");

		return mav;
	}
	
}
