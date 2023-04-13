package com.armitage.server.iscm.purchasemanage.purchasesetting.model;


public class ScmPurchaseCanuseSetOrg2 extends ScmPurchaseCanuseSetOrg{

	public static final String FN_ORGUNITNAME ="orgUnitName";
	
	private String orgUnitName;

	public String getOrgUnitName() {
		return orgUnitName;
	}
	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}
	public ScmPurchaseCanuseSetOrg2() {
		super();
	}
	public ScmPurchaseCanuseSetOrg2(boolean defaultValue) {
		super(defaultValue);
	}
}
