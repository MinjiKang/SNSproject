package com.manage.biz.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.manage.biz.vo.Friends;
import com.manage.biz.vo.JoinMember;


@Repository
public class JoinMemberDaoImpl implements JoinMemberDao{
	
	@Autowired
	SqlSessionTemplate sqlSession;

	/*회원가입 */
	public int insertJoinMember(JoinMember insert_member) throws Exception{
		sqlSession.insert("JoinMemberDao.insertMember", insert_member);
		int m_seq = insert_member.getMember_no();
		return m_seq;
	}
	
	/*회원정보 수정*/
	public void updateUserInfo(JoinMember joinmember) throws Exception{
		System.out.println(joinmember.getMember_id());
		System.out.println(joinmember.getMember_password());
		sqlSession.update("JoinMemberDao.UpdateUserInfo", joinmember);
	}
	
	/*로그인 처리*/
	public JoinMember findByUserIdAndPassword(String userId, String password) throws Exception{
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("member_id", userId);
        paramMap.put("member_password", password);

        return sqlSession.selectOne("JoinMemberDao.selectLoginUser", paramMap);
	}
	
	/*회원탈퇴*/
	public JoinMember deleteMemeber(JoinMember member) throws Exception{
		return sqlSession.selectOne("JoinMemberDao.deleteMember", member);
	}
	
	/*비밀번호 찾기*/
	public int findPassword(JoinMember joinmember) throws Exception{
		int m_password = sqlSession.selectOne("JoinMemberDao.selectFind", joinmember);
		return m_password;
	}
	
	/*비밀번호 수정*/
	public void updatePassword(JoinMember joinmember) throws Exception{
		sqlSession.update("JoinMemberDao.UpdatePassword", joinmember);
	}

	/*아이디 비밀번호 일치 여부*/
	public int matching(JoinMember joinmember) throws Exception{
		int matching_ok = sqlSession.selectOne("JoinMemberDao.matching", joinmember);
		return matching_ok;
	}
	
	/*아이디 중복검사*/
	public int CheckID(JoinMember joinmember) throws Exception{
		int m_id = sqlSession.selectOne("JoinMemberDao.selectCheckID", joinmember);
		return m_id;
	}	
	
	/*이름으로 사람들 찾기*/
	public List<JoinMember> findPeople(JoinMember joinmember) throws Exception{
		System.out.println("dao list");
		List<JoinMember> peoplelist = sqlSession.selectList("JoinMemberDao.findPeople", joinmember);
		return peoplelist;
	}
	
	/*내 친구목록*/
	public List<JoinMember> myfriend(Friends friends) throws Exception{
		
		List<JoinMember> my_friend_list = sqlSession.selectList("JoinMemberDao.myfriend", friends);
		return my_friend_list;
	}
	
	/*친구 신청하기*/
	public int addfriend(Friends friends) throws Exception{
		int m_friend = sqlSession.insert("JoinMemberDao.addfriend", friends);
		return m_friend ;
	}
	
	public int addfriend2(Friends friends) throws Exception{
		return sqlSession.insert("JoinMemberDao.addfriend2", friends);
	}
	
	/*친구신청목록*/
	public List<Friends> selectfriends() throws Exception{
		List<Friends> friendslist = sqlSession.selectList("JoinMemberDao.selectfriends");
		return friendslist;
	}
	
	/*친구 수락*/
	public void allowfriends(Friends friends) throws Exception{
		sqlSession.update("JoinMemberDao.allowfriends", friends);
	}
}
