package com.armitage.server.iscm.purchasemanage.pricemanage.model;


public class ScmPurQuotationPlanEntry2 extends ScmPurQuotationPlanEntry{
	public static final String FN_ITEMNO = "itemNo";
	public static final String FN_ITEMNAME = "itemName";
	public static final String FN_SPEC = "spec";
	public static final String FN_PURUNITNAME = "purUnitName";
	public static final String FN_PURCHASEQTY = "purchaseQty";
	public static final String FN_PURCHASE = "purchaseAmt";
	
	private String itemNo;
	private String itemName;
	private String spec;
	private String purChaseQty;
	private String purUnitName;
	private String purChaseAmt;

	public String getPurChaseAmt() {
		return purChaseAmt;
	}

	public void setPurChaseAmt(String purChaseAmt) {
		this.purChaseAmt = purChaseAmt;
	}

	public void setPurChaseQty(String purChaseQty) {
		this.purChaseQty = purChaseQty;
	}


	public String getPurChaseQty() {
		return purChaseQty;
	}

	public void setPurchaseQty(String purChaseQty) {
		this.purChaseQty = purChaseQty;
	}

	public String getPurchaseAmt() {
		return purChaseAmt;
	}

	public void setPurchaseAmt(String purchaseAmt) {
		this.purChaseAmt = purchaseAmt;
	}

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

	public ScmPurQuotationPlanEntry2(boolean defaultValue) {
		super(defaultValue);
	}

	public ScmPurQuotationPlanEntry2() {
		super();
	}
}
