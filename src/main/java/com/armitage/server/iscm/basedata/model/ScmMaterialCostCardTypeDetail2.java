package com.armitage.server.iscm.basedata.model;

public class ScmMaterialCostCardTypeDetail2 extends ScmMaterialCostCardTypeDetail {
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

	public ScmMaterialCostCardTypeDetail2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue){
		}
	}
	
	public ScmMaterialCostCardTypeDetail2(){
		super();
	}
}
