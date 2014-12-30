package com.sau.socialsau.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sau.socialsau.dao.UserDao;
import com.sau.socialsau.dto.User;
import com.sau.socialsau.util.DB;
import com.sau.socialsau.util.DateUtil;

public class UserAction extends ActionSupport implements ServletRequestAware, SessionAware {

	private static final long serialVersionUID = -3681564406490966384L;

	HttpServletRequest request;
	Map<String, Object> session;
	
	public String execute() {
		return SUCCESS;
	}
	
	public String addUser() throws SQLException {
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String birthday = request.getParameter("birthday");
		
        ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
        reCaptcha.setPrivateKey("6LffNf0SAAAAANVQKrXWtQ0wTXNWcTYcF-EsDxai");

        // challenge ภาพคำถาม
        String challenge = request.getParameter("recaptcha_challenge_field");
        String uresponse = request.getParameter("recaptcha_response_field");
        ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer("127.0.0.1", challenge, uresponse);
		
        if (!reCaptchaResponse.isValid()) {
        	addActionError("กรุณากรอก \'รหัสภาพ\' ให้ถูกต้อง");
        	return INPUT;
        } 
        
		Connection conn = null;
		if (firstname != null && lastname != null && email != null && password != null && birthday != null) {
			try {
				// เช็คการกรอก Email
				if (email.contains("@")) {
					if (!email.contains("@sau.ac.th")) {
						addActionError("กรุณากรอกอีเมล์ sau เท่านั้น");
						return INPUT;
					}
				}else {
					email += "@sau.ac.th";
				}
				conn = DB.getConnection();
				UserDao userDao = new UserDao();
				if (userDao.findByEmail(email, conn) == null) {
					//yy 01, yyyy 2001, yyyyy 02001, 
					//MM 07, MMM Jul (แปลง full month ได้)
					//d 7, dd 07
					User user = new User(firstname, lastname, email, password, DateUtil.setStrInputToUtilDate(birthday,  "yyyy-MMM-dd"));
					userDao.saveUser(user, conn);
					
					addActionMessage("บันทึกข้อมูลเรียบร้อยแล้ว กรุณายืนยันอีเมล์");
					return SUCCESS;
				} else {
					addActionError("Email นี้มีอยู่ในระบบแล้ว");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DB.closeConnection();
			}
		}
		return INPUT;
	}
	
	
	public String checkConfirmCodePerform() throws SQLException {
		String confirmEmail = request.getParameter("confirmEmail");
		String confirmCode = request.getParameter("confirmCode");
		Connection conn = null;
		if (confirmEmail != null && confirmCode != null) {
			try {
				conn = DB.getConnection();
				UserDao userDao = new UserDao();
				if (userDao.findByEmail(confirmEmail, conn) == null) {
					addActionError("Email นี้มีไม่มีอยู่ในระบบ");
				}else {
					if (userDao.findByConfirmCode(confirmEmail, confirmCode, conn)) {
						userDao.updateStatusUser(confirmEmail, confirmCode, conn);
						addActionMessage("ยืนยัน Email เรียบร้อยแล้ว กรุณาล็อกอิน");
						return SUCCESS;
					}else {
						addActionError("Code ของคุณไม่ตรง กรุณาลองใหม่อีกครั้ง");
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DB.closeConnection();
			}
		}
		return INPUT;
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
