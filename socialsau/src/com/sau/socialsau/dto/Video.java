package com.sau.socialsau.dto;

import java.util.Date;

public class Video {

	private Integer videoId;
	private Integer tutorialId;
	private String videoName;
	private String videoUrl;
	private String videoCode;
	private Date videoCreate;
	private String status;
	
	// Constructor
	public Video() {}
	public Video(Integer tutorialId, String videoName, String videoUrl) {
		this.tutorialId = tutorialId;
		this.videoName = videoName;
		this.videoUrl = videoUrl;
	}
	public Video (Integer tutorialId, String videoName, String videoUrl, String videoCode) {
		this.tutorialId = tutorialId;
		this.videoName = videoName;
		this.videoUrl = videoUrl;
		this.videoCode = videoCode;
	}
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVideoCode() {
		return videoCode;
	}
	public void setVideoCode(String videoCode) {
		this.videoCode = videoCode;
	}
	@Override
	public String toString() {
		return "Video [videoId=" + videoId + ", tutorialId=" + tutorialId
				+ ", videoName=" + videoName + ", videoUrl=" + videoUrl + "]";
	}
	
}
