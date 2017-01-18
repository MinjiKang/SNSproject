package com.manage.biz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manage.biz.dao.JoinMemberDao;
import com.manage.biz.vo.JoinMember;


@Service
public class JoinMemberServiceImpl implements JoinMemberService{
	@Autowired
	private JoinMemberDao joinmemberDao;
	
	//회원가입
	public int insertJoinMember(JoinMember joinmember) throws Exception{
		int m_seq =  joinmemberDao.insertJoinMember(joinmember);
		return m_seq;
	}
	
	public JoinMember getAllMember(String member_id) throws Exception{
		return joinmemberDao.selectJoinMember(member_id);
	}

	//로그인 처리
    public JoinMember findByUserIdAndPassword(String userId, String password) throws Exception{
		return joinmemberDao.findByUserIdAndPassword(userId, password);
	}
	    
}