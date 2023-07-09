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

import com.youtube.model.vo.CommentLike;
import com.youtube.model.vo.Member;
import com.youtube.model.vo.VideoComment;
import com.youtube.model.vo.VideoLike;

import config.ServerInfo;

public class CommentLikeDAO implements CommentLikeDAOTemplate{
	
	private Properties p = new Properties();
	
	public CommentLikeDAO() {
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
	public int addLike(VideoLike like) throws SQLException {
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement("addLike");
		
		st.setInt(1, like.getVideo().getVideoCode());
		st.setString(2, like.getMember().getMemberId());
		
		int result = st.executeUpdate();
		closeAll(st, conn);
		
		return result;
	}

	@Override
	public int deleteLike(int likeCode) throws SQLException {
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement("deleteLike");
		
		st.setInt(1, likeCode);
		
		int result = st.executeUpdate();
		closeAll(st, conn);
		
		
		return result;
	}

	@Override
	public int addComment(VideoComment comment) throws SQLException {
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement("addComment");
		
		st.setString(1, comment.getCommentDesc());
		st.setInt(2, comment.getVideo().getVideoCode());
		st.setString(3, comment.getMember().getMemberId());
		
		int result = st.executeUpdate();
		closeAll(st, conn);
		
		return result;
	}

	@Override
	public int updateComment(VideoComment comment) throws SQLException {
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement("updateComment");
		
		st.setString(1, comment.getCommentDesc());
		st.setInt(2, comment.getCommentCode());
		
		int result = st.executeUpdate();
		closeAll(st, conn);
		
		return result;
	}

	@Override
	public int deleteComment(int CommentCode) throws SQLException {
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement("deleteComment");
		
		st.setInt(1, CommentCode);
		
		int result = st.executeUpdate();
		closeAll(st, conn);
		
		return result;
	}

	@Override
	public ArrayList<CommentLike> videoCommentList(int videoCode) throws SQLException {
		
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("videoCommentList"));
		st.setInt(1, videoCode);
		
		ResultSet rs = st.executeQuery();
		ArrayList<CommentLike> list = new ArrayList<>();
		while(rs.next()) {
			CommentLike like = new CommentLike();
			like.setCommLikeCode(rs.getInt("comm_like_code"));
			like.setConnLikeDate(rs.getDate("comm_like_date"));
			
			VideoComment comment = new VideoComment();
			comment.setCommentCode(rs.getInt("comment_code"));
			comment.setCommentDesc(rs.getString("comment_desc"));
			like.setVideoComment(comment);
			
			Member member = new Member();
			member.setMemberNickname(rs.getString("member_nickname"));
			like.setMember(member);
			
			list.add(like);
		}
		closeAll(rs, st, conn);
		return list;
	}

	@Override
	public int addCommentLike(CommentLike like) throws SQLException {
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement("addCommentLike");
		
		st.setInt(1, like.getVideoComment().getCommentCode());
		st.setString(2, like.getMember().getMemberId());
		
		int result = st.executeUpdate();
		closeAll(st, conn);
		
		return result;
	}

	@Override
	public int deleteCommentLike(int commentCodeLike) throws SQLException {	
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement("deleteCommentLike");
		
		st.setInt(1, commentCodeLike);
		
		int result = st.executeUpdate();
		closeAll(st, conn);
		
		return result;
	}

}
