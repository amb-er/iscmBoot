package com.armitage.server.iscm.common.model;

public class ScmDataCollectionReg2 extends ScmDataCollectionReg {
	private String orgUnitNo;
	private String reqNo;
	public String getOrgUnitNo() {
		return orgUnitNo;
	}
	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}
	public String getReqNo() {
		return reqNo;
	}
	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}
	public ScmDataCollectionReg2() {
		super();
	}
	public ScmDataCollectionReg2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue) {
		}
	}
}
