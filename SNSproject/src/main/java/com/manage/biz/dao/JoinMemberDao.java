package com.manage.biz.dao;

import com.manage.biz.vo.JoinMember;


public interface JoinMemberDao {

	int insertJoinMember(JoinMember insert_member) throws Exception;
	JoinMember selectJoinMember(String member_id) throws Exception;
    public JoinMember findByUserIdAndPassword(String userId, String password) throws Exception;

}
