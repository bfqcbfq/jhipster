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
	
	
	
}
