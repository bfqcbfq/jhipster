package com.ivision.app.domain;

import java.util.List;

public class MxInvoice {
	
	private String type;
	private String templateType;
	private String errorMessage;
	private String filepath;
	private String title;
	private MxDeliverMessage mxDeliverMessage;
//	private String deliveryNo;
//	private String deliveryCompany;
//	private String deliveryDate;
//	private String address;
//	private String contactNUmber;
//	private String note;
//	private String handler;
//	private String picker;
	private List<MxDeliveryDetails>  deliveryDetails;
//	private List<DeliveryDetails> deliveryDetails;
	public MxInvoice() {
		
	}
	public MxInvoice(String type, String templateType, String errorMessage, String filepath, String title,
			MxDeliverMessage mxDeliverMessage, List<MxDeliveryDetails> deliveryDetails) {
		super();
		this.type = type;
		this.templateType = templateType;
		this.errorMessage = errorMessage;
		this.filepath = filepath;
		this.title = title;
		this.mxDeliverMessage = mxDeliverMessage;
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
	public MxDeliverMessage getMxDeliverMessage() {
		return mxDeliverMessage;
	}
	public void setMxDeliverMessage(MxDeliverMessage mxDeliverMessage) {
		this.mxDeliverMessage = mxDeliverMessage;
	}
	public List<MxDeliveryDetails> getDeliveryDetails() {
		return deliveryDetails;
	}
	public void setDeliveryDetails(List<MxDeliveryDetails> deliveryDetails) {
		this.deliveryDetails = deliveryDetails;
	}
	@Override
	public String toString() {
		return "MxInvoice [type=" + type + ", templateType=" + templateType + ", errorMessage=" + errorMessage
				+ ", filepath=" + filepath + ", title=" + title + ", mxDeliverMessage=" + mxDeliverMessage
				+ ", deliveryDetails=" + deliveryDetails + "]";
	}
	
	
}
