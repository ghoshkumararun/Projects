package com.sau.socialsau.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sau.socialsau.dto.User;

public class SettingDao {

	public void updatePersonalDate(User user, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE user SET ");
		sql.append("user.FIRSTNAME = ?, ");
		sql.append("user.LASTNAME = ?, ");
		sql.append("user.BIRTHDAY = ? ");
		sql.append("WHERE user.USER_ID = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, user.getFirstname());
			ps.setString(2, user.getLastname());
			ps.setDate(3, new java.sql.Date(user.getBirthday().getTime()));
			ps.setInt(4, user.getUserId());
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
	}
	
	public void updateColorProfile(Integer userId, String colorProfile, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE user SET user.COLOR_PROFILE = ? WHERE user.USER_ID = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, colorProfile);
			ps.setInt(2, userId);
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
	}
	
	public void updateImageProfileByUserId(User user, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE user SET ");
		sql.append("user.IMAGE_PROFILE = ? ");
		sql.append("WHERE user.USER_ID = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, user.getImageProfile());
			ps.setInt(2, user.getUserId());
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
	}
	
}
