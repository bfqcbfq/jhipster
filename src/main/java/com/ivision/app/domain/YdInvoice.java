package com.ivision.app.domain;

import java.io.Serializable;
import java.util.List;

public class YdInvoice implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private String type;
	private String templateType;
	private String filepath;
	private String title;
	private YdDeliverMessage ydDeliverMessage;
	private List<YdDeliveryDetails>  ydDeliveryDetails;
	public YdInvoice() {
		
	}
	public YdInvoice(String type, String templateType, String filepath, String title,
			YdDeliverMessage ydDeliverMessage, List<YdDeliveryDetails> ydDeliveryDetails) {
		super();
		this.type = type;
		this.templateType = templateType;
		this.filepath = filepath;
		this.title = title;
		this.ydDeliverMessage = ydDeliverMessage;
		this.ydDeliveryDetails = ydDeliveryDetails;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTemplateType() {
		return templateType;
	}
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public YdDeliverMessage getYdDeliverMessage() {
		return ydDeliverMessage;
	}
	public void setYdDeliverMessage(YdDeliverMessage ydDeliverMessage) {
		this.ydDeliverMessage = ydDeliverMessage;
	}
	public List<YdDeliveryDetails> getYdDeliveryDetails() {
		return ydDeliveryDetails;
	}
	public void setYdDeliveryDetails(List<YdDeliveryDetails> ydDeliveryDetails) {
		this.ydDeliveryDetails = ydDeliveryDetails;
	}
	@Override
	public String toString() {
		return "YdInvoice [type=" + type + ", templateType=" + templateType + ", errorMessage="
				+ ", filepath=" + filepath + ", title=" + title + ", ydDeliverMessage=" + ydDeliverMessage
				+ ", ydDeliveryDetails=" + ydDeliveryDetails + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filepath == null) ? 0 : filepath.hashCode());
		result = prime * result + ((templateType == null) ? 0 : templateType.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((ydDeliverMessage == null) ? 0 : ydDeliverMessage.hashCode());
		result = prime * result + ((ydDeliveryDetails == null) ? 0 : ydDeliveryDetails.hashCode());
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
		YdInvoice other = (YdInvoice) obj;
		if (filepath == null) {
			if (other.filepath != null)
				return false;
		} else if (!filepath.equals(other.filepath))
			return false;
		if (templateType == null) {
			if (other.templateType != null)
				return false;
		} else if (!templateType.equals(other.templateType))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (ydDeliverMessage == null) {
			if (other.ydDeliverMessage != null)
				return false;
		} else if (!ydDeliverMessage.equals(other.ydDeliverMessage))
			return false;
		if (ydDeliveryDetails == null) {
			if (other.ydDeliveryDetails != null)
				return false;
		} else if (!ydDeliveryDetails.equals(other.ydDeliveryDetails))
			return false;
		return true;
	}
	
	
	
}
