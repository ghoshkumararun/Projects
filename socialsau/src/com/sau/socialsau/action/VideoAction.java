package com.sau.socialsau.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.sau.socialsau.dao.ChapterDao;
import com.sau.socialsau.dao.QuestionDao;
import com.sau.socialsau.dao.TutorialDao;
import com.sau.socialsau.dao.VideoDao;
import com.sau.socialsau.dto.Question;
import com.sau.socialsau.dto.Tutorial;
import com.sau.socialsau.dto.TutorialGroupUser;
import com.sau.socialsau.dto.Video;
import com.sau.socialsau.util.DB;

public class VideoAction extends ActionSupport implements ServletRequestAware, SessionAware {

	private static final long serialVersionUID = -1432232604642334161L;

	HttpServletRequest request;
	Map<String, Object> session;
	
	public String allVideo() {
		String tutorialId = request.getParameter("tutorialId");
		if (tutorialId != null) {
			Connection conn = null;
			List<Video> videos;
			Tutorial tutorial;
			//TutorialGroupUser tutorial;
			try {
				conn = DB.getConnection();
				
				// Tutorial
				TutorialDao tutorialDao = new TutorialDao();
				tutorial = tutorialDao.findTutorial(Integer.parseInt(tutorialId), conn);
				request.setAttribute("tutorial", tutorial);
				
				VideoDao videoDao = new VideoDao();
				videos = videoDao.findAllVideoByTutorialId(tutorialId, conn);
				request.setAttribute("videos", videos);
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
	
	public String createVideo() {
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
	
	public String editVideo() {
		String tutorialId = request.getParameter("tutorialId");
		String videoId = request.getParameter("videoId");
		if (tutorialId != null && videoId != null) {
			Connection conn = null;
			Video video;
			Tutorial tutorial;
			//TutorialGroupUser tutorial;
			try {
				conn = DB.getConnection();
				
				// Tutorial
				TutorialDao tutorialDao = new TutorialDao();
				tutorial = tutorialDao.findTutorial(Integer.parseInt(tutorialId), conn);
				request.setAttribute("tutorial", tutorial);
				
				// Video
				VideoDao videoDao = new VideoDao();
				video = videoDao.findVideoByVideoId(videoId, conn);
				request.setAttribute("video", video);
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
	
	public void editVideoPerformAJAX() {
		String videoId = request.getParameter("videoId");
		String videoName = request.getParameter("videoName");
		String url = request.getParameter("url");
		String code = request.getParameter("code");
		
		if (videoId != null && videoName != null && url != null && code != null) {
			Connection conn = null;
			Video video = new Video();
			try {
				conn = DB.getConnection();
				
				// Update Video
				video.setVideoId(Integer.parseInt(videoId));
				video.setVideoName(videoName);
				video.setVideoUrl(url);
				video.setVideoCode(code);
				
				VideoDao videoDao = new VideoDao();
				videoDao.updateVideo(video, conn);
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				DB.closeConnection();
			}
		}
	}
	
	public void createVideoPerformAJAX() {
		String tutorialId = request.getParameter("tutorialId");
		String videoName = request.getParameter("videoName");
		String url = request.getParameter("url");
		String code = request.getParameter("code");
		if (tutorialId != null && videoName != null && url != null && code != null) {
			Connection conn = null;
			try {
				conn = DB.getConnection();
				// Save Video
				Video video = new Video(Integer.parseInt(tutorialId), videoName, url, code);
				VideoDao videoDao = new VideoDao();
				videoDao.saveVideo(video, conn);
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				DB.closeConnection();
			}
		}
	}
	
	public void deleteVideoPerformAJAX() {
		String videoId = request.getParameter("videoId");
		if (videoId != null) {
			Connection conn = null;
			try {
				conn = DB.getConnection();
				// delete video
				VideoDao videoDao = new VideoDao();
				videoDao.deleteVideoByVideoId(videoId, conn);
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				DB.closeConnection();
			}
		}
	}
	
	public void updateVideoStatusAjax() {
		String videoId = request.getParameter("videoId");
		String status = request.getParameter("status");
		if (videoId != null && status != null) {
			Connection conn = null;
			try {
				conn = DB.getConnection();
				VideoDao videoDao = new VideoDao();
				videoDao.updateVideoStatus(Integer.parseInt(videoId), status, conn);
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
