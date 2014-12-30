package com.sau.socialsau.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
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
import com.sau.socialsau.dao.GroupDao;
import com.sau.socialsau.dao.TutorialDao;
import com.sau.socialsau.dao.UploadDao;
import com.sau.socialsau.dto.Group;
import com.sau.socialsau.dto.Tutorial;
import com.sau.socialsau.dto.TutorialGroupUser;
import com.sau.socialsau.dto.User;
import com.sau.socialsau.util.DB;

public class TutorialAction extends ActionSupport implements ServletRequestAware, SessionAware {

	private static final long serialVersionUID = 4430061014825473663L;

	HttpServletRequest request;
	Map<String, Object> session;

	private File   upload;
    private String uploadFileName;
    private String uploadContentType;

    public void setUpload(File upload){this.upload = upload;}
    public void setUploadFileName(String uploadFileName){this.uploadFileName = uploadFileName;}
    public void setUploadContentType(String uploadContentType){this.uploadContentType = uploadContentType;}
	
	// หน้า management - tutorial/all
	public String all() throws Exception {
		Connection conn = null;
		List<Group> groups;
		try {
			conn = DB.getConnection();
			GroupDao groupDao = new GroupDao();
			
			// groups
			groups = groupDao.findAllGroup(conn);
			request.setAttribute("groups", groups);
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closeConnection();
		}
		return SUCCESS;
	}
	
	// หน้า Create Tutorial
	public String createTutorial() throws Exception {
		Connection conn = null;
		List<Group> groups;
		try {
			conn = DB.getConnection();
			GroupDao groupDao = new GroupDao();
			
			// groups
			groups = groupDao.findAllGroup(conn);
			request.setAttribute("groups", groups);
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closeConnection();
		}
		return SUCCESS;
	}

