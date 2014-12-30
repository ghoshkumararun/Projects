package com.sau.socialsau.dto;

import java.util.Date;

public class Chapter {

	// Chapter
	private Integer chapterId;
	private Integer tutorialId;
	private Integer chapterNo;
	private String chapterName;
	private String chapterDetail;
	private String chapterDetailReport;
	private String userUpdate;
	private String userCreate;
	private Date chapterUpdate;
	private Date chapterCreate;
	private String status;

	// Generate Getter and Setter
	public Integer getChapterId() {
		return chapterId;
	}

	public void setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public String getChapterDetail() {
		return chapterDetail;
	}

	public void setChapterDetail(String chapterDetail) {
		this.chapterDetail = chapterDetail;
	}

	public Integer getTutorialId() {
		return tutorialId;
	}

	public void setTutorialId(Integer tutorialId) {
		this.tutorialId = tutorialId;
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

	public Date getChapterUpdate() {
		return chapterUpdate;
	}

	public void setChapterUpdate(Date chapterUpdate) {
		this.chapterUpdate = chapterUpdate;
	}

	public Date getChapterCreate() {
		return chapterCreate;
	}

	public void setChapterCreate(Date chapterCreate) {
		this.chapterCreate = chapterCreate;
	}

	public Integer getChapterNo() {
		return chapterNo;
	}

	public void setChapterNo(Integer chapterNo) {
		this.chapterNo = chapterNo;
	}
	
	public String getChapterDetailReport() {
		return chapterDetailReport;
	}

	public void setChapterDetailReport(String chapterDetailReport) {
		this.chapterDetailReport = chapterDetailReport;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Chapter [chapterId=" + chapterId + ", tutorialId=" + tutorialId
				+ ", chapterNo=" + chapterNo + ", chapterName=" + chapterName
				+ ", chapterDetail=" + chapterDetail + ", userUpdate="
				+ userUpdate + ", userCreate=" + userCreate
				+ ", chapterUpdate=" + chapterUpdate + ", chapterCreate="
				+ chapterCreate + "]";
	}

}
