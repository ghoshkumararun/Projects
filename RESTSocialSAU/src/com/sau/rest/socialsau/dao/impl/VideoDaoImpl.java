package com.sau.rest.socialsau.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sau.rest.socialsau.dao.VideoDao;
import com.sau.rest.socialsau.dto.Tutorial;
import com.sau.rest.socialsau.dto.Video;
import com.sau.rest.socialsau.util.DB;

@Repository("videoDao")
public class VideoDaoImpl extends HibernateDaoSupport implements VideoDao {

	@Autowired
	public void init(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	@Override
	public List<Video> findAllVideo(Integer tutorialId) {
		Connection conn = null;
		
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM video ");
		sql.append("WHERE video.TUTORIAL_ID = ? AND ");
		sql.append("video.`STATUS` = 'A'");
		
		List<Video> videos = new ArrayList<Video>();
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, tutorialId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				Video video = new Video();
				video.setVideoId(rs.getInt("VIDEO_ID"));
				video.setTutorialId(rs.getInt("TUTORIAL_ID"));
				video.setVideoName(rs.getString("VIDEO_NAME"));
				video.setVideoUrl(rs.getString("VIDEO_URL"));
				video.setVideoCode(rs.getString("VIDEO_CODE"));
				video.setVideoCreate(rs.getTimestamp("VIDEO_CREATE"));
				
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
				video.setVideoCreateStr(df.format(rs.getTimestamp("VIDEO_CREATE")));
				
				video.setStatus(rs.getString("STATUS"));
				
				videos.add(video);
			}
			
			if (videos != null && !videos.isEmpty()) {
				return videos;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DB.closeConnection();
		}
		return null;
	}

}