	public String createTutorialPerform() throws Exception {
		Connection conn = null;
		Tutorial tutorialFile = null;
		User user = (User) session.get("user");
		
		if (user != null) {
			
			String tutorialName = request.getParameter("tutorialName");
			String groupTutorial = request.getParameter("groupTutorial");
			String tutorialDetail = request.getParameter("tutorialDetail");
			String tutorialDetailReport = request.getParameter("tutorialDetailReport");
			
			// เช็ค contentType นามสกุลไฟล์
			if (upload != null) {
				if (!uploadContentType.equals("image/bmp") && !uploadContentType.equals("image/gif") && !uploadContentType.equals("image/jpeg") && !uploadContentType.equals("image/png")) {
					// ถ้าไฟล์ไม่เท่ากับ bmp, gif, jpeg และ png (ไฟล์ผิด ไม่ตรงกับด้านบน)
					addActionError("กรุณาเลือก Image File เท่านั้น");
					
					List<Group> groups;
					try {
						conn = DB.getConnection();
						GroupDao groupDao = new GroupDao();
						
						groups = groupDao.findAllGroup(conn);
						request.setAttribute("groups", groups);
					}catch (SQLException e) {
						e.printStackTrace();
					}finally {
						DB.closeConnection();
					}
					
					return ERROR;
				}
			}
			
			try {
				if (tutorialName != null && groupTutorial != null && tutorialDetail != null && tutorialDetailReport != null) {
					conn = DB.getConnection();
					TutorialDao tutorialDao = new TutorialDao();
					
					Tutorial tutorial = new Tutorial(user.getUserId(), Integer.parseInt(groupTutorial), tutorialName, tutorialDetail, tutorialDetailReport);
					
					// save - mysql
					tutorialFile = tutorialDao.saveTutorial(tutorial, user, conn);
					
					if (upload != null) {
						// update - mysql
						String filename = tutorialFile.getTutorialId() + ".jpg";
						tutorialDao.updateTutorialImageByTutorialId(tutorialFile.getTutorialId(), filename, conn);
						
						// upload ขึ้น server
						String path = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/");
						File dirStore = new File(path + File.separator + "resources" + File.separator + "photos-tutorial");
						
						BufferedImage imageUpload = ImageIO.read(upload);
			            ImageIO.write(imageUpload, "jpg", new File(dirStore.getPath() + File.separator + filename));
					}
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} finally {
				DB.closeConnection();
			}
		}
		return SUCCESS;
	}

	// หน้า  View Tutorial 
	public String viewTutorial() throws Exception {
		String tutorialId = request.getParameter("tutorialId");
		if (tutorialId != null) {
			Connection conn = null;
			Tutorial tutorial;
			try {
				conn = DB.getConnection();
				
				// turorial
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
	
	// หน้า editTutorial 
	public String editTutorial() {
		String tutorialId = request.getParameter("tutorialId");
		if (tutorialId != null) {
			Connection conn = null;
			Tutorial tutorial;
			List<Group> groups;
			//TutorialGroupUser tutorial;
			try {
				conn = DB.getConnection();
				
				// แสดงข้อมูล tutorial
				TutorialDao tutorialDao = new TutorialDao();
				tutorial = tutorialDao.findTutorial(Integer.parseInt(tutorialId), conn);
				request.setAttribute("tutorial", tutorial);
				
				// groups
				GroupDao groupDao = new GroupDao();
				groups = groupDao.findAllGroup(conn);
				request.setAttribute("groups", groups);
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
	public String editTutorialPerform() {
		Connection conn;
		User user = (User)session.get("user");
		
		if (user != null) {
		
			String tutorialId = request.getParameter("tutorialId");
			String tutorialName = request.getParameter("tutorialName");
			String groupTutorial = request.getParameter("groupTutorial");
			String tutorialDetail = request.getParameter("tutorialDetail");
			String tutorialDetailReport = request.getParameter("tutorialDetailReport");
			
			// เช็ค contentType นามสกุลไฟล์
			if (upload != null) {
				if (!uploadContentType.equals("image/bmp") && !uploadContentType.equals("image/gif") && !uploadContentType.equals("image/jpeg") && !uploadContentType.equals("image/png")) {
					// ถ้าไฟล์ไม่เท่ากับ bmp, gif, jpeg และ png (ไฟล์ผิด ไม่ตรงกับด้านบน)
					addActionError("กรุณาเลือก Image File เท่านั้น");
					
					List<Group> groups;
					Tutorial tutorial;
					
					try {
						conn = DB.getConnection();
						GroupDao groupDao = new GroupDao();
						TutorialDao tutorialDao = new TutorialDao();
	
						// groups
						groups = groupDao.findAllGroup(conn);
						request.setAttribute("groups", groups);
						
						// tutorial
						tutorial = tutorialDao.findTutorial(Integer.parseInt(tutorialId), conn);
						request.setAttribute("tutorial", tutorial);
					}catch (SQLException e) {
						e.printStackTrace();
					}finally {
						DB.closeConnection();
					}
					
					return ERROR;
				}
			}
			
			if (tutorialId != null && tutorialName != null && groupTutorial != null && tutorialDetail != null && tutorialDetailReport != null) {
				try {
					conn = DB.getConnection();
						
					// update
					Tutorial tutorial = new Tutorial(Integer.parseInt(tutorialId), Integer.parseInt(groupTutorial), tutorialName, tutorialDetail, tutorialDetailReport, user.getFirstname());
					if (upload != null) tutorial.setTutorialImage(tutorialId + ".jpg");
					TutorialDao tutorialDao = new TutorialDao();
					tutorialDao.editPerform(tutorial, conn);
					
					if (upload != null) {
						
						// ลบไฟล์เก่าบน Server
						String path = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/");
						File dirPhotoClean = new File(path + File.separator + "resources" + File.separator + "photos-tutorial" + File.separator + tutorialId + ".jpg");
						if (dirPhotoClean.exists()) {
							FileUtils.forceDelete(dirPhotoClean);
						}
						
						
						// upload ขึ้น server
						String filename = tutorialId + ".jpg";
						File dirPhotoTutorial = new File(path + File.separator + "resources" + File.separator + "photos-tutorial");
						
						BufferedImage image = ImageIO.read(upload);
						ImageIO.write(image, "jpg", new File(dirPhotoTutorial.getPath() + File.separator + filename));
						
					}
				}catch (FileNotFoundException e) {
					e.printStackTrace();
				}catch (Exception e) {
					e.printStackTrace();
				}finally {
					DB.closeConnection();
				}
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
	public String findTutorialRelationship() throws Exception {
		Connection conn = null;
		String tutorialId = request.getParameter("tutorialId");
		if (tutorialId != null) {
			try {
				conn = DB.getConnection();
				TutorialDao tutorialDao = new TutorialDao();
				stream = new ByteArrayInputStream(tutorialDao.findTutorialRelationship(Integer.parseInt(tutorialId), conn).getBytes("UTF-8"));
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
	public void deleteTutorialAJAX() throws Exception {
		Connection conn = null;
		String tutorialId = request.getParameter("tutorialId");
		if (tutorialId != null) {
			try {
				conn = DB.getConnection();
				TutorialDao tutorialDao = new TutorialDao();
				tutorialDao.deleteTutorial(Integer.parseInt(tutorialId), conn);
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				DB.closeConnection();
			}
		}
	}
	
	public void updateTutorialStatusAjax() throws Exception {
		Connection conn = null;
		String tutorialId = request.getParameter("tutorialId");
		String status = request.getParameter("status");
		if (tutorialId != null && status != null) {
			try {
				conn = DB.getConnection();
				TutorialDao tutorialDao = new TutorialDao();
				tutorialDao.updateTutorialStatus(Integer.parseInt(tutorialId), status, conn);
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
