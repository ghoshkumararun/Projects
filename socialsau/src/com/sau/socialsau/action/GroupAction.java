package com.sau.socialsau.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sau.socialsau.dao.GroupDao;
import com.sau.socialsau.dto.Group;
import com.sau.socialsau.util.DB;

public class GroupAction extends ActionSupport implements ServletRequestAware, SessionAware {

	private static final long serialVersionUID = -6339776129960080978L;
	
	HttpServletRequest request;
	Map<String, Object> session;

	// หน้า  create-group.jsp
	public String createGroup() {
		return SUCCESS;
	}
	
	// หน้า  change-group.jsp
	public String changeGroup() {
		Connection conn = null;
		try {
			conn = DB.getConnection();
			GroupDao groupDao = new GroupDao();
			List<Group> groups = new ArrayList<Group>();
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
	
	// หน้า delete-group.jsp
	public String deleteGroup() throws Exception {
		Connection conn = null;
		try {
			conn = DB.getConnection();
			GroupDao groupDao = new GroupDao();
			List<Group> groups = new ArrayList<Group>();
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
	
	// AJAX เพิ่ม group ในหน้า create-group.jsp
	public void createGroupPerformAJAX() throws Exception {
		String groupName = request.getParameter("groupName");
		Connection conn = null;
		try {
			if (groupName != null) {
				conn = DB.getConnection();
				GroupDao groupDao = new GroupDao();
				groupDao.saveGroup(groupName, conn);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} finally {
			DB.closeConnection();
		}
	}

	// AJAX แก้ไข group ในหน้า change-group.jsp
	public void changeGroupPerformAJAX() throws Exception {
		String groupId = request.getParameter("groupId");
		String groupName = request.getParameter("groupName");
		Connection conn = null;
		if (groupId != null && groupName != null) {
			try {
				conn = DB.getConnection();
				// edit ชื่อ group
				GroupDao groupDao = new GroupDao();
				groupDao.editGroupByGroupId(groupId, groupName, conn);
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				DB.closeConnection();
			}
		}
	}
	
	
	// Stream เปรียบเสมือนท่อที่ใช้ส่งข้อมูลของ Byte
	private InputStream stream;
	//getter here
	public InputStream getStream() {
		return stream;
	}
	
	public String findGroupRelationship() throws Exception {
		String groupId = request.getParameter("groupId");
		Connection conn = null;
		if (groupId != null) {
			try {
				conn = DB.getConnection();
				GroupDao groupDao = new GroupDao();
				stream = new ByteArrayInputStream(groupDao.findGroupRelationship(Integer.parseInt(groupId), conn).getBytes("UTF-8"));
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
	
	// Delete Group ในหน้า delete-group.jsp
	public void deleteGroupPerformAJAX() throws Exception {
		String groupId = request.getParameter("groupId");
		Connection conn = null;
		if (groupId != null) {
			try {
				conn = DB.getConnection();
				GroupDao groupDao = new GroupDao();
				groupDao.deleteGroupByGroupId(Integer.parseInt(groupId), conn);
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				DB.closeConnection();
			}
		}
	}
	
	public void updateGroupStatusAjax() {
		String groupId = request.getParameter("groupId");
		String status = request.getParameter("status");
		Connection conn = null;
		if (groupId != null && status != null) {
			try {
				conn = DB.getConnection();
				GroupDao groupDao = new GroupDao();
				groupDao.updateGroupStatus(Integer.parseInt(groupId), status, conn);
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
