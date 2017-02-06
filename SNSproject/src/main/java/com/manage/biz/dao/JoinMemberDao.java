package com.manage.biz.dao;

import java.util.List;

import com.manage.biz.vo.Board;
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
    void addfriend(Friends friends) throws Exception; 
    void addfriend2(Friends friends) throws Exception;
    List<Friends> selectfriends(Friends friends) throws Exception; // ģ����û���
    List<Friends> request(Friends friends) throws Exception; //ģ�� ��û
    void allowfriends(Friends friends) throws Exception; //ģ�� ����
    void cancelfriends(Friends friends) throws Exception; //ģ�� ��û ����
    void stopfriend(Friends friends) throws Exception; //ģ�� ����
    int insertBoardContent(Board board_contents) throws Exception; //�Խñ� �ۼ�
    List<Board> listBoardContents(JoinMember joinmember) throws Exception; //�Խñ� �ۼ�
    Board deleteBoardContent(Board board) throws Exception; //�Խù� ����
}
