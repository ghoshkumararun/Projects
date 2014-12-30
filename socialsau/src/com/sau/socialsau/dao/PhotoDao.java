package com.sau.socialsau.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.PSource;

import com.sau.socialsau.dto.Photo;
import com.sau.socialsau.dto.User;

public class PhotoDao {

	public List<Photo> findAllPhoto(Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM photo");
		List<Photo> photos = new ArrayList<Photo>();
		try {
			ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Photo photo = new Photo();
				photo.setPhotoId(rs.getInt("PHOTO_ID"));
				photo.setUserId(rs.getInt("USER_ID"));
				photo.setPhotoUrl(rs.getString("PHOTO_URL"));
				photo.setPhotoSize(rs.getLong("PHOTO_SIZE"));
				photo.setPhotoCreate(rs.getTimestamp("PHOTO_CREATE"));
				photos.add(photo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			ps.close();
		}
		return photos;
	}
	
	public User findImageProfileByUserId(Integer userId, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM user WHERE user.USER_ID = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				// User
				user.setUserId(rs.getInt("USER_ID"));
				user.setFirstname(rs.getString("FIRSTNAME"));
				user.setLastname(rs.getString("LASTNAME"));
				user.setEmail(rs.getString("EMAIL"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setBirthday(rs.getDate("BIRTHDAY"));
				user.setRole(rs.getString("ROLE"));
				user.setUrlProfile(rs.getString("URL_PROFILE"));
				user.setConfirmCode(rs.getString("CONFIRM_CODE"));
				user.setStatus(rs.getString("STATUS"));
				user.setColorProfile(rs.getString("COLOR_PROFILE"));
				user.setImageProfile(rs.getString("IMAGE_PROFILE"));
				return user;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			ps.close();
		}
		return null;
	}
	
	public List<Photo> findAllPhotoByUserId(Integer userId, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM photo WHERE photo.USER_ID = ?");
		List<Photo> photos = new ArrayList<Photo>();
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Photo photo = new Photo();
				photo.setPhotoId(rs.getInt("PHOTO_ID"));
				photo.setUserId(rs.getInt("USER_ID"));
				photo.setPhotoUrl(rs.getString("PHOTO_URL"));
				photo.setPhotoSize(rs.getLong("PHOTO_SIZE"));
				photo.setPhotoCreate(rs.getTimestamp("PHOTO_CREATE"));
				photos.add(photo);
			}
			return photos;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			ps.close();
		}
		return null;
	}
	
	public Photo findPhotoByPhotoId(String photoId, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM photo WHERE photo.PHOTO_ID = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, photoId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Photo photo = new Photo();
				photo.setPhotoId(rs.getInt("PHOTO_ID"));
				photo.setUserId(rs.getInt("USER_ID"));
				photo.setPhotoUrl(rs.getString("PHOTO_URL"));
				photo.setPhotoSize(rs.getLong("PHOTO_SIZE"));
				photo.setPhotoCreate(rs.getTimestamp("PHOTO_CREATE"));
				return photo;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			ps.close();
		}
		return null;
	}
	
	public Photo findPhotoEnabled(Integer userId, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM user ");
		sql.append("INNER JOIN photo ON photo.USER_ID = user.USER_ID ");
		sql.append("WHERE photo.PHOTO_STATUS = 'enabled' AND user.USER_ID = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Photo photo = new Photo();
				photo.setPhotoId(rs.getInt("PHOTO_ID"));
				photo.setUserId(rs.getInt("USER_ID"));
				photo.setPhotoUrl(rs.getString("PHOTO_URL"));
				photo.setPhotoSize(rs.getLong("PHOTO_SIZE"));
				photo.setPhotoCreate(rs.getTimestamp("PHOTO_CREATE"));
				return photo;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			ps.close();
		}
		return null;
	}
	
	
}
