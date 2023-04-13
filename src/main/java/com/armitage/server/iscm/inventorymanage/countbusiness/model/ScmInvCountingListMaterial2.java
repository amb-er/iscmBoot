package com.armitage.server.iscm.inventorymanage.countbusiness.model;

public class ScmInvCountingListMaterial2 extends ScmInvCountingListMaterial{
	public static final String FN_ITEMNO = "itemNo";
	public static final String FN_ITEMNAME = "itemName";
	
	private String itemNo;
	private String itemName;
	
	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public ScmInvCountingListMaterial2(boolean defaultValue) {
		super(defaultValue);
	}

	public ScmInvCountingListMaterial2() {
		super();
	}
}
