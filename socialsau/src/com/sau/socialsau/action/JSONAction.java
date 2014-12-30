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
import com.sau.socialsau.dao.JSONDao;
import com.sau.socialsau.dto.Contact;
import com.sau.socialsau.dto.Group;
import com.sau.socialsau.dto.Score;
import com.sau.socialsau.dto.TutorialJSON;
import com.sau.socialsau.dto.User;
import com.sau.socialsau.util.DB;

public class JSONAction extends ActionSupport implements ServletRequestAware, SessionAware  {

	private static final long serialVersionUID = 2288972311549772315L;

	HttpServletRequest request;
	Map<String, Object> session;
	
	// JSON loadAllGroup
	private List<Group> groups = new ArrayList<Group>();
	public String loadAllGroup() {
		Connection conn = null;
		try {
			conn = DB.getConnection();
			JSONDao jsonDao = new JSONDao();
			groups = jsonDao.loadAllGroup(conn);
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.closeConnection();
		} 
		return SUCCESS;
	}

	// JSON load Tutorials By GroupID
	private List<TutorialJSON> tutorialsJSON = new ArrayList<TutorialJSON>();
	public String tutorialsByGroupId() {
		Connection conn = null;
		Integer groupId = Integer.parseInt(request.getParameter("groupId"));
		try {
			conn = DB.getConnection();
			JSONDao jsonDao = new JSONDao();
			tutorialsJSON = jsonDao.searchTutorialByGroupId(groupId, conn);
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.closeConnection();
		}
		return SUCCESS;
	}
	
	
	private List<Score> scores = new ArrayList<Score>();
	public String loadChartJSON() {
		String userId = request.getParameter("userId");
		String tutorialId = request.getParameter("tutorialId");
		try {
			if (userId != null && tutorialId != null) {
				Connection conn = DB.getConnection();
				JSONDao jsonDao = new JSONDao();
				scores = jsonDao.findScore(userId, tutorialId, conn);
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
	
	private List<User> friendsJson = new ArrayList<User>();
	public List<User> getFriendsJson() {
		return friendsJson;
	}
	public String searchFriendsJSON() {
		String userId = request.getParameter("userId");
		String keywordsSearch = request.getParameter("keywordsSearch");
		Connection conn = null;
		try {
			if (userId != null && keywordsSearch != null) {
				conn = DB.getConnection();
				JSONDao jsonDao = new JSONDao();
				friendsJson = jsonDao.findFriendByUserId(userId, keywordsSearch, conn);
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
	
	List<Contact> contacts = new ArrayList<Contact>();
	public List<Contact> getContacts() {return contacts;}
	public void setContacts(List<Contact> contacts) {this.contacts = contacts;}
	
	public String loadContactAdmin() {
		String status = request.getParameter("status");
		Connection conn = null;
		try {
			if (status != null) {
				conn = DB.getConnection();
				JSONDao jsonDao = new JSONDao();
				contacts = jsonDao.findAllContactByStatus(status, conn);
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
	
	List<Contact> searchContact = new ArrayList<Contact>();
	public List<Contact> getSearchContact() {return searchContact;}
	public void setSearchContact(List<Contact> searchContact) {this.searchContact = searchContact;}
	
	public String searchContactUserJSON() {
		String keyword = request.getParameter("keywordsSearch");
		Connection conn = null;
		try {
			if (keyword != null) {
				conn = DB.getConnection();
				JSONDao jsonDao = new JSONDao();
				searchContact = jsonDao.searchAllContact(keyword, conn);
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
	
	
	// Generate Getter and Setter
	public List<Group> getGroups() {
		return groups;
	}
	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	public List<TutorialJSON> getTutorialsJSON() {
		return tutorialsJSON;
	}
	public void setTutorialsJSON(List<TutorialJSON> tutorialsJSON) {
		this.tutorialsJSON = tutorialsJSON;
	}
	public List<Score> getScores() {
		return scores;
	}
	public void setScores(List<Score> scores) {
		this.scores = scores;
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
