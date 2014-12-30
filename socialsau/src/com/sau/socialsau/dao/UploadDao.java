package com.sau.socialsau.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sau.socialsau.dto.Photo;

public class UploadDao {

	// photo ล่าสุด
	public Integer lastPhoto(Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT MAX(photo.PHOTO_ID) AS LAST_PHOTO FROM photo");
		try {
			ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Integer lastPhoto = rs.getInt("LAST_PHOTO");
				return lastPhoto;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
		return 0;
	}
	
	// Save Photo
	// Step 1. save
	public Integer savePhotoUpload(Photo photo, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO photo ");
		sql.append("(USER_ID, ");
		sql.append("PHOTO_SIZE, ");
		sql.append("PHOTO_CREATE) ");
		sql.append("VALUES (?, ?, NOW())");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, photo.getUserId());
			ps.setLong(2, photo.getPhotoSize());
			// Save
			ps.executeUpdate();
			
			String sql2 = "SELECT photo.PHOTO_ID FROM photo ORDER BY photo.PHOTO_ID DESC LIMIT 1";
			ps = conn.prepareStatement(sql2);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Integer lastPhotoId = rs.getInt("PHOTO_ID");
				return lastPhotoId;
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
	// Step 2. นำ lastPhotoId มาเป็นส่วนหนึ่งของชื่อเพื่อ > Update
	public void updatePhotoURL(Integer lastPhotoId, String photoUrl, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE photo SET photo.PHOTO_URL = ? WHERE photo.PHOTO_ID = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, photoUrl);
			ps.setInt(2, lastPhotoId);
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
	}
	
	// delete Photo recent
	// step 1. store filename
	// step 2. delete data within mysql
	public String deletePhotoRecentByPhotoId(String photoId, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		
		StringBuffer sql1 = new StringBuffer();
		sql1.append("SELECT * FROM photo WHERE photo.PHOTO_ID = ?");
		
		StringBuffer sql2 = new StringBuffer();
		sql2.append("DELETE FROM photo ");
		sql2.append("WHERE photo.PHOTO_ID = ?");
		
		String photoUrl = null;
		try {
			ps = conn.prepareStatement(sql1.toString());
			ps.setString(1, photoId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				photoUrl = rs.getString("PHOTO_URL");
			}
			ps = conn.prepareStatement(sql2.toString());
			ps.setString(1, photoId);
			ps.executeUpdate();
			return photoUrl;
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
		return null;
	}
	
	
}
