package com.armitage.server.iscm.purchasemanage.pricemanage.model;

public class ScmPurMarketPriceEntry2 extends ScmPurMarketPriceEntry{

	public static final String FN_ITEMNO = "itemNo";
	public static final String FN_ITEMNAME = "itemName";
	public static final String FN_SPEC = "spec";
	public static final String FN_PURUNITNAME = "purUnitName";
	
	private String itemNo;
	private String itemName;
	private String spec;
	private String purUnitName;
	
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

	public String getPurUnitName() {
		return purUnitName;
	}

	public void setPurUnitName(String purUnitName) {
		this.purUnitName = purUnitName;
	}

	public ScmPurMarketPriceEntry2(boolean defaultValue) {
		super(defaultValue);
	}

	public ScmPurMarketPriceEntry2() {
		super();
	}
}
