package com.manage.biz.dao;

import com.manage.biz.vo.JoinMember;


public interface JoinMemberDao {

	int insertJoinMember(JoinMember insert_member) throws Exception; //ȸ������ 
	JoinMember selectJoinMember(String member_id) throws Exception;
    JoinMember findByUserIdAndPassword(String userId, String password) throws Exception; //�α���
    JoinMember deleteMemeber(JoinMember member) throws Exception; //ȸ��Ż��
    int findPassword(JoinMember joinmember) throws Exception; //��й�ȣ ã��
    void updatePassword(JoinMember joinmember) throws Exception; //��й�ȣ ����
    int matching(JoinMember joinmember) throws Exception; //���̵� �н����� ��ġ ���� 
    int CheckID(JoinMember joinmember) throws Exception; //���̵� �ߺ� �˻� 
}
