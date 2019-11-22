package com.ivision.app.domain;

public class BaseResource {
	
	private String errorMessage;
	private String errorCode;
	private String warning;
	private String filepath;
	private String filpathType;
	private String templateType;
	private String type;

	public BaseResource() {
		super();
	}

	public BaseResource(String errorMessage, String errorCode, String warning, String filepath, String filpathType,
			String templateType, String type) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.warning = warning;
		this.filepath = filepath;
		this.filpathType = filpathType;
		this.templateType = templateType;
		this.type = type;
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
		result = prime * result + ((templateType == null) ? 0 : templateType.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		BaseResource other = (BaseResource) obj;
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
		if (warning == null) {
			if (other.warning != null)
				return false;
		} else if (!warning.equals(other.warning))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BaseResource [errorMessage=" + errorMessage + ", errorCode=" + errorCode + ", warning=" + warning
				+ ", filepath=" + filepath + ", filpathType=" + filpathType + ", templateType=" + templateType
				+ ", type=" + type + "]";
	}
	
	

	
	

}
