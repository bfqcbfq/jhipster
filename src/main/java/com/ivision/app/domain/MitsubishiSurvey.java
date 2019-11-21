package com.ivision.app.domain;

public class MitsubishiSurvey extends BaseResource {
	
	private String name;
	private String companyName;
	private String telphone;
	private String email;
	private String questionOne;
	private String questionTwo;
	private String questionThree;
	private String questionFour;
	private String questionFive;
	private String questionSix;
	private String questionSeven;
	private String questionEight;
	private String questionNine;
	private String questionTen;
	private String comment;
	public MitsubishiSurvey() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MitsubishiSurvey(String name, String companyName, String telphone, String email, String questionOne,
			String questionTwo, String questionThree, String questionFour, String questionFive, String questionSix,
			String questionSeven, String questionEight, String questionNine, String questionTen, String comment) {
		super();
		this.name = name;
		this.companyName = companyName;
		this.telphone = telphone;
		this.email = email;
		this.questionOne = questionOne;
		this.questionTwo = questionTwo;
		this.questionThree = questionThree;
		this.questionFour = questionFour;
		this.questionFive = questionFive;
		this.questionSix = questionSix;
		this.questionSeven = questionSeven;
		this.questionEight = questionEight;
		this.questionNine = questionNine;
		this.questionTen = questionTen;
		this.comment = comment;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getQuestionEight() {
		return questionEight;
	}
	public void setQuestionEight(String questionEight) {
		this.questionEight = questionEight;
	}
	public String getQuestionNine() {
		return questionNine;
	}
	public void setQuestionNine(String questionNine) {
		this.questionNine = questionNine;
	}
	public String getQuestionTen() {
		return questionTen;
	}
	public void setQuestionTen(String questionTen) {
		this.questionTen = questionTen;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "MitsubishiSurvey [name=" + name + ", companyName=" + companyName + ", telphone=" + telphone + ", email="
				+ email + ", questionOne=" + questionOne + ", questionTwo=" + questionTwo + ", questionThree="
				+ questionThree + ", questionFour=" + questionFour + ", questionFive=" + questionFive + ", questionSix="
				+ questionSix + ", questionSeven=" + questionSeven + ", questionEight=" + questionEight
				+ ", questionNine=" + questionNine + ", questionTen=" + questionTen + ", comment=" + comment + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((questionEight == null) ? 0 : questionEight.hashCode());
		result = prime * result + ((questionFive == null) ? 0 : questionFive.hashCode());
		result = prime * result + ((questionFour == null) ? 0 : questionFour.hashCode());
		result = prime * result + ((questionNine == null) ? 0 : questionNine.hashCode());
		result = prime * result + ((questionOne == null) ? 0 : questionOne.hashCode());
		result = prime * result + ((questionSeven == null) ? 0 : questionSeven.hashCode());
		result = prime * result + ((questionSix == null) ? 0 : questionSix.hashCode());
		result = prime * result + ((questionTen == null) ? 0 : questionTen.hashCode());
		result = prime * result + ((questionThree == null) ? 0 : questionThree.hashCode());
		result = prime * result + ((questionTwo == null) ? 0 : questionTwo.hashCode());
		result = prime * result + ((telphone == null) ? 0 : telphone.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MitsubishiSurvey other = (MitsubishiSurvey) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (questionEight == null) {
			if (other.questionEight != null)
				return false;
		} else if (!questionEight.equals(other.questionEight))
			return false;
		if (questionFive == null) {
			if (other.questionFive != null)
				return false;
		} else if (!questionFive.equals(other.questionFive))
			return false;
		if (questionFour == null) {
			if (other.questionFour != null)
				return false;
		} else if (!questionFour.equals(other.questionFour))
			return false;
		if (questionNine == null) {
			if (other.questionNine != null)
				return false;
		} else if (!questionNine.equals(other.questionNine))
			return false;
		if (questionOne == null) {
			if (other.questionOne != null)
				return false;
		} else if (!questionOne.equals(other.questionOne))
			return false;
		if (questionSeven == null) {
			if (other.questionSeven != null)
				return false;
		} else if (!questionSeven.equals(other.questionSeven))
			return false;
		if (questionSix == null) {
			if (other.questionSix != null)
				return false;
		} else if (!questionSix.equals(other.questionSix))
			return false;
		if (questionTen == null) {
			if (other.questionTen != null)
				return false;
		} else if (!questionTen.equals(other.questionTen))
			return false;
		if (questionThree == null) {
			if (other.questionThree != null)
				return false;
		} else if (!questionThree.equals(other.questionThree))
			return false;
		if (questionTwo == null) {
			if (other.questionTwo != null)
				return false;
		} else if (!questionTwo.equals(other.questionTwo))
			return false;
		if (telphone == null) {
			if (other.telphone != null)
				return false;
		} else if (!telphone.equals(other.telphone))
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	

}
