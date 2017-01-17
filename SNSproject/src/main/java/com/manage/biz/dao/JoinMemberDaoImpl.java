package com.manage.biz.dao;


import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.manage.biz.vo.JoinMember;

@Repository
public class JoinMemberDaoImpl implements JoinMemberDao{
	
	@Autowired
	SqlSessionTemplate sqlSession;

	/*회원가입*/
	public int insertJoinMember(JoinMember insert_member) throws Exception{
		sqlSession.insert("JoinMemberDao.insertJoinMember", insert_member);
		int m_seq = insert_member.getMember_no();
		return m_seq;
	}
	
	public JoinMember selectJoinMember(String member_id) throws Exception{
		return sqlSession.selectOne("JoinMemberDao.JoinMember", member_id);
	}
	
	public String loginIdentify(HashMap<String, String> hstParam){
		return sqlSession.selectOne("JoinMemberDao.loginIdentify", hstParam);
	}
	
}
