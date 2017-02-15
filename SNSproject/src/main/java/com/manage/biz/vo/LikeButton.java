package com.manage.biz.vo;

public class LikeButton {
	int board_no;
	int member_no;
	String like_time;
	int like_count;
	int like_status;
	
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public String getLike_time() {
		return like_time;
	}
	public void setLike_time(String like_time) {
		this.like_time = like_time;
	}
	public int getLike_count() {
		return like_count;
	}
	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}
	public int getLike_status() {
		return like_status;
	}
	public void setLike_status(int like_status) {
		this.like_status = like_status;
	}
}
