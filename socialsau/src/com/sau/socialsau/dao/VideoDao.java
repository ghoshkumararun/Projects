package com.sau.socialsau.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sau.socialsau.dto.Video;

public class VideoDao {

	public List<Video> findAllVideoByTutorialIdStatusA(String tutorialId, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		String sql = "SELECT * FROM video WHERE video.TUTORIAL_ID = ? AND video.`STATUS` = 'A'";
		List<Video> videos = new ArrayList<Video>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, tutorialId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Video video = new Video();
				video.setVideoId(rs.getInt("VIDEO_ID"));
				video.setTutorialId(rs.getInt("TUTORIAL_ID"));
				video.setVideoName(rs.getString("VIDEO_NAME"));
				video.setVideoUrl(rs.getString("VIDEO_URL"));
				video.setVideoCreate(rs.getTimestamp("VIDEO_CREATE"));
				videos.add(video);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
		return videos;
	}
	
	public List<Video> findAllVideoByTutorialId(String tutorialId, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		String sql = "SELECT * FROM video WHERE video.TUTORIAL_ID = ?";
		List<Video> videos = new ArrayList<Video>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, tutorialId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Video video = new Video();
				video.setVideoId(rs.getInt("VIDEO_ID"));
				video.setTutorialId(rs.getInt("TUTORIAL_ID"));
				video.setVideoName(rs.getString("VIDEO_NAME"));
				video.setVideoUrl(rs.getString("VIDEO_URL"));
				video.setVideoCreate(rs.getTimestamp("VIDEO_CREATE"));
				video.setStatus(rs.getString("STATUS"));
				videos.add(video);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
		return videos;
	}
	
	public void saveVideo(Video video, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO video ");
		sql.append("(TUTORIAL_ID, ");
		sql.append("VIDEO_NAME, ");
		sql.append("VIDEO_URL, ");
		sql.append("VIDEO_CODE, ");
		sql.append("VIDEO_CREATE, ");
		sql.append("`STATUS`) ");
		sql.append("VALUES (?, ?, ?, ?, NOW(), 'A')");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, video.getTutorialId());
			ps.setString(2, video.getVideoName());
			ps.setString(3, video.getVideoUrl());
			ps.setString(4, video.getVideoCode());
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
	}
	
	public void deleteVideoByVideoId(String videoId, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM video WHERE video.VIDEO_ID = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, videoId);
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
	}
	
	public Video findVideoByVideoId(String videoId, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM video WHERE video.VIDEO_ID = ?");
		Video video = new Video();
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, videoId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				video.setVideoId(rs.getInt("VIDEO_ID"));
				video.setTutorialId(rs.getInt("TUTORIAL_ID"));
				video.setVideoName(rs.getString("VIDEO_NAME"));
				video.setVideoUrl(rs.getString("VIDEO_URL"));
				video.setVideoCreate(rs.getTimestamp("VIDEO_CREATE"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
		return video;
	}
	
	public void updateVideo(Video video, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE video SET video.VIDEO_NAME = ?, video.VIDEO_URL = ?, video.VIDEO_CODE = ? ");
		sql.append("WHERE video.VIDEO_ID = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, video.getVideoName());
			ps.setString(2, video.getVideoUrl());
			ps.setString(3, video.getVideoCode());
			ps.setInt(4, video.getVideoId());
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
	}
	
	public void updateVideoStatus(Integer videoId, String status, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE video SET video.`STATUS` = ? WHERE video.VIDEO_ID = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, status);
			ps.setInt(2, videoId);
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
