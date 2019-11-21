package com.ivision.app.domain;

import java.io.Serializable;

public class DeliveryDetails implements Serializable {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batchNo == null) ? 0 : batchNo.hashCode());
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((materialNo == null) ? 0 : materialNo.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((singleWeight == null) ? 0 : singleWeight.hashCode());
		result = prime * result + ((storehouseNo == null) ? 0 : storehouseNo.hashCode());
		result = prime * result + ((totalWeight == null) ? 0 : totalWeight.hashCode());
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeliveryDetails other = (DeliveryDetails) obj;
		if (batchNo == null) {
			if (other.batchNo != null)
				return false;
		} else if (!batchNo.equals(other.batchNo))
			return false;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (materialNo == null) {
			if (other.materialNo != null)
				return false;
		} else if (!materialNo.equals(other.materialNo))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (singleWeight == null) {
			if (other.singleWeight != null)
				return false;
		} else if (!singleWeight.equals(other.singleWeight))
			return false;
		if (storehouseNo == null) {
			if (other.storehouseNo != null)
				return false;
		} else if (!storehouseNo.equals(other.storehouseNo))
			return false;
		if (totalWeight == null) {
			if (other.totalWeight != null)
				return false;
		} else if (!totalWeight.equals(other.totalWeight))
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		return true;
	}
	
	
	
	 
	 
}
