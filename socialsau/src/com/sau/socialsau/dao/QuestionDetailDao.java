package com.sau.socialsau.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sau.socialsau.dto.QuestionDetail;
import com.sau.socialsau.dto.Score;
import com.sau.socialsau.util.DB;

public class QuestionDetailDao {

	// Save Answer
	public void saveAnswer(QuestionDetail answer, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO question_detail (USER_ID, QUESTION_ID, TEST_NO, `ANSWER`) VALUES (?, ?, ?, ?)");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, answer.getUserId());
			ps.setInt(2, answer.getQuestionId());
			ps.setInt(3, answer.getTestNo());
			ps.setString(4, answer.getAnswer());
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
	}
	
	// คะแนนที่ได้
	public int scoreCorrect(Integer userId, Integer testNo, Integer tutorialId, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(*) AS COUNT_ANSWER_CORRECT FROM question_detail ");
		sql.append("INNER JOIN question ON question.QUESTION_ID = question_detail.QUESTION_ID ");
		sql.append("WHERE question_detail.USER_ID = ? AND ");
		sql.append("question_detail.TEST_NO = ? AND ");
		sql.append("question.TUTORIAL_ID = ? AND ");
		sql.append("question_detail.ANSWER = question.QUESTION_ANSWER");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, userId);
			ps.setInt(2, testNo);
			ps.setInt(3, tutorialId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int countAnswerCorrect =  rs.getInt("COUNT_ANSWER_CORRECT");
				return countAnswerCorrect;
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
	
}
