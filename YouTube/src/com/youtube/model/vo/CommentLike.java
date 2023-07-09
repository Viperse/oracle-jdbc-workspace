package com.youtube.model.vo;

import java.util.Date;

public class CommentLike {
	
	private int commLikeCode;
	private Date commLikeDate;
	private VideoComment videoComment;
	private Member member;
	
	public CommentLike() {}

	public CommentLike(int commLikeCode, Date commLikeDate, VideoComment videoComment, Member member) {
		this.commLikeCode = commLikeCode;
		this.commLikeDate = commLikeDate;
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
		return commLikeDate;
	}

	public void setConnLikeDate(Date commLikeDate) {
		this.commLikeDate = commLikeDate;
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
		return "CommentLike [commLikeCode=" + commLikeCode + ", commLikeDate=" + commLikeDate + ", videoComment="
				+ videoComment + ", member=" + member + "]";
	}
	
}
