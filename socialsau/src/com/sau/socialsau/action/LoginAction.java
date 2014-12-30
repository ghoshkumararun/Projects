package com.sau.socialsau.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sau.socialsau.dao.JSONDao;
import com.sau.socialsau.dao.UserDao;
import com.sau.socialsau.dto.TutorialGroupUser;
import com.sau.socialsau.dto.User;
import com.sau.socialsau.util.DB;

public class LoginAction extends ActionSupport implements ServletRequestAware, SessionAware{

	private static final long serialVersionUID = -6778054299853668307L;

	HttpServletRequest request;
	Map<String, Object> session;
	
	public String execute() {
		return SUCCESS;
	}
	
	public String loginSubmit() throws Exception {
		String email = request.getParameter("emailLogin");
		String password = request.getParameter("passwordLogin");
		Connection conn = null;
		try {
			conn = DB.getConnection();
			if (email != null && password != null) {
				UserDao userDao = new UserDao();
				User user = new User();
				user = userDao.userAuthen(new User(email, password), conn);
				// มีผู้ใช้งาน
				if (user != null) {
						session.put("user", user);
						return SUCCESS;
				}else {
					addActionError("กรุณาตรวจสอบ อีเมล์/รหัสผ่าน ของท่านอีกครั้ง");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.closeConnection();
		}
		return INPUT;
	}

	public String logout() {
		if (session.containsKey("user")) {
			// session.remove("user");
			session.clear();
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
