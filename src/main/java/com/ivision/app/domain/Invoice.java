package com.ivision.app.domain;

import java.util.List;

public class Invoice {

	private String type;
	private String templateType;
	private String errorMessage;
	private String filepath;
	private String title;
	private DeliverMessage deliverMessage;
	private List<DeliveryDetails> deliveryDetails;
	
	public Invoice() {
	}

	public Invoice(String type, String templateType, String errorMessage, String filepath, String title,
			DeliverMessage deliverMessage, List<DeliveryDetails> deliveryDetails) {
		super();
		this.type = type;
		this.templateType = templateType;
		this.errorMessage = errorMessage;
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

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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
		return "Invoice [type=" + type + ", templateType=" + templateType + ", errorMessage=" + errorMessage
				+ ", filepath=" + filepath + ", title=" + title + ", deliverMessage=" + deliverMessage
				+ ", deliveryDetails=" + deliveryDetails + "]";
	}

	
}
