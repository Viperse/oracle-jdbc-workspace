package com.youtube.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import com.youtube.model.dao.ChannelDAO;
import com.youtube.model.dao.MemberDAO;
import com.youtube.model.dao.VideoDAO;
import com.youtube.model.vo.Category;
import com.youtube.model.vo.Channel;
import com.youtube.model.vo.Member;
import com.youtube.model.vo.Video;

public class YouTubeController {
	
	Member member = new Member();
	Channel channel = new Channel();
	Video video = new Video();
	
	private MemberDAO memberDao = new MemberDAO();
	private ChannelDAO channelDao = new ChannelDAO();
	private VideoDAO videoDao = new VideoDAO();
	
	public boolean register(Member member) {
		try {
			if(memberDao.register(member) == 1) return true;
		} catch (SQLException e) {
			return false;
		}
		return false;
	}
	
	public Member login(String id, String password)  {
		
		try {
			member = memberDao.login(id, password);
			if(memberDao.login(id, password) != null) {
				return member;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean addChannel(Channel channel) {
		try {
			channel.setMember(member);
			if(channelDao.addChannel(channel) == 1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateChannel(Channel channel) {
		try {
			if(channelDao.updateChannel(channel) == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteChannel() {
		
		myChannel();
		try {
			if(channelDao.deleteChannel(channel.getChannelCode()) == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Channel myChannel() {
		try {
			channel = channelDao.myChannel(member.getMemberId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return channel;
	}
	
	public boolean addVideo(Video video) {
		myChannel();
		video.setChannel(channel);
		video.setMember(member);
		
		try {
			if(videoDao.addVideo(video) == 1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<Category> categoryList() {
		try {
			return videoDao.categoryList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
