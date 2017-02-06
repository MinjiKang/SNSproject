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


@Repository
public class JoinMemberDaoImpl implements JoinMemberDao{
	
	@Autowired
	SqlSessionTemplate sqlSession;

	/*ȸ������ */
	public int insertJoinMember(JoinMember insert_member) throws Exception{
		sqlSession.insert("JoinMemberDao.insertMember", insert_member);
		int m_seq = insert_member.getMember_no();
		return m_seq;
	}
	
	/*ȸ������ ����*/
	public void updateUserInfo(JoinMember joinmember) throws Exception{
		System.out.println(joinmember.getMember_id());
		System.out.println(joinmember.getMember_password());
		sqlSession.update("JoinMemberDao.UpdateUserInfo", joinmember);
	}
	
	/*�α��� ó��*/
	public JoinMember findByUserIdAndPassword(String userId, String password) throws Exception{
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("member_id", userId);
        paramMap.put("member_password", password);

        return sqlSession.selectOne("JoinMemberDao.selectLoginUser", paramMap);
	}
	
	/*ȸ��Ż��*/
	public JoinMember deleteMemeber(JoinMember member) throws Exception{
		return sqlSession.selectOne("JoinMemberDao.deleteMember", member);
	}
	
	/*��й�ȣ ã��*/
	public int findPassword(JoinMember joinmember) throws Exception{
		int m_password = sqlSession.selectOne("JoinMemberDao.selectFind", joinmember);
		return m_password;
	}
	
	/*��й�ȣ ����*/
	public void updatePassword(JoinMember joinmember) throws Exception{
		sqlSession.update("JoinMemberDao.UpdatePassword", joinmember);
	}

	/*���̵� ��й�ȣ ��ġ ����*/
	public int matching(JoinMember joinmember) throws Exception{
		int matching_ok = sqlSession.selectOne("JoinMemberDao.matching", joinmember);
		return matching_ok;
	}
	
	/*���̵� �ߺ��˻�*/
	public int CheckID(JoinMember joinmember) throws Exception{
		int m_id = sqlSession.selectOne("JoinMemberDao.selectCheckID", joinmember);
		return m_id;
	}	
	
	/*�̸����� ����� ã��*/
	public List<JoinMember> findPeople(JoinMember joinmember) throws Exception{
		
		List<JoinMember> peoplelist = sqlSession.selectList("JoinMemberDao.findPeople", joinmember);
		return peoplelist;
	}
	
	/*�� ģ�����*/
	public List<JoinMember> myfriend(Friends friends) throws Exception{
		
		List<JoinMember> my = sqlSession.selectList("JoinMemberDao.myfriend", friends);
		return my;
	}
	
	/*ģ�� ��û�ϱ�*/
	public void addfriend(Friends friends) throws Exception{
		sqlSession.insert("JoinMemberDao.addfriend", friends);
	}
	
	public void addfriend2(Friends friends) throws Exception{
		sqlSession.insert("JoinMemberDao.addfriend2", friends);
	}
	
	/*ģ����û���*/
	public List<Friends> selectfriends(Friends friends) throws Exception{
		List<Friends> friendslist = sqlSession.selectList("JoinMemberDao.selectfriends",friends);
		return friendslist;
	}
	
	public List<Friends> request(Friends friends) throws Exception{
		List<Friends> re = sqlSession.selectList("JoinMemberDao.request",friends);
		return re;
	}
	
	/*ģ�� ����*/
	public void allowfriends(Friends friends) throws Exception{
		sqlSession.update("JoinMemberDao.allowfriends", friends);
	}
	
    /*ģ����û ���*/
	public void cancelfriends(Friends friends) throws Exception{
		sqlSession.delete("JoinMemberDao.cancelfriends", friends);
	}
	
	/*ģ�� ����*/
	public void stopfriend(Friends friends) throws Exception{
		sqlSession.delete("JoinMemberDao.stopfriend", friends);
	}
	
	/*�Խ��� �� �ۼ�*/
	public int insertBoardContent(Board board_contents) throws Exception{
		sqlSession.insert("JoinMemberDao.insertBoardContent", board_contents);
		int b_seq = board_contents.getBoard_no();
		return b_seq;
	} 
	
	/*�Խñ� ��ȸ*/
	public List<Board> listBoardContents(JoinMember joinmember) throws Exception{
		List<Board> board_contents_list = sqlSession.selectList("JoinMemberDao.selectBoardContent", joinmember);
		return board_contents_list;
	}
	
	/*�Խù� ����*/
	public Board deleteBoardContent(Board board) throws Exception{
		return sqlSession.selectOne("JoinMemberDao.deleteBoardContent", board);
	}
}
