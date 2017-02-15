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
import com.manage.biz.vo.LikeButton;


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
	public String loginProcess(JoinMember user, Board board, HttpSession session, HttpServletRequest req, Model model) throws Exception {
    	
		JoinMember loginUser = joinmemberService.findByUserIdAndPassword(user.getMember_id(), user.getMember_password());

		if (loginUser != null) { //session check
			session.setAttribute("userLoginInfo", loginUser);
			List<JoinMember> listboard_contents = joinmemberService.listBoardContents(loginUser);
			model.addAttribute("listcontents", listboard_contents);
			
			return "sns/main"; //로그인 시 넘어가는 화면
		}
		
		return "sns/FailPage";	//로그인 실패 시 

	}
    
    
    //main에서 이벤트 업데이트 화면
    @RequestMapping("/goMain")
	public String goMain(HttpSession session, HttpServletRequest req, Model model) throws Exception {
    	
    	JoinMember sessionInfo = (JoinMember)session.getAttribute("userLoginInfo");
    	
		JoinMember loginUser = joinmemberService.findByUserIdAndPassword(sessionInfo.getMember_id(), sessionInfo.getMember_password());

		if (loginUser != null) { //session check
			session.setAttribute("userLoginInfo", loginUser);
			Board board = new Board();
			List<JoinMember> listboard_contents = joinmemberService.listBoardContents(loginUser);
			model.addAttribute("listcontents", listboard_contents);
			
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
	public String updateInfo(JoinMember joinmember, HttpSession session, Model model) throws Exception {
		
		model.addAttribute("joinmember", joinmember);
		JoinMember sessionMember = (JoinMember)session.getAttribute("userLoginInfo");
		joinmember.setMember_id(sessionMember.getMember_id());
		joinmemberService.updateUserInfo(joinmember);
		return "sns/main";
		
	}
	
	//친구 찾기
	@RequestMapping("/findpeople")
	public String FindPeople(JoinMember joinmember, Model model, HttpServletRequest req) throws Exception {

		List<JoinMember> peoplelist = joinmemberService.findPeople(joinmember);// 친구 목록 찾기 -- 나 제외 (status 2=신청중,3=수락대기 ,9 신청가능)
		model.addAttribute("joinmember", peoplelist);
		
		model.addAttribute("member_name", joinmember.getMember_name());
		model.addAttribute("member_no", joinmember.getMember_no());
		model.addAttribute("msg", req.getParameter("msg")); 
		return "sns/PeopleList";

	}
	
	//친구 신청 및 신청한 목록
	@RequestMapping("/addfriend")
	public String addfriend( Model model, Friends friends,JoinMember joinmember, HttpServletRequest req) throws Exception {
		joinmemberService.addfriend(friends);
		model.addAttribute("joinmember", joinmember);
		String msg = "add friend finish";
		return "redirect:findpeople?member_name="+req.getParameter("member_name")+"&member_no="+req.getParameter("member_no")+"&msg="+msg; 
	}
	
	//친구 수락
	@RequestMapping("/friendsList") 
	public String friendslist(Friends friends, Model model) throws Exception {
		List<Friends> friendslist = joinmemberService.selectfriends(friends);
		model.addAttribute("friends", friendslist);
		
		return "sns/FriendList";
	}
	
	//친구 수락할 리스트
	@RequestMapping("/allowfriends") 
	public String allowfriends(Friends friends, Model model) throws Exception {
		joinmemberService.allowfriends(friends);
		List<Friends> friendslist = joinmemberService.selectfriends(friends);
		model.addAttribute("friends", friendslist);
		return "sns/FriendList";
	}
	
	//친구 요청
	@RequestMapping("/request") 
	public String Request(Friends friends, Model model) throws Exception {
		List<Friends> re = joinmemberService.request(friends);
		model.addAttribute("friends", re);
		return "sns/Request";
	}
	
	//친구 신청 취소하기
	@RequestMapping("/cancelfriends") 
	public String cancelfriends(Friends friends, Model model) throws Exception {

		joinmemberService.cancelfriends(friends);
		List<Friends> re = joinmemberService.request(friends);
		model.addAttribute("friends", re);
	
		return "sns/Request";
	}
	
	//내 친구 조회
	@RequestMapping("/myfriend") 
	public String Myfriend(Friends friends, Model model,HttpServletRequest req) throws Exception {
	

		List<JoinMember> my = joinmemberService.myfriend(friends);
		model.addAttribute("myfriend", my);
		
		model.addAttribute("msg1", req.getParameter("msg1"));
		model.addAttribute("user1", friends.getUser1());
		
		return "sns/Myfriend";
	}	
	
	//친구 끊기
	@RequestMapping("/stopfriend") 
	public String Stopfriend(Friends friends, Model model,HttpServletRequest req) throws Exception {
	
		joinmemberService.stopfriend(friends);
		String msg1 = "friend remove finish";
	
		return "redirect:myfriend?user1="+req.getParameter("user1")+"&msg1="+msg1;
	}	
	
	//게시물 작성
	@RequestMapping("/writeBoard")
	public String board(JoinMember joinmember, Board board_contents, HttpSession session, Model model) throws Exception {
		
		model.addAttribute("joinmember", joinmember);
		JoinMember sessionMember = (JoinMember)session.getAttribute("userLoginInfo");
		board_contents.setBoard_writer(sessionMember.getMember_id());
		joinmemberService.insertBoardContent(board_contents);
		
		return "redirect:goMain";
	
	}

	//게시글 삭제
	@RequestMapping("/deleteBoardContent")
    public String deleteBoardContent(Board board, Model model, HttpSession session) throws Exception{

		// like table delete
		LikeButton likebutton = new LikeButton();
		likebutton.setBoard_no(board.getBoard_no());
		joinmemberService.deleteLike(likebutton);
		// comment table delete
		
		// board table delete
		joinmemberService.removeBoardContent(board);
		
		return "redirect:goMain";
		
    }
	
	//게시물 수정
	@RequestMapping("/modifyBoardContent")
	public String modifyBoardContent(JoinMember joinmember, HttpServletRequest req, Board board, Model model) throws Exception {

		Board board1 = new Board();
		int board_no = board.getBoard_no();
		board1.setBoard_no(board_no);
		board1.setBoard_contents(req.getParameter("content"));
		
		joinmemberService.updateBoardContent(board1);
		return "redirect:goMain";
	}
		
	//좋아요 
	@RequestMapping("/clickLikeButton")
	public String ClickLikeButton(JoinMember joinmember, Board board, LikeButton likebutton, HttpSession session, Model model) throws Exception {
		
		model.addAttribute("joinmember", joinmember);
		JoinMember sessionMember = (JoinMember)session.getAttribute("userLoginInfo");
		likebutton.setMember_no(sessionMember.getMember_no());
		int board_no = board.getBoard_no();
		likebutton.setBoard_no(board_no);
		int like_status = likebutton.getLike_status();
		likebutton.setLike_status(like_status);
		
		if(like_status == 1){
			joinmemberService.cancleLike(likebutton);
		}
		else{
			joinmemberService.insertLike(likebutton);
		}
		return "redirect:goMain";
	}
	
	//좋아요  한 사람 리스트 
	@RequestMapping("/likePeoplList")
	public String likePeoplList(LikeButton likebutton, Model model) throws Exception {
		
		List<LikeButton> like_people_list = joinmemberService.likePeopleList(likebutton);
		model.addAttribute("likePeopleList", like_people_list);
		
		return "sns/likePeopleList"; //jsp -> likePeopleList.jsp 추가
	}	
	
}
