package com.youtube.model.vo;

import java.util.Date;

public class Subscribe {
	
	private int subsCode;
	private Date susDate;
	private Member member;
	private Channel channel;
	
	public Subscribe() {}

	public Subscribe(int subsCode, Date susDate, Member member, Channel channel) {
		this.subsCode = subsCode;
		this.susDate = susDate;
		this.member = member;
		this.channel = channel;
	}

	public int getSubsCode() {
		return subsCode;
	}

	public void setSubsCode(int subsCode) {
		this.subsCode = subsCode;
	}

	public Date getSusDate() {
		return susDate;
	}

	public void setSusDate(Date susDate) {
		this.susDate = susDate;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	@Override
	public String toString() {
		return "Subscribe [subsCode=" + subsCode + ", susDate=" + susDate + ", member=" + member + ", channel="
				+ channel + "]";
	}

}
