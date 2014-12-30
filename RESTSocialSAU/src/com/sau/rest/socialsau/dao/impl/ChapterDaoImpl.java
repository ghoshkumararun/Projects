package com.sau.rest.socialsau.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sau.rest.socialsau.dao.ChapterDao;
import com.sau.rest.socialsau.dao.TutorialDao;
import com.sau.rest.socialsau.dto.Chapter;
import com.sau.rest.socialsau.dto.Tutorial;
import com.sau.rest.socialsau.dto.User;
import com.sau.rest.socialsau.util.DB;
import com.sau.rest.socialsau.util.MD5;

@Repository("chapterDao")
public class ChapterDaoImpl extends HibernateDaoSupport implements ChapterDao {

	@Autowired
	public void init(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	public List<Chapter> findAllChapters(Integer tutorialId) {
		Connection conn = null;
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM chapter ");
		sql.append("WHERE chapter.TUTORIAL_ID = ? ");
		sql.append("ORDER BY chapter.CHAPTER_NO ASC");
		List<Chapter> chapters = new ArrayList<Chapter>();
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, tutorialId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Chapter chapter = new Chapter();
				chapter.setChapterId(rs.getInt("CHAPTER_ID"));
				chapter.setTutorialId(rs.getInt("TUTORIAL_ID"));
				chapter.setChapterNo(rs.getInt("CHAPTER_NO"));
				chapter.setChapterName(rs.getString("CHAPTER_NAME"));
				chapter.setChapterDetail(rs.getString("CHAPTER_DETAIL"));
				chapter.setChapterDetailReport(rs.getString("CHAPTER_DETAIL_REPORT"));
				chapter.setUserUpdate(rs.getString("USER_UPDATE"));
				chapter.setUserCreate(rs.getString("USER_CREATE"));
				chapter.setChapterUpdate(rs.getTimestamp("CHAPTER_UPDATE"));
				chapter.setChapterCreate(rs.getTimestamp("CHAPTER_CREATE"));
				
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
				chapter.setChapterUpdateStr(df.format(rs.getTimestamp("CHAPTER_UPDATE")));
				chapter.setChapterCreateStr(df.format(rs.getTimestamp("CHAPTER_CREATE")));
				chapters.add(chapter);
			}
			
			if (chapters != null && !chapters.isEmpty()) {
				return chapters;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DB.closeConnection();
		}
		return null;
	}

	@Override
	public Chapter findChapter(Integer chapterId) {
		Connection conn = null;
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM chapter ");
		sql.append("WHERE chapter.CHAPTER_ID = ? ");
		sql.append("AND chapter.`STATUS` = 'A'");
		
		Chapter chapter = null;
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, chapterId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				chapter = new Chapter();
				chapter.setChapterId(rs.getInt("CHAPTER_ID"));
				chapter.setTutorialId(rs.getInt("TUTORIAL_ID"));
				chapter.setChapterNo(rs.getInt("CHAPTER_NO"));
				chapter.setChapterName(rs.getString("CHAPTER_NAME"));
				chapter.setChapterDetail(rs.getString("CHAPTER_DETAIL"));
				chapter.setChapterDetailReport(rs.getString("CHAPTER_DETAIL_REPORT"));
				chapter.setUserUpdate(rs.getString("USER_UPDATE"));
				chapter.setUserCreate(rs.getString("USER_CREATE"));
				chapter.setChapterUpdate(rs.getTimestamp("CHAPTER_UPDATE"));
				chapter.setChapterCreate(rs.getTimestamp("CHAPTER_CREATE"));
				
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
				chapter.setChapterUpdateStr(df.format(rs.getTimestamp("CHAPTER_UPDATE")));
				chapter.setChapterCreateStr(df.format(rs.getTimestamp("CHAPTER_CREATE")));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DB.closeConnection();
		}
		return chapter;
	}
	
}

