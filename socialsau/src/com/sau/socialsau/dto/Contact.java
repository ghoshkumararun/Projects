package com.sau.socialsau.dto;

import java.util.Date;

public class Contact {

	private Integer contactId;
	private Integer userId;
	private String contactFirstname;
	private String contactLastname;
	private String contactEmail;
	private String contactDetail;
	private Date contactCreate;
	private String contactCreateStr;
	private String contactStatus;
	private String contactAnswer;
	
	public Contact() {}
	public Contact(Integer userId, String contactFirstname, String contactLastname, String contactEmail, String contactDetail) {
		this.userId = userId;
		this.contactFirstname = contactFirstname;
		this.contactLastname = contactLastname;
		this.contactEmail = contactEmail;
		this.contactDetail = contactDetail;
	}
	
	public Integer getContactId() {
		return contactId;
	}
	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}
	public String getContactFirstname() {
		return contactFirstname;
	}
	public void setContactFirstname(String contactFirstname) {
		this.contactFirstname = contactFirstname;
	}
	public String getContactLastname() {
		return contactLastname;
	}
	public void setContactLastname(String contactLastname) {
		this.contactLastname = contactLastname;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getContactDetail() {
		return contactDetail;
	}
	public void setContactDetail(String contactDetail) {
		this.contactDetail = contactDetail;
	}
	public Date getContactCreate() {
		return contactCreate;
	}
	public void setContactCreate(Date contactCreate) {
		this.contactCreate = contactCreate;
	}
	public String getContactStatus() {
		return contactStatus;
	}
	public void setContactStatus(String contactStatus) {
		this.contactStatus = contactStatus;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getContactCreateStr() {
		return contactCreateStr;
	}
	public void setContactCreateStr(String contactCreateStr) {
		this.contactCreateStr = contactCreateStr;
	}
	public String getContactAnswer() {
		return contactAnswer;
	}
	public void setContactAnswer(String contactAnswer) {
		this.contactAnswer = contactAnswer;
	}
	
}
