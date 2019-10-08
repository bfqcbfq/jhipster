package com.ivision.app.domain;

public class YdDeliverMessage {
	
	private String mainBusiness;
	private String orderMaker;
	private String csahier;
	private String customerSign;
	private String deliveryNo;
	private String TotalAmountSmall;
	private String InvoiceType;
	private String TotalAmountBig;
	private String deliveryDate;
	private String settleStyle;
	private String CompanyPhone;
	private String customerName;
	private String address;
	private String customerPhone;
	private String deliveryer;
	private String receiver;
	private String totalAccount;
	private String page;
	public YdDeliverMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public YdDeliverMessage(String mainBusiness, String orderMaker, String csahier, String customerSign,
			String deliveryNo, String totalAmountSmall, String invoiceType, String totalAmountBig, String deliveryDate,
			String settleStyle, String companyPhone, String customerName, String address, String customerPhone,
			String deliveryer, String receiver, String totalAccount, String page) {
		super();
		this.mainBusiness = mainBusiness;
		this.orderMaker = orderMaker;
		this.csahier = csahier;
		this.customerSign = customerSign;
		this.deliveryNo = deliveryNo;
		TotalAmountSmall = totalAmountSmall;
		InvoiceType = invoiceType;
		TotalAmountBig = totalAmountBig;
		this.deliveryDate = deliveryDate;
		this.settleStyle = settleStyle;
		CompanyPhone = companyPhone;
		this.customerName = customerName;
		this.address = address;
		this.customerPhone = customerPhone;
		this.deliveryer = deliveryer;
		this.receiver = receiver;
		this.totalAccount = totalAccount;
		this.page = page;
	}
	public String getMainBusiness() {
		return mainBusiness;
	}
	public void setMainBusiness(String mainBusiness) {
		this.mainBusiness = mainBusiness;
	}
	public String getOrderMaker() {
		return orderMaker;
	}
	public void setOrderMaker(String orderMaker) {
		this.orderMaker = orderMaker;
	}
	public String getCsahier() {
		return csahier;
	}
	public void setCsahier(String csahier) {
		this.csahier = csahier;
	}
	public String getCustomerSign() {
		return customerSign;
	}
	public void setCustomerSign(String customerSign) {
		this.customerSign = customerSign;
	}
	public String getDeliveryNo() {
		return deliveryNo;
	}
	public void setDeliveryNo(String deliveryNo) {
		this.deliveryNo = deliveryNo;
	}
	public String getTotalAmountSmall() {
		return TotalAmountSmall;
	}
	public void setTotalAmountSmall(String totalAmountSmall) {
		TotalAmountSmall = totalAmountSmall;
	}
	public String getInvoiceType() {
		return InvoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		InvoiceType = invoiceType;
	}
	public String getTotalAmountBig() {
		return TotalAmountBig;
	}
	public void setTotalAmountBig(String totalAmountBig) {
		TotalAmountBig = totalAmountBig;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getSettleStyle() {
		return settleStyle;
	}
	public void setSettleStyle(String settleStyle) {
		this.settleStyle = settleStyle;
	}
	public String getCompanyPhone() {
		return CompanyPhone;
	}
	public void setCompanyPhone(String companyPhone) {
		CompanyPhone = companyPhone;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getDeliveryer() {
		return deliveryer;
	}
	public void setDeliveryer(String deliveryer) {
		this.deliveryer = deliveryer;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getTotalAccount() {
		return totalAccount;
	}
	public void setTotalAccount(String totalAccount) {
		this.totalAccount = totalAccount;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	@Override
	public String toString() {
		return "YdDeliverMessage [mainBusiness=" + mainBusiness + ", orderMaker=" + orderMaker + ", csahier=" + csahier
				+ ", customerSign=" + customerSign + ", deliveryNo=" + deliveryNo + ", TotalAmountSmall="
				+ TotalAmountSmall + ", InvoiceType=" + InvoiceType + ", TotalAmountBig=" + TotalAmountBig
				+ ", deliveryDate=" + deliveryDate + ", settleStyle=" + settleStyle + ", CompanyPhone=" + CompanyPhone
				+ ", customerName=" + customerName + ", address=" + address + ", customerPhone=" + customerPhone
				+ ", deliveryer=" + deliveryer + ", receiver=" + receiver + ", totalAccount=" + totalAccount + ", page="
				+ page + "]";
	}
	
	
}
