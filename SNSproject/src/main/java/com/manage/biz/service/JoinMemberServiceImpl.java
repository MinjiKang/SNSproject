package com.manage.biz.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manage.biz.dao.JoinMemberDao;
import com.manage.biz.vo.JoinMember;

@Service
public class JoinMemberServiceImpl implements JoinMemberService{
	@Autowired
	private JoinMemberDao joinmemberDao;
	
	public int insertJoinMember(JoinMember joinmember) throws Exception{
		int m_seq =  joinmemberDao.insertJoinMember(joinmember);
		return m_seq;
	}
	
	public JoinMember getAllMember(String member_id) throws Exception{
		return joinmemberDao.selectJoinMember(member_id);
	}
	
	public int loginCheck(HashMap<String, String> hstParam){
		int identify = joinmemberDao.loginIdentify(hstParam);
		return identify;
	}
	
}