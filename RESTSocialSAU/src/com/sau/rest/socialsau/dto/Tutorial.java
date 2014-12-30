package com.sau.rest.socialsau.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tutorial")
public class Tutorial {

	@Id
	@Column(name = "TUTORIAL_ID")
	private Integer tutorialId;
	
	@Column(name = "USER_ID")
	private Integer userId;
	
	@Column(name = "GROUP_ID")
	private Integer groupId;
	
	@Column(name = "TUTORIAL_NAME")
	private String tutorialName;
	
	@Column(name = "TUTORIAL_DETAIL")
	private String tutorialDetail;
	
	@Column(name = "TUTORIAL_DETAIL_REPORT")
	private String tutorialDetailReport;
	
	@Column(name = "TUTORIAL_IMAGE")
	private String tutorialImage;
	
	@Column(name = "USER_UPDATE")
	private String userUpdate;
	
	@Column(name = "USER_CREATE")
	private String userCreate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TUTORIAL_UPDATE")
	private Date tutorialUpdate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TUTORIAL_CREATE")
	private Date tutorialCreate;
	
	private String tutorialUpdateStr;
	private String tutorialCreateStr;
	
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
