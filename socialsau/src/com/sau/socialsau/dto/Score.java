package com.sau.socialsau.dto;

import java.util.Date;

public class Score {

	private Integer scoreId;
	private Integer userId;
	private Integer tutorialId;
	private Integer testNo;
	private Integer scoreCorrect;
	private Integer maxScore;
	private Double percentCorrect;
	private Date scoreCreate;
	private String time;
	
	// Constructor
	public Score() {}
	
	public Score(Integer userId, Integer tutorialId, Integer testNo, Integer scoreCorrect , Integer maxScore, Double percentCorrect, String time) {
		this.userId = userId;
		this.tutorialId = tutorialId;
		this.scoreCorrect = scoreCorrect;
		this.testNo = testNo;
		this.maxScore = maxScore;
		this.percentCorrect = percentCorrect;
		this.time = time;
	}

	// Generate Getter and Setter
	public Integer getScoreId() {
		return scoreId;
	}
	public void setScoreId(Integer scoreId) {
		this.scoreId = scoreId;
	}
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTutorialId() {
		return tutorialId;
	}

	public void setTutorialId(Integer tutorialId) {
		this.tutorialId = tutorialId;
	}

	public Integer getScoreCorrect() {
		return scoreCorrect;
	}
	public void setScoreCorrect(Integer scoreCorrect) {
		this.scoreCorrect = scoreCorrect;
	}
	public Integer getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(Integer maxScore) {
		this.maxScore = maxScore;
	}
	public Date getScoreCreate() {
		return scoreCreate;
	}
	public void setScoreCreate(Date scoreCreate) {
		this.scoreCreate = scoreCreate;
	}

	public Integer getTestNo() {
		return testNo;
	}

	public void setTestNo(Integer testNo) {
		this.testNo = testNo;
	}
	public Double getPercentCorrect() {
		return percentCorrect;
	}

	public void setPercentCorrect(Double percentCorrect) {
		this.percentCorrect = percentCorrect;
	}
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Score [scoreId=" + scoreId + ", userId=" + userId
				+ ", tutorialId=" + tutorialId + ", testNo=" + testNo
				+ ", scoreCorrect=" + scoreCorrect + ", maxScore=" + maxScore
				+ ", percentCorrect=" + percentCorrect + ", scoreCreate="
				+ scoreCreate + "]";
	}

}
