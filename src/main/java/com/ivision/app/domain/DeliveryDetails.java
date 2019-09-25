package com.ivision.app.domain;


public class DeliveryDetails {

	 private String storehouseNo;
	 private String materialNo;
	 private String brand;
	 private String unit;
	 private float quantity;
	 private float singleWeight;
	 private float totalWeight;
	 private int batchNo;
	 private String date;
	 private String comment;
	 
	 
	public DeliveryDetails() {
	}
	public DeliveryDetails(String storehouseNo, String materialNo, String brand, String unit, float quantity,
			float singleWeight, float totalWeight, int batchNo, String date, String comment) {
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
	public float getQuantity() {
		return quantity;
	}
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
	public float getSingleWeight() {
		return singleWeight;
	}
	public void setSingleWeight(float singleWeight) {
		this.singleWeight = singleWeight;
	}
	public float getTotalWeight() {
		return totalWeight;
	}
	public void setTotalWeight(float totalWeight) {
		this.totalWeight = totalWeight;
	}
	public int getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(int batchNo) {
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
