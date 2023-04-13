package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;

public class ScmInvSalePriceentry2 extends ScmInvSalePriceentry{
	public static final String FN_ITEMNO = "itemNo";
	public static final String FN_ITEMNAME = "itemName";
	public static final String FN_SPEC = "spec";
	public static final String FN_UNITNAME = "unitName";
	
	private String itemNo;
	private String itemName;
	private String spec;
	private String unitName;
	
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

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public ScmInvSalePriceentry2(boolean defaultValue) {
		super(defaultValue);
	}

	public ScmInvSalePriceentry2() {
		super();
	}
}
