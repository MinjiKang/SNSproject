package com.manage.biz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manage.biz.dao.JoinMemberDao;
import com.manage.biz.vo.Board;
import com.manage.biz.vo.Friends;
import com.manage.biz.vo.JoinMember;


@Service
public class JoinMemberServiceImpl implements JoinMemberService{
	@Autowired
	private JoinMemberDao joinmemberDao;
	
	//ȸ������
	public int insertJoinMember(JoinMember joinmember) throws Exception{
		int m_seq =  joinmemberDao.insertJoinMember(joinmember);
		return m_seq;
	}
	
	//ȸ������ ����
	public void updateUserInfo(JoinMember joinmember) throws Exception{
		joinmemberDao.updateUserInfo(joinmember);
	}

	//�α��� ó��
    public JoinMember findByUserIdAndPassword(String userId, String password) throws Exception{
		return joinmemberDao.findByUserIdAndPassword(userId, password);
	}
    
    //ȸ��Ż��
    public JoinMember removeMember(JoinMember member) throws Exception{
    	return joinmemberDao.deleteMemeber(member);
    }
    
    //��й�ȣ ã��
    public int findPassword(JoinMember joinmember)throws Exception{
    	int m_password = joinmemberDao.findPassword(joinmember);
    	return m_password;
    }
    
    //��й�ȣ ����
    public void updatePassword(JoinMember joinmember) throws Exception{
    	joinmemberDao.updatePassword(joinmember);
    }
    
    //���̵� �н����� ��ġ����
    public int matching(JoinMember joinmember) throws Exception {
    	int matching_ok = joinmemberDao.matching(joinmember);
    	return matching_ok;
    }
    
    //���̵� �ߺ��˻�
    public int CheckID(JoinMember joinmember)throws Exception{
    	
    	int m_id = joinmemberDao.CheckID(joinmember);
    	return m_id;
    }
    
    //�̸����� ����� ã��
    public List<JoinMember> findPeople(JoinMember joinmember) throws Exception{
    	List<JoinMember> peoplelist = joinmemberDao.findPeople(joinmember);
    	return peoplelist;
	}
    
    //�� ģ�����
    public List<JoinMember> myfriend(Friends friends) throws Exception{
    	List<JoinMember> my = joinmemberDao.myfriend(friends);
    	return my;
	}
    
    //ģ�� ��õ�ϱ�
	public void addfriend(Friends friends) throws Exception{
		joinmemberDao.addfriend(friends);
		joinmemberDao.addfriend2(friends);
	}
	
	//ģ�� ��û���
	public List<Friends> selectfriends(Friends friends) throws Exception{
		List<Friends> friendslist= joinmemberDao.selectfriends(friends);
    	return friendslist;
    }
	
	//ģ�� ��û
	public List<Friends> request(Friends friends) throws Exception{
		
	    List<Friends> re= joinmemberDao.request(friends);
	    return re;
    }
    
    //ģ�� ����
    public void allowfriends(Friends friends) throws Exception{
    	joinmemberDao.allowfriends(friends);
    }
    
    //ģ�� ��û ���
    public void cancelfriends(Friends friends) throws Exception{
    	joinmemberDao.cancelfriends(friends);
    }
    
    //ģ�� ����
    public void stopfriend(Friends friends) throws Exception{
    	joinmemberDao.stopfriend(friends);
    }
    
    //�Խñ� �ۼ�
    public int insertBoardContent(Board board_contents) throws Exception{
    	int b_seq =  joinmemberDao.insertBoardContent(board_contents);
		return b_seq;
    }

    //�Խñ� ��ȸ
    public List<Board> listBoardContents(JoinMember joinmember) throws Exception{
    	List<Board> board_contents_list = joinmemberDao.listBoardContents(joinmember);
    	return board_contents_list;
    }
    
    //�Խù� ����
    public Board removeBoardContent(Board board_num) throws Exception{
    	return joinmemberDao.deleteBoardContent(board_num);
    }

}