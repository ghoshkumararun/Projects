package com.sau.socialsau.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sau.socialsau.dto.Group;
import com.sau.socialsau.dto.Tutorial;
import com.sau.socialsau.dto.TutorialGroup;
import com.sau.socialsau.dto.TutorialGroupUser;
import com.sau.socialsau.dto.User;
import com.sau.socialsau.util.DB;

public class TutorialDao {
	
	// step 1. save
	// Create Tutorials
	public Tutorial saveTutorial(Tutorial tutorial,User user, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO tutorial ");
		sql.append("(USER_ID, ");
		sql.append("GROUP_ID, ");
		sql.append("TUTORIAL_NAME, ");
		sql.append("TUTORIAL_DETAIL, ");
		sql.append("TUTORIAL_DETAIL_REPORT, ");
		sql.append("USER_UPDATE, ");
		sql.append("USER_CREATE, ");
		sql.append("TUTORIAL_UPDATE, ");
		sql.append("TUTORIAL_CREATE) ");
		sql.append("VALUE (?, ?, ?, ?, ?, ?, ?, NOW(), NOW())");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, tutorial.getUserId());
			ps.setInt(2, tutorial.getGroupId());
			ps.setString(3, tutorial.getTutorialName());
			ps.setString(4, tutorial.getTutorialDetail());
			ps.setString(5, tutorial.getTutorialDetailReport());
			ps.setString(6, user.getFirstname());
			ps.setString(7, user.getFirstname());
			ps.executeUpdate();
			
