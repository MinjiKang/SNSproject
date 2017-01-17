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
	
	// id, password üũ �� ������ ���� ���
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
	
	//�α��� �Ǿ������� ������������, �α��εǾ����� ������ �α����������� �̵�
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
