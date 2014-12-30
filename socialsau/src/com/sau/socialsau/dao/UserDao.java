package com.sau.socialsau.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sau.socialsau.dao.UserDao;
import com.sau.socialsau.dto.User;
import com.sau.socialsau.dto.ViewUser;
import com.sau.socialsau.util.MD5;
import com.sau.socialsau.util.MailUtil;

public class UserDao {

	public User userAuthen(User user, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM user WHERE user.EMAIL = ? AND user.PASSWORD = ? AND user.STATUS = 'enabled'";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getEmail());
			ps.setString(2, MD5.getInstance(user.getPassword()));
			rs = ps.executeQuery();
			while (rs.next()) {
				User userObj = new User();
				// User
				userObj.setUserId(rs.getInt("USER_ID"));
				userObj.setFirstname(rs.getString("FIRSTNAME"));
				userObj.setLastname(rs.getString("LASTNAME"));
				userObj.setEmail(rs.getString("EMAIL"));
				userObj.setPassword(rs.getString("PASSWORD"));
				userObj.setBirthday(rs.getDate("BIRTHDAY"));
				userObj.setRole(rs.getString("ROLE"));
				userObj.setUrlProfile(rs.getString("URL_PROFILE"));
				userObj.setConfirmCode(rs.getString("CONFIRM_CODE"));
				userObj.setStatus(rs.getString("STATUS"));
				userObj.setColorProfile(rs.getString("COLOR_PROFILE"));
				userObj.setImageProfile(rs.getString("IMAGE_PROFILE"));
				return userObj;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ps.close();
		}
		return null;
	}

	public User findByEmail(String email, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM user WHERE user.EMAIL = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
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
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ps.close();
		}
		return null;
	}

	public void saveUser(User user, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO user ");
		sql.append("(ROLE, FIRSTNAME,");
		sql.append("LASTNAME, PASSWORD,");
		sql.append("EMAIL, URL_PROFILE,");
		sql.append("BIRTHDAY, STATUS,");
		sql.append("CONFIRM_CODE, COLOR_PROFILE)");
		sql.append("VALUES ('user', ?, ?, ?, ?, ?, ?, 'disable', ?, '#54A854')");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, user.getFirstname());
			ps.setString(2, user.getLastname());
			
			// เข้ารหัสได้ 32 ตัวอักษร
			ps.setString(3, MD5.getInstance(user.getPassword()));
			ps.setString(4, user.getEmail());
			
			// ตัดข้อความ thanatornho53  ให้เป็น urlProfile
			if (user.getEmail().contains("@") == true) {
				String[] strSplit1 = user.getEmail().split("@");
				String[] strSplit2 = strSplit1[0].split("\\.");
				ps.setString(5, strSplit2[0] + strSplit2[1]);
			}else {
				ps.setString(5, user.getEmail());
			}
			
			ps.setDate(6, new java.sql.Date(user.getBirthday().getTime()));
			
			// Confirm Code โค้ดยืนยัน 20 ตัว
			user.setConfirmCode(MailUtil.generateConfirmCode(20));
			ps.setString(7, user.getConfirmCode());
			
			ps.executeUpdate();
			
			// ส่งเมล์
			MailUtil.sendMail(user.getEmail(), user.getConfirmCode());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ps.close();
		}
	}

	public boolean findByConfirmCode(String confirmEmail, String confirmCode, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) AS CONFIRMCODE FROM user WHERE user.EMAIL = ? AND user.CONFIRM_CODE = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, confirmEmail);
			ps.setString(2, confirmCode);
			rs = ps.executeQuery();
			while (rs.next()) {
				int statusConfirmCode = rs.getInt("CONFIRMCODE");
				if (statusConfirmCode == 1) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ps.close();
		}
		return false;
	}

	public void updateStatusUser(String confirmEmail, String confirmCode, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		String sql = "UPDATE user SET user.STATUS = 'enabled' WHERE user.EMAIL = ? AND user.CONFIRM_CODE = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, confirmEmail);
			ps.setString(2, confirmCode);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ps.close();
		}
	}
	
	public User findUserByUserId(Integer userId, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM user ");
		sql.append("WHERE user.USER_ID = ?");
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
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ps.close();
		}
		return null;
	}
	
	public User findUserByURLProfile(String urlProfile, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM user ");
		sql.append("WHERE user.URL_PROFILE = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, urlProfile);
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
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ps.close();
		}
		return null;
	}
	
	public void updatePassword(Integer userId, String password, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE user SET user.PASSWORD = ? WHERE user.USER_ID = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, MD5.getInstance(password));
			ps.setInt(2, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ps.close();
		}
	}
	
	public List<User> findAllUser(Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM user ");
		List<User> users = new ArrayList<User>();
		try {
			ps = conn.prepareStatement(sql.toString());
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
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ps.close();
		}
		return null;
	}
	

}
