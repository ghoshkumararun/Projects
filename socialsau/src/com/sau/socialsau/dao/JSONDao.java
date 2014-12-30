package com.sau.socialsau.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.sau.socialsau.dto.Chart;
import com.sau.socialsau.dto.Contact;
import com.sau.socialsau.dto.Group;
import com.sau.socialsau.dto.Score;
import com.sau.socialsau.dto.TutorialJSON;
import com.sau.socialsau.dto.User;
import com.sau.socialsau.util.DB;

public class JSONDao {

	public List<Group> loadAllGroup(Connection conn) throws SQLException {
		PreparedStatement ps = null;
		List<Group> list = new ArrayList<Group>();
		try {
			ps = conn.prepareStatement("SELECT * FROM `group`");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Group group = new Group();
				group.setGroupId(rs.getInt("GROUP_ID"));
				group.setGroupName(rs.getString("GROUP_NAME"));
				list.add(group);
			}
			return list;
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			ps.close();
		}
		return null;
	}
	
	
	public List<TutorialJSON> searchTutorialByGroupId(Integer groupId, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM tutorial ");
		// sql.append("WHERE ");
		if (groupId != 0) {
			sql.append("WHERE tutorial.GROUP_ID = ? ");
			// sql.append("tutorial.GROUP_ID = ? AND ");
		}
		// sql.append("tutorial.`STATUS` = 'A' ");
		sql.append("ORDER BY tutorial.TUTORIAL_UPDATE DESC");
		try {
			ps = conn.prepareStatement(sql.toString());
			if (groupId != 0) {
				ps.setInt(1, groupId);
			}
			ResultSet rs = ps.executeQuery();
			List<TutorialJSON> tutorialsJSON = new ArrayList<TutorialJSON>();
			while (rs.next()) {
				TutorialJSON tutorialJSON = new TutorialJSON();
				tutorialJSON.setTutorialId(rs.getInt("TUTORIAL_ID"));
				tutorialJSON.setUserId(rs.getInt("USER_ID"));
				tutorialJSON.setGroupId(rs.getInt("GROUP_ID"));
				tutorialJSON.setTutorialName(rs.getString("TUTORIAL_NAME"));
				tutorialJSON.setTutorialDetail(rs.getString("TUTORIAL_DETAIL"));
				tutorialJSON.setTutorialDetailReport(rs.getString("TUTORIAL_DETAIL_REPORT"));
				tutorialJSON.setTutorialImage(rs.getString("TUTORIAL_IMAGE"));
				tutorialJSON.setUserUpdate(rs.getString("USER_UPDATE"));
				tutorialJSON.setUserCreate(rs.getString("USER_CREATE"));
				tutorialJSON.setTutorialUpdate(rs.getTimestamp("TUTORIAL_UPDATE"));
				tutorialJSON.setTutorialCreate(rs.getTimestamp("TUTORIAL_CREATE"));
				tutorialJSON.setStatus(rs.getString("STATUS"));
				
				
				// ถ้า response เป็น JSON ผลลัพท์จะเป็น ISODatetime 2014-07-17T08:31:13
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				tutorialJSON.setTutorialUpdateString(df.format(rs.getTimestamp("TUTORIAL_UPDATE")));
				
				tutorialsJSON.add(tutorialJSON);
			}
			if (tutorialsJSON != null && !tutorialsJSON.isEmpty()) {
				return tutorialsJSON;
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			ps.close();
		}
		return null;
	}
	
	public List<Score> findScore(String userId, String tutorialId, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM score WHERE score.USER_ID = ? AND score.TUTORIAL_ID = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, userId);
			ps.setString(2, tutorialId);
			ResultSet rs = ps.executeQuery();
			List<Score> scores = new ArrayList<Score>();
			while (rs.next()) {
				Score score = new Score();
				score.setScoreId(rs.getInt("SCORE_ID"));
				score.setUserId(rs.getInt("USER_ID"));
				score.setTutorialId(rs.getInt("TUTORIAL_ID"));
				score.setTestNo(rs.getInt("TEST_NO"));
				score.setScoreCorrect(rs.getInt("SCORE_CORRECT"));
				score.setMaxScore(rs.getInt("MAX_SCORE"));
				score.setPercentCorrect(rs.getDouble("PERCENT_CORRECT"));
				score.setScoreCreate(rs.getTimestamp("SCORE_CREATE"));
				scores.add(score);
			}
			return scores;
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
		return null;
	}
	
	public List<User> findFriendByUserId(String userId, String keywordsSearch, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM user ");
		sql.append("WHERE user.USER_ID NOT IN(?) ");
		sql.append("AND user.FIRSTNAME LIKE ? ");
		sql.append("OR user.EMAIL LIKE ? ");
		sql.append("OR user.URL_PROFILE LIKE ?");
		List<User> users = new ArrayList<User>();
		try {
			String keywords = keywordsSearch + "%";
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, userId);
			ps.setString(2, keywords);
			ps.setString(3, keywords);
			ps.setString(4, keywords);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("USER_ID"));
				user.setFirstname(rs.getString("FIRSTNAME"));
				user.setLastname(rs.getString("LASTNAME"));
				user.setEmail(rs.getString("EMAIL"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setBirthday(rs.getDate("BIRTHDAY"));
				user.setRole(rs.getString("ROLE"));
				user.setConfirmCode(rs.getString("CONFIRM_CODE"));
				user.setStatus(rs.getString("STATUS"));
				user.setUrlProfile(rs.getString("URL_PROFILE"));
				users.add(user);
			}
			if (!users.isEmpty()) {
				return users;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
		return null;
	}
	
	public List<Contact> findAllContactByStatus(String status, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM contact ");
		sql.append("WHERE contact.CONTACT_STATUS = ?");
		List<Contact> contacts = new ArrayList<Contact>();
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, status);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Contact contact = new Contact();
				contact.setContactId(rs.getInt("CONTACT_ID"));
				contact.setUserId(rs.getInt("USER_ID"));
				contact.setContactFirstname(rs.getString("CONTACT_FIRSTNAME"));
				contact.setContactLastname(rs.getString("CONTACT_LASTNAME"));
				contact.setContactEmail(rs.getString("CONTACT_EMAIL"));
				contact.setContactDetail(rs.getString("CONTACT_DETAIL"));
				contact.setContactCreate(rs.getTimestamp("CONTACT_CREATE"));
				
				// ถ้า response เป็น JSON ผลลัพท์จะเป็น ISODatetime 2014-07-17T08:31:13
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				contact.setContactCreateStr(df.format(rs.getTimestamp("CONTACT_CREATE")));
				
				contact.setContactStatus(rs.getString("CONTACT_STATUS"));
				contacts.add(contact);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
		return contacts;
	}
	
	public List<Contact> searchAllContact(String keyword, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM contact ");
		sql.append("WHERE contact.CONTACT_DETAIL LIKE ? OR ");
		List<Contact> searchContact = new ArrayList<Contact>();
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, keyword + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Contact contact = new Contact();
				contact.setContactId(rs.getInt("CONTACT_ID"));
				contact.setUserId(rs.getInt("USER_ID"));
				contact.setContactFirstname(rs.getString("CONTACT_FIRSTNAME"));
				contact.setContactLastname(rs.getString("CONTACT_LASTNAME"));
				contact.setContactEmail(rs.getString("CONTACT_EMAIL"));
				contact.setContactDetail(rs.getString("CONTACT_DETAIL"));
				contact.setContactCreate(rs.getTimestamp("CONTACT_CREATE"));
				contact.setContactStatus(rs.getString("CONTACT_STATUS"));
				searchContact.add(contact);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
		return searchContact;
	}
	
}