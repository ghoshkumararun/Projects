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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sau.rest.socialsau.dao.TutorialDao;
import com.sau.rest.socialsau.dto.Tutorial;
import com.sau.rest.socialsau.util.DB;

@Repository("tutorialDao")
public class TutorialDaoImpl extends HibernateDaoSupport implements TutorialDao {

	@Autowired
	public void init(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	@Override
	public List<Tutorial> getAllTutorial() {
		/*Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(Tutorial.class);
		List<Tutorial> tutorials = criteria.list();*/
		Connection conn = null;
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM tutorial ");
		sql.append("WHERE tutorial.`STATUS` = 'A' ");
		sql.append("ORDER BY tutorial.TUTORIAL_UPDATE DESC");
		List<Tutorial> tutorials = new ArrayList<Tutorial>();
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql.toString());
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
				
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
				tutorial.setTutorialUpdateStr(df.format(rs.getTimestamp("TUTORIAL_UPDATE")));
				tutorial.setTutorialCreateStr(df.format(rs.getTimestamp("TUTORIAL_CREATE")));
				tutorials.add(tutorial);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DB.closeConnection();
		}
		return tutorials;
	}

	@Override
	public Tutorial searchTutorial(Integer tutorialId) {
		Connection conn = null;
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM tutorial WHERE tutorial.TUTORIAL_ID = ?");
		Tutorial tutorial = new Tutorial();
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, tutorialId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
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
				
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
				tutorial.setTutorialUpdateStr(df.format(rs.getTimestamp("TUTORIAL_UPDATE")));
				tutorial.setTutorialCreateStr(df.format(rs.getTimestamp("TUTORIAL_CREATE")));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DB.closeConnection();
		}
		return tutorial;
	}

}
