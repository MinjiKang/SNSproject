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
    
    //회원탈퇴
    public JoinMember removeMember(JoinMember member) throws Exception{
    	return joinmemberDao.deleteMemeber(member);
    }
    
    //비밀번호 찾기
    public int findPassword(JoinMember joinmember)throws Exception{
    	int m_password = joinmemberDao.findPassword(joinmember);
    	return m_password;
    }
    
    //비밀번호 수정
    public void updatePassword(JoinMember joinmember) throws Exception{
    	joinmemberDao.updatePassword(joinmember);
    }
    
    //아이디 패스워드 일치여부
    public int matching(JoinMember joinmember) throws Exception {
    	int matching_ok = joinmemberDao.matching(joinmember);
    	return matching_ok;
    }
    
    //아이디 중복검사
    public int CheckID(JoinMember joinmember)throws Exception{
    	
    	int m_id = joinmemberDao.CheckID(joinmember);
    	return m_id;
    }

}