package com.ivision.app.domain;

public class YdDeliveryDetails {
	
	private String orderNumber;
	private String partsNumber;
	private String partsName;
	private String vehicleType;
	private String productionAarea;
	private String unit;
	private String unitPrice;
	private String quantity;
	private String account;
	private String comment;
	public YdDeliveryDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public YdDeliveryDetails(String orderNumber, String partsNumber, String partsName, String vehicleType,
			String productionAarea, String unit, String unitPrice, String quantity, String account, String comment) {
		super();
		this.orderNumber = orderNumber;
		this.partsNumber = partsNumber;
		this.partsName = partsName;
		this.vehicleType = vehicleType;
		this.productionAarea = productionAarea;
		this.unit = unit;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.account = account;
		this.comment = comment;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getPartsNumber() {
		return partsNumber;
	}
	public void setPartsNumber(String partsNumber) {
		this.partsNumber = partsNumber;
	}
	public String getPartsName() {
		return partsName;
	}
	public void setPartsName(String partsName) {
		this.partsName = partsName;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getProductionAarea() {
		return productionAarea;
	}
	public void setProductionAarea(String productionAarea) {
		this.productionAarea = productionAarea;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
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
		return "YdDeliveryDetails [orderNumber=" + orderNumber + ", partsNumber=" + partsNumber + ", partsName="
				+ partsName + ", vehicleType=" + vehicleType + ", productionAarea=" + productionAarea + ", unit=" + unit
				+ ", unitPrice=" + unitPrice + ", quantity=" + quantity + ", account=" + account + ", comment="
				+ comment + "]";
	}
	
	
	
	
}
