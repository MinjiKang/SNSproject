package com.manage.biz.dao;

import java.util.List;

import com.manage.biz.vo.Friends;
import com.manage.biz.vo.JoinMember;


public interface JoinMemberDao {

	int insertJoinMember(JoinMember insert_member) throws Exception; //ȸ������ 
	void updateUserInfo(JoinMember joinmember) throws Exception; //ȸ������ ����
    JoinMember findByUserIdAndPassword(String userId, String password) throws Exception; //�α���
    JoinMember deleteMemeber(JoinMember member) throws Exception; //ȸ��Ż��
    int findPassword(JoinMember joinmember) throws Exception; //��й�ȣ ã��
    void updatePassword(JoinMember joinmember) throws Exception; //��й�ȣ ����
    int matching(JoinMember joinmember) throws Exception; //���̵� �н����� ��ġ ���� 
    int CheckID(JoinMember joinmember) throws Exception; //���̵� �ߺ� �˻� 
    List<JoinMember> findPeople(JoinMember joinmember) throws Exception; //�̸����� ����� ã��
    List<JoinMember> myfriend(Friends friends) throws Exception; //�� ģ�����
    int addfriend(Friends friends) throws Exception; //ģ�� ��û�ϱ�
    int addfriend2(Friends friends) throws Exception; 
    List<Friends> selectfriends() throws Exception; // ģ����û���
    void allowfriends(Friends friends) throws Exception; //ģ�� ����
}
