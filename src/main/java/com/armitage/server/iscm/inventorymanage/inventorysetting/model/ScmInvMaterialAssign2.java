package com.armitage.server.iscm.inventorymanage.inventorysetting.model;

public class ScmInvMaterialAssign2 extends ScmInvMaterialAssign{
	public static final String FN_ITEMNO ="itemNo";
    public static final String FN_ITEMNAME ="itemName";
    
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

	public ScmInvMaterialAssign2(boolean defaultValue) {
		super(defaultValue);
	}

	public ScmInvMaterialAssign2() {
		super();
	}
}
