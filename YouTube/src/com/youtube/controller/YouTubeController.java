package com.youtube.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import com.youtube.model.dao.ChannelDAO;
import com.youtube.model.dao.CommentLikeDAO;
import com.youtube.model.dao.MemberDAO;
import com.youtube.model.dao.VideoDAO;
import com.youtube.model.vo.Category;
import com.youtube.model.vo.Channel;
import com.youtube.model.vo.CommentLike;
import com.youtube.model.vo.Member;
import com.youtube.model.vo.Subscribe;
import com.youtube.model.vo.Video;
import com.youtube.model.vo.VideoComment;
import com.youtube.model.vo.VideoLike;

public class YouTubeController {
	
	Member member = new Member();
	Channel channel = new Channel();
	Video video = new Video();
	CommentLike commentLike = new CommentLike();
	
	private MemberDAO memberDao = new MemberDAO();
	private ChannelDAO channelDao = new ChannelDAO();
	private VideoDAO videoDao = new VideoDAO();
	private CommentLikeDAO commentLikeDao = new CommentLikeDAO();
	
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
	
	public boolean updateVideo(Video video) {
		myChannel();
		video.setChannel(channel);
		video.setMember(member);
		try {
			if(videoDao.updateVideo(video) == 1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteVideo(int videoCode) {
		try {
			if(videoDao.deleteVideo(videoCode) == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<Video> videoAllList() {
		try {
			return videoDao.videoAllList();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Category> categoryList() {
		try {
			return videoDao.categoryList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Video> channelVideoList() {
		myChannel();
		try {
			return videoDao.channelVideoList(channel.getChannelCode());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Video viewVideo(int videoCode) {
		try {
			return videoDao.viewVideo(videoCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<CommentLike> videoCommentList(int videoCode) {
		
		try {
			return commentLikeDao.videoCommentList(videoCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean addComment(VideoComment videoComment) {
		try {
			if(commentLikeDao.addComment(videoComment) == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateComment(VideoComment videoComment) {
		try {
			if(commentLikeDao.updateComment(videoComment) == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	public boolean deleteComment(int CommentCode) {
		try {
			if(commentLikeDao.deleteComment(CommentCode) == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean addCommentLike(CommentLike commentLike) {
		try {
			if(commentLikeDao.addCommentLike(commentLike) == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteCommentLike(int commentCodeLike) {
		try {
			if(commentLikeDao.deleteCommentLike(commentCodeLike) == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean addLike(VideoLike like) {
		try {
			if(commentLikeDao.addLike(like) == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteLike(int likeCode) {
		try {
			if(commentLikeDao.deleteLike(likeCode) == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean addSubscribe(int channelCode) {
		
		Subscribe subscribe = new Subscribe();
		subscribe.setMember(member);
		
		Channel channel = new Channel();
		channel.setChannelCode(channelCode);
		subscribe.setChannel(channel);
		
		try {
			if(memberDao.addSubscribe(subscribe) == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteSubscribe(int subsCode) {
		try {
			if(memberDao.deleteSubscribe(subsCode) == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public ArrayList<Channel> mySubscribeList() {
		try {
			return memberDao.mySubscribeList(member.getMemberId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
