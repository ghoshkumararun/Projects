package com.sau.socialsau.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sau.socialsau.dao.QuestionDetailDao;
import com.sau.socialsau.dao.ChapterDao;
import com.sau.socialsau.dao.GroupDao;
import com.sau.socialsau.dao.QuestionDao;
import com.sau.socialsau.dao.ScoreDao;
import com.sau.socialsau.dao.TutorialDao;
import com.sau.socialsau.dao.VideoDao;
import com.sau.socialsau.dto.QuestionDetail;
import com.sau.socialsau.dto.Chapter;
import com.sau.socialsau.dto.Group;
import com.sau.socialsau.dto.Question;
import com.sau.socialsau.dto.Score;
import com.sau.socialsau.dto.Tutorial;
import com.sau.socialsau.dto.TutorialGroupUser;
import com.sau.socialsau.dto.User;
import com.sau.socialsau.dto.Video;
import com.sau.socialsau.util.DB;

public class MainAction extends ActionSupport implements ServletRequestAware, SessionAware {

	private static final long serialVersionUID = -1041113109305649848L;

	HttpServletRequest request;
	Map<String, Object> session;
	
	// หน้า  main
	public String execute() {
		Connection conn = null;
		List<Tutorial> tutorials;
		try {
			conn = DB.getConnection();
			
			// tutorials
			TutorialDao tutorialDao = new TutorialDao();
			tutorials = tutorialDao.findAllTutorials(conn);
			request.setAttribute("tutorials", tutorials);
			
			// groups
			GroupDao groupDao = new GroupDao();
			List<Group> groups = new ArrayList<Group>();
			groups = groupDao.findAllGroupTutorialStatusA(conn);
			request.setAttribute("groups", groups);
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.closeConnection();
		}
		return SUCCESS;
	}
	
	// หน้า tutorial
	public String tutorial() {
		if (request.getMethod().equals("POST")) {
			String tutorialId = request.getParameter("tutorialId");
			if (tutorialId != null) {
				Connection conn = null;
				List<Chapter> chapters;
				Tutorial tutorial;
				//TutorialGroupUser tutorial;
				try {
					conn = DB.getConnection();
					// Tutorial
					TutorialDao tutorialDao = new TutorialDao();
					tutorial = tutorialDao.findTutorial(Integer.parseInt(tutorialId), conn);
					request.setAttribute("tutorial", tutorial);
					
					// Chapters status A
					ChapterDao chapterDao = new ChapterDao();
					chapters = chapterDao.findChapterStatusA(Integer.parseInt(tutorialId), conn);
					request.setAttribute("chapters", chapters);
				}catch (SQLException e) {
					e.printStackTrace();
				}catch (Exception e) {
					e.printStackTrace();
				} finally {
					DB.closeConnection();
				}
			}
		}else {
			return INPUT;
		}
		return SUCCESS;
	}
	
	// หน้า chapter
	public String chapter() {
		if (request.getMethod().equals("POST")) {
			String tutorialId = request.getParameter("tutorialId");
			String chapterId = request.getParameter("chapterId");
			if (tutorialId != null && chapterId != null) {
				Connection conn = null;
				List<Chapter> chapters;
				Tutorial tutorial;
				//TutorialGroupUser tutorial;
				try {
					conn = DB.getConnection();
					
					// Tutorial
					TutorialDao tutorialDao = new TutorialDao();
					tutorial = tutorialDao.findTutorial(Integer.parseInt(tutorialId), conn);
					request.setAttribute("tutorial", tutorial);
					
					// Chapters status A แสดงเมนูด้านข้าง
					ChapterDao chapterDao = new ChapterDao();
					chapters = chapterDao.findChapterStatusA(Integer.parseInt(tutorialId), conn);
					request.setAttribute("chapters", chapters);
					
					// Chapter แสดงเนื้อหา
					Chapter chapter = new Chapter();
					chapter = chapterDao.findChapterByChapterId(chapterId, conn);
					request.setAttribute("chapter", chapter);
				}catch (SQLException e) {
					e.printStackTrace();
				}catch (Exception e) {
					e.printStackTrace();
				} finally {
					DB.closeConnection();
				}
			}
		}else {
			return INPUT;
		}
		return SUCCESS;
	}
	
