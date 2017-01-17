package com.manage.biz.service;

import java.util.HashMap;
import java.util.List;

import com.manage.biz.vo.JoinMember;

public interface JoinMemberService {
	int insertJoinMember(JoinMember joinmember) throws Exception;
	JoinMember getAllMember(String member_id) throws Exception;
	public String loginCheck(HashMap<String, String> hstParam);
}
