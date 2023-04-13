package com.armitage.server.ifbc.costcard.model;

public class ScmCostCardDetail2 extends ScmCostCardDetail{
	public static final String FN_CSTUNITNAME = "cstUnitName";
	
	private String cstUnitName;
	private String orgUnitNo;
	private String resOrgUnitNo;

	public String getCstUnitName() {
		return cstUnitName;
	}

	public void setCstUnitName(String cstUnitName) {
		this.cstUnitName = cstUnitName;
	}

	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}

	public String getResOrgUnitNo() {
		return resOrgUnitNo;
	}

	public void setResOrgUnitNo(String resOrgUnitNo) {
		this.resOrgUnitNo = resOrgUnitNo;
	}

	public ScmCostCardDetail2() {
		super();
	}

	public ScmCostCardDetail2(boolean defaultValue) {
		super(defaultValue);
	}

}
