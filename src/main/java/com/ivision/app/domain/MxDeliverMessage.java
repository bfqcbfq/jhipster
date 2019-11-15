package com.ivision.app.domain;

import java.io.Serializable;

public class MxDeliverMessage implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String deliveryNo;
	private String businessCode;
	private String totalQuantity;
	private String totalAmount ;
	private String orderMaker;
	private String deliveryDate;
	private String address;
	private String note;
	private String deliverySign;
	private String handlerSign;
	public MxDeliverMessage() {
		
	}
	public MxDeliverMessage(String deliveryNo, String businessCode, String totalQuantity, String totalAmount,
			String orderMaker, String deliveryDate, String address, String note, String deliverySign,
			String handlerSign) {
		super();
		this.deliveryNo = deliveryNo;
		this.businessCode = businessCode;
		this.totalQuantity = totalQuantity;
		this.totalAmount = totalAmount;
		this.orderMaker = orderMaker;
		this.deliveryDate = deliveryDate;
		this.address = address;
		this.note = note;
		this.deliverySign = deliverySign;
		this.handlerSign = handlerSign;
	}
	public String getDeliveryNo() {
		return deliveryNo;
	}
	public void setDeliveryNo(String deliveryNo) {
		this.deliveryNo = deliveryNo;
	}
	public String getBusinessCode() {
		return businessCode;
	}
	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}
	public String getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(String totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getOrderMaker() {
		return orderMaker;
	}
	public void setOrderMaker(String orderMaker) {
		this.orderMaker = orderMaker;
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getDeliverySign() {
		return deliverySign;
	}
	public void setDeliverySign(String deliverySign) {
		this.deliverySign = deliverySign;
	}
	public String getHandlerSign() {
		return handlerSign;
	}
	public void setHandlerSign(String handlerSign) {
		this.handlerSign = handlerSign;
	}
	@Override
	public String toString() {
		return "MxDeliverMessage [deliveryNo=" + deliveryNo + ", businessCode=" + businessCode + ", totalQuantity="
				+ totalQuantity + ", totalAmount=" + totalAmount + ", orderMaker=" + orderMaker + ", deliveryDate="
				+ deliveryDate + ", address=" + address + ", note=" + note + ", deliverySign=" + deliverySign
				+ ", handlerSign=" + handlerSign + "]";
	}
	

}
