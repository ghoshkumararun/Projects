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
import com.sau.socialsau.dao.ContactDao;
import com.sau.socialsau.dto.Contact;
import com.sau.socialsau.util.DB;

public class ContactAction extends ActionSupport implements ServletRequestAware, SessionAware{

	private static final long serialVersionUID = 745841267840961914L;
	HttpServletRequest request;
	Map<String, Object> session;

	public String execute(){
		return SUCCESS;
	}
	
	public String receive() {
		Connection conn = null;
		try {
			conn = DB.getConnection();
			List<Contact> contacts = new ArrayList<Contact>();
			ContactDao contactDao = new ContactDao();
			contacts = contactDao.findAllContactReceive(conn);
			request.setAttribute("contacts", contacts);
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.closeConnection();
		}
		return SUCCESS;
	}
	
	public void sendPerformAjax() throws SQLException {
		String userId = request.getParameter("userId");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String problemDetail = request.getParameter("problemDetail");
		if (userId != null && firstname != null && lastname != null && email != null && problemDetail != null) {
			Connection conn = null;
			try {
				conn = DB.getConnection();
				ContactDao contactDao = new ContactDao();
				contactDao.saveProblemContact(new Contact(Integer.parseInt(userId), firstname, lastname, email, problemDetail), conn);
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
