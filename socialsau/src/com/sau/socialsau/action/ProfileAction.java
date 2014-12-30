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
import com.sau.socialsau.dao.PhotoDao;
import com.sau.socialsau.dao.PostDao;
import com.sau.socialsau.dao.UserDao;
import com.sau.socialsau.dto.Photo;
import com.sau.socialsau.dto.Post;
import com.sau.socialsau.dto.ViewPost;
import com.sau.socialsau.dto.User;
import com.sau.socialsau.util.DB;

public class ProfileAction extends ActionSupport implements ServletRequestAware, SessionAware {

	private static final long serialVersionUID = -7450375043566862073L;

	HttpServletRequest request;
	Map<String, Object> session;
	
	public String profile() {
		String urlProfile = ActionContext.getContext().getName();
		User user = (User)session.get("user");
		if (user != null) {
			Connection conn = null;
			try {
				conn = DB.getConnection();
				// user ที่อยู่  profile นั้น
				User userProfile = new User();
				UserDao userDao = new UserDao();
				userProfile = userDao.findUserByURLProfile(urlProfile, conn);
				request.setAttribute("userProfile", userProfile);
				
				// Photos
				List<Photo> photos = new ArrayList<Photo>();
				PhotoDao photoDao = new PhotoDao();
				photos = photoDao.findAllPhoto(conn);
				request.setAttribute("photos", photos);
				
				// user post
				// 1. หา postId
				PostDao postDao = new PostDao();
				List<Integer> postIdList  = postDao.findUserPost(userProfile.getUserId(), conn);
				
				// 2. viewPost ข้อมูลคนโพสทั้งหมด
				List<ViewPost> posts = new ArrayList<ViewPost>();
				for (Integer postId : postIdList) {
					ViewPost viewPost = new ViewPost();
					viewPost = postDao.findViewPostByUserId(postId, conn);
					posts.add(viewPost);
				}
				
				request.setAttribute("posts", posts);
				
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
	
	public void postAjax() throws Exception {
		String userId = request.getParameter("userId");
		String postBy = request.getParameter("postBy");
		String postDetail = request.getParameter("post");
		if (userId != null && postBy != null && postDetail != null) {
			Connection conn = null;
			try {
				conn = DB.getConnection();
				PostDao postDao = new PostDao();
				Post post = new Post(Integer.parseInt(userId), Integer.parseInt(postBy), postDetail);
				postDao.savePost(post, conn);
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				DB.closeConnection();
			}
		}
	}
	
	public void deletePostAjax() throws Exception {
		String postId = request.getParameter("postId");
		if (postId != null) {
			try {
				Connection conn = DB.getConnection();
				PostDao postDao = new PostDao();
				postDao.deletePostByPostId(postId, conn);
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				DB.closeConnection();
			}
		}
	}
	
	public void editPostAjax() {
		String postId = request.getParameter("postId");
		String postDetail = request.getParameter("postDetail");
		if (postId != null && postDetail != null) {
			try {
				Connection conn = DB.getConnection();
				PostDao postDao = new PostDao();
				postDao.updatePost(postId, postDetail, conn);
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
