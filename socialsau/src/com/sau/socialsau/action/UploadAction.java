package com.sau.socialsau.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
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
import com.sau.socialsau.dao.TutorialDao;
import com.sau.socialsau.dao.UploadDao;
import com.sau.socialsau.dto.Chapter;
import com.sau.socialsau.dto.Photo;
import com.sau.socialsau.dto.TutorialGroupUser;
import com.sau.socialsau.dto.User;
import com.sau.socialsau.util.DB;

public class UploadAction extends ActionSupport implements ServletRequestAware, SessionAware {

	private static final long serialVersionUID = -607367699339548985L;
	
	HttpServletRequest request;
	Map<String, Object> session;
	
	public String fileupload() {
		return SUCCESS;
	}
	
	// File upload
	private List<File> upload;
	private List<String> uploadContentType;
	private List<String> uploadFileName;

	public void setUpload(List<File> upload) {this.upload = upload;}
	public void setUploadContentType(List<String> uploadContentType) {this.uploadContentType = uploadContentType;}
	public void setUploadFileName(List<String> uploadFileName) {this.uploadFileName = uploadFileName;}

	public List<File> getUpload() {return upload;}
	public List<String> getUploadContentType() {return uploadContentType;}
	public List<String> getUploadFileName() {return uploadFileName;}
	
	public String fileuploadPerform() throws Exception {
		Connection conn = null;
		User user = (User) session.get("user");
		
		if (upload != null && !upload.isEmpty() && user != null) {
			try {
				conn = DB.getConnection();
				
				// เช็ค contentType นามสกุลไฟล์
				for (String contentType : uploadContentType) {
					if (!contentType.equals("image/bmp") && !contentType.equals("image/gif") && !contentType.equals("image/jpeg") && !contentType.equals("image/png")) {
						// ถ้าไฟล์ไม่เท่ากับ bmp, gif, jpeg และ png (ไฟล์ผิด ไม่ตรงกับด้านบน)
						addActionError("กรุณาเลือก Image File เท่านั้น");
						return ERROR;
					}
				}
				
				String path = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/");
				File dirStore = new File(path + File.separator + "resources" + File.separator + "photos" + File.separator + user.getUserId());
				System.out.println(dirStore.getPath());
				
				if (!dirStore.exists()) {
					System.out.println("create diractory : " + dirStore.getPath());
					dirStore.mkdir(); // Create directory
				}
			
				UploadDao uploadDao = new UploadDao();
				List<Photo> photoUploads = new ArrayList<Photo>();
				for (int i = 0; i < upload.size(); i++) {
					// save
					Photo photo = new Photo(user.getUserId(), upload.get(i).length());
					Integer photoId = uploadDao.savePhotoUpload(photo, conn);
					
					// update
					String filename = photoId + ".jpg";
					uploadDao.updatePhotoURL(photoId, filename, conn);
					
					// upload ขึ้น server
					BufferedImage image = ImageIO.read(upload.get(i));
		            ImageIO.write(image, "jpg", new File(dirStore.getPath() + File.separator + filename));
		            
		            
		            // เก็บ Photo เพื่อแสดงผล
		            Photo photoUpload = new Photo();
		            PhotoDao photoDao = new PhotoDao();
		            photoUpload = photoDao.findPhotoByPhotoId(String.valueOf(filename), conn);
		            photoUploads.add(photoUpload);
				}
				
				request.setAttribute("photoUploads", photoUploads);
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
	
	// delete Photo recent
	public void deletePhotoUpload() {
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
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
}
