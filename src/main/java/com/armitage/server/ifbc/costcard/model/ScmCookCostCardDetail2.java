package com.armitage.server.ifbc.costcard.model;

public class ScmCookCostCardDetail2 extends ScmCookCostCardDetail{
	public static final String FN_TYPENAME = "typeName";
	public static final String FN_CSTUNITNAME = "cstUnitName";
	
	private String typeName;
	private String cstUnitName;
	private String orgUnitNo;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

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

	public ScmCookCostCardDetail2() {
		super();
	}

	public ScmCookCostCardDetail2(boolean defaultValue) {
		super(defaultValue);
	}
}
