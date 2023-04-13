package com.armitage.server.iscm.purchasemanage.pricemanage.model;


public class ScmPurQuotationEntry2 extends ScmPurQuotationEntry{
	public static final String FN_ITEMNO = "itemNo";
	public static final String FN_ITEMNAME = "itemName";
	public static final String FN_SPEC = "spec";
	public static final String FN_PURUNITNAME = "purUnitName";
	public static final String FN_TAXRATESTR = "taxRateStr";
	
	private String itemNo;
	private String itemName;
	private String spec;
	private String purUnitName;
	private String taxRateStr;
	private String attachmentName;
	private long attachmentId;
	
	
	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public long getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(long attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getTaxRateStr() {
		return taxRateStr;
	}

	public void setTaxRateStr(String taxRateStr) {
		this.taxRateStr = taxRateStr;
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

	public ScmPurQuotationEntry2(boolean defaultValue) {
		super(defaultValue);
		if (defaultValue) {
			this.taxRateStr="0%";
		}
	}

	public ScmPurQuotationEntry2() {
		super();
	}
}
