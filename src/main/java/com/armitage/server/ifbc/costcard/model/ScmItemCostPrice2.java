package com.armitage.server.ifbc.costcard.model;

public class ScmItemCostPrice2 extends ScmItemCostPrice{
	
	public static final String FN_ITEMNO = "itemNo";
	public static final String FN_ITEMNAME = "itemName";
	
    private String itemNo;
    private String itemName;
	private boolean	updHistory;	//是否需更新历史数据
	
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

	public boolean isUpdHistory() {
		return updHistory;
	}

	public void setUpdHistory(boolean updHistory) {
		this.updHistory = updHistory;
	}

	public ScmItemCostPrice2() {
		super();
	}

	public ScmItemCostPrice2(boolean defaultValue) {
		super(defaultValue);
	}
}
