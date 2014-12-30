package com.sau.socialsau.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sau.socialsau.dao.ChapterDao;
import com.sau.socialsau.dao.PhotoDao;
import com.sau.socialsau.dao.PostDao;
import com.sau.socialsau.dao.SettingDao;
import com.sau.socialsau.dao.TutorialDao;
import com.sau.socialsau.dao.UploadDao;
import com.sau.socialsau.dao.UserDao;
import com.sau.socialsau.dto.Chapter;
import com.sau.socialsau.dto.Photo;
import com.sau.socialsau.dto.Post;
import com.sau.socialsau.dto.TutorialGroupUser;
import com.sau.socialsau.dto.User;
import com.sau.socialsau.util.DB;
import com.sau.socialsau.util.DateUtil;
import com.sau.socialsau.util.MailUtil;

public class SettingAction extends ActionSupport implements ServletRequestAware, SessionAware {

	private static final long serialVersionUID = -8814880716846724604L;
	
	HttpServletRequest request;
	Map<String, Object> session;

	public String personalData() {
		Connection conn = null;
		try {
			conn = DB.getConnection();
			User sessionUser = (User) session.get("user");
			if (sessionUser != null) {
				UserDao userDao = new UserDao();
				// user
				User user = new User();
				user = userDao.findByEmail(sessionUser.getEmail(), conn);
				request.setAttribute("user", user);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DB.closeConnection();
		}
		return SUCCESS;
	}
	
	public String template() {
		Connection conn = null;
		try {
			User sessionUser = (User)session.get("user");
			if (sessionUser != null) {
				conn = DB.getConnection();
				User user = new User();
				PhotoDao photoDao = new PhotoDao();
				user = photoDao.findImageProfileByUserId(sessionUser.getUserId(), conn);
				request.setAttribute("user", user);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DB.closeConnection();
		}
		return SUCCESS;
	}
	
	public String changePassword() {
		String oldPassword = request.getParameter("old-password");
		String newPassword = request.getParameter("new-password");
		Connection conn = null;
		
		User sessionUser = (User)session.get("user");
		if (oldPassword != null && newPassword != null && sessionUser != null) {
			try {
				conn = DB.getConnection();
				
				UserDao userDao = new UserDao();
				User user = new User();
				user = userDao.userAuthen(new User(sessionUser.getEmail(), oldPassword), conn);
				// มีผู้ใช้งาน + รหัสผ่านถูกต้อง
				if (user != null) {
					// update บันทึกรหัสผ่านใหม่
					userDao.updatePassword(sessionUser.getUserId(), newPassword, conn);
					addActionMessage("บันทึกรหัสผ่านใหม่ เรียบร้อย");
					return SUCCESS;
				}else {
					addActionError("กรุณาตรวจสอบ รหัสผ่าน ของท่านอีกครั้ง");
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
	
	public String photoManagement() {
		Connection conn = null;
		User user = (User)session.get("user");
		if (user != null) {
			try {
				conn = DB.getConnection();
				List<Photo> photos = new ArrayList<Photo>();
				PhotoDao photoDao = new PhotoDao();
				photos = photoDao.findAllPhotoByUserId(user.getUserId(), conn);
				// Photos
				request.setAttribute("photos", photos);
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				DB.closeConnection();
			}
		}
		return SUCCESS;
	}
	
	public String profilePhotoStep1() {
		Connection conn = null;
		User user = (User)session.get("user");
		if (user != null) {
			try {
				conn = DB.getConnection();
				List<Photo> photos = new ArrayList<Photo>();
				PhotoDao photoDao = new PhotoDao();
				photos = photoDao.findAllPhotoByUserId(user.getUserId(), conn);
				// Photos
				request.setAttribute("photos", photos);
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				DB.closeConnection();
			}
		}
		return SUCCESS;
	}
	public String profilePhotoStep2() {
		if (request.getMethod().equals("POST")) {
			String photoId = request.getParameter("photoId");
			if (photoId != null) {
				Connection conn = null;
				try {
					// Photo
					conn = DB.getConnection();
					Photo photo = new Photo();
					PhotoDao photoDao = new PhotoDao();
					photo = photoDao.findPhotoByPhotoId(photoId, conn);
					request.setAttribute("photo", photo);
				}catch (SQLException e) {
					e.printStackTrace();
				}catch (Exception e) {
					e.printStackTrace();
				}finally {
					DB.closeConnection();
				}
			}
			return SUCCESS;
		}
		return INPUT;
		
	}
	public void profilePhotoStep2Ajax() {
		Connection conn = null;
		UserDao userDao = new UserDao();
		
		String photoUrl = request.getParameter("photoUrl");
		String x = request.getParameter("x");
		String y = request.getParameter("y");
		String width = request.getParameter("width");
		String height = request.getParameter("height");
		
		User userSession = (User)session.get("user");
		if (photoUrl != null && x != null && y != null && width != null && height != null && userSession != null) {
			try {
				conn = DB.getConnection();
				
				User user = userDao.findUserByUserId(userSession.getUserId(), conn);
				
				// ชื่อไฟล์เก่า
				String filename = user.getImageProfile();
				user.setImageProfile(photoUrl);
				
				// Update
				SettingDao settingDao = new SettingDao();
				settingDao.updateImageProfileByUserId(user, conn);
				
				// ลบไฟล์เก่าบน Server
				String path = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/");
				File dirPhotosProfileClean = new File(path + File.separator + "resources" + File.separator + "photos-profile" + File.separator + filename);
				if (dirPhotosProfileClean.exists()) {
					FileUtils.forceDelete(dirPhotosProfileClean);
				}
				
				// Upload ขึ้น server
				// ตำแหน่ง ไฟล์ที่ crop
				File dirPhotoCrop = new File(path + File.separator + "resources" + File.separator + "photos" + File.separator + user.getUserId() + File.separator + photoUrl);
				// ตำแหน่งเก็บ
				File dirPhotoStore = new File(path + File.separator + "resources" + File.separator + "photos-profile");
				
				BufferedImage image = ImageIO.read(dirPhotoCrop);
				BufferedImage crop = image.getSubimage(Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(width), Integer.parseInt(height));
				
				// upload ไว้ใน photos-profile
	            ImageIO.write(crop, "jpg", new File(dirPhotoStore.getPath() + File.separator + photoUrl));
	            
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				DB.closeConnection();
			}
		}
	}
	
	
	public void personalDataUpdateAjax() {
		Connection conn = null;
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String birthday = request.getParameter("birthday");
		
		User sessionUser = (User)session.get("user");
		if (firstname != null && lastname != null && birthday != null && sessionUser != null) {
			try {
				conn = DB.getConnection();
				
				User user = new User(sessionUser.getUserId(), firstname, lastname, DateUtil.setStrInputToUtilDate(birthday, "yyyy-MMM-dd"));
				SettingDao settingDao = new SettingDao();
				settingDao.updatePersonalDate(user, conn);
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				DB.closeConnection();
			}
		}
	}
	
	public void templateUpdateAjaxColor() {
		Connection conn = null;
		String colorProfile = request.getParameter("colorProfile");
		
		User user = (User)session.get("user");
		if (colorProfile != null && user != null) {
			try {
				conn = DB.getConnection();
				
				SettingDao settingDao = new SettingDao();
				settingDao.updateColorProfile(user.getUserId(), colorProfile, conn);
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				DB.closeConnection();
			}
		}
	}
	
	// delete Photo
	public void deletePhoto() {
		String photoId = request.getParameter("photoId");
		User user = (User) session.get("user");
		if (photoId != null && user != null) {
			Connection conn = null;
			try {
				conn = DB.getConnection();
				// delete data within mysql
				UploadDao uploadDao = new UploadDao();
				String photoUrl = uploadDao.deletePhotoRecentByPhotoId(photoId, conn);
				
				// delete file
				String path = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/");
				File dirStore = new File(path + File.separator + "resources" + File.separator + "photos" + File.separator + user.getUserId() + File.separator + photoUrl);
				dirStore.delete();
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
