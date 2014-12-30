package com.sau.socialsau.dto;

public class QuestionDetail {

	private Integer questionDetailId;
	private Integer userId;
	private Integer questionId;
	// แก้เป็น INT
	private Integer testNo;
	private String answer;
	
	// Constructor
	public QuestionDetail() {}
	public QuestionDetail(Integer userId, Integer questionId, Integer testNo, String answer) {
		this.userId = userId;
		this.questionId = questionId;
		this.testNo = testNo;
		this.answer = answer;
	}
	
	// Generate Getter and Setter
	public Integer getUserId() {
		return userId;
	}
	public Integer getQuestionDetailId() {
		return questionDetailId;
	}
	public void setQuestionDetailId(Integer questionDetailId) {
		this.questionDetailId = questionDetailId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Integer getTestNo() {
		return testNo;
	}
	public void setTestNo(Integer testNo) {
		this.testNo = testNo;
	}
	
}
