package com.manage.biz.service;


import java.util.List;

import com.manage.biz.vo.Board;
import com.manage.biz.vo.Friends;
import com.manage.biz.vo.JoinMember;

public interface JoinMemberService {
	int insertJoinMember(JoinMember joinmember) throws Exception; //회원가입
	void updateUserInfo(JoinMember joinmember) throws Exception; //회원정보 수정
	JoinMember findByUserIdAndPassword(String userId, String password) throws Exception; //로그인	
	JoinMember removeMember(JoinMember member) throws Exception; //회원탈퇴
	int findPassword(JoinMember joinmember) throws Exception; //비밀번호 찾기
	void updatePassword(JoinMember joinmember) throws Exception; //비밀번호 수정
	int matching(JoinMember joinmember) throws Exception; //아이디 패스워드 일치 여부 
	int CheckID(JoinMember joinmember) throws Exception; //아이디 중복검사
	List<JoinMember> findPeople(JoinMember joinmember) throws Exception; //이름으로 사람들 찾기
	List<JoinMember> myfriend(Friends friends) throws Exception; //내 친구목록
	int addfriend(Friends friends) throws Exception; //친구 신천하기
	List<Friends> selectfriends(Friends friends) throws Exception; //친구 신청목록
	List<Friends> request(Friends friends) throws Exception; //친구 요청
	void allowfriends(Friends friends) throws Exception; //친구 수락
	void cancelfriends(Friends friends) throws Exception; //친구 요청 취소
	void stopfriend(Friends friends) throws Exception; //친구 끊기
	int insertBoardContent(Board board_contents) throws Exception; //게시글 작성
	List<Board> listBoardContents(JoinMember joinmember) throws Exception; //게시글 조회
	Board removeBoardContent(Board board_num) throws Exception; //게시물 삭제
}
