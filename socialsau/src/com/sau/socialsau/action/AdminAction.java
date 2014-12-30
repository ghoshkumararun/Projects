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
import com.sau.socialsau.dao.ContactDao;
import com.sau.socialsau.dao.TutorialDao;
import com.sau.socialsau.dto.Chapter;
import com.sau.socialsau.dto.Contact;
import com.sau.socialsau.dto.TutorialGroupUser;
import com.sau.socialsau.util.DB;

public class AdminAction extends ActionSupport implements ServletRequestAware, SessionAware {

	private static final long serialVersionUID = 2953608815142374996L;

	Map<String, Object> session;
	HttpServletRequest request;
	
	public String userContact() {
		Connection conn = null;
		try {
			conn = DB.getConnection();
			
			// contacts
			List<Contact> contacts = new ArrayList<Contact>();
			ContactDao contactDao = new ContactDao();
			contacts = contactDao.findAllContactAdmin(conn);
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
	public String answerQuestion() {
		String contactId = request.getParameter("contactId");
		if (contactId != null) {
			Connection conn = null;
			try {
				conn = DB.getConnection();
				
				// contact
				ContactDao contactDao = new ContactDao();
				Contact contact = new Contact();
				contact = contactDao.findContactByContactId(contactId, conn);
				request.setAttribute("contact", contact);
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
	
	public void answerQuestionUpdateAjax() {
		String contactId = request.getParameter("contactId");
		String answer = request.getParameter("answer");
		if (contactId != null && answer != null) {
			Connection conn = null;
			try {
				conn = DB.getConnection();
				ContactDao contactDao = new ContactDao();
				contactDao.updateContactAnswer(contactId, answer, conn);
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
