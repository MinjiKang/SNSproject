package com.manage.biz.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manage.biz.service.JoinMemberService;
import com.manage.biz.vo.Board;
import com.manage.biz.vo.Friends;
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
	
	//ȸ������ ������
	@RequestMapping("/application") 
	public String IntroPage(Locale locale, Model model) throws Exception {

		return "sns/JoinMembership"; //views->sns->JoinMembership.jsp
		
	}
	
	//ȸ������ - db�� ����
	@RequestMapping("/insert") 
	public String JoinMemberList(JoinMember joinmember, Model model) throws Exception {
		
		joinmemberService.insertJoinMember(joinmember);
		return "sns/loginpage"; //views->sns->loginpage.jsp
		
	}
	
	//�α��� ������(���̵�, ��й�ȣ �Է�)
	@RequestMapping("/loginForm") //Ȩ������ �ּ� http://localhost:8080/biz/loginForm
    public String loginForm(Model model){
		
        return "sns/loginpage";
        
    }
	
    //�α��� ó��
    @RequestMapping("/loginProcess")
	public String loginProcess(JoinMember user, Board board, HttpSession session, HttpServletRequest req, Model model) throws Exception {
    	
		JoinMember loginUser = joinmemberService.findByUserIdAndPassword(user.getMember_id(), user.getMember_password());

		if (loginUser != null) { //session check
			session.setAttribute("userLoginInfo", loginUser);
			List<Board> listboard_contents = joinmemberService.listBoardContents(loginUser);
			model.addAttribute("listcontents", listboard_contents);
			
			return "sns/main"; //�α��� �� �Ѿ�� ȭ��
		}
		
		return "sns/FailPage";	//�α��� ���� �� 

	}
    
    //main���� �̺�Ʈ ������Ʈ ȭ��
    @RequestMapping("/goMain")
	public String goMain(HttpSession session, HttpServletRequest req, Model model) throws Exception {
    	
    	JoinMember sessionInfo = (JoinMember)session.getAttribute("userLoginInfo");
    	
		JoinMember loginUser = joinmemberService.findByUserIdAndPassword(sessionInfo.getMember_id(), sessionInfo.getMember_password());

		if (loginUser != null) { //session check
			session.setAttribute("userLoginInfo", loginUser);
			Board board = new Board();
			List<Board> listboard_contents = joinmemberService.listBoardContents(loginUser);
			model.addAttribute("listcontents", listboard_contents);
			
			return "sns/main"; //�α��� �� �Ѿ�� ȭ��
		}
		
		return "sns/FailPage";	//�α��� ���� �� 

	}
    
    // �α׾ƿ�
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        /*session.setAttribute("userLoginInfo", null);*/
        session.invalidate(); //session ����(�ȿ��ִ� ������ �ٻ���)
        return "redirect:loginForm";
        
    }
 
    //ȸ��Ż�� ������  
    @RequestMapping("/deleteForm")
    public String deletePage() throws Exception{

    	return "sns/delForm";
    	
    }
   
    //ȸ��Ż��
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
    		model.addAttribute("msg1", "��й�ȣ�� Ȯ���ϼ���.");
			return "sns/delForm";	
    	}
    	
    }

    //�н����� ã�� ������
    @RequestMapping("/pass")
	public String IntroPage1(Locale locale, Model model) throws Exception {

		return "sns/FindPassword"; 
		
	}
	
    //��й�ȣ ã��
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
			model.addAttribute("msg1", "Ʋ�Ƚ��ϴ�.");
			return "sns/FindPassword";	
		}
		
	}
	
	//��й�ȣ ����
	@RequestMapping("/updatepassword") 
	public String UpdatePassword(JoinMember joinmember, Model model) throws Exception {
		
		joinmemberService.updatePassword(joinmember);

		return "sns/loginpage";
		
	}
	
	//���̵� �ߺ��˻�
	@RequestMapping("/CheckID")
	public String CheckID(JoinMember joinmember, Model model) throws Exception {

		int rtn1=joinmemberService.CheckID(joinmember);
		
		if(rtn1==0)
		{
			model.addAttribute("joinmember", joinmember);
			model.addAttribute("msg2", "������ ���̵��Դϴ�");
			return "sns/JoinMembership"; 
		}
		else
		{	
			model.addAttribute("msg2", "�ߺ��Դϴ�.");
			return "sns/JoinMembership";	
		}
	}
	
	//ȸ������ ���� ������
	@RequestMapping("/memberUpdateForm")
	public String editMember() throws Exception {
		return "sns/editForm";
	}
	
	//ȸ������ ����
	@RequestMapping("/UpdateInfo")
	public String updateInfo(JoinMember joinmember, HttpSession session, Model model) throws Exception {
		
		model.addAttribute("joinmember", joinmember);
		JoinMember sessionMember = (JoinMember)session.getAttribute("userLoginInfo");
		joinmember.setMember_id(sessionMember.getMember_id());
		joinmemberService.updateUserInfo(joinmember);
		return "sns/main";
		
	}
	
	//ģ�� ã��
	@RequestMapping("/findpeople")
	public String FindPeople(JoinMember joinmember, Model model, HttpServletRequest req) throws Exception {

		List<JoinMember> peoplelist = joinmemberService.findPeople(joinmember);
		model.addAttribute("joinmember", peoplelist);
		
		
		model.addAttribute("msg", req.getParameter("msg")); //addfriend -> findpeople
		model.addAttribute("member_name", joinmember.getMember_name());//addfriend -> findpeople
		return "sns/PeopleList";

	}
	
	//ģ�� ��û �� ��û�� ���
	@RequestMapping("/addfriend")
	public String addfriend( Model model, Friends friends,JoinMember joinmember, HttpServletRequest req) throws Exception {
		int m_friend = joinmemberService.addfriend(friends);
		model.addAttribute("joinmember", joinmember);
		String msg = "add friend finish";
		return "redirect:findpeople?member_name="+req.getParameter("member_name")+"&msg="+msg; //redirect member_name �� message ����
	}
	
	// ģ�� ����
	@RequestMapping("/friendsList") 
	public String friendslist(Friends friends, Model model) throws Exception {
		List<Friends> friendslist = joinmemberService.selectfriends(friends);
		model.addAttribute("friends", friendslist);
		
		return "sns/FriendList";
	}
	
	//ģ�� ������ ���� ��Ȳ
	@RequestMapping("/allowfriends") 
	public String allowfriends(Friends friends, Model model) throws Exception {

		joinmemberService.allowfriends(friends);
		List<Friends> friendslist = joinmemberService.selectfriends(friends);
		model.addAttribute("friends", friendslist);
		return "sns/FriendList";
	}
	
	// ģ�� ��û�� ��Ȳ
	@RequestMapping("/request") 
	public String Request(Friends friends, Model model) throws Exception {
		
		List<Friends> re = joinmemberService.request(friends);
		model.addAttribute("friends", re);
		return "sns/Request";
	}
	
	//ģ�� ��û ����ϱ�
	@RequestMapping("/cancelfriends") 
	public String cancelfriends(Friends friends, Model model) throws Exception {

		joinmemberService.cancelfriends(friends);
		List<Friends> re = joinmemberService.request(friends);
		model.addAttribute("friends", re);
	
		return "sns/Request";
	}
	
	//ģ����ȸ
	@RequestMapping("/myfriend") 
	public String Myfriend(Friends friends, Model model,HttpServletRequest req) throws Exception {
	

		List<JoinMember> my = joinmemberService.myfriend(friends);
		model.addAttribute("myfriend", my);
		
		model.addAttribute("msg1", req.getParameter("msg1"));
		model.addAttribute("user1", friends.getUser1());
		
		return "sns/Myfriend";
	}	
	
	//ģ������
	@RequestMapping("/stopfriend") 
	public String Stopfriend(Friends friends, Model model,HttpServletRequest req) throws Exception {
	
		joinmemberService.stopfriend(friends);
		String msg1 = "friend remove finish";
	
		return "redirect:myfriend?user1="+req.getParameter("user1")+"&msg1="+msg1;
	}	
	
	//�Խù� �ۼ�
	@RequestMapping("/writeBoard")
	public String board(JoinMember joinmember, Board board_contents, HttpSession session, Model model) throws Exception {
		
		model.addAttribute("joinmember", joinmember);
		JoinMember sessionMember = (JoinMember)session.getAttribute("userLoginInfo");
		board_contents.setBoard_writer(sessionMember.getMember_id());
		joinmemberService.insertBoardContent(board_contents);
		
		return "redirect:goMain";
		
	}

	//�Խñ� ����
	@RequestMapping("/deleteBoardContent")
    public String deleteBoardContent(Board board, Model model, HttpSession session) throws Exception{
		
		joinmemberService.removeBoardContent(board);
		
		return "redirect:goMain";
		
    }
}

