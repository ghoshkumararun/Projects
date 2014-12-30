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
import com.sau.socialsau.dao.ChapterDao;
import com.sau.socialsau.dao.QuestionDao;
import com.sau.socialsau.dao.TutorialDao;
import com.sau.socialsau.dto.Chapter;
import com.sau.socialsau.dto.Tutorial;
import com.sau.socialsau.dto.TutorialGroupUser;
import com.sau.socialsau.dto.User;
import com.sau.socialsau.util.DB;

public class ChapterAction extends ActionSupport implements ServletRequestAware, SessionAware{

	private static final long serialVersionUID = 6724569635156336049L;
	
	HttpServletRequest request;
	Map<String, Object> session;
	
	// แสดงผล หน้า chapterTutorial.jsp 
	public String allChapter() {
		
		String tutorialId = request.getParameter("tutorialId");
		if (tutorialId != null) {
			Connection conn = null;
			List<Chapter> chapters = new ArrayList<Chapter>();
			Tutorial tutorial;
			//TutorialGroupUser tutorial;
			try {
				conn = DB.getConnection();
				// Tutorial
				TutorialDao tutorialDao = new TutorialDao();
				tutorial = tutorialDao.findTutorial(Integer.parseInt(tutorialId), conn);
				request.setAttribute("tutorial", tutorial);
				// Chapters
				ChapterDao chapterDao = new ChapterDao();
				chapters = chapterDao.findChapter(Integer.parseInt(tutorialId), conn);
				request.setAttribute("chapters", chapters);
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
	
	// แสดงผล หน้า  create-chapter.jsp
	public String createChapter() {
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
					
				// last Chapter No.
				ChapterDao chapterDao = new ChapterDao();
				int lastChapterNo = chapterDao.lastChapterNoByTutorialId(tutorialId, conn);
				request.setAttribute("lastChapterNo", lastChapterNo);
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
	// AJAX เมื่อกดปุ่ม ADD ในหน้า create-chapter.jsp
	public void createChapterSaveAJAX() {
		Integer tutorialId = Integer.parseInt(request.getParameter("tutorialId"));
		String lastChapterNo = request.getParameter("lastChapterNo");
		String chapterName = request.getParameter("chapterName");
		String chapterDetail = request.getParameter("chapterDetail");
		String chapterDetailReport = request.getParameter("chapterDetailReport");
		
		User user = (User) session.get("user");
		if (chapterDetail != null && lastChapterNo != null && chapterName != null && chapterDetail != null && user != null) {
			Connection conn = null;
			try {
				conn = DB.getConnection();
				Chapter chapter = new Chapter();
				chapter.setTutorialId(tutorialId);
				chapter.setChapterNo(Integer.parseInt(lastChapterNo));
				chapter.setChapterName(chapterName);
				chapter.setChapterDetail(chapterDetail);
				chapter.setChapterDetailReport(chapterDetailReport);
				chapter.setUserCreate(user.getFirstname());
				chapter.setUserUpdate(user.getFirstname());
				
				// Save
				ChapterDao chapterDao = new ChapterDao();
				chapterDao.saveChapter(chapter, conn);
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				DB.closeConnection();
			}
		}
	}
	
	// แสดงผล หน้า  description-chapter.jsp
	public String descriptionChapter() {
		String chapterId = request.getParameter("chapterId");
		Connection conn = null;
		try {
			if (chapterId != null) {
				conn = DB.getConnection();
				// Chapter
				Chapter chapter = new Chapter();
				ChapterDao chapterDao = new ChapterDao();
				chapter = chapterDao.findChapterByChapterId(chapterId, conn);
				request.setAttribute("chapter", chapter);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.closeConnection();
		}
		return SUCCESS;
	}
	
	// แสดงผล หน้า edit-chapter.jsp
	public String editChapter() {
		String tutorialId = request.getParameter("tutorialId");
		String chapterId = request.getParameter("chapterId");
		if (tutorialId != null && chapterId != null) {
			Connection conn = null;
			//TutorialGroupUser tutorial;
			Tutorial tutorial;
			try {
				conn = DB.getConnection();
				// Tutorial แสดงเมนู ด้านข้าง
				TutorialDao tutorialDao = new TutorialDao();
				tutorial = tutorialDao.findTutorial(Integer.parseInt(tutorialId), conn);
				request.setAttribute("tutorial", tutorial);
				
				// Chapter
				Chapter chapter = new Chapter();
				ChapterDao chapterDao = new ChapterDao();
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
		return SUCCESS;
	}
	// AJAX
	public void editChapterEditAJAX() {
		Connection conn = null;
		Integer chapterId = Integer.parseInt(request.getParameter("chapterId"));
		String chapterName = request.getParameter("chapterName");
		String chapterDetail = request.getParameter("chapterDetail");
		String chapterDetailReport = request.getParameter("chapterDetailReport");
		
		User user = (User) session.get("user");
		if (chapterId != null &&  chapterName != null && chapterDetail != null && user != null) {
			try {
				conn = DB.getConnection();
				ChapterDao chapterDao = new ChapterDao();
				Chapter chapter = new Chapter();
				chapter.setChapterId(chapterId);
				chapter.setChapterName(chapterName);
				chapter.setChapterDetail(chapterDetail);
				chapter.setChapterDetailReport(chapterDetailReport);
					
				// Session user ที่อัพเดรต
				chapter.setUserUpdate(user.getFirstname());
					
				// Update
				chapterDao.editChapterByChapterId(chapter, conn);
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				DB.closeConnection();
			}
		}
	}
	
	// แสดงผล หน้า change-chapter.jsp
	public String changeChapter() {
		String tutorialId = request.getParameter("tutorialId");
		if (tutorialId != null) {
			Connection conn = null;
			List<Chapter> chapters = new ArrayList<Chapter>();
			Tutorial tutorial;
			//TutorialGroupUser tutorial;
			try {
				conn = DB.getConnection();
				// Tutorial
				TutorialDao tutorialDao = new TutorialDao();
				tutorial = tutorialDao.findTutorial(Integer.parseInt(tutorialId), conn);
				request.setAttribute("tutorial", tutorial);
				// Chapter
				ChapterDao chapterDao = new ChapterDao();
				chapters = chapterDao.findChapter(Integer.parseInt(tutorialId), conn);
				request.setAttribute("chapters", chapters);
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
	
	// Stream เปรียบเสมือนท่อที่ใช้ส่งข้อมูลของ Byte
	private InputStream stream;
	//getter here
	public InputStream getStream() {
		return stream;
	}
	
	// AJAX
	public String changeChapterPerformAJAX() throws Exception {
		Connection conn = null;
		String chapterIdCollection = request.getParameter("chapterIdCollection");
		
		// chapterId
		String[] chapterIdArray = chapterIdCollection.split(", ");
		
		try {
			conn = DB.getConnection();
			ChapterDao chapterDao = new ChapterDao();
			for (int i = 0; i < chapterIdArray.length; i++) {
				// update
				chapterDao.editChapterNo(chapterIdArray[i], i + 1, conn);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.closeConnection();
		}
		stream = new ByteArrayInputStream(SUCCESS.getBytes("UTF-8"));
		return SUCCESS;
	}
	
	// AJAX
	public void deleteChapterAJAX() {
		// chapterId ใช้สำหรับ ลบข้อมูล
		// tutoiralId ใช้สำหรับ หาข้อมูลของ chapter ทั้งหมดได้เป็น List ASC แล้วเอามา update บทที่ ใหม่
		String tutorialId = request.getParameter("tutorialId");
		String chapterId = request.getParameter("chapterId");
		
		if (tutorialId != null && chapterId != null) {
			Connection conn = null;
			try {
				conn = DB.getConnection();
				// Delete - Update
				ChapterDao chapterDao = new ChapterDao();
				chapterDao.deleteAndUpdateChapterNo(tutorialId, chapterId, conn);
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				DB.closeConnection();
			}
		}
	}
	
	public void updateChapterStatusAjax() {
		String chapterId = request.getParameter("chapterId");
		String status = request.getParameter("status");
		if (chapterId != null && status != null) {
			Connection conn = null;
			try {
				conn = DB.getConnection();
				ChapterDao chapterDao = new ChapterDao();
				chapterDao.updateChapterStatus(Integer.parseInt(chapterId), status, conn);
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
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
