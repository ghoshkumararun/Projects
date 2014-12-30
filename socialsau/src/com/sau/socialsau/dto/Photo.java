package com.sau.socialsau.dto;

import java.util.Date;

public class Photo {

	private Integer photoId;
	private Integer userId;
	private String photoUrl;
	private Long photoSize;
	private Date photoCreate;
	
	// Constructor
	public Photo() {}
	public Photo(Integer userId, Long photoSize) {
		this.userId = userId;
		this.photoSize = photoSize;
	}
	
	// Generate Getter and Setter
	public Integer getPhotoId() {
		return photoId;
	}
	public void setPhotoId(Integer photoId) {
		this.photoId = photoId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public Date getPhotoCreate() {
		return photoCreate;
	}
	public void setPhotoCreate(Date photoCreate) {
		this.photoCreate = photoCreate;
	}
	public Long getPhotoSize() {
		return photoSize;
	}
	public void setPhotoSize(Long photoSize) {
		this.photoSize = photoSize;
	}
}
