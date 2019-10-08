package com.ivision.app.domain;

import java.util.List;

public class YdInvoice {
	
	private String title;
	private YdDeliverMessage ydDeliverMessage;
//	private String deliveryNo;
//	private String deliveryCompany;
//	private String deliveryDate;
//	private String address;
//	private String contactNUmber;
//	private String note;
//	private String handler;
//	private String picker;
	private List<YdDeliveryDetails>  ydDeliveryDetails;
//	private List<DeliveryDetails> deliveryDetails;
	public YdInvoice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public YdInvoice(String title, YdDeliverMessage ydDeliverMessage, List<YdDeliveryDetails> ydDeliveryDetails) {
		super();
		this.title = title;
		this.ydDeliverMessage = ydDeliverMessage;
		this.ydDeliveryDetails = ydDeliveryDetails;
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
		return "YdInvoice [title=" + title + ", ydDeliverMessage=" + ydDeliverMessage + ", ydDeliveryDetails="
				+ ydDeliveryDetails + "]";
	}
	
	

	
}
