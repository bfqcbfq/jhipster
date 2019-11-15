package com.ivision.app.domain;

import java.io.Serializable;

public class DeliverMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String deliveryNo;
	private String deliveryCompany;
	private String deliveryDate;
	private String address;
	private String contactNUmber;
	private String note;
	private String handler;
	private String picker;
	public DeliverMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DeliverMessage(String deliveryNo, String deliveryCompany, String deliveryDate, String address,
			String contactNUmber, String note, String handler, String picker) {
		super();
		this.deliveryNo = deliveryNo;
		this.deliveryCompany = deliveryCompany;
		this.deliveryDate = deliveryDate;
		this.address = address;
		this.contactNUmber = contactNUmber;
		this.note = note;
		this.handler = handler;
		this.picker = picker;
	}
	public String getDeliveryNo() {
		return deliveryNo;
	}
	public void setDeliveryNo(String deliveryNo) {
		this.deliveryNo = deliveryNo;
	}
	public String getDeliveryCompany() {
		return deliveryCompany;
	}
	public void setDeliveryCompany(String deliveryCompany) {
		this.deliveryCompany = deliveryCompany;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactNUmber() {
		return contactNUmber;
	}
	public void setContactNUmber(String contactNUmber) {
		this.contactNUmber = contactNUmber;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getHandler() {
		return handler;
	}
	public void setHandler(String handler) {
		this.handler = handler;
	}
	public String getPicker() {
		return picker;
	}
	public void setPicker(String picker) {
		this.picker = picker;
	}
	@Override
	public String toString() {
		return "DeliverMessage [deliveryNo=" + deliveryNo + ", deliveryCompany=" + deliveryCompany + ", deliveryDate="
				+ deliveryDate + ", address=" + address + ", contactNUmber=" + contactNUmber + ", note=" + note
				+ ", handler=" + handler + ", picker=" + picker + "]";
	}
	
	
}
