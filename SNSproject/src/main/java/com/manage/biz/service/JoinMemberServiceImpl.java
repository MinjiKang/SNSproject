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
	
	//회원가입
	public int insertJoinMember(JoinMember joinmember) throws Exception{
		int m_seq =  joinmemberDao.insertJoinMember(joinmember);
		return m_seq;
	}
	
	//회원정보 수정
	public void updateUserInfo(JoinMember joinmember) throws Exception{
		joinmemberDao.updateUserInfo(joinmember);
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
    
    //이름으로 사람들 찾기
    public List<JoinMember> findPeople(JoinMember joinmember) throws Exception{
    	List<JoinMember> peoplelist = joinmemberDao.findPeople(joinmember);
    	return peoplelist;
	}
    
    //내 친구목록
    public List<JoinMember> myfriend(Friends friends) throws Exception{
    	List<JoinMember> my = joinmemberDao.myfriend(friends);
    	return my;
	}
    
    //친구 신천하기
	public int addfriend(Friends friends) throws Exception{
		int m_friend =  joinmemberDao.addfriend(friends);
		return m_friend;
	}
	
	//친구 신청목록
	public List<Friends> selectfriends(Friends friends) throws Exception{
		List<Friends> friendslist= joinmemberDao.selectfriends(friends);
    	return friendslist;

    }
	
	//친구 요청
	public List<Friends> request(Friends friends) throws Exception{
		
	    List<Friends> re= joinmemberDao.request(friends);
	    return re;
    }
    
    //친구 수락
    public void allowfriends(Friends friends) throws Exception{
    	joinmemberDao.allowfriends(friends);
    	joinmemberDao.addfriend2(friends);
    }
    
    //친구 요청 취소
    public void cancelfriends(Friends friends) throws Exception{
    	joinmemberDao.cancelfriends(friends);
    }
    
    //친구 끊기
    public void stopfriend(Friends friends) throws Exception{
    	joinmemberDao.stopfriend(friends);
    }
    
    //게시글 작성
    public int insertBoardContent(Board board_contents) throws Exception{
    	int b_seq =  joinmemberDao.insertBoardContent(board_contents);
		return b_seq;
    }

    //게시글 조회
    public List<Board> listBoardContents(JoinMember joinmember) throws Exception{
    	List<Board> board_contents_list = joinmemberDao.listBoardContents(joinmember);
    	return board_contents_list;
    }
    
    //게시물 삭제
    public Board removeBoardContent(Board board_num) throws Exception{
    	return joinmemberDao.deleteBoardContent(board_num);
    }

}