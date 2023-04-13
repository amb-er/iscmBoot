package com.armitage.server.iscm.basedata.model;

public class ScmBillPending2 extends ScmBillPending {
	private String usrCodes;
	private String billTypeName;
	private String orgUnitName;
	private String checkerName;
	private String confirmorName;

	public String getUsrCodes() {
		return usrCodes;
	}

	public void setUsrCodes(String usrCodes) {
		this.usrCodes = usrCodes;
	}

	public String getBillTypeName() {
		return billTypeName;
	}

	public void setBillTypeName(String billTypeName) {
		this.billTypeName = billTypeName;
	}

	public String getOrgUnitName() {
		return orgUnitName;
	}

	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}

	public String getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}

	public String getConfirmorName() {
		return confirmorName;
	}

	public void setConfirmorName(String confirmorName) {
		this.confirmorName = confirmorName;
	}

	public ScmBillPending2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue){
		}
	}
	
	public ScmBillPending2(){
		super();
	}
}
