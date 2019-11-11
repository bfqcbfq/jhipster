package com.ivision.app.domain;


/**
 * 调查问卷客户留言信息
 *
 */
public class SurveyBPNote {
	
	private String questionOne;
	private String questionTwo;
	private String questionThree;
	private String questionFour;
	private String questionFive;;
	private String questionSix;
	private String questionSeven;
	public SurveyBPNote() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SurveyBPNote(String questionOne, String questionTwo, String questionThree, String questionFour,
			String questionFive, String questionSix, String questionSeven) {
		super();
		this.questionOne = questionOne;
		this.questionTwo = questionTwo;
		this.questionThree = questionThree;
		this.questionFour = questionFour;
		this.questionFive = questionFive;
		this.questionSix = questionSix;
		this.questionSeven = questionSeven;
	}
	public String getQuestionOne() {
		return questionOne;
	}
	public void setQuestionOne(String questionOne) {
		this.questionOne = questionOne;
	}
	public String getQuestionTwo() {
		return questionTwo;
	}
	public void setQuestionTwo(String questionTwo) {
		this.questionTwo = questionTwo;
	}
	public String getQuestionThree() {
		return questionThree;
	}
	public void setQuestionThree(String questionThree) {
		this.questionThree = questionThree;
	}
	public String getQuestionFour() {
		return questionFour;
	}
	public void setQuestionFour(String questionFour) {
		this.questionFour = questionFour;
	}
	public String getQuestionFive() {
		return questionFive;
	}
	public void setQuestionFive(String questionFive) {
		this.questionFive = questionFive;
	}
	public String getQuestionSix() {
		return questionSix;
	}
	public void setQuestionSix(String questionSix) {
		this.questionSix = questionSix;
	}
	public String getQuestionSeven() {
		return questionSeven;
	}
	public void setQuestionSeven(String questionSeven) {
		this.questionSeven = questionSeven;
	}
	@Override
	public String toString() {
		return "SurveyBPNote [questionOne=" + questionOne + ", questionTwo=" + questionTwo + ", questionThree="
				+ questionThree + ", questionFour=" + questionFour + ", questionFive=" + questionFive + ", questionSix="
				+ questionSix + ", questionSeven=" + questionSeven + "]";
	}
	
	
	
	
}
