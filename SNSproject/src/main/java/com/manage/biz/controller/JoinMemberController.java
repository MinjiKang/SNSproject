package com.manage.biz.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	//회원가입 페이지
	@RequestMapping("/application") 
	public String IntroPage(Locale locale, Model model) throws Exception {

		return "sns/JoinMembership"; //views->sns->JoinMembership.jsp
		
	}
	
	//회원가입 - db에 저장
	@RequestMapping("/insert") 
	public String JoinMemberList(JoinMember joinmember, Model model) throws Exception {
		
		joinmemberService.insertJoinMember(joinmember);
		return "sns/loginpage"; //views->sns->loginpage.jsp
		
	}
	
	//로그인 페이지(아이디, 비밀번호 입력)
	@RequestMapping("/loginForm") //홈페이지 주소 http://localhost:8080/biz/loginForm
    public String loginForm(Model model){
		
        return "sns/loginpage";
        
    }
	
    //로그인 처리
    @RequestMapping("/loginProcess")
	public String loginProcess(JoinMember user, HttpSession session, Model model) throws Exception {
    	
		JoinMember loginUser = joinmemberService.findByUserIdAndPassword(user.getMember_id(), user.getMember_password());

		if (loginUser != null) { //session check
			session.setAttribute("userLoginInfo", loginUser);
			return "sns/main"; //로그인 시 넘어가는 화면
		}
		
		return "sns/FailPage";	//로그인 실패 시 

	}
    
    // 로그아웃
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        /*session.setAttribute("userLoginInfo", null);*/
        session.invalidate(); //session 종료(안에있는 데이터 다삭제)
        return "redirect:loginForm";
        
    }
 
    //회원탈퇴 페이지  
    @RequestMapping("/deleteForm")
    public String deletePage() throws Exception{

    	return "sns/delForm";
    	
    }
   
    //회원탈퇴
    @RequestMapping("/delete")
    public String execute(JoinMember user, HttpSession session, Model model) throws Exception{
    	
    	int rtn = joinmemberService.matching(user);
    	
    	if(rtn == 1){
    		model.addAttribute("user", user);
	    	session.invalidate();
	    	joinmemberService.removeMember(user);
	    	return "redirect:loginForm";
    	}
    	else{
    		model.addAttribute("msg1", "비밀번호를 확인하세요.");
			return "sns/delForm";	
    	}
    	
    }

    //패스워드 찾기 페이지
    @RequestMapping("/pass")
	public String IntroPage1(Locale locale, Model model) throws Exception {

		return "sns/FindPassword"; 
		
	}
	
    //비밀번호 찾기
	@RequestMapping("/findpassword")
	public String FindPassword(JoinMember joinmember, Model model) throws Exception {

		int rtn=joinmemberService.findPassword(joinmember);
		if(rtn==1)
		{
			model.addAttribute("joinmember", joinmember);
			model.addAttribute("msg", "change your password.");
			return "sns/UpdatePassword";
		}
		else
		{	
			model.addAttribute("msg1", "틀렸습니다.");
			return "sns/FindPassword";	
		}
		
	}
	
	//비밀번호 수정
	@RequestMapping("/updatepassword") 
	public String UpdatePassword(JoinMember joinmember, Model model) throws Exception {
		
		joinmemberService.updatePassword(joinmember);

		return "sns/loginpage";
		
	}
	
	//아이디 중복검사
	@RequestMapping("/CheckID")
	public String CheckID(JoinMember joinmember, Model model) throws Exception {

		int rtn1=joinmemberService.CheckID(joinmember);
		
		if(rtn1==0)
		{
			model.addAttribute("joinmember", joinmember);
			model.addAttribute("msg2", "가능한 아이디입니다");
			return "sns/JoinMembership"; 
		}
		else
		{	
			model.addAttribute("msg2", "중복입니다.");
			return "sns/JoinMembership";	
		}
	}
	
	//회원정보 수정 페이지
	@RequestMapping("/memberUpdateForm")
	public String editMember() throws Exception {
		return "sns/editForm";
	}
	
	//회원정보 수정
	@RequestMapping("/UpdateInfo")
	public String updateInfo(JoinMember joinmember, Model model) throws Exception {
		joinmemberService.updatePassword(joinmember);
		return "sns/main";
	}
}

