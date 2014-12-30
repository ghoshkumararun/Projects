package com.sau.android.socialsau.dto;

import java.io.Serializable;
import java.util.Date;

public class Tutorial implements Serializable{

	private static final long serialVersionUID = 6070842357687725650L;
	
	private Integer tutorialId;
	private Integer userId;
	private Integer groupId;
	private String tutorialName;
	private String tutorialDetailReport;
	private String userUpdate;
	private String userCreate;
	private String tutorialUpdateStr;
	private String tutorialCreateStr;
	
	
	public Tutorial() {}
	public Tutorial(String tutorialName, String tutorialUpdateStr) {
		this.tutorialName = tutorialName;
		this.tutorialUpdateStr = tutorialUpdateStr;
	}
	public Tutorial(Integer userId, Integer groupId, String tutorialName, String tutorialDetail, String tutorialDetailReport) {
		this.userId = userId;
		this.groupId = groupId;
		this.tutorialName = tutorialName;
		this.tutorialDetailReport = tutorialDetailReport;
	}
	public Tutorial(Integer tutorialId, Integer groupId, String tutorialName, String tutorialDetail, String tutorialDetailReport, String userUpdate) {
		this.tutorialId = tutorialId;
		this.groupId = groupId;
		this.tutorialName = tutorialName;
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
	public String getTutorialDetailReport() {
		return tutorialDetailReport;
	}
	public void setTutorialDetailReport(String tutorialDetailReport) {
		this.tutorialDetailReport = tutorialDetailReport;
	}
	public String getTutorialUpdateStr() {
		return tutorialUpdateStr;
	}
	public void setTutorialUpdateStr(String tutorialUpdateStr) {
		this.tutorialUpdateStr = tutorialUpdateStr;
	}
	public String getTutorialCreateStr() {
		return tutorialCreateStr;
	}
	public void setTutorialCreateStr(String tutorialCreateStr) {
		this.tutorialCreateStr = tutorialCreateStr;
	}
	
}
