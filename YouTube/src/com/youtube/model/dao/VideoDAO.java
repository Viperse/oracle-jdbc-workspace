package com.youtube.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.youtube.model.vo.Category;
import com.youtube.model.vo.Channel;
import com.youtube.model.vo.Video;

import config.ServerInfo;

public class VideoDAO implements VideoDAOTemplate{
	
	private Properties p = new Properties();
	
	public VideoDAO() {
		try {
			p.load(new FileInputStream("src/config/jdbc.properties"));
			Class.forName(ServerInfo.DRIVER_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Connection getConnect() throws SQLException {
		Connection conn = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
		return conn;
	}

	@Override
	public void closeAll(PreparedStatement st, Connection conn) throws SQLException {
		st.close();
		conn.close();
	}

	@Override
	public void closeAll(ResultSet rs, PreparedStatement st, Connection conn) throws SQLException {
		rs.close();
		closeAll(st, conn);
	}

	@Override
	public int addVideo(Video video) throws SQLException {
		
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("addVideo"));
		
		st.setString(1, video.getVideoTitle());
		st.setString(2, video.getVideoUrl());
		st.setString(3, video.getVideoPhoto());
		st.setInt(4, video.getCategory().getCategoryCode());
		st.setInt(5, video.getChannel().getChannelCode());
		st.setString(6, video.getMember().getMemberId());
		
		int result = st.executeUpdate();
		closeAll(st, conn);
		
		return result;
	}

	@Override
	public int updateVideo(Video video) throws SQLException {
		
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("updateVideo"));
		
		st.setString(1, video.getVideoTitle());
		st.setInt(2, video.getVideoCode());
		
		int result = st.executeUpdate();
		closeAll(st, conn);
		
		return result;
	}

	@Override
	public int deleteVideo(int videoCode) throws SQLException {
		
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("deleteVideo"));
		
		st.setInt(1, videoCode);
		
		int result = st.executeUpdate();
		
		return result;
	}

	@Override
	public ArrayList<Video> videoAllList() throws SQLException {
		
		ArrayList<Video> videoList = new ArrayList<>();
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("videoAllList"));
		
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			Video video = new Video();
			video.setVideoCode(rs.getInt("video_code"));
			video.setVideoTitle(rs.getString("video_title"));
			video.setVideoDate(rs.getDate("video_date"));
			video.setVideoUrl(rs.getString("video_url"));
			video.setVideoPhoto(rs.getString("video_photo"));
			
			Channel channel = new Channel();
			channel.setChannelName(rs.getString("channel_name"));
			video.setChannel(channel);
			
			videoList.add(video);
		}
		closeAll(rs, st, conn);
		return videoList;
	}

	@Override
	public ArrayList<Video> channelVideoList(int channelCode) throws SQLException {
		
		ArrayList<Video> cvList = new ArrayList<>();
		
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("channelVideoList"));
		
		st.setInt(1, channelCode);
		
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			Video video = new Video();
			video.setVideoCode(rs.getInt("video_code"));
			video.setVideoTitle(rs.getString("video_title"));
			video.setVideoDate(rs.getDate("video_date"));
			video.setVideoUrl(rs.getString("video_url"));
			video.setVideoPhoto(rs.getString("video_photo"));
			
			Channel channel = new Channel();
			channel.setChannelName(rs.getString("channel_name"));
			video.setChannel(channel);
			
			cvList.add(video);
			
		}
		closeAll(rs, st, conn);
		return cvList;
	}

	@Override
	public Video viewVideo(int videoCode) throws SQLException {
		
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("viewVideo"));
		
		st.setInt(1, videoCode);
		
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			Video video = new Video();
			video.setVideoCode(rs.getInt("video_code"));
			video.setVideoTitle(rs.getString("video_title"));
			video.setVideoDate(rs.getDate("video_date"));
			video.setVideoUrl(rs.getString("video_url"));
			video.setVideoPhoto(rs.getString("video_photo"));
			
			Channel channel = new Channel();
			channel.setChannelName(rs.getString("channel_name"));
			video.setChannel(channel);
			
			return video;
		}
		return null;
	}

	@Override
	public ArrayList<Category> categoryList() throws SQLException {
		
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("categoryList"));
		
		ResultSet rs = st.executeQuery();
		ArrayList<Category> list = new ArrayList<>();
		while(rs.next()) {
			list.add((new Category(rs.getInt("category_code"), rs.getString("category_name"))));
			
		}
		return list;
	}

}
