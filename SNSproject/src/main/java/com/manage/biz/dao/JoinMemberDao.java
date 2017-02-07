package com.manage.biz.dao;

import java.util.List;

import com.manage.biz.vo.Board;
import com.manage.biz.vo.Friends;
import com.manage.biz.vo.JoinMember;
import com.manage.biz.vo.LikeButton;


public interface JoinMemberDao {

	int insertJoinMember(JoinMember insert_member) throws Exception; //회원가입
	void updateUserInfo(JoinMember joinmember) throws Exception; //회원정보 수정
    JoinMember findByUserIdAndPassword(String userId, String password) throws Exception; //로그인 처리
    JoinMember deleteMemeber(JoinMember member) throws Exception; //회원 탈퇴
    int findPassword(JoinMember joinmember) throws Exception; //비밀번호 찾기
    void updatePassword(JoinMember joinmember) throws Exception; //비밀번호 수정
    int matching(JoinMember joinmember) throws Exception; //아이디, 패스워드 일치 여부
    int CheckID(JoinMember joinmember) throws Exception; //아이디 중복검사
    List<JoinMember> findPeople(JoinMember joinmember) throws Exception;
    List<JoinMember> myfriend(Friends friends) throws Exception;
    void addfriend(Friends friends) throws Exception; 
    void addfriend2(Friends friends) throws Exception; 
    List<Friends> selectfriends(Friends friends) throws Exception; 
    List<Friends> request(Friends friends) throws Exception; 
    void allowfriends(Friends friends) throws Exception; 
    void cancelfriends(Friends friends) throws Exception; 
    void stopfriend(Friends friends) throws Exception; 
    int insertBoardContent(Board board_contents) throws Exception; //게시글 작성
    List<Board> listBoardContents(JoinMember joinmember) throws Exception; //게시물 조회
    Board deleteBoardContent(Board board) throws Exception; //게시물 삭제
    int insertLike(LikeButton likebutton) throws Exception; //좋아요 버튼 클릭시
    LikeButton cancleLike(LikeButton likebutton) throws Exception; //좋아요 취소
}