			String sql2 = "SELECT * FROM tutorial ORDER BY tutorial.TUTORIAL_ID DESC LIMIT 1";
			ps = conn.prepareStatement(sql2);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Tutorial tutorialObj = new Tutorial();
				// Tutorial
				tutorialObj.setTutorialId(rs.getInt("TUTORIAL_ID"));
				tutorialObj.setUserId(rs.getInt("USER_ID"));
				tutorialObj.setGroupId(rs.getInt("GROUP_ID"));
				tutorialObj.setTutorialName(rs.getString("TUTORIAL_NAME"));
				tutorialObj.setTutorialDetail(rs.getString("TUTORIAL_DETAIL"));
				tutorialObj.setTutorialDetailReport(rs.getString("TUTORIAL_DETAIL_REPORT"));
				tutorialObj.setTutorialImage(rs.getString("TUTORIAL_IMAGE"));
				tutorialObj.setUserUpdate(rs.getString("USER_UPDATE"));
				tutorialObj.setUserCreate(rs.getString("USER_CREATE"));
				tutorialObj.setTutorialUpdate(rs.getTimestamp("TUTORIAL_UPDATE"));
				tutorialObj.setTutorialCreate(rs.getTimestamp("TUTORIAL_CREATE"));
				return tutorialObj;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			ps.close();
		}
		return null;
	}
	
	// step 2. Update
	public void updateTutorialImageByTutorialId(Integer tutorialId, String tutorialImage, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE tutorial SET tutorial.TUTORIAL_IMAGE = ? WHERE tutorial.TUTORIAL_ID = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, tutorialImage);
			ps.setInt(2, tutorialId);
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ps.close();
		}
	}
	
	// ค้นหา tutorial 
	public Tutorial findTutorial(Integer tutorialId, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM tutorial WHERE tutorial.TUTORIAL_ID = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, tutorialId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Tutorial tutorial = new Tutorial();
				tutorial.setTutorialId(rs.getInt("TUTORIAL_ID"));
				tutorial.setUserId(rs.getInt("USER_ID"));
				tutorial.setGroupId(rs.getInt("GROUP_ID"));
				tutorial.setTutorialName(rs.getString("TUTORIAL_NAME"));
				tutorial.setTutorialDetail(rs.getString("TUTORIAL_DETAIL"));
				tutorial.setTutorialDetailReport(rs.getString("TUTORIAL_DETAIL_REPORT"));
				tutorial.setTutorialImage(rs.getString("TUTORIAL_IMAGE"));
				tutorial.setUserUpdate(rs.getString("USER_UPDATE"));
				tutorial.setUserCreate(rs.getString("USER_CREATE"));
				tutorial.setTutorialUpdate(rs.getTimestamp("TUTORIAL_UPDATE"));
				tutorial.setTutorialCreate(rs.getTimestamp("TUTORIAL_CREATE"));
				tutorial.setStatus(rs.getString("STATUS"));
				return tutorial;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ps.close();
		}
		return null;
	}
	
	public String findTutorialRelationship(Integer tutorialId, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sqlChapter = new StringBuffer();
		sqlChapter.append("SELECT COUNT(*) AS COUNT_CHAPTER FROM tutorial ");
		sqlChapter.append("INNER JOIN chapter ON chapter.TUTORIAL_ID = tutorial.TUTORIAL_ID ");
		sqlChapter.append("WHERE tutorial.TUTORIAL_ID = ?");
		try {
			ps = conn.prepareStatement(sqlChapter.toString());
			ps.setInt(1, tutorialId);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				if (rs1.getInt("COUNT_CHAPTER") > 0) {
					return "chapter_fail";
				}else {
					StringBuffer sqlQuestion = new StringBuffer();
					sqlQuestion.append("SELECT COUNT(*) AS COUNT_QUESTION FROM tutorial ");
					sqlQuestion.append("INNER JOIN question ON question.TUTORIAL_ID = tutorial.TUTORIAL_ID ");
					sqlQuestion.append("WHERE tutorial.TUTORIAL_ID = ?");
					
					ps = conn.prepareStatement(sqlQuestion.toString());
					ps.setInt(1, tutorialId);
					ResultSet rs2 = ps.executeQuery();
					while(rs2.next()) {
						if (rs2.getInt("COUNT_QUESTION") > 0) {
							return "question_fail";
						}else {
							StringBuffer sqlVideo = new StringBuffer();
							sqlVideo.append("SELECT COUNT(*) AS COUNT_VIDEO FROM tutorial ");
							sqlVideo.append("INNER JOIN video ON video.TUTORIAL_ID = tutorial.TUTORIAL_ID ");
							sqlVideo.append("WHERE tutorial.TUTORIAL_ID = ?");
							
							ps = conn.prepareStatement(sqlVideo.toString());
							ps.setInt(1, tutorialId);
							ResultSet rs3 = ps.executeQuery();
							while (rs3.next()) {
								if (rs3.getInt("COUNT_VIDEO") > 0) {
									return "video_fail";
								}
							}
						}
					}
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
		return "success";
	}
	
	
	// Delete ข้อมูลที่เกี่ยวกับ tutorial 
	// Delete แบบ Constraint 
	public void deleteTutorial(Integer tutorialId, Connection conn) throws SQLException {
		conn.setAutoCommit(false);
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM tutorial WHERE tutorial.TUTORIAL_ID = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, tutorialId);
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
			conn.setAutoCommit(true);
		}
	}
	
	// Edit เมื่อกดปุ่ม EDIT
	public void editPerform(Tutorial tutorial, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE tutorial SET tutorial.TUTORIAL_NAME = ?, ");
		sql.append("tutorial.GROUP_ID = ?, ");
		sql.append("tutorial.TUTORIAL_DETAIL = ?, ");
		sql.append("tutorial.TUTORIAL_DETAIL_REPORT = ?, ");
		
		if (tutorial.getTutorialImage() != null) {
			sql.append("tutorial.TUTORIAL_IMAGE = ?, ");
		}
		
		sql.append("tutorial.USER_UPDATE = ?, ");
		sql.append("tutorial.TUTORIAL_UPDATE = NOW() ");
		sql.append("WHERE tutorial.TUTORIAL_ID = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, tutorial.getTutorialName());
			ps.setInt(2, tutorial.getGroupId());
			ps.setString(3, tutorial.getTutorialDetail());
			ps.setString(4, tutorial.getTutorialDetailReport());
			
			if (tutorial.getTutorialImage() != null) {
				ps.setString(5, tutorial.getTutorialImage());
				ps.setString(6, tutorial.getUserUpdate());
				ps.setInt(7, tutorial.getTutorialId());
			}else {
				ps.setString(5, tutorial.getUserUpdate());
				ps.setInt(6, tutorial.getTutorialId());
			}
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			ps.close();
		}
	}
	
	// main แสดงส่วน setInterval
	public List<Tutorial> findAllTutorials(Connection conn) throws Exception {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM tutorial WHERE tutorial.`STATUS` = 'A'");
		try {
			ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			List<Tutorial> tutorials = new ArrayList<Tutorial>();
			while (rs.next()) {
				Tutorial tutorial = new Tutorial();
				tutorial.setTutorialId(rs.getInt("TUTORIAL_ID"));
				tutorial.setUserId(rs.getInt("USER_ID"));
				tutorial.setGroupId(rs.getInt("GROUP_ID"));
				tutorial.setTutorialName(rs.getString("TUTORIAL_NAME"));
				tutorial.setTutorialDetail(rs.getString("TUTORIAL_DETAIL"));
				tutorial.setUserUpdate(rs.getString("USER_UPDATE"));
				tutorial.setUserCreate(rs.getString("USER_CREATE"));
				tutorial.setTutorialUpdate(rs.getTimestamp("TUTORIAL_UPDATE"));
				tutorial.setTutorialCreate(rs.getTimestamp("TUTORIAL_CREATE"));
				tutorials.add(tutorial);
			}
			return tutorials;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			ps.close();
		}
		return null;
	}
	
	// ข้อมูล tutorials ทั้งหมด
	public List<TutorialGroup> findChapterByTutorialId(Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM `group`");
		sql.append("INNER JOIN tutorial ON tutorial.GROUP_ID = `group`.GROUP_ID");
		try {
			ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			List<TutorialGroup> tutorialGroups = new ArrayList<TutorialGroup>();
			while (rs.next()) {
				TutorialGroup tutorialGroup = new TutorialGroup();
				tutorialGroup.setTutorialId(rs.getInt("TUTORIAL_ID"));
				tutorialGroup.setUserId(rs.getInt("USER_ID"));
				tutorialGroup.setGroupId(rs.getInt("GROUP_ID"));
				tutorialGroup.setGroupName(rs.getString("GROUP_NAME"));
				tutorialGroup.setTutorialName(rs.getString("TUTORIAL_NAME"));
				tutorialGroup.setTutorialDetail(rs.getString("TUTORIAL_DETAIL"));
				tutorialGroup.setUserUpdate(rs.getString("USER_UPDATE"));
				tutorialGroup.setUserCreate(rs.getString("USER_CREATE"));
				tutorialGroup.setTutorialUpdate(rs.getTimestamp("TUTORIAL_UPDATE"));
				tutorialGroup.setTutorialCreate(rs.getTimestamp("TUTORIAL_CREATE"));
				tutorialGroups.add(tutorialGroup);
			}
			return tutorialGroups;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			ps.close();
		}
		return null;
	}
	
	public void updateTutorialStatus(Integer tutorialId, String status, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE tutorial SET tutorial.`STATUS` = ? WHERE tutorial.TUTORIAL_ID = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, status);
			ps.setInt(2, tutorialId);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			ps.close();
		}
	}
	
}
