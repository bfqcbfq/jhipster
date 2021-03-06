package com.ivision.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "iv_ocr_mitsubishi_survey")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MitsubishiSurvey extends BaseResource {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
    
    @Column(name = "mitsubishi_name")
	private String mitsubishiName;
    
    @Column(name = "mitsubishi_company_name")
	private String mitsubishiCompanyName;
	
    @Column(name = "mitsubishi_telphone")
	private String mitsubishiTelphone;
	
    @Column(name = "mitsubishi_email")
	private String mitsubishiEmail;
	
    @Column(name = "questionOne")
	private String questionOne;
	
    @Column(name = "questionTwo")
	private String questionTwo;
	
    @Column(name = "questionThree")
	private String questionThree;
	
    @Column(name = "questionFour")
	private String questionFour;
	
    @Column(name = "questionFive")
	private String questionFive;
	
    @Column(name = "questionSix")
	private String questionSix;
	
    @Column(name = "questionSeven")
	private String questionSeven;
	
    @Column(name = "questionEight")
	private String questionEight;
	
    @Column(name = "questionNine")
	private String questionNine;
	
    @Column(name = "questionTen")
	private String questionTen;
	
    @Column(name = "mitsubishi_comment")
	private String mitsubishiComment;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMitsubishiName() {
		return mitsubishiName;
	}

	public void setMitsubishiName(String mitsubishiName) {
		this.mitsubishiName = mitsubishiName;
	}

	public String getMitsubishiCompanyName() {
		return mitsubishiCompanyName;
	}

	public void setMitsubishiCompanyName(String mitsubishiCompanyName) {
		this.mitsubishiCompanyName = mitsubishiCompanyName;
	}

	public String getMitsubishiTelphone() {
		return mitsubishiTelphone;
	}

	public void setMitsubishiTelphone(String mitsubishiTelphone) {
		this.mitsubishiTelphone = mitsubishiTelphone;
	}

	public String getMitsubishiEmail() {
		return mitsubishiEmail;
	}

	public void setMitsubishiEmail(String mitsubishiEmail) {
		this.mitsubishiEmail = mitsubishiEmail;
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

	public String getMitsubishiComment() {
		return mitsubishiComment;
	}

	public void setMitsubishiComment(String mitsubishiComment) {
		this.mitsubishiComment = mitsubishiComment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((mitsubishiComment == null) ? 0 : mitsubishiComment.hashCode());
		result = prime * result + ((mitsubishiCompanyName == null) ? 0 : mitsubishiCompanyName.hashCode());
		result = prime * result + ((mitsubishiEmail == null) ? 0 : mitsubishiEmail.hashCode());
		result = prime * result + ((mitsubishiName == null) ? 0 : mitsubishiName.hashCode());
		result = prime * result + ((mitsubishiTelphone == null) ? 0 : mitsubishiTelphone.hashCode());
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
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MitsubishiSurvey other = (MitsubishiSurvey) obj;
		if (id != other.id)
			return false;
		if (mitsubishiComment == null) {
			if (other.mitsubishiComment != null)
				return false;
		} else if (!mitsubishiComment.equals(other.mitsubishiComment))
			return false;
		if (mitsubishiCompanyName == null) {
			if (other.mitsubishiCompanyName != null)
				return false;
		} else if (!mitsubishiCompanyName.equals(other.mitsubishiCompanyName))
			return false;
		if (mitsubishiEmail == null) {
			if (other.mitsubishiEmail != null)
				return false;
		} else if (!mitsubishiEmail.equals(other.mitsubishiEmail))
			return false;
		if (mitsubishiName == null) {
			if (other.mitsubishiName != null)
				return false;
		} else if (!mitsubishiName.equals(other.mitsubishiName))
			return false;
		if (mitsubishiTelphone == null) {
			if (other.mitsubishiTelphone != null)
				return false;
		} else if (!mitsubishiTelphone.equals(other.mitsubishiTelphone))
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
		return true;
	}

	@Override
	public String toString() {
		return "MitsubishiSurvey [id=" + id + ", mitsubishiName=" + mitsubishiName + ", mitsubishiCompanyName="
				+ mitsubishiCompanyName + ", mitsubishiTelphone=" + mitsubishiTelphone + ", mitsubishiEmail="
				+ mitsubishiEmail + ", questionOne=" + questionOne + ", questionTwo=" + questionTwo + ", questionThree="
				+ questionThree + ", questionFour=" + questionFour + ", questionFive=" + questionFive + ", questionSix="
				+ questionSix + ", questionSeven=" + questionSeven + ", questionEight=" + questionEight
				+ ", questionNine=" + questionNine + ", questionTen=" + questionTen + ", mitsubishiComment="
				+ mitsubishiComment + "]";
	}
	
    
}
