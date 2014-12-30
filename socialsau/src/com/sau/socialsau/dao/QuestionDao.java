package com.sau.socialsau.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.sau.socialsau.dto.Question;
import com.sau.socialsau.util.DB;

public class QuestionDao {

	// ค้นหา question ทั้งหมดของ tutorial แสดงหน้า all-question.jsp
	public List<Question> findAllQuestion(Integer tutorialId, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM question ");
		sql.append("WHERE question.TUTORIAL_ID = ? ");
		sql.append("ORDER BY question.QUESTION_UPDATE DESC");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, tutorialId);
			ResultSet rs = ps.executeQuery();
			List<Question> questions = new ArrayList<Question>();
			while (rs.next()) {
				Question question = new Question();
				question.setQuestionId(rs.getInt("QUESTION_ID"));
				question.setTutorialId(rs.getInt("TUTORIAL_ID"));
				question.setQuestionName(rs.getString("QUESTION_NAME"));
				question.setQuestionA(rs.getString("QUESTION_A"));
				question.setQuestionB(rs.getString("QUESTION_B"));
				question.setQuestionC(rs.getString("QUESTION_C"));
				question.setQuestionD(rs.getString("QUESTION_D"));
				question.setQuestionAnswer(rs.getString("QUESTION_ANSWER"));
				question.setQuestionExplanation(rs.getString("QUESTION_EXPLANATION"));
				question.setUserUpdate(rs.getString("USER_UPDATE"));
				question.setUserCreate(rs.getString("USER_CREATE"));
				question.setQuestionUpdate(rs.getTimestamp("QUESTION_UPDATE"));
				question.setQuestionCreate(rs.getTimestamp("QUESTION_CREATE"));
				question.setStatus(rs.getString("STATUS"));
				questions.add(question);
			}
			return questions;
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
		return null;
	}
	
	public List<Question> findAllQuestionStautsA(Integer tutorialId, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT question.* FROM tutorial ");
		sql.append("INNER JOIN question ON question.TUTORIAL_ID = tutorial.TUTORIAL_ID ");
		sql.append("WHERE tutorial.TUTORIAL_ID = ? ");
		sql.append("AND question.`STATUS` = 'A' ");
		sql.append("ORDER BY RAND() LIMIT 10");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, tutorialId);
			ResultSet rs = ps.executeQuery();
			List<Question> questions = new ArrayList<Question>();
			while (rs.next()) {
				Question question = new Question();
				question.setQuestionId(rs.getInt("QUESTION_ID"));
				question.setTutorialId(rs.getInt("TUTORIAL_ID"));
				question.setQuestionName(rs.getString("QUESTION_NAME"));
				question.setQuestionA(rs.getString("QUESTION_A"));
				question.setQuestionB(rs.getString("QUESTION_B"));
				question.setQuestionC(rs.getString("QUESTION_C"));
				question.setQuestionD(rs.getString("QUESTION_D"));
				question.setQuestionAnswer(rs.getString("QUESTION_ANSWER"));
				question.setQuestionExplanation(rs.getString("QUESTION_EXPLANATION"));
				question.setUserUpdate(rs.getString("USER_UPDATE"));
				question.setUserCreate(rs.getString("USER_CREATE"));
				question.setQuestionUpdate(rs.getTimestamp("QUESTION_UPDATE"));
				question.setQuestionCreate(rs.getTimestamp("QUESTION_CREATE"));
				question.setStatus(rs.getString("STATUS"));
				questions.add(question);
			}
			return questions;
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
		return null;
	}
	
	// ค้นหา question โดย questionId
	public Question findQuestionByID(String questionId, Connection conn)throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM question WHERE question.QUESTION_ID = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, questionId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Question question = new Question();
				question.setQuestionId(rs.getInt("QUESTION_ID"));
				question.setTutorialId(rs.getInt("TUTORIAL_ID"));
				question.setQuestionName(rs.getString("QUESTION_NAME"));
				question.setQuestionA(rs.getString("QUESTION_A"));
				question.setQuestionB(rs.getString("QUESTION_B"));
				question.setQuestionC(rs.getString("QUESTION_C"));
				question.setQuestionD(rs.getString("QUESTION_D"));
				question.setQuestionAnswer(rs.getString("QUESTION_ANSWER"));
				question.setQuestionExplanation(rs.getString("QUESTION_EXPLANATION"));
				question.setUserUpdate(rs.getString("USER_UPDATE"));
				question.setUserCreate(rs.getString("USER_CREATE"));
				question.setQuestionUpdate(rs.getTimestamp("QUESTION_UPDATE"));
				question.setQuestionCreate(rs.getTimestamp("QUESTION_CREATE"));
				return question;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
		return null;
	}
	
	// create question
	public void saveQuestion(Question question, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO question(");
		sql.append("TUTORIAL_ID, ");
		sql.append("QUESTION_NAME, ");
		sql.append("QUESTION_NAME_REPORT, ");
		
		sql.append("QUESTION_A, ");
		sql.append("QUESTION_B, ");
		sql.append("QUESTION_C, ");
		sql.append("QUESTION_D, ");
		sql.append("QUESTION_ANSWER, ");
		sql.append("QUESTION_EXPLANATION, ");
		sql.append("QUESTION_EXPLANATION_REPORT, ");
		
		sql.append("USER_UPDATE, ");
		sql.append("USER_CREATE, ");
		sql.append("QUESTION_UPDATE, ");
		sql.append("QUESTION_CREATE) ");
		sql.append("VALUES (");
		sql.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, question.getTutorialId());
			ps.setString(2, question.getQuestionName());
			ps.setString(3, question.getQuestionNameReport());
			ps.setString(4, question.getQuestionA());
			ps.setString(5, question.getQuestionB());
			ps.setString(6, question.getQuestionC());
			ps.setString(7, question.getQuestionD());
			ps.setString(8, question.getQuestionAnswer());
			ps.setString(9, question.getQuestionExplanation());
			ps.setString(10, question.getQuestionExplanationReport());
			ps.setString(11, question.getUserUpdate());
			ps.setString(12, question.getUserCreate());
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
	}
	
	// change QuestionAt
	public void editQuestionNoByQuestionID(Integer questionId, Integer questionNo, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE question SET question.QUESTION_NO = ? ");
		sql.append("WHERE question.QUESTION_ID = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, questionNo);
			ps.setInt(2, questionId);
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
	}
	
	// edit question 
	public void editQuestion(Question question, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE question SET ");
		sql.append("question.QUESTION_NAME = ?, ");
		sql.append("question.QUESTION_NAME_REPORT = ?, ");
		
		sql.append("question.QUESTION_A = ?, ");
		sql.append("question.QUESTION_B = ?, ");
		sql.append("question.QUESTION_C = ?, ");
		sql.append("question.QUESTION_D = ?, ");
		sql.append("question.QUESTION_ANSWER = ?, ");
		sql.append("question.QUESTION_EXPLANATION = ?, ");
		sql.append("question.QUESTION_EXPLANATION_REPORT = ?, ");
		
		sql.append("question.USER_UPDATE = ?, ");
		sql.append("question.QUESTION_UPDATE = NOW() ");
		sql.append("WHERE ");
		sql.append("question.QUESTION_ID = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, question.getQuestionName());
			ps.setString(2, question.getQuestionNameReport());
			
			ps.setString(3, question.getQuestionA());
			ps.setString(4, question.getQuestionB());
			ps.setString(5, question.getQuestionC());
			ps.setString(6, question.getQuestionD());
			ps.setString(7, question.getQuestionAnswer());
			ps.setString(8, question.getQuestionExplanation());
			ps.setString(9, question.getQuestionExplanationReport());
			
			ps.setString(10, question.getUserUpdate());
			ps.setInt(11, question.getQuestionId());
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
	}
	
	// Delete Question โดย Question Id
	public void deleteQuestion(Integer questionId, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM question WHERE question.QUESTION_ID = ?");
		try {
			// delete
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, questionId);
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
	}
	
	// จำนวนคำถาม
	public int countQuestionByTutorialId(String tutorialId, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(*) AS COUNT_QUESTION FROM tutorial ");
		sql.append("INNER JOIN question ON question.TUTORIAL_ID = tutorial.TUTORIAL_ID ");
		sql.append("WHERE tutorial.TUTORIAL_ID = ? ");
		sql.append("AND question.`STATUS` = 'A'");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, tutorialId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int countQuestion = rs.getInt("COUNT_QUESTION");
				return countQuestion;
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
	
	public void updateQuestionStatus(Integer questionId, String status, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE question SET question.`STATUS` = ? WHERE question.QUESTION_ID = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, status);
			ps.setInt(2, questionId);
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
