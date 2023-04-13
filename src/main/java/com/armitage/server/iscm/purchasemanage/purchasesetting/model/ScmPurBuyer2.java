package com.armitage.server.iscm.purchasemanage.purchasesetting.model;


public class ScmPurBuyer2 extends ScmPurBuyer{

    public static final String FN_VENDORNAME = "vendorName";
	public static final String FN_EMPNO ="empNo";
	public static final String FN_EMPNAME ="empName";
	public static final String FN_GENDER ="gender";
	public static final String FN_ORGUNITNO ="orgUnitNo";
	
	private String empNo;
	private String empName;
	private String gender;
	private String orgUnitNo;
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getOrgUnitNo() {
		return orgUnitNo;
	}
	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public ScmPurBuyer2() {
		super();
	}
	public ScmPurBuyer2(boolean defaultValue) {
		super(defaultValue);
	}
}
