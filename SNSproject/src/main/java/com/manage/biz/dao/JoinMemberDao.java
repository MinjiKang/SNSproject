package com.manage.biz.dao;

import com.manage.biz.vo.JoinMember;


public interface JoinMemberDao {

	int insertJoinMember(JoinMember insert_member) throws Exception; //회원가입 
	JoinMember selectJoinMember(String member_id) throws Exception;
    JoinMember findByUserIdAndPassword(String userId, String password) throws Exception; //로그인
    JoinMember deleteMemeber(JoinMember member) throws Exception; //회원탈퇴
    int findPassword(JoinMember joinmember) throws Exception; //비밀번호 찾기
    void updatePassword(JoinMember joinmember) throws Exception; //비밀번호 수정
    int matching(JoinMember joinmember) throws Exception; //아이디 패스워드 일치 여부 
    int CheckID(JoinMember joinmember) throws Exception; //아이디 중복 검사 
}
