package com.sau.socialsau.dto;

import java.util.Date;

public class Post {

	private Integer postId;
	private Integer userId;
	private Integer postBy;
	private String postDetail;
	private Date postCreate;
	
	// Constructor
	public Post() {}
	public Post(Integer userId, Integer postBy, String postDetail) {
		this.userId = userId;
		this.postBy = postBy;
		this.postDetail = postDetail;
	}
	
	// Generate Getter and Setter
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	public Integer getPostBy() {
		return postBy;
	}
	public void setPostBy(Integer postBy) {
		this.postBy = postBy;
	}
}
