package com.sau.socialsau.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sau.socialsau.dto.Contact;
import com.sau.socialsau.util.DB;

public class ContactDao {
	
	public List<Contact> findAllContactAdmin(Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM contact WHERE contact.CONTACT_STATUS = 'disabled'");
		try {
			ps = conn.prepareStatement(sql.toString());
			List<Contact> contacts = new ArrayList<Contact>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Contact contact = new Contact();
				contact.setContactId(rs.getInt("CONTACT_ID"));
				contact.setUserId(rs.getInt("USER_ID"));
				contact.setContactFirstname(rs.getString("CONTACT_FIRSTNAME"));
				contact.setContactLastname(rs.getString("CONTACT_LASTNAME"));
				contact.setContactEmail(rs.getString("CONTACT_EMAIL"));
				contact.setContactDetail(rs.getString("CONTACT_DETAIL"));
				contact.setContactCreate(rs.getTimestamp("CONTACT_CREATE"));
				contact.setContactStatus(rs.getString("CONTACT_STATUS"));
				contact.setContactAnswer(rs.getString("CONTACT_ANSWER"));
				contacts.add(contact);
			}
			return contacts;
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
		return null;
	}
	
	public void saveProblemContact(Contact contact, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO contact ");
		sql.append("(CONTACT_FIRSTNAME, ");
		sql.append("USER_ID, ");
		sql.append("CONTACT_LASTNAME, ");
		sql.append("CONTACT_EMAIL, ");
		sql.append("CONTACT_DETAIL, ");
		sql.append("CONTACT_CREATE, ");
		sql.append("CONTACT_STATUS) ");
		sql.append("VALUES (?, ?, ?, ?, ?, NOW(), 'disabled')");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, contact.getContactFirstname());
			ps.setInt(2, contact.getUserId());
			ps.setString(3, contact.getContactLastname());
			ps.setString(4, contact.getContactEmail());
			ps.setString(5, contact.getContactDetail());
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
	}
	
	public List<Contact> findAllContactReceive(Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM contact WHERE contact.CONTACT_STATUS = 'enabled'");
		try {
			ps = conn.prepareStatement(sql.toString());
			List<Contact> contacts = new ArrayList<Contact>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Contact contact = new Contact();
				contact.setContactId(rs.getInt("CONTACT_ID"));
				contact.setUserId(rs.getInt("USER_ID"));
				contact.setContactFirstname(rs.getString("CONTACT_FIRSTNAME"));
				contact.setContactLastname(rs.getString("CONTACT_LASTNAME"));
				contact.setContactEmail(rs.getString("CONTACT_EMAIL"));
				contact.setContactDetail(rs.getString("CONTACT_DETAIL"));
				contact.setContactCreate(rs.getTimestamp("CONTACT_CREATE"));
				contact.setContactStatus(rs.getString("CONTACT_STATUS"));
				contact.setContactAnswer(rs.getString("CONTACT_ANSWER"));
				contacts.add(contact);
			}
			return contacts;
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
		return null;
	}
	
	public Contact findContactByContactId(String contactId, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM contact ");
		sql.append("WHERE contact.CONTACT_ID = ?");
		Contact contact = new Contact();
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, contactId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				contact.setContactId(rs.getInt("CONTACT_ID"));
				contact.setUserId(rs.getInt("USER_ID"));
				contact.setContactFirstname(rs.getString("CONTACT_FIRSTNAME"));
				contact.setContactLastname(rs.getString("CONTACT_LASTNAME"));
				contact.setContactEmail(rs.getString("CONTACT_EMAIL"));
				contact.setContactDetail(rs.getString("CONTACT_DETAIL"));
				contact.setContactCreate(rs.getTimestamp("CONTACT_CREATE"));
				contact.setContactStatus(rs.getString("CONTACT_STATUS"));
				contact.setContactAnswer(rs.getString("CONTACT_ANSWER"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
		return contact;
	}

	public void updateContactAnswer(String contactId, String answer, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE contact SET ");
		sql.append("contact.CONTACT_STATUS = 'enabled', ");
		sql.append("contact.CONTACT_ANSWER = ? ");
		sql.append("WHERE contact.CONTACT_ID = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, answer);
			ps.setString(2, contactId);
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
		}
	}
	
}
