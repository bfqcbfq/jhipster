package com.ivision.app.domain;

public class BaseResource {
	
	private String errorMessage;
	private String errorCode;
	private String warning;
	private String filepath;
	private String filpathType;

	public BaseResource() {
		super();
	}
	
	
	public BaseResource(String errorMessage, String errorCode, String warning, String filepath) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.warning = warning;
		this.filepath = filepath;
	}


	public BaseResource(String filpathType) {
		super();
		this.filpathType = filpathType;
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


	@Override
	public String toString() {
		return "BaseResource [errorMessage=" + errorMessage + ", errorCode=" + errorCode + ", warning=" + warning
				+ ", filepath=" + filepath + ", filpathType=" + filpathType + "]";
	}

}
