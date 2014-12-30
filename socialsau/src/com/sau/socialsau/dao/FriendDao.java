package com.sau.socialsau.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sau.socialsau.dto.User;

public class FriendDao {

	public List<User> findFrindByUserId(Integer userId, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM user ");
		sql.append("WHERE user.USER_ID NOT IN(?)");
		List<User> users = new ArrayList<User>();
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
				users.add(user);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
		return users;
	}
	
}
