package com.armitage.server.iscm.purchasemanage.purchasesetting.model;


public class ScmPurSupplyRecOrg2 extends ScmPurSupplyRecOrg{

    public static final String FN_RECEIVEORGUNITNAME ="receiveOrgUnitName";
	
    private String receiveOrgUnitName;

	public String getReceiveOrgUnitName() {
		return receiveOrgUnitName;
	}
	public void setReceiveOrgUnitName(String receiveOrgUnitName) {
		this.receiveOrgUnitName = receiveOrgUnitName;
	}
	public ScmPurSupplyRecOrg2() {
		super();
	}
	public ScmPurSupplyRecOrg2(boolean defaultValue) {
		super(defaultValue);
	}
}
