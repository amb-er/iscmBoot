package com.armitage.server.iscm.purchasemanage.purchasesetting.model;


public class ScmPurSupplierSourceEntry2 extends ScmPurSupplierSourceEntry{

	public static final String FN_ITEMNO = "itemNo";
	public static final String FN_ITEMNAME = "itemName";
	public static final String FN_SPEC = "spec";
	public static final String FN_TAXRATESTR = "taxRateStr";
	
	private String itemNo;
	private String itemName;
	private String spec;
	private String purUnitName;
	private String taxRateStr;
    private String refPriceStatusName;
	
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
	public String getTaxRateStr() {
		return taxRateStr;
	}
	public void setTaxRateStr(String taxRateStr) {
		this.taxRateStr = taxRateStr;
	}
	public String getRefPriceStatusName() {
		return refPriceStatusName;
	}
	public void setRefPriceStatusName(String refPriceStatusName) {
		this.refPriceStatusName = refPriceStatusName;
	}
	public ScmPurSupplierSourceEntry2() {
		super();
	}
	public ScmPurSupplierSourceEntry2(boolean defaultValue) {
		super(defaultValue);
	}
}