	public String video() {
		if (request.getMethod().equals("POST")) {
			String tutorialId = request.getParameter("tutorialId");
			if (tutorialId != null) {
				Connection conn = null;
				List<Chapter> chapters;
				List<Video> videos;
				Tutorial tutorial;
				//TutorialGroupUser tutorial;
				try {
					conn = DB.getConnection();
					
					// Tutorial
					TutorialDao tutorialDao = new TutorialDao();
					tutorial = tutorialDao.findTutorial(Integer.parseInt(tutorialId), conn);
					request.setAttribute("tutorial", tutorial);
					
					// Chapters status A แสดงเมนูด้านข้าง
					ChapterDao chapterDao = new ChapterDao();
					chapters = chapterDao.findChapterStatusA(Integer.parseInt(tutorialId), conn);
					request.setAttribute("chapters", chapters);
					
					// Videos
					VideoDao videoDao = new VideoDao();
					videos = videoDao.findAllVideoByTutorialIdStatusA(tutorialId, conn);
					request.setAttribute("videos", videos);
					
				}catch (SQLException e) {
					e.printStackTrace();
				}catch (Exception e) {
					e.printStackTrace();
				} finally {
					DB.closeConnection();
				}
			}
		} else {
			return INPUT;
		}
		return SUCCESS;
	}
	
	// หน้า questions.jsp
	public String questions() {
		if (request.getMethod().equals("POST")) {
			String tutorialId = request.getParameter("tutorialId");
			User user = (User)session.get("user");
			if (tutorialId != null && user != null) {
				Connection conn = null;
				List<Chapter> chapters;
				List<Question> questions;
				List<Score> scores;
				Tutorial tutorial;
				//TutorialGroupUser tutorial;
				try {
					conn = DB.getConnection();
					// Tutorial
					TutorialDao tutorialDao = new TutorialDao();
					tutorial = tutorialDao.findTutorial(Integer.parseInt(tutorialId), conn);
					request.setAttribute("tutorial", tutorial);
					
					// Chapters status A แสดงเมนูด้านข้าง
					ChapterDao chapterDao = new ChapterDao();
					chapters = chapterDao.findChapterStatusA(Integer.parseInt(tutorialId), conn);
					request.setAttribute("chapters", chapters);
					
					// Questions
					QuestionDao questionDao = new QuestionDao();
					questions = questionDao.findAllQuestionStautsA(Integer.parseInt(tutorialId), conn);
					request.setAttribute("questions", questions);
					
					// จำนวน Question
					int countQuestion = questionDao.countQuestionByTutorialId(tutorialId, conn);
					request.setAttribute("countQuestion", countQuestion);
					
					// Score All
					ScoreDao scoreDao = new ScoreDao();
					scores = scoreDao.findScore(user.getUserId(), tutorialId, conn);
					request.setAttribute("scores", scores);
				}catch (SQLException e) {
					e.printStackTrace();
				}catch (Exception e) {
					e.printStackTrace();
				} finally {
					DB.closeConnection();
				}
			}
		}else {
			return INPUT;
		}
		return SUCCESS;
	}
	
