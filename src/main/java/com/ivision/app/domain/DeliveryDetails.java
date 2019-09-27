package com.ivision.app.domain;


public class DeliveryDetails {

	 private String storehouseNo;
	 private String materialNo;
	 private String brand;
	 private String unit;
	 private String quantity;
	 private String singleWeight;
	 private String totalWeight;
	 private String batchNo;
	 private String date;
	 private String comment;
	public DeliveryDetails() {
	}
	public DeliveryDetails(String storehouseNo, String materialNo, String brand, String unit, String quantity,
			String singleWeight, String totalWeight, String batchNo, String date, String comment) {
		super();
		this.storehouseNo = storehouseNo;
		this.materialNo = materialNo;
		this.brand = brand;
		this.unit = unit;
		this.quantity = quantity;
		this.singleWeight = singleWeight;
		this.totalWeight = totalWeight;
		this.batchNo = batchNo;
		this.date = date;
		this.comment = comment;
	}
	public String getStorehouseNo() {
		return storehouseNo;
	}
	public void setStorehouseNo(String storehouseNo) {
		this.storehouseNo = storehouseNo;
	}
	public String getMaterialNo() {
		return materialNo;
	}
	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getSingleWeight() {
		return singleWeight;
	}
	public void setSingleWeight(String singleWeight) {
		this.singleWeight = singleWeight;
	}
	public String getTotalWeight() {
		return totalWeight;
	}
	public void setTotalWeight(String totalWeight) {
		this.totalWeight = totalWeight;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "DeliveryDetails [storehouseNo=" + storehouseNo + ", materialNo=" + materialNo + ", brand=" + brand
				+ ", unit=" + unit + ", quantity=" + quantity + ", singleWeight=" + singleWeight + ", totalWeight="
				+ totalWeight + ", batchNo=" + batchNo + ", date=" + date + ", comment=" + comment + "]";
	}
	
	 
	 
}
