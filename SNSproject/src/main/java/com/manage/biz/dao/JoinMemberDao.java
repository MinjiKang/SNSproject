package com.manage.biz.dao;

import com.manage.biz.vo.JoinMember;


public interface JoinMemberDao {

	int insertJoinMember(JoinMember insert_member) throws Exception;
	JoinMember selectJoinMember(String member_id) throws Exception;
    JoinMember findByUserIdAndPassword(String userId, String password) throws Exception;
    JoinMember deleteMemeber(JoinMember member) throws Exception; //È¸¿øÅ»Åð
    int findPassword(JoinMember joinmember) throws Exception;
    void updatePassword(JoinMember joinmember) throws Exception;
    int matching(JoinMember joinmember) throws Exception;
}
