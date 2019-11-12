package com.ivision.app.domain;

import java.io.Serializable;
import java.util.List;

public class Invoice implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String type;
	private String templateType;
	private String filepath;
	private String title;
	private DeliverMessage deliverMessage;
	private List<DeliveryDetails> deliveryDetails;
	
	public Invoice() {
	}

	public Invoice(String type, String templateType,  String filepath, String title,
			DeliverMessage deliverMessage, List<DeliveryDetails> deliveryDetails) {
		super();
		this.type = type;
		this.templateType = templateType;
		this.filepath = filepath;
		this.title = title;
		this.deliverMessage = deliverMessage;
		this.deliveryDetails = deliveryDetails;
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

	public DeliverMessage getDeliverMessage() {
		return deliverMessage;
	}

	public void setDeliverMessage(DeliverMessage deliverMessage) {
		this.deliverMessage = deliverMessage;
	}

	public List<DeliveryDetails> getDeliveryDetails() {
		return deliveryDetails;
	}

	public void setDeliveryDetails(List<DeliveryDetails> deliveryDetails) {
		this.deliveryDetails = deliveryDetails;
	}

	@Override
	public String toString() {
		return "Invoice [type=" + type + ", templateType=" + templateType + ", errorMessage=" 
				+ ", filepath=" + filepath + ", title=" + title + ", deliverMessage=" + deliverMessage
				+ ", deliveryDetails=" + deliveryDetails + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deliverMessage == null) ? 0 : deliverMessage.hashCode());
		result = prime * result + ((deliveryDetails == null) ? 0 : deliveryDetails.hashCode());
		result = prime * result + ((filepath == null) ? 0 : filepath.hashCode());
		result = prime * result + ((templateType == null) ? 0 : templateType.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Invoice other = (Invoice) obj;
		if (deliverMessage == null) {
			if (other.deliverMessage != null)
				return false;
		} else if (!deliverMessage.equals(other.deliverMessage))
			return false;
		if (deliveryDetails == null) {
			if (other.deliveryDetails != null)
				return false;
		} else if (!deliveryDetails.equals(other.deliveryDetails))
			return false;
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
		return true;
	}
	
	

	
}
