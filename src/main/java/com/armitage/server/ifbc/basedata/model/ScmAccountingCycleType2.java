package com.armitage.server.ifbc.basedata.model;

public class ScmAccountingCycleType2 extends ScmAccountingCycleType  {
    public static final String FN_PERIODTYPENAME ="periodTypeName";

    private String periodTypeName;
    private String orgUnitNo;
    
	public String getPeriodTypeName() {
		return periodTypeName;
	}
	public void setPeriodTypeName(String periodTypeName) {
		this.periodTypeName = periodTypeName;
	}
	public String getOrgUnitNo() {
		return orgUnitNo;
	}
	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}
	public ScmAccountingCycleType2() {
		super();
	}
	public ScmAccountingCycleType2(boolean defaultValue) {
		super(defaultValue);
	}
	
}