package com.sau.android.socialsau.dto;

import java.util.Date;

public class Video {

	private Integer videoId;
	private Integer tutorialId;
	private String videoName;
	private String videoUrl;
	private String videoCode;
	private Date videoCreate;
	private String videoCreateStr;
	private String stauts;
	
	// Generate getter and setter
	public Integer getVideoId() {
		return videoId;
	}
	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}
	public Integer getTutorialId() {
		return tutorialId;
	}
	public void setTutorialId(Integer tutorialId) {
		this.tutorialId = tutorialId;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public Date getVideoCreate() {
		return videoCreate;
	}
	public void setVideoCreate(Date videoCreate) {
		this.videoCreate = videoCreate;
	}
	public String getVideoCreateStr() {
		return videoCreateStr;
	}
	public void setVideoCreateStr(String videoCreateStr) {
		this.videoCreateStr = videoCreateStr;
	}
	public String getVideoCode() {
		return videoCode;
	}
	public void setVideoCode(String videoCode) {
		this.videoCode = videoCode;
	}
	public String getStauts() {
		return stauts;
	}
	public void setStauts(String stauts) {
		this.stauts = stauts;
	}
	@Override
	public String toString() {
		return "Video [videoId=" + videoId + ", tutorialId=" + tutorialId
				+ ", videoName=" + videoName + ", videoUrl=" + videoUrl
				+ ", videoCode=" + videoCode + ", videoCreate=" + videoCreate
				+ ", videoCreateStr=" + videoCreateStr + ", stauts=" + stauts
				+ "]";
	}
	
}
