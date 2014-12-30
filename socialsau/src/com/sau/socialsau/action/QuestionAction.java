package com.sau.socialsau.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.sau.socialsau.dao.QuestionDao;
import com.sau.socialsau.dao.TutorialDao;
import com.sau.socialsau.dto.Question;
import com.sau.socialsau.dto.Tutorial;
import com.sau.socialsau.dto.TutorialGroupUser;
import com.sau.socialsau.dto.User;
import com.sau.socialsau.util.DB;

public class QuestionAction extends ActionSupport implements ServletRequestAware, SessionAware {

	private static final long serialVersionUID = -7309005461273126453L;
	
	HttpServletRequest request;
	Map<String, Object> session;

	// หน้า all-question.jsp
	public String allQuestion() {
		String tutorialId = request.getParameter("tutorialId");
		if (tutorialId != null) {
			Connection conn = null;
			List<Question> questions;
			Tutorial tutorial;
			//TutorialGroupUser tutorial;
			try {
				conn = DB.getConnection();
				
				// Tutorial
				TutorialDao tutorialDao = new TutorialDao();
				tutorial = tutorialDao.findTutorial(Integer.parseInt(tutorialId), conn);
				request.setAttribute("tutorial", tutorial);
				
				// Questions
				QuestionDao questionDao = new QuestionDao();
				questions = questionDao.findAllQuestion(Integer.parseInt(tutorialId), conn);
				request.setAttribute("questions", questions);
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				DB.closeConnection();
			}
		}
		return SUCCESS;
	}
	
	// หน้า edit-question.jsp
	public String editQuestion() {
		String tutorialId = request.getParameter("tutorialId");
		String questionId = request.getParameter("questionId");
		if (tutorialId != null && questionId != null) {
			Connection conn = null;
			Question question;
			Tutorial tutorial;
			//TutorialGroupUser tutorial;
			try {
				conn = DB.getConnection();
				
				// Tutorial
				TutorialDao tutorialDao = new TutorialDao();
				tutorial = tutorialDao.findTutorial(Integer.parseInt(tutorialId), conn);
				request.setAttribute("tutorial", tutorial);
				
				// Question
				QuestionDao questionDao = new QuestionDao();
				question = questionDao.findQuestionByID(questionId, conn);
				request.setAttribute("question", question);
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				DB.closeConnection();
			}
		}
		return SUCCESS;
	}
	
	// AJAX edit ข้อมูล question
	public void editQuestionPerformAJAX() {
		String questionId = request.getParameter("questionId");
		String questionName = request.getParameter("questionName");
		String questionA = request.getParameter("questionA");
		String questionB = request.getParameter("questionB");
		String questionC = request.getParameter("questionC");
		String questionD = request.getParameter("questionD");
		String answer = request.getParameter("answer");
		String explanation = request.getParameter("explanation");
		
		String questionNameReport = request.getParameter("questionNameReport");
		String explanationReport = request.getParameter("explanationReport");
		
		User user = (User) session.get("user");
		if (questionId != null && questionName != null && questionA != null && questionB != null && questionD != null && answer != null && explanation != null 
				&& questionNameReport != null && explanationReport != null && user != null) {
			
			Connection conn = null;
			Question questionBean = new Question();
			try {
				conn = DB.getConnection();
				questionBean.setQuestionId(Integer.parseInt(questionId));
				questionBean.setQuestionName(questionName);
				questionBean.setQuestionNameReport(questionNameReport);
				questionBean.setQuestionA(questionA);
				questionBean.setQuestionB(questionB);
				questionBean.setQuestionC(questionC);
				questionBean.setQuestionD(questionD);
				questionBean.setQuestionAnswer(answer.trim());
				questionBean.setQuestionExplanation(explanation);
				questionBean.setQuestionExplanationReport(explanationReport);
				
				questionBean.setUserUpdate(user.getFirstname());
				
				// editQuestion
				QuestionDao questionDao = new QuestionDao();
				questionDao.editQuestion(questionBean, conn);
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				DB.closeConnection();
			}
		}
	}
	
	
	// หน้า create-question.jsp
	public String createQuestion() {
		String tutorialId = request.getParameter("tutorialId");
		if (tutorialId != null) {
			Connection conn = null;
			Tutorial tutorial;
			//TutorialGroupUser tutorial;
			try {
				conn = DB.getConnection();
				
				// Tutorial
				TutorialDao tutorialDao = new TutorialDao();
				tutorial = tutorialDao.findTutorial(Integer.parseInt(tutorialId), conn);
				request.setAttribute("tutorial", tutorial);
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				DB.closeConnection();
			}
		}
		return SUCCESS;
	}
	
	// AJAX save question
	public void createQuestionPerformAJAX() {
		String tutorialId = request.getParameter("tutorialId");
		String questionName = request.getParameter("questionName");
		String questionA = request.getParameter("questionA");
		String questionB = request.getParameter("questionB");
		String questionC = request.getParameter("questionC");
		String questionD = request.getParameter("questionD");
		String answer = request.getParameter("answer");
		String explanation = request.getParameter("explanation");
		String questionNameReport = request.getParameter("questionNameReport");
		String explanationReport = request.getParameter("explanationReport");
		
		User user = (User) session.get("user");
		if (tutorialId != null && questionName != null && questionA != null && questionB != null && questionD != null && answer != null && explanation != null 
				&& questionNameReport != null && explanationReport != null && user != null) {
			
			Connection conn = null;
			Question questionBean = new Question();
			try {
				conn = DB.getConnection();
				questionBean.setTutorialId(Integer.parseInt(tutorialId));
				questionBean.setQuestionName(questionName);
				questionBean.setQuestionNameReport(questionNameReport);
				questionBean.setQuestionA(questionA);
				questionBean.setQuestionB(questionB);
				questionBean.setQuestionC(questionC);
				questionBean.setQuestionD(questionD);
				questionBean.setQuestionAnswer(answer.trim());
				questionBean.setQuestionExplanation(explanation);
				questionBean.setQuestionExplanationReport(explanationReport);
				questionBean.setUserUpdate(user.getFirstname());
				questionBean.setUserCreate(user.getFirstname());
				
				// Save Question
				QuestionDao questionDao = new QuestionDao();
				questionDao.saveQuestion(questionBean, conn);
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				DB.closeConnection();
			}
		}
	}
	
	// Delete Question
	public void deleteQuestionPerformAJAX() throws Exception {
		String questionId = request.getParameter("questionId");
		if (questionId != null) {
			Connection conn = null;
			try {
				conn = DB.getConnection();
				
				// Delete
				QuestionDao questionDao = new QuestionDao();
				questionDao.deleteQuestion(Integer.parseInt(questionId), conn);
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				DB.closeConnection();
			}
		}
	}

	public void updateQuestionStatusAjax() {
		String questionId = request.getParameter("questionId");
		String status = request.getParameter("status");
		if (questionId != null && status != null) {
			Connection conn = null;
			try {
				conn = DB.getConnection();
				QuestionDao questionDao = new QuestionDao();
				questionDao.updateQuestionStatus(Integer.parseInt(questionId), status, conn);
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				DB.closeConnection();
			}
		}
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
}
