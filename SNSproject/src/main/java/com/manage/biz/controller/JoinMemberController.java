package com.manage.biz.controller;


import java.util.Locale;

import javax.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.manage.biz.service.JoinMemberService;
import com.manage.biz.vo.JoinMember;
import javax.servlet.http.HttpServletRequest;

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
	
	//회원가입 페이지
	@RequestMapping("/application") //홈페이지 주소 http://localhost:8080/biz/Intro
	public String IntroPage(Locale locale, Model model) throws Exception {

		return "sns/JoinMembership"; //views->sns->JoinMembership.jsp
	}
	
	//회원가입 - db에 저장
	@RequestMapping("/insert") 
	public String JoinMemberList(JoinMember joinmember, Model model) throws Exception {
		//System.out.println(joinmember.getMember_id());
		//System.out.println(joinmember.getMember_birth());
		System.out.println(joinmember.getMember_sex());
		joinmemberService.insertJoinMember(joinmember);
		return "sns/loginpage"; //views->sns->loginpage.jsp
	}
	
	//로그인 화면(아이디, 비밀번호 입력)
	@RequestMapping("/loginForm")
    public String loginForm(){
        return "sns/loginpage";
    }
	
    //로그인 처리
    @RequestMapping("/loginProcess")
	public ModelAndView loginProcess(JoinMember user, HttpSession session, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:loginForm");
		
		JoinMember loginUser = joinmemberService.findByUserIdAndPassword(user.getMember_id(), user.getMember_password());
		
		if (loginUser != null) { //session check
			session.setAttribute("userLoginInfo", loginUser);
		}
		return mav;
	}
    
    // 로그아웃
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute("userLoginInfo", null);
        //session.invalidate(); //session 종료(안에있는 데이터 다삭제)
        return "redirect:loginForm";
    }
}

