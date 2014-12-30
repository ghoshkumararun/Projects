package com.sau.socialsau.dto;

import java.util.Date;

public class ViewPost {

	// Post
	private Integer postId;
	// ถ้าเป็น id เพื่อนต้องเอาจาก psotFormUserId
	private Integer postBy;
	private String postDetail;
	private Date postCreate;
	
	// User
	private Integer userId;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private Date birthday;
	private String confirmCode;
	private String status;
	private String role;
	private String urlProfile;
	private String colorProfile;
	
	private String imageProfile;
	
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public Integer getPostBy() {
		return postBy;
	}
	public void setPostBy(Integer postBy) {
		this.postBy = postBy;
	}
	public String getPostDetail() {
		return postDetail;
	}
	public void setPostDetail(String postDetail) {
		this.postDetail = postDetail;
	}
	public Date getPostCreate() {
		return postCreate;
	}
	public void setPostCreate(Date postCreate) {
		this.postCreate = postCreate;
	}
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
	public String getUrlProfile() {
		return urlProfile;
	}
	public void setUrlProfile(String urlProfile) {
		this.urlProfile = urlProfile;
	}
	public String getColorProfile() {
		return colorProfile;
	}
	public void setColorProfile(String colorProfile) {
		this.colorProfile = colorProfile;
	}
	public String getImageProfile() {
		return imageProfile;
	}
	public void setImageProfile(String imageProfile) {
		this.imageProfile = imageProfile;
	}
	
}
