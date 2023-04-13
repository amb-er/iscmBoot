package com.armitage.server.iscm.purchasemanage.purchasesetting.model;

public class ScmPurBuyerOrg2 extends ScmPurBuyerOrg {
	public static final String FN_ORGUNITNAME ="orgUnitName";

	private String orgUnitName;

    public String getOrgUnitName() {
		return orgUnitName;
	}

	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}

	public ScmPurBuyerOrg2(boolean defaultValue) {
		super(defaultValue);
	}

	public ScmPurBuyerOrg2() {
		super();
	}
}
