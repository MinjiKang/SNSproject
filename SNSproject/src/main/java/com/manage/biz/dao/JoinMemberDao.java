package com.manage.biz.dao;

import java.util.HashMap;

import com.manage.biz.vo.JoinMember;


public interface JoinMemberDao {

	int insertJoinMember(JoinMember insert_member) throws Exception;
	JoinMember selectJoinMember(String member_id) throws Exception;
	int loginIdentify(HashMap<String, String> hstParam);
}
