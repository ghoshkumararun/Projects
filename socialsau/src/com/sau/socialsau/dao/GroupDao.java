package com.sau.socialsau.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.PSource;

import com.sau.socialsau.dto.Group;
import com.sau.socialsau.util.DB;

public class GroupDao {

	public void saveGroup(String groupName, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO `group` (GROUP_NAME) VALUE (?)");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, groupName);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			ps.close();
		}
	}
	
	// AJAX EDIT
	public void editGroupByGroupId(String groupId, String groupName, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE `group` SET `group`.GROUP_NAME = ?");
		sql.append("WHERE `group`.GROUP_ID = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, groupName);
			ps.setString(2, groupId);
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
	}
	
	public List<Group> findAllGroup(Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM `group`");
		try {
			ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			List<Group> groups = new ArrayList<Group>();
			while (rs.next()) {
				Group group = new Group();
				group.setGroupId(rs.getInt("GROUP_ID"));
				group.setGroupName(rs.getString("GROUP_NAME"));
				group.setStatus(rs.getString("STATUS"));
				groups.add(group);
			}
			return groups;
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
		return null;
	}
	
	// findAll Status Active
	// ถ้าไม่มีการ inner join มันจะเอา group มาทั้งหมด
	public List<Group> findAllGroupTutorialStatusA(Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM `group` ");
		sql.append("INNER JOIN tutorial ON tutorial.GROUP_ID = `group`.GROUP_ID ");
		sql.append("WHERE `group`.`STATUS` = 'A' ");
		sql.append("GROUP BY `group`.GROUP_NAME");
		try {
			ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			List<Group> groups = new ArrayList<Group>();
			while (rs.next()) {
				Group group = new Group();
				group.setGroupId(rs.getInt("GROUP_ID"));
				group.setGroupName(rs.getString("GROUP_NAME"));
				group.setStatus(rs.getString("STATUS"));
				groups.add(group);
			}
			return groups;
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
		return null;
	}
	
	public String findGroupRelationship(Integer groupId, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(*) AS COUNT_RELATIONSHIP FROM `group` ");
		sql.append("INNER JOIN tutorial ON tutorial.GROUP_ID = `group`.GROUP_ID ");
		sql.append("WHERE `group`.GROUP_ID = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, groupId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getInt("COUNT_RELATIONSHIP") > 0) {
					return "fail";
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
		return "success";
	}
	
	// Delete ข้อมูลที่เกี่ยวกับ tutorial 
	// Delete แบบ Constraint
	public void deleteGroupByGroupId(Integer groupId, Connection conn) throws SQLException {
		conn.setAutoCommit(false);
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM `group` WHERE `group`.GROUP_ID = ?");
		try {
			// Delete
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, groupId);
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
			conn.setAutoCommit(true);
		}
	}
	
	public void updateGroupStatus(Integer groupId, String status, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE `group` SET `group`.`STATUS` = ? WHERE `group`.GROUP_ID = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, status);
			ps.setInt(2, groupId);
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
	}
	
}
