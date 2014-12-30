package com.sau.socialsau.dto;

import java.util.Date;

public class Tutorial {

	private Integer tutorialId;
	private Integer userId;
	private Integer groupId;
	private String tutorialName;
	private String tutorialDetail;
	private String tutorialDetailReport;
	private String tutorialImage;
	private String userUpdate;
	private String userCreate;
	private Date tutorialUpdate;
	private Date tutorialCreate;
	private String status;
	
	public Tutorial() {}
	public Tutorial(Integer userId, Integer groupId, String tutorialName, String tutorialDetail, String tutorialDetailReport) {
		this.userId = userId;
		this.groupId = groupId;
		this.tutorialName = tutorialName;
		this.tutorialDetail = tutorialDetail;
		this.tutorialDetailReport = tutorialDetailReport;
	}
	
	public Tutorial(Integer tutorialId, Integer groupId, String tutorialName, String tutorialDetail, String tutorialDetailReport, String userUpdate) {
		this.tutorialId = tutorialId;
		this.groupId = groupId;
		this.tutorialName = tutorialName;
		this.tutorialDetail = tutorialDetail;
		this.tutorialDetailReport = tutorialDetailReport;
		this.userUpdate = userUpdate;
	}
	
	// Generate Getter and Setter
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
