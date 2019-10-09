package com.ivision.app.domain;

public class BeanRsource {
	
	private String errorMessage;
	private String errorCode;
	private String warning;
	public BeanRsource() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BeanRsource(String errorMessage, String errorCode, String warning) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.warning = warning;
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
	@Override
	public String toString() {
		return "BeanRsource [errorMessage=" + errorMessage + ", errorCode=" + errorCode + ", warning=" + warning + "]";
	}
	
	

}
