package com.manage.biz.service;


import com.manage.biz.vo.JoinMember;

public interface JoinMemberService {
	int insertJoinMember(JoinMember joinmember) throws Exception; //ȸ������
	JoinMember getAllMember(String member_id) throws Exception;
	JoinMember findByUserIdAndPassword(String userId, String password) throws Exception; //�α���	
	JoinMember removeMember(JoinMember member) throws Exception; //ȸ��Ż��
	int findPassword(JoinMember joinmember) throws Exception; //��й�ȣ ã��
	void updatePassword(JoinMember joinmember) throws Exception; //��й�ȣ ����
	int matching(JoinMember joinmember) throws Exception; //���̵� �н����� ��ġ ���� 
	int CheckID(JoinMember joinmember) throws Exception; //���̵� �ߺ��˻�
}
