package com.youtube.model.vo;

import java.util.Date;

public class CommentLike {
	
	private int commLikeCode;
	private Date connLikeDate;
	private VideoComment videoComment;
	private Member member;
	
	public CommentLike() {}

	public CommentLike(int commLikeCode, Date connLikeDate, VideoComment videoComment, Member member) {
		this.commLikeCode = commLikeCode;
		this.connLikeDate = connLikeDate;
		this.videoComment = videoComment;
		this.member = member;
	}

	public int getCommLikeCode() {
		return commLikeCode;
	}

	public void setCommLikeCode(int commLikeCode) {
		this.commLikeCode = commLikeCode;
	}

	public Date getConnLikeDate() {
		return connLikeDate;
	}

	public void setConnLikeDate(Date connLikeDate) {
		this.connLikeDate = connLikeDate;
	}

	public VideoComment getVideoComment() {
		return videoComment;
	}

	public void setVideoComment(VideoComment videoComment) {
		this.videoComment = videoComment;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Override
	public String toString() {
		return "CommentLike [commLikeCode=" + commLikeCode + ", connLikeDate=" + connLikeDate + ", videoComment="
				+ videoComment + ", member=" + member + "]";
	}
	
}
