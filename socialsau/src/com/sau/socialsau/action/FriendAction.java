package com.sau.socialsau.action;

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
import com.sau.socialsau.dao.FriendDao;
import com.sau.socialsau.dao.PhotoDao;
import com.sau.socialsau.dao.TutorialDao;
import com.sau.socialsau.dto.Chapter;
import com.sau.socialsau.dto.Photo;
import com.sau.socialsau.dto.TutorialGroupUser;
import com.sau.socialsau.dto.User;
import com.sau.socialsau.util.DB;

public class FriendAction extends ActionSupport implements ServletRequestAware, SessionAware {

	private static final long serialVersionUID = -7875870930643479517L;
	
	HttpServletRequest request;
	Map<String, Object> session;
	
	public String all() {
		Connection conn = null;
		User sessionUser = (User) session.get("user");
		try {
			if (sessionUser != null) {
				conn = DB.getConnection();
				
				// Friends
				FriendDao friendDao = new FriendDao();
				List<User> friends = friendDao.findFrindByUserId(sessionUser.getUserId(), conn);
				request.setAttribute("friends", friends);
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

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
}
