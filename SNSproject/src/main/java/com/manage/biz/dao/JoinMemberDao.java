package com.manage.biz.dao;

import java.util.List;

import com.manage.biz.vo.Friends;
import com.manage.biz.vo.JoinMember;


public interface JoinMemberDao {

	int insertJoinMember(JoinMember insert_member) throws Exception; //회원가입 
	void updateUserInfo(JoinMember joinmember) throws Exception; //회원정보 수정
    JoinMember findByUserIdAndPassword(String userId, String password) throws Exception; //로그인
    JoinMember deleteMemeber(JoinMember member) throws Exception; //회원탈퇴
    int findPassword(JoinMember joinmember) throws Exception; //비밀번호 찾기
    void updatePassword(JoinMember joinmember) throws Exception; //비밀번호 수정
    int matching(JoinMember joinmember) throws Exception; //아이디 패스워드 일치 여부 
    int CheckID(JoinMember joinmember) throws Exception; //아이디 중복 검사 
    List<JoinMember> findPeople(JoinMember joinmember) throws Exception; //이름으로 사람들 찾기
    List<JoinMember> myfriend(Friends friends) throws Exception; //내 친구목록
    int addfriend(Friends friends) throws Exception; //친구 신청하기
    int addfriend2(Friends friends) throws Exception; 
    List<Friends> selectfriends() throws Exception; // 친구신청목록
    void allowfriends(Friends friends) throws Exception; //친구 수락
}
