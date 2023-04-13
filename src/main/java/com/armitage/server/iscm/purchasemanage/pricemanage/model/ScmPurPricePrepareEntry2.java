package com.armitage.server.iscm.purchasemanage.pricemanage.model;

public class ScmPurPricePrepareEntry2 extends ScmPurPricePrepareEntry{
	public static final String FN_ITEMNO = "itemNo";
	public static final String FN_ITEMNAME = "itemName";
	public static final String FN_SPEC = "spec";
	public static final String FN_PURUNITNAME = "purUnitName";
	public static final String FN_VENDORNAME1 = "vendorName1";
	public static final String FN_VENDORNAME2 = "vendorName2";
	public static final String FN_VENDORNAME3 = "vendorName3";
	public static final String FN_TAXRATESTR1 = "taxRateStr1";
	public static final String FN_TAXRATESTR2 = "taxRateStr2";
	public static final String FN_TAXRATESTR3 = "taxRateStr3";
	public static final String FN_SELVNDRID = "selVndrId";
	public static final String FN_SELVNDRIDNAME = "selVndrIdName";
	public static final String FN_PRESELVNDR1 = "preSelVndr1";
	public static final String FN_PRESELVNDR2 = "preSelVndr2";
	public static final String FN_PRESELVNDR3 = "preSelVndr3";
	
	private String itemNo;
	private String itemName;
	private String spec;
	private String purUnitName;
	private String vendorName1;
	private String vendorName2;
	private String vendorName3;
	private String taxRateStr1;
	private String taxRateStr2;
	private String taxRateStr3;
	private long selVndrId;
	private String selVndrIdName;
	private boolean modified;
	private boolean preSelVndr1;
	private boolean preSelVndr2;
	private boolean preSelVndr3;
	
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

	
	public String getVendorName1() {
		return vendorName1;
	}

	public void setVendorName1(String vendorName1) {
		this.vendorName1 = vendorName1;
	}

	public String getVendorName2() {
		return vendorName2;
	}

	public void setVendorName2(String vendorName2) {
		this.vendorName2 = vendorName2;
	}

	public String getVendorName3() {
		return vendorName3;
	}

	public void setVendorName3(String vendorName3) {
		this.vendorName3 = vendorName3;
	}

	public String getTaxRateStr1() {
		return taxRateStr1;
	}

	public void setTaxRateStr1(String taxRateStr1) {
		this.taxRateStr1 = taxRateStr1;
	}

	public String getTaxRateStr2() {
		return taxRateStr2;
	}

	public void setTaxRateStr2(String taxRateStr2) {
		this.taxRateStr2 = taxRateStr2;
	}

	public String getTaxRateStr3() {
		return taxRateStr3;
	}

	public void setTaxRateStr3(String taxRateStr3) {
		this.taxRateStr3 = taxRateStr3;
	}

	public String getSelVndrIdName() {
		return selVndrIdName;
	}

	public void setSelVndrIdName(String selVndrIdName) {
		this.selVndrIdName = selVndrIdName;
	}

	public long getSelVndrId() {
		return selVndrId;
	}

	public void setSelVndrId(long selVndrId) {
		this.selVndrId = selVndrId;
	}

	public boolean isModified() {
		return modified;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}

	public boolean isPreSelVndr1() {
		return preSelVndr1;
	}

	public void setPreSelVndr1(boolean preSelVndr1) {
		this.preSelVndr1 = preSelVndr1;
	}

	public boolean isPreSelVndr2() {
		return preSelVndr2;
	}

	public void setPreSelVndr2(boolean preSelVndr2) {
		this.preSelVndr2 = preSelVndr2;
	}

	public boolean isPreSelVndr3() {
		return preSelVndr3;
	}

	public void setPreSelVndr3(boolean preSelVndr3) {
		this.preSelVndr3 = preSelVndr3;
	}

	public ScmPurPricePrepareEntry2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue){
			this.selVndrId=0;
			this.modified=false;
			this.preSelVndr1=false;
			this.preSelVndr2=false;
			this.preSelVndr3=false;
		}
	}

	public ScmPurPricePrepareEntry2() {
		super();
	}
}
