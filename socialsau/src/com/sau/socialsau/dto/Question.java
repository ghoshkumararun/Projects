package com.sau.socialsau.dto;

import java.util.Date;

public class Question {

	private Integer questionId;
	private Integer tutorialId;
	private String questionName;
	private String questionNameReport;
	private String questionA;
	private String questionB;
	private String questionC;
	private String questionD;
	private String questionAnswer;
	private String questionExplanation;
	private String questionExplanationReport;
	private String userUpdate;
	private String userCreate;
	private Date questionUpdate;
	private Date questionCreate;
	private String status;
	
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public String getQuestionA() {
		return questionA;
	}
	public void setQuestionA(String questionA) {
		this.questionA = questionA;
	}
	public String getQuestionB() {
		return questionB;
	}
	public void setQuestionB(String questionB) {
		this.questionB = questionB;
	}
	public String getQuestionC() {
		return questionC;
	}
	public void setQuestionC(String questionC) {
		this.questionC = questionC;
	}
	public String getQuestionD() {
		return questionD;
	}
	public void setQuestionD(String questionD) {
		this.questionD = questionD;
	}
	public String getQuestionAnswer() {
		return questionAnswer;
	}
	public void setQuestionAnswer(String questionAnswer) {
		this.questionAnswer = questionAnswer;
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
	public Date getQuestionUpdate() {
		return questionUpdate;
	}
	public void setQuestionUpdate(Date questionUpdate) {
		this.questionUpdate = questionUpdate;
	}
	public Date getQuestionCreate() {
		return questionCreate;
	}
	public void setQuestionCreate(Date questionCreate) {
		this.questionCreate = questionCreate;
	}
	public Integer getTutorialId() {
		return tutorialId;
	}
	public void setTutorialId(Integer tutorialId) {
		this.tutorialId = tutorialId;
	}
	public String getQuestionName() {
		return questionName;
	}
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	public String getQuestionExplanation() {
		return questionExplanation;
	}
	public void setQuestionExplanation(String questionExplanation) {
		this.questionExplanation = questionExplanation;
	}
	public String getQuestionNameReport() {
		return questionNameReport;
	}
	public void setQuestionNameReport(String questionNameReport) {
		this.questionNameReport = questionNameReport;
	}
	public String getQuestionExplanationReport() {
		return questionExplanationReport;
	}
	public void setQuestionExplanationReport(String questionExplanationReport) {
		this.questionExplanationReport = questionExplanationReport;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