	public String questionsAnswer() {
		if (request.getMethod().equals("POST")) {
			String tutorialId = request.getParameter("tutorialId");
			if (tutorialId != null) {
				Connection conn = null;
				List<Chapter> chapters;
				List<Question> questions;
				Tutorial tutorial;
				//TutorialGroupUser tutorial;
				try {
					conn = DB.getConnection();
					
					// Tutorial
					TutorialDao tutorialDao = new TutorialDao();
					tutorial = tutorialDao.findTutorial(Integer.parseInt(tutorialId), conn);
					request.setAttribute("tutorial", tutorial);
					
					// Chapters status A แสดงเมนูด้านข้าง
					ChapterDao chapterDao = new ChapterDao();
					chapters = chapterDao.findChapterStatusA(Integer.parseInt(tutorialId), conn);
					request.setAttribute("chapters", chapters);
					
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
		}else {
			return INPUT;
		}
		return SUCCESS;
	}
	
	// หน้า questions-start.jsp
	public String questionStart() {
		if (request.getMethod().equals("POST")) {
			String tutorialId = request.getParameter("tutorialId");
			User user = (User)session.get("user");
			if (tutorialId != null && user != null) {
				Connection conn = null;
				List<Chapter> chapters;
				List<Question> questions;
				Tutorial tutorial;
				//TutorialGroupUser tutorial;
				try {
					conn = DB.getConnection();
					// Tutorial
					TutorialDao tutorialDao = new TutorialDao();
					tutorial = tutorialDao.findTutorial(Integer.parseInt(tutorialId), conn);
					request.setAttribute("tutorial", tutorial);
					
					// Chapters status A แสดงเมนูด้านข้าง
					ChapterDao chapterDao = new ChapterDao();
					chapters = chapterDao.findChapterStatusA(Integer.parseInt(tutorialId), conn);
					request.setAttribute("chapters", chapters);
					
					// Questions พร้อมกับ RAND()
					QuestionDao questionDao = new QuestionDao();
					questions = questionDao.findAllQuestionStautsA(Integer.parseInt(tutorialId), conn);
					request.setAttribute("questions", questions);
					
					// ครั้งที่ทดสอบล่าสุด
					ScoreDao scoreDao = new ScoreDao();
					int lastTest = scoreDao.lastTest(user.getUserId(), tutorialId, conn);
					request.setAttribute("lastTest", lastTest);
				}catch (SQLException e) {
					e.printStackTrace();
				}catch (Exception e) {
					e.printStackTrace();
				} finally {
					DB.closeConnection();
				}
			}
		}else {
			return INPUT;
		}
		return SUCCESS;
	}
	
	// Stream เปรียบเสมือนท่อที่ใช้ส่งข้อมูลของ Byte
	private InputStream stream;
	
	public InputStream getStream() {
		return stream;
	}

	public String saveAnswerAndScore() {
		String tutorialId = request.getParameter("tutorialId");
		// ครั้งที่ทดสอบล่าสุด
		String lastTest = request.getParameter("lastTest");
		String questionsIDAnswer = request.getParameter("questionsIDAnswer");
		String timer = request.getParameter("timer");
		
		User user = (User)session.get("user");
		if (tutorialId != null && lastTest != null && questionsIDAnswer != null && timer != null && user != null) {
			
			Connection conn = null;
			
			QuestionDetailDao answerDao = new QuestionDetailDao();
			ScoreDao scoreDao = new ScoreDao();
			try {
				
				conn = DB.getConnection();
				// 11:A, 8:A, 17:A, 12:A, 10:A, 3:A, 18:A, 1:A, 13:A, 5:A, 
				String[] questionIDAnswer = questionsIDAnswer.split(", ");
				
				// Loop 10 รอบ
				for (int i = 0; i < questionIDAnswer.length; i++) {
					String[] question = questionIDAnswer[i].split(":");
					String questionId = question[0];
					String answer = question[1];
					
					// save answer
					answerDao.saveAnswer(new QuestionDetail(user.getUserId(), Integer.parseInt(questionId), Integer.parseInt(lastTest), answer), conn);
				}
				
				// Score ที่ตอบถูก
				int scoreCorrect = answerDao.scoreCorrect(user.getUserId(), Integer.parseInt(lastTest), Integer.parseInt(tutorialId), conn);
				double percent = (scoreCorrect / 10.0) * 100;
				
				// save score
				scoreDao.saveScore(new Score(user.getUserId(), Integer.parseInt(tutorialId), Integer.parseInt(lastTest), scoreCorrect, 10, percent, timer), conn);
				stream = new ByteArrayInputStream(("Score: " + scoreCorrect + "/" + 10 + "\n" + "Percent: " + percent + "%").getBytes("UTF-8"));
				
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

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
}
