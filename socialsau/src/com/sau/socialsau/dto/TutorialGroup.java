package com.sau.socialsau.dto;

import java.util.Date;

public class TutorialGroup {

	private Integer tutorialId;
	private Integer userId;
	private Integer groupId;
	private String groupName;
	private String tutorialName;
	private String tutorialDetail;
	private String userUpdate;
	private String userCreate;
	private Date tutorialUpdate;
	private Date tutorialCreate;
	
	public Integer getTutorialId() {
		return tutorialId;
	}
	public void setTutorialId(Integer tutorialId) {
		this.tutorialId = tutorialId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getTutorialName() {
		return tutorialName;
	}
	public void setTutorialName(String tutorialName) {
		this.tutorialName = tutorialName;
	}
	public String getTutorialDetail() {
		return tutorialDetail;
	}
	public void setTutorialDetail(String tutorialDetail) {
		this.tutorialDetail = tutorialDetail;
	}
	public String getUserUpdate() {
		return userUpdate;
	}
	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}
	public String getUserCreate() {
		return userCreate;
	}
	public void setUserCreate(String userCreate) {
		this.userCreate = userCreate;
	}
	public Date getTutorialUpdate() {
		return tutorialUpdate;
	}
	public void setTutorialUpdate(Date tutorialUpdate) {
		this.tutorialUpdate = tutorialUpdate;
	}
	public Date getTutorialCreate() {
		return tutorialCreate;
	}
	public void setTutorialCreate(Date tutorialCreate) {
		this.tutorialCreate = tutorialCreate;
	}
	
	@Override
	public String toString() {
		return "TutorialGroup [tutorialId=" + tutorialId + ", userId=" + userId
				+ ", groupId=" + groupId + ", groupName=" + groupName
				+ ", tutorialName=" + tutorialName + ", tutorialDetail="
				+ tutorialDetail + ", userUpdate=" + userUpdate
				+ ", userCreate=" + userCreate + ", tutorialUpdate="
				+ tutorialUpdate + ", tutorialCreate=" + tutorialCreate + "]";
	}
	
}
