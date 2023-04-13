package com.armitage.server.ifbc.basedata.model;

public class ScmAccountingCycleTypeToOrg2 extends ScmAccountingCycleTypeToOrg  {
	 
    public static final String FN_ORGUNITNAME ="orgUnitName";
    
    private String orgUnitName;

	public String getOrgUnitName() {
		return orgUnitName;
	}
	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}
	public ScmAccountingCycleTypeToOrg2() {
		super();
	}
	public ScmAccountingCycleTypeToOrg2(boolean defaultValue) {
		super(defaultValue);
	}
	
}