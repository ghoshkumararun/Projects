package com.sau.socialsau.dto;

import java.util.Date;

public class TutorialGroupUser {

	private Integer tutorialId;
	private String userName;
	private String groupName;
	private String tutorialName;
	private String tutorialDetail;
	private String tutorialDetailReport;
	private String tutorialImage;
	private String userUpdate;
	private String userCreate;
	private Date tutorialUpdate;
	private Date tutorialCreate;
	private String tutorialUpdateString;
	
	public Integer getTutorialId() {
		return tutorialId;
	}
	public void setTutorialId(Integer tutorialId) {
		this.tutorialId = tutorialId;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTutorialUpdateString() {
		return tutorialUpdateString;
	}
	public void setTutorialUpdateString(String tutorialUpdateString) {
		this.tutorialUpdateString = tutorialUpdateString;
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
	public String getTutorialDetailReport() {
		return tutorialDetailReport;
	}
	public void setTutorialDetailReport(String tutorialDetailReport) {
		this.tutorialDetailReport = tutorialDetailReport;
	}
	public String getTutorialImage() {
		return tutorialImage;
	}
	public void setTutorialImage(String tutorialImage) {
		this.tutorialImage = tutorialImage;
	}
	@Override
	public String toString() {
		return "TutorialGroupUser [tutorialId=" + tutorialId + ", userName="
				+ userName + ", tutorialImage=" + tutorialImage + "]";
	}
}
