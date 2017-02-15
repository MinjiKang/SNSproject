package com.manage.biz.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.manage.biz.vo.Board;
import com.manage.biz.vo.Friends;
import com.manage.biz.vo.JoinMember;
import com.manage.biz.vo.LikeButton;


@Repository
public class JoinMemberDaoImpl implements JoinMemberDao{
	
	@Autowired
	SqlSessionTemplate sqlSession;

	//회원가입
	public int insertJoinMember(JoinMember insert_member) throws Exception{
		sqlSession.insert("JoinMemberDao.insertMember", insert_member);
		int m_seq = insert_member.getMember_no();
		return m_seq;
	}
	
	//회원정보 수정
	public void updateUserInfo(JoinMember joinmember) throws Exception{
		System.out.println(joinmember.getMember_id());
		System.out.println(joinmember.getMember_password());
		sqlSession.update("JoinMemberDao.UpdateUserInfo", joinmember);
	}
	
	//로그인 처리
	public JoinMember findByUserIdAndPassword(String userId, String password) throws Exception{
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("member_id", userId);
        paramMap.put("member_password", password);

        return sqlSession.selectOne("JoinMemberDao.selectLoginUser", paramMap);
	}
	
	//회원 탈퇴
	public JoinMember deleteMemeber(JoinMember member) throws Exception{
		return sqlSession.selectOne("JoinMemberDao.deleteMember", member);
	}
	
	//비밀번호 찾기
	public int findPassword(JoinMember joinmember) throws Exception{
		int m_password = sqlSession.selectOne("JoinMemberDao.selectFind", joinmember);
		return m_password;
	}
	
	//비밀번호 수정
	public void updatePassword(JoinMember joinmember) throws Exception{
		sqlSession.update("JoinMemberDao.UpdatePassword", joinmember);
	}

	//아이디, 패스워드 일치 여부
	public int matching(JoinMember joinmember) throws Exception{
		int matching_ok = sqlSession.selectOne("JoinMemberDao.matching", joinmember);
		return matching_ok;
	}
	
	 //아이디 중복검사
	public int CheckID(JoinMember joinmember) throws Exception{
		int m_id = sqlSession.selectOne("JoinMemberDao.selectCheckID", joinmember);
		return m_id;
	}	
	
	public List<JoinMember> findPeople(JoinMember joinmember) throws Exception{
		List<JoinMember> peoplelist = sqlSession.selectList("JoinMemberDao.findPeople", joinmember);
		return peoplelist;
	}
	
	public List<JoinMember> myfriend(Friends friends) throws Exception{
		
		List<JoinMember> my = sqlSession.selectList("JoinMemberDao.myfriend", friends);
		return my;
	}
	
	public void addfriend(Friends friends) throws Exception{
		sqlSession.insert("JoinMemberDao.addfriend", friends);
	}
	
	public void addfriend2(Friends friends) throws Exception{
		sqlSession.insert("JoinMemberDao.addfriend2", friends);
	}
	
	public List<Friends> selectfriends(Friends friends) throws Exception{
		List<Friends> friendslist = sqlSession.selectList("JoinMemberDao.selectfriends",friends);
		return friendslist;
	}
	
	public List<Friends> request(Friends friends) throws Exception{
		List<Friends> re = sqlSession.selectList("JoinMemberDao.request",friends);
		return re;
	}
	
	public void allowfriends(Friends friends) throws Exception{
		sqlSession.update("JoinMemberDao.allowfriends", friends);
	}
	
	public void cancelfriends(Friends friends) throws Exception{
		sqlSession.delete("JoinMemberDao.cancelfriends", friends);
	}
	
	public void stopfriend(Friends friends) throws Exception{
		sqlSession.delete("JoinMemberDao.stopfriend", friends);
	}
	
	//게시글 작성
	public int insertBoardContent(Board board_contents) throws Exception{
		sqlSession.insert("JoinMemberDao.insertBoardContent", board_contents);
		int b_seq = board_contents.getBoard_no();
		return b_seq;
	} 
	
	//게시물 조회
	public List<JoinMember> listBoardContents(JoinMember joinmember) throws Exception{
		List<JoinMember> board_contents_list = sqlSession.selectList("JoinMemberDao.selectBoardContent", joinmember);
		return board_contents_list;
	}
	
	//게시물 삭제
	public Board deleteBoardContent(Board board) throws Exception{
		return sqlSession.selectOne("JoinMemberDao.deleteBoardContent", board);
	}

	//게시물 수정
		public void updateBoardContent(Board board) throws Exception{
			sqlSession.update("JoinMemberDao.updateBoardContent", board);
	}
		
	//좋아요 버튼 클릭시
	public void insertLike(LikeButton likebutton) throws Exception{
		sqlSession.insert("JoinMemberDao.insertLike", likebutton);
	}
	
	//좋아요 취소
	public LikeButton cancleLike(LikeButton likebutton) throws Exception{
		return sqlSession.selectOne("JoinMemberDao.cancleLike", likebutton);
	}
	
	//게시물 삭제에 다른 like 삭제 
	public LikeButton deleteLike(LikeButton likebutton) throws Exception{
		return sqlSession.selectOne("JoinMemberDao.deleteLike", likebutton);
	}
	
	//like한 사람 리스트
	public List<LikeButton> likePeopleList(LikeButton likebutton) throws Exception{
		List<LikeButton> like_people_list = sqlSession.selectList("JoinMemberDao.likePeopleList", likebutton);
		return like_people_list;
	}
}
