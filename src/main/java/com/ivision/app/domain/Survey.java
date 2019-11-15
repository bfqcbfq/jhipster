package com.ivision.app.domain;

public class Survey {
	
	private SurveyBPInformation surveyBPInformation;
	
	private SurveyBPNote surveryBPNote;

	public Survey() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Survey(SurveyBPInformation surveyBPInformation, SurveyBPNote surveryBPNote) {
		super();
		this.surveyBPInformation = surveyBPInformation;
		this.surveryBPNote = surveryBPNote;
	}

	public SurveyBPInformation getSurveyBPInformation() {
		return surveyBPInformation;
	}

	public void setSurveyBPInformation(SurveyBPInformation surveyBPInformation) {
		this.surveyBPInformation = surveyBPInformation;
	}

	public SurveyBPNote getSurveryBPNote() {
		return surveryBPNote;
	}

	public void setSurveryBPNote(SurveyBPNote surveryBPNote) {
		this.surveryBPNote = surveryBPNote;
	}

	@Override
	public String toString() {
		return "Survey [surveyBPInformation=" + surveyBPInformation + ", surveryBPNote=" + surveryBPNote + "]";
	}
	
	

}
