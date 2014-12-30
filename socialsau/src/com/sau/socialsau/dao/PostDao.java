package com.sau.socialsau.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sau.socialsau.dto.Post;
import com.sau.socialsau.dto.ViewPost;
import com.sau.socialsau.util.DB;

public class PostDao {

	public List<Integer> findUserPost(Integer userId, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT post.POST_ID FROM post ");
		sql.append("WHERE post.USER_ID = ? ");
		sql.append("ORDER BY post.POST_CREATE DESC");
		List<Integer> postIdList = new ArrayList<Integer>();
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, userId);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				postIdList.add(new Integer(rs.getInt("POST_ID")));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			ps.close();
		}
		return postIdList;
	}
	
	public ViewPost findViewPostByUserId(Integer postId, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM post ");
		sql.append("INNER JOIN user ON user.USER_ID  = post.POST_BY ");
		sql.append("WHERE post.POST_ID = ? ");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, postId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ViewPost viewPost = new ViewPost();
				
				// Post
				viewPost.setPostId(rs.getInt("POST_ID"));
				viewPost.setPostBy(rs.getInt("POST_BY"));
				viewPost.setPostDetail(rs.getString("POST_DETAIL"));
				viewPost.setPostCreate(rs.getTimestamp("POST_CREATE"));
				
				// User
				viewPost.setUserId(rs.getInt("USER_ID"));
				viewPost.setFirstname(rs.getString("FIRSTNAME"));
				viewPost.setLastname(rs.getString("LASTNAME"));
				viewPost.setEmail(rs.getString("EMAIL"));
				viewPost.setPassword(rs.getString("PASSWORD"));
				viewPost.setBirthday(rs.getDate("BIRTHDAY"));
				viewPost.setRole(rs.getString("ROLE"));
				viewPost.setUrlProfile(rs.getString("URL_PROFILE"));
				viewPost.setConfirmCode(rs.getString("CONFIRM_CODE"));
				viewPost.setStatus(rs.getString("STATUS"));
				viewPost.setColorProfile(rs.getString("COLOR_PROFILE"));
				viewPost.setImageProfile(rs.getString("IMAGE_PROFILE"));
				return viewPost;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			ps.close();
		}
		return null;
	}
	
	public void savePost(Post post, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO post ");
		sql.append("(USER_ID, ");
		sql.append("POST_BY, ");
		sql.append("POST_DETAIL, ");
		sql.append("POST_CREATE) ");
		sql.append("VALUES (?, ?, ?, NOW())");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, post.getUserId());
			ps.setInt(2, post.getPostBy());
			ps.setString(3, post.getPostDetail());
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			ps.close();
		}
	}
	
	public void deletePostByPostId(String postId, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM post WHERE post.POST_ID = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, postId);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			ps.close();
		}
	}
	
	public void updatePost(String postId, String postDetail, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE post SET post.POST_DETAIL = ? WHERE post.POST_ID = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, postDetail);
			ps.setString(2, postId);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			ps.close();
		}
	}
	
}
