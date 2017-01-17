package com.manage.biz.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.manage.biz.service.JoinMemberService;
import com.manage.biz.vo.JoinMember;

/**
 * Handles requests for the application home page.
 */
@Controller
public class JoinMemberController {
	
	@Autowired
	JoinMemberService joinmemberService;
	
	private static final Logger logger = LoggerFactory.getLogger(JoinMemberController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws Exception 
	 */
	
	//초기화면
	@RequestMapping("/Intro") //홈페이지 주소 http://localhost:8080/biz/Intro
	public String IntroPage(Locale locale, Model model) throws Exception {

		return "sns/login"; //views->sns->login.jsp
	}
	
	//회원가입
	@RequestMapping("/insert") 
	public String JoinMemberList(JoinMember joinmember, Model model) throws Exception {
		//System.out.println(joinmember.getMember_id());
		//System.out.println(joinmember.getMember_birth());
		System.out.println(joinmember.getMember_sex());
		joinmemberService.insertJoinMember(joinmember);
		return "sns/login"; //views->sns->login.jsp
	}
	
	//로그인 처리
	/*@RequestMapping("/loginCheck")
	public String loginCheck(HttpServletRequest request, HttpServletResponse response){
		logger.debug("==============login check");
		
		String returnURL = "";
		
		if("ID".equals(request.getParameter("member_id"))&& "PASSWORD".equals(request.getParameter("member_password"))){
			Map<String, String> map = new HashMap<String, String>();
			map.put("admin_id", "admin");
			map.put("admin_name", "administrator");
			request.getSession().setAttribute("logininfo", map);
			returnURL = "home";
		}else{
			returnURL = "login";
		}
		return returnURL;
		}
	
	@RequestMapping("/home")
	public String home(Locale locale, Model model){
		logger.debug("======= home 화면 =========");
		return "home";
	}*/
	
}

