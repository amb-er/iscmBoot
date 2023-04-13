package com.armitage.server.iscm.inventorymanage.countbusiness.model;

public class ScmInvCountingListWarehouse2 extends ScmInvCountingListWarehouse{
	public static final String FN_WHNO = "whNo";
	public static final String FN_WHNAME = "whName";
	
	private String whNo;
	private String whName;
	
	public String getWhNo() {
		return whNo;
	}

	public void setWhNo(String whNo) {
		this.whNo = whNo;
	}

	public String getWhName() {
		return whName;
	}

	public void setWhName(String whName) {
		this.whName = whName;
	}

	public ScmInvCountingListWarehouse2(boolean defaultValue) {
		super(defaultValue);
	}

	public ScmInvCountingListWarehouse2() {
		super();
	}
}
