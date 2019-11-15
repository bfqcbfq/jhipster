package com.ivision.app.domain;

/**
 * 调查问卷填写人信息
 *
 */
public class SurveyBPInformation {
	
	private String CompanyName;
	
	private String department;
	
	private String name;
	
	private String phone;
	
	private String email;

	public SurveyBPInformation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SurveyBPInformation(String companyName, String department, String name, String phone, String email) {
		super();
		CompanyName = companyName;
		this.department = department;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "SurveyBusinessMessage [CompanyName=" + CompanyName + ", department=" + department + ", name=" + name
				+ ", phone=" + phone + ", email=" + email + "]";
	}
	
	
} 