package com.sau.socialsau.dto;

import java.util.Date;

public class ViewUser {

	// User
	private Integer userId;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private Date birthday;
	private String role;
	private String confirmCode;
	private String status;
	private String urlProfile;
	
	// Photo
	private Integer photoId;
	private String photoUrl;
	private Date photoUpdate;
	private Date photoCreate;
	private String photoStatus;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getConfirmCode() {
		return confirmCode;
	}
	public void setConfirmCode(String confirmCode) {
		this.confirmCode = confirmCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Integer getPhotoId() {
		return photoId;
	}
	public void setPhotoId(Integer photoId) {
		this.photoId = photoId;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public Date getPhotoUpdate() {
		return photoUpdate;
	}
	public void setPhotoUpdate(Date photoUpdate) {
		this.photoUpdate = photoUpdate;
	}
	public Date getPhotoCreate() {
		return photoCreate;
	}
	public void setPhotoCreate(Date photoCreate) {
		this.photoCreate = photoCreate;
	}
	public String getPhotoStatus() {
		return photoStatus;
	}
	public void setPhotoStatus(String photoStatus) {
		this.photoStatus = photoStatus;
	}
	public String getUrlProfile() {
		return urlProfile;
	}
	public void setUrlProfile(String urlProfile) {
		this.urlProfile = urlProfile;
	}
	
}
