package com.sau.socialsau.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.sau.socialsau.dto.Score;

public class ScoreDao {

	// ครั้งที่ทดสอบล่าสุด
	public int lastTest(Integer userId,String tutorialId, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT MAX(score.TEST_NO) AS LAST_TEST_NO FROM score WHERE score.USER_ID = ? AND score.TUTORIAL_ID = ?");
		int lastTest = 0; 
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, userId);
			ps.setString(2, tutorialId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				lastTest =  rs.getInt("LAST_TEST_NO"); // default return null is 0
				return rs.wasNull() ? 0 : lastTest;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
		// Exception
		return 0;
	}
	
	// Score All
	public List<Score> findScore(Integer userId, String tutorialId, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM score WHERE score.USER_ID = ? AND score.TUTORIAL_ID = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, userId);
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
				// จัดรูปแบบ #.00
				/*DecimalFormat df = new DecimalFormat("#.00");
				String dfParse = df.format(rs.getDouble("PERCENT_CORRECT"));*/
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
	
	public void saveScore(Score score, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO score (USER_ID, TUTORIAL_ID, TEST_NO, SCORE_CORRECT, MAX_SCORE, TIME, PERCENT_CORRECT, SCORE_CREATE) VALUES (?, ?, ?, ?, ?, ?, ?, NOW())");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, score.getUserId());
			ps.setInt(2, score.getTutorialId());
			ps.setInt(3, score.getTestNo());
			ps.setInt(4, score.getScoreCorrect());
			ps.setInt(5, score.getMaxScore());
			ps.setString(6, score.getTime());
			ps.setDouble(7, score.getPercentCorrect());
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
