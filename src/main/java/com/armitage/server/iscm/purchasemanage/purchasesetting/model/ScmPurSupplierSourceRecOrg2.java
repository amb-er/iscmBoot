package com.armitage.server.iscm.purchasemanage.purchasesetting.model;


public class ScmPurSupplierSourceRecOrg2 extends ScmPurSupplierSourceRecOrg{

    public static final String FN_ORGUNITNAME = "orgUnitName";
	
	private String orgUnitName;

	public String getOrgUnitName() {
		return orgUnitName;
	}
	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}
	public ScmPurSupplierSourceRecOrg2() {
		super();
	}
	public ScmPurSupplierSourceRecOrg2(boolean defaultValue) {
		super(defaultValue);
	}
}
