package com.ivision.app.domain;

import java.io.Serializable;

public class MxDeliveryDetails implements Serializable {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String styleNo;
	 private String style;
	 private String color;
	 private String unit;
	 private String modelS;
	 private String modelM;
	 private String modelL;
	 private String subtotal;
	 private String unitPrice;
	 private String account;
	 private String comment;
	public MxDeliveryDetails() {
		
	}
	public MxDeliveryDetails(String styleNo, String style, String color, String unit, String modelS, String modelM,
			String modelL, String subtotal, String unitPrice, String account, String comment) {
		super();
		this.styleNo = styleNo;
		this.style = style;
		this.color = color;
		this.unit = unit;
		this.modelS = modelS;
		this.modelM = modelM;
		this.modelL = modelL;
		this.subtotal = subtotal;
		this.unitPrice = unitPrice;
		this.account = account;
		this.comment = comment;
	}
	public String getStyleNo() {
		return styleNo;
	}
	public void setStyleNo(String styleNo) {
		this.styleNo = styleNo;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getModelS() {
		return modelS;
	}
	public void setModelS(String modelS) {
		this.modelS = modelS;
	}
	public String getModelM() {
		return modelM;
	}
	public void setModelM(String modelM) {
		this.modelM = modelM;
	}
	public String getModelL() {
		return modelL;
	}
	public void setModelL(String modelL) {
		this.modelL = modelL;
	}
	public String getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "MxDeliveryDetails [styleNo=" + styleNo + ", style=" + style + ", color=" + color + ", unit=" + unit
				+ ", modelS=" + modelS + ", modelM=" + modelM + ", modelL=" + modelL + ", subtotal=" + subtotal
				+ ", unitPrice=" + unitPrice + ", account=" + account + ", comment=" + comment + "]";
	}
	
	
	
}
