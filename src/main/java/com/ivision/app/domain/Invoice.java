package com.ivision.app.domain;

import java.util.List;

public class Invoice {

	private String type;
	private String title;
	private DeliverMessage deliverMessage;
//	private String deliveryNo;
//	private String deliveryCompany;
//	private String deliveryDate;
//	private String address;
//	private String contactNUmber;
//	private String note;
//	private String handler;
//	private String picker;
	private List<DeliveryDetails> deliveryDetails;

//	private List<DeliveryDetails> deliveryDetails;
	public Invoice() {
	}

	public Invoice(String type, String title, DeliverMessage deliverMessage, List<DeliveryDetails> deliveryDetails) {
		super();
		this.type = type;
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
		return "Invoice [type=" + type + ", title=" + title + ", deliverMessage=" + deliverMessage
				+ ", deliveryDetails=" + deliveryDetails + "]";
	}

}
