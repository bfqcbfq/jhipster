package com.ivision.app.domain;

import java.util.List;

public class IvisionAndNriSemira {
	
	
	private String semiraCompanyName;
	private String semiraSyozoku;
	private String semiraShimei;
	private String semiraTelphoneNumber;
	private String semiraEmail;
	private String semiraQuestionOneSentaku;
	private String semiraQuestionOneKaitou;
	private String semiraQuestionTwoSentaku;
	private String semiraQuestionTwoKaitou;
	private String semiraQuestionThreeSentaku;
	private String semiraQuestionThreeKaitou;
	private String semiraQuestionFourKaitou;
	private String semiraQuestionFiveKaitou;
	private List<String> sentakuList;
	private String semiraQuestionSixKaitou;
	private String semiraQuestionSenvenSentaku;
	private String semiraQuestionSenvenKaitou;
	public IvisionAndNriSemira() {
		super();
	}
	public IvisionAndNriSemira(String semiraCompanyName, String semiraSyozoku, String semiraShimei,
			String semiraTelphoneNumber, String semiraEmail, String semiraQuestionOneSentaku,
			String semiraQuestionOneKaitou, String semiraQuestionTwoSentaku, String semiraQuestionTwoKaitou,
			String semiraQuestionThreeSentaku, String semiraQuestionThreeKaitou, String semiraQuestionFourKaitou,
			String semiraQuestionFiveKaitou, List<String> sentakuList, String semiraQuestionSixKaitou,
			String semiraQuestionSenvenSentaku, String semiraQuestionSenvenKaitou) {
		super();
		this.semiraCompanyName = semiraCompanyName;
		this.semiraSyozoku = semiraSyozoku;
		this.semiraShimei = semiraShimei;
		this.semiraTelphoneNumber = semiraTelphoneNumber;
		this.semiraEmail = semiraEmail;
		this.semiraQuestionOneSentaku = semiraQuestionOneSentaku;
		this.semiraQuestionOneKaitou = semiraQuestionOneKaitou;
		this.semiraQuestionTwoSentaku = semiraQuestionTwoSentaku;
		this.semiraQuestionTwoKaitou = semiraQuestionTwoKaitou;
		this.semiraQuestionThreeSentaku = semiraQuestionThreeSentaku;
		this.semiraQuestionThreeKaitou = semiraQuestionThreeKaitou;
		this.semiraQuestionFourKaitou = semiraQuestionFourKaitou;
		this.semiraQuestionFiveKaitou = semiraQuestionFiveKaitou;
		this.sentakuList = sentakuList;
		this.semiraQuestionSixKaitou = semiraQuestionSixKaitou;
		this.semiraQuestionSenvenSentaku = semiraQuestionSenvenSentaku;
		this.semiraQuestionSenvenKaitou = semiraQuestionSenvenKaitou;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((semiraCompanyName == null) ? 0 : semiraCompanyName.hashCode());
		result = prime * result + ((semiraEmail == null) ? 0 : semiraEmail.hashCode());
		result = prime * result + ((semiraQuestionFiveKaitou == null) ? 0 : semiraQuestionFiveKaitou.hashCode());
		result = prime * result + ((semiraQuestionFourKaitou == null) ? 0 : semiraQuestionFourKaitou.hashCode());
		result = prime * result + ((semiraQuestionOneKaitou == null) ? 0 : semiraQuestionOneKaitou.hashCode());
		result = prime * result + ((semiraQuestionOneSentaku == null) ? 0 : semiraQuestionOneSentaku.hashCode());
		result = prime * result + ((semiraQuestionSenvenKaitou == null) ? 0 : semiraQuestionSenvenKaitou.hashCode());
		result = prime * result + ((semiraQuestionSenvenSentaku == null) ? 0 : semiraQuestionSenvenSentaku.hashCode());
		result = prime * result + ((semiraQuestionSixKaitou == null) ? 0 : semiraQuestionSixKaitou.hashCode());
		result = prime * result + ((semiraQuestionThreeKaitou == null) ? 0 : semiraQuestionThreeKaitou.hashCode());
		result = prime * result + ((semiraQuestionThreeSentaku == null) ? 0 : semiraQuestionThreeSentaku.hashCode());
		result = prime * result + ((semiraQuestionTwoKaitou == null) ? 0 : semiraQuestionTwoKaitou.hashCode());
		result = prime * result + ((semiraQuestionTwoSentaku == null) ? 0 : semiraQuestionTwoSentaku.hashCode());
		result = prime * result + ((semiraShimei == null) ? 0 : semiraShimei.hashCode());
		result = prime * result + ((semiraSyozoku == null) ? 0 : semiraSyozoku.hashCode());
		result = prime * result + ((semiraTelphoneNumber == null) ? 0 : semiraTelphoneNumber.hashCode());
		result = prime * result + ((sentakuList == null) ? 0 : sentakuList.hashCode());
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
		IvisionAndNriSemira other = (IvisionAndNriSemira) obj;
		if (semiraCompanyName == null) {
			if (other.semiraCompanyName != null)
				return false;
		} else if (!semiraCompanyName.equals(other.semiraCompanyName))
			return false;
		if (semiraEmail == null) {
			if (other.semiraEmail != null)
				return false;
		} else if (!semiraEmail.equals(other.semiraEmail))
			return false;
		if (semiraQuestionFiveKaitou == null) {
			if (other.semiraQuestionFiveKaitou != null)
				return false;
		} else if (!semiraQuestionFiveKaitou.equals(other.semiraQuestionFiveKaitou))
			return false;
		if (semiraQuestionFourKaitou == null) {
			if (other.semiraQuestionFourKaitou != null)
				return false;
		} else if (!semiraQuestionFourKaitou.equals(other.semiraQuestionFourKaitou))
			return false;
		if (semiraQuestionOneKaitou == null) {
			if (other.semiraQuestionOneKaitou != null)
				return false;
		} else if (!semiraQuestionOneKaitou.equals(other.semiraQuestionOneKaitou))
			return false;
		if (semiraQuestionOneSentaku == null) {
			if (other.semiraQuestionOneSentaku != null)
				return false;
		} else if (!semiraQuestionOneSentaku.equals(other.semiraQuestionOneSentaku))
			return false;
		if (semiraQuestionSenvenKaitou == null) {
			if (other.semiraQuestionSenvenKaitou != null)
				return false;
		} else if (!semiraQuestionSenvenKaitou.equals(other.semiraQuestionSenvenKaitou))
			return false;
		if (semiraQuestionSenvenSentaku == null) {
			if (other.semiraQuestionSenvenSentaku != null)
				return false;
		} else if (!semiraQuestionSenvenSentaku.equals(other.semiraQuestionSenvenSentaku))
			return false;
		if (semiraQuestionSixKaitou == null) {
			if (other.semiraQuestionSixKaitou != null)
				return false;
		} else if (!semiraQuestionSixKaitou.equals(other.semiraQuestionSixKaitou))
			return false;
		if (semiraQuestionThreeKaitou == null) {
			if (other.semiraQuestionThreeKaitou != null)
				return false;
		} else if (!semiraQuestionThreeKaitou.equals(other.semiraQuestionThreeKaitou))
			return false;
		if (semiraQuestionThreeSentaku == null) {
			if (other.semiraQuestionThreeSentaku != null)
				return false;
		} else if (!semiraQuestionThreeSentaku.equals(other.semiraQuestionThreeSentaku))
			return false;
		if (semiraQuestionTwoKaitou == null) {
			if (other.semiraQuestionTwoKaitou != null)
				return false;
		} else if (!semiraQuestionTwoKaitou.equals(other.semiraQuestionTwoKaitou))
			return false;
		if (semiraQuestionTwoSentaku == null) {
			if (other.semiraQuestionTwoSentaku != null)
				return false;
		} else if (!semiraQuestionTwoSentaku.equals(other.semiraQuestionTwoSentaku))
			return false;
		if (semiraShimei == null) {
			if (other.semiraShimei != null)
				return false;
		} else if (!semiraShimei.equals(other.semiraShimei))
			return false;
		if (semiraSyozoku == null) {
			if (other.semiraSyozoku != null)
				return false;
		} else if (!semiraSyozoku.equals(other.semiraSyozoku))
			return false;
		if (semiraTelphoneNumber == null) {
			if (other.semiraTelphoneNumber != null)
				return false;
		} else if (!semiraTelphoneNumber.equals(other.semiraTelphoneNumber))
			return false;
		if (sentakuList == null) {
			if (other.sentakuList != null)
				return false;
		} else if (!sentakuList.equals(other.sentakuList))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "IvisionAndNriSemira [semiraCompanyName=" + semiraCompanyName + ", semiraSyozoku=" + semiraSyozoku
				+ ", semiraShimei=" + semiraShimei + ", semiraTelphoneNumber=" + semiraTelphoneNumber + ", semiraEmail="
				+ semiraEmail + ", semiraQuestionOneSentaku=" + semiraQuestionOneSentaku + ", semiraQuestionOneKaitou="
				+ semiraQuestionOneKaitou + ", semiraQuestionTwoSentaku=" + semiraQuestionTwoSentaku
				+ ", semiraQuestionTwoKaitou=" + semiraQuestionTwoKaitou + ", semiraQuestionThreeSentaku="
				+ semiraQuestionThreeSentaku + ", semiraQuestionThreeKaitou=" + semiraQuestionThreeKaitou
				+ ", semiraQuestionFourKaitou=" + semiraQuestionFourKaitou + ", semiraQuestionFiveKaitou="
				+ semiraQuestionFiveKaitou + ", sentakuList=" + sentakuList + ", semiraQuestionSixKaitou="
				+ semiraQuestionSixKaitou + ", semiraQuestionSenvenSentaku=" + semiraQuestionSenvenSentaku
				+ ", semiraQuestionSenvenKaitou=" + semiraQuestionSenvenKaitou + "]";
	}
	public String getSemiraCompanyName() {
		return semiraCompanyName;
	}
	public void setSemiraCompanyName(String semiraCompanyName) {
		this.semiraCompanyName = semiraCompanyName;
	}
	public String getSemiraSyozoku() {
		return semiraSyozoku;
	}
	public void setSemiraSyozoku(String semiraSyozoku) {
		this.semiraSyozoku = semiraSyozoku;
	}
	public String getSemiraShimei() {
		return semiraShimei;
	}
	public void setSemiraShimei(String semiraShimei) {
		this.semiraShimei = semiraShimei;
	}
	public String getSemiraTelphoneNumber() {
		return semiraTelphoneNumber;
	}
	public void setSemiraTelphoneNumber(String semiraTelphoneNumber) {
		this.semiraTelphoneNumber = semiraTelphoneNumber;
	}
	public String getSemiraEmail() {
		return semiraEmail;
	}
	public void setSemiraEmail(String semiraEmail) {
		this.semiraEmail = semiraEmail;
	}
	public String getSemiraQuestionOneSentaku() {
		return semiraQuestionOneSentaku;
	}
	public void setSemiraQuestionOneSentaku(String semiraQuestionOneSentaku) {
		this.semiraQuestionOneSentaku = semiraQuestionOneSentaku;
	}
	public String getSemiraQuestionOneKaitou() {
		return semiraQuestionOneKaitou;
	}
	public void setSemiraQuestionOneKaitou(String semiraQuestionOneKaitou) {
		this.semiraQuestionOneKaitou = semiraQuestionOneKaitou;
	}
	public String getSemiraQuestionTwoSentaku() {
		return semiraQuestionTwoSentaku;
	}
	public void setSemiraQuestionTwoSentaku(String semiraQuestionTwoSentaku) {
		this.semiraQuestionTwoSentaku = semiraQuestionTwoSentaku;
	}
	public String getSemiraQuestionTwoKaitou() {
		return semiraQuestionTwoKaitou;
	}
	public void setSemiraQuestionTwoKaitou(String semiraQuestionTwoKaitou) {
		this.semiraQuestionTwoKaitou = semiraQuestionTwoKaitou;
	}
	public String getSemiraQuestionThreeSentaku() {
		return semiraQuestionThreeSentaku;
	}
	public void setSemiraQuestionThreeSentaku(String semiraQuestionThreeSentaku) {
		this.semiraQuestionThreeSentaku = semiraQuestionThreeSentaku;
	}
	public String getSemiraQuestionThreeKaitou() {
		return semiraQuestionThreeKaitou;
	}
	public void setSemiraQuestionThreeKaitou(String semiraQuestionThreeKaitou) {
		this.semiraQuestionThreeKaitou = semiraQuestionThreeKaitou;
	}
	public String getSemiraQuestionFourKaitou() {
		return semiraQuestionFourKaitou;
	}
	public void setSemiraQuestionFourKaitou(String semiraQuestionFourKaitou) {
		this.semiraQuestionFourKaitou = semiraQuestionFourKaitou;
	}
	public String getSemiraQuestionFiveKaitou() {
		return semiraQuestionFiveKaitou;
	}
	public void setSemiraQuestionFiveKaitou(String semiraQuestionFiveKaitou) {
		this.semiraQuestionFiveKaitou = semiraQuestionFiveKaitou;
	}
	public List<String> getSentakuList() {
		return sentakuList;
	}
	public void setSentakuList(List<String> sentakuList) {
		this.sentakuList = sentakuList;
	}
	public String getSemiraQuestionSixKaitou() {
		return semiraQuestionSixKaitou;
	}
	public void setSemiraQuestionSixKaitou(String semiraQuestionSixKaitou) {
		this.semiraQuestionSixKaitou = semiraQuestionSixKaitou;
	}
	public String getSemiraQuestionSenvenSentaku() {
		return semiraQuestionSenvenSentaku;
	}
	public void setSemiraQuestionSenvenSentaku(String semiraQuestionSenvenSentaku) {
		this.semiraQuestionSenvenSentaku = semiraQuestionSenvenSentaku;
	}
	public String getSemiraQuestionSenvenKaitou() {
		return semiraQuestionSenvenKaitou;
	}
	public void setSemiraQuestionSenvenKaitou(String semiraQuestionSenvenKaitou) {
		this.semiraQuestionSenvenKaitou = semiraQuestionSenvenKaitou;
	}
	
	
}
