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
@Table(name = "mitsubishi_survey")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MitsubishiSurvey {
	
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
	
    @Column(name = "question_one")
	private String questionOne;
	
    @Column(name = "question_two")
	private String questionTwo;
	
    @Column(name = "question_Three")
	private String questionThree;
	
    @Column(name = "question_Four")
	private String questionFour;
	
    @Column(name = "question_Five")
	private String questionFive;
	
    @Column(name = "question_Six")
	private String questionSix;
	
    @Column(name = "question_Seven")
	private String questionSeven;
	
    @Column(name = "question_Eight")
	private String questionEight;
	
    @Column(name = "question_Nine")
	private String questionNine;
	
    @Column(name = "question_Ten")
	private String questionTen;
	
    @Column(name = "mitsubishi_comment")
	private String mitsubishiComment;
    
    @Column(name = "version")
	private Integer version;
	
    @Column(name = "update_flag")
	private String updateFlag;
    
    @Column(name = "error_message")
	private String errorMessage;
    
    @Column(name = "error_code")
	private String errorCode;
    
    @Column(name = "warning")
	private String warning;
    
    @Column(name = "filepath")
	private String filepath;
    
    @Column(name = "filpath_type")
	private String filpathType;
    
    @Column(name = "template_type")
	private String templateType;
    
    @Column(name = "type")
	private String type;
    
	public MitsubishiSurvey() {
		super();
	}

	public MitsubishiSurvey(long id, String mitsubishiName, String mitsubishiCompanyName, String mitsubishiTelphone,
			String mitsubishiEmail, String questionOne, String questionTwo, String questionThree, String questionFour,
			String questionFive, String questionSix, String questionSeven, String questionEight, String questionNine,
			String questionTen, String mitsubishiComment, Integer version, String updateFlag, String errorMessage,
			String errorCode, String warning, String filepath, String filpathType, String templateType, String type) {
		super();
		this.id = id;
		this.mitsubishiName = mitsubishiName;
		this.mitsubishiCompanyName = mitsubishiCompanyName;
		this.mitsubishiTelphone = mitsubishiTelphone;
		this.mitsubishiEmail = mitsubishiEmail;
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
		this.mitsubishiComment = mitsubishiComment;
		this.version = version;
		this.updateFlag = updateFlag;
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.warning = warning;
		this.filepath = filepath;
		this.filpathType = filpathType;
		this.templateType = templateType;
		this.type = type;
	}

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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getWarning() {
		return warning;
	}

	public void setWarning(String warning) {
		this.warning = warning;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getFilpathType() {
		return filpathType;
	}

	public void setFilpathType(String filpathType) {
		this.filpathType = filpathType;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((errorCode == null) ? 0 : errorCode.hashCode());
		result = prime * result + ((errorMessage == null) ? 0 : errorMessage.hashCode());
		result = prime * result + ((filepath == null) ? 0 : filepath.hashCode());
		result = prime * result + ((filpathType == null) ? 0 : filpathType.hashCode());
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
		result = prime * result + ((templateType == null) ? 0 : templateType.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((updateFlag == null) ? 0 : updateFlag.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		result = prime * result + ((warning == null) ? 0 : warning.hashCode());
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
		if (errorCode == null) {
			if (other.errorCode != null)
				return false;
		} else if (!errorCode.equals(other.errorCode))
			return false;
		if (errorMessage == null) {
			if (other.errorMessage != null)
				return false;
		} else if (!errorMessage.equals(other.errorMessage))
			return false;
		if (filepath == null) {
			if (other.filepath != null)
				return false;
		} else if (!filepath.equals(other.filepath))
			return false;
		if (filpathType == null) {
			if (other.filpathType != null)
				return false;
		} else if (!filpathType.equals(other.filpathType))
			return false;
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
		if (templateType == null) {
			if (other.templateType != null)
				return false;
		} else if (!templateType.equals(other.templateType))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (updateFlag == null) {
			if (other.updateFlag != null)
				return false;
		} else if (!updateFlag.equals(other.updateFlag))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		if (warning == null) {
			if (other.warning != null)
				return false;
		} else if (!warning.equals(other.warning))
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
				+ mitsubishiComment + ", version=" + version + ", updateFlag=" + updateFlag + ", errorMessage="
				+ errorMessage + ", errorCode=" + errorCode + ", warning=" + warning + ", filepath=" + filepath
				+ ", filpathType=" + filpathType + ", templateType=" + templateType + ", type=" + type + "]";
	}
	

}
