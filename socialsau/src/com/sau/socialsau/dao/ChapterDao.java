package com.sau.socialsau.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sau.socialsau.dto.Chapter;
import com.sau.socialsau.dto.Question;
import com.sau.socialsau.util.DB;

public class ChapterDao {

	// ส่วนแสดงผล Chapter โดยใช้  tutorial id ได้เป็น List
	public List<Chapter> findChapter(Integer tutorialId, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM chapter ");
		sql.append("WHERE chapter.TUTORIAL_ID = ? ");
		sql.append("ORDER BY chapter.CHAPTER_NO ASC");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, tutorialId);
			ResultSet rs = ps.executeQuery();
			List<Chapter> list = new ArrayList<Chapter>();
			while (rs.next()) {
				Chapter chapter = new Chapter();
				chapter.setChapterId(rs.getInt("CHAPTER_ID"));
				chapter.setTutorialId(rs.getInt("TUTORIAL_ID"));
				chapter.setChapterNo(rs.getInt("CHAPTER_NO"));
				chapter.setChapterName(rs.getString("CHAPTER_NAME"));
				chapter.setChapterDetail(rs.getString("CHAPTER_DETAIL"));
				chapter.setUserUpdate(rs.getString("USER_UPDATE"));
				chapter.setUserCreate(rs.getString("USER_CREATE"));
				chapter.setChapterUpdate(rs.getTimestamp("CHAPTER_UPDATE"));
				chapter.setChapterCreate(rs.getTimestamp("CHAPTER_CREATE"));
				chapter.setStatus(rs.getString("STATUS"));
				list.add(chapter);
			}
			return list;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			ps.close();
		}
		return null;
	}
	
	public List<Chapter> findChapterStatusA(Integer tutorialId, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM chapter ");
		sql.append("WHERE chapter.TUTORIAL_ID = ? ");
		sql.append("AND chapter.`STATUS` = 'A' ");
		sql.append("ORDER BY chapter.CHAPTER_NO ASC");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, tutorialId);
			ResultSet rs = ps.executeQuery();
			List<Chapter> list = new ArrayList<Chapter>();
			while (rs.next()) {
				Chapter chapter = new Chapter();
				chapter.setChapterId(rs.getInt("CHAPTER_ID"));
				chapter.setTutorialId(rs.getInt("TUTORIAL_ID"));
				chapter.setChapterNo(rs.getInt("CHAPTER_NO"));
				chapter.setChapterName(rs.getString("CHAPTER_NAME"));
				chapter.setChapterDetail(rs.getString("CHAPTER_DETAIL"));
				chapter.setUserUpdate(rs.getString("USER_UPDATE"));
				chapter.setUserCreate(rs.getString("USER_CREATE"));
				chapter.setChapterUpdate(rs.getTimestamp("CHAPTER_UPDATE"));
				chapter.setChapterCreate(rs.getTimestamp("CHAPTER_CREATE"));
				chapter.setStatus(rs.getString("STATUS"));
				list.add(chapter);
			}
			return list;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			ps.close();
		}
		return null;
	}
	
	// ค้นหา Chapter โดย chapterId
	public Chapter findChapterByChapterId(String chapterId, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM chapter ");
		sql.append("WHERE chapter.CHAPTER_ID = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, chapterId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Chapter chapter = new Chapter();
				chapter.setChapterId(rs.getInt("CHAPTER_ID"));
				chapter.setTutorialId(rs.getInt("TUTORIAL_ID"));
				chapter.setChapterNo(rs.getInt("CHAPTER_NO"));
				chapter.setChapterName(rs.getString("CHAPTER_NAME"));
				chapter.setChapterDetail(rs.getString("CHAPTER_DETAIL"));
				chapter.setUserUpdate(rs.getString("USER_UPDATE"));
				chapter.setUserCreate(rs.getString("USER_CREATE"));
				chapter.setChapterUpdate(rs.getTimestamp("CHAPTER_UPDATE"));
				chapter.setChapterCreate(rs.getTimestamp("CHAPTER_CREATE"));
				return chapter;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			ps.close();
		}
		return null;
	}
	
	// เมื่อกดปุ่ม ADD ในหน้า create-chapter.jsp
	public void saveChapter(Chapter chapter, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO chapter (TUTORIAL_ID, CHAPTER_NO, CHAPTER_NAME, CHAPTER_DETAIL, CHAPTER_DETAIL_REPORT, USER_UPDATE, USER_CREATE, CHAPTER_UPDATE, CHAPTER_CREATE) ");
		sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, NOW(), NOW())");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, chapter.getTutorialId());
			ps.setInt(2, chapter.getChapterNo());
			ps.setString(3, chapter.getChapterName());
			ps.setString(4, chapter.getChapterDetail());
			ps.setString(5, chapter.getChapterDetailReport());
			ps.setString(6, chapter.getUserCreate());
			ps.setString(7, chapter.getUserUpdate());
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			ps.close();
		}
	}
	
	// บทเรียนล่าสุด
	public int lastChapterNoByTutorialId(String tutorialId, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT MAX(chapter.CHAPTER_NO) AS LAST_CHAPTER_NO FROM chapter ");
		sql.append("WHERE chapter.TUTORIAL_ID = ?");
		
		int lastChapterNo = 0;
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, tutorialId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				lastChapterNo = rs.getInt("LAST_CHAPTER_NO");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
		return lastChapterNo;
	}
	
	// Ajax update เมื่อกดปุ่มในหน้า edit-chapter.jsp
	public void editChapterByChapterId(Chapter chapter, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE chapter SET ");
		sql.append("chapter.CHAPTER_NAME = ?, ");
		sql.append("chapter.CHAPTER_DETAIL = ?, ");
		sql.append("chapter.CHAPTER_DETAIL_REPORT = ?, ");
		sql.append("chapter.USER_UPDATE = ?, ");
		sql.append("chapter.CHAPTER_UPDATE = NOW() ");
		sql.append("WHERE chapter.CHAPTER_ID = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, chapter.getChapterName());
			ps.setString(2, chapter.getChapterDetail());
			ps.setString(3, chapter.getChapterDetailReport());
			ps.setString(4, chapter.getUserUpdate());
			ps.setInt(5, chapter.getChapterId());
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			ps.close();
		}
	}
	
	public void editChapterNo(String chapterId, Integer chapterNo, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE chapter SET chapter.CHAPTER_NO = ? ");
		sql.append("WHERE chapter.CHAPTER_ID = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, chapterNo);
			ps.setString(2, chapterId);
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
	}

	public void deleteAndUpdateChapterNo(String tutorialId, String chapterId, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM chapter WHERE chapter.CHAPTER_ID = ?");
		try {
			// Delete Chapter
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, chapterId);
			ps.executeUpdate();
			
			// Update หลังจาก Delete
			List<Chapter> chapters = findChapter(Integer.parseInt(tutorialId), conn);
			for (int i = 0; i < chapters.size(); i++) {
				editChapterNo(String.valueOf(chapters.get(i).getChapterId()), i + 1, conn);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
	}
	
	public void updateChapterStatus(Integer chapterId, String status, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE chapter SET chapter.`STATUS` = ? WHERE chapter.CHAPTER_ID = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, status);
			ps.setInt(2, chapterId);
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
