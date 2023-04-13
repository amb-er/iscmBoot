package com.armitage.server.iscm.inventorymanage.inventoryinitialization.model;

import java.math.BigDecimal;

public class ScmInvInitBillEntry2 extends ScmInvInitBillEntry{
	public static final String FN_ITEMNO = "itemNo";
	public static final String FN_ITEMNAME = "itemName";
	public static final String FN_SPEC = "spec";
	public static final String FN_UNITNAME = "unitName";
	public static final String FN_TAXRATESTR = "taxRateStr";
	public static final String FN_CONVRATE = "convrate";
	public static final String FN_PIEUNITNAME = "pieUnitName";
	
	private String itemNo;
	private String itemName;
	private String spec;
	private String unitName;
	private String taxRateStr;
	private BigDecimal convrate;
	private String pieUnitName;
	
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
	public String getTaxRateStr() {
		return taxRateStr;
	}
	public void setTaxRateStr(String taxRateStr) {
		this.taxRateStr = taxRateStr;
	}	
	public BigDecimal getConvrate() {
		return convrate;
	}
	public void setConvrate(BigDecimal convrate) {
		this.convrate = convrate;
	}
	
	public String getPieUnitName() {
		return pieUnitName;
	}
	public void setPieUnitName(String pieUnitName) {
		this.pieUnitName = pieUnitName;
	}
	public ScmInvInitBillEntry2(boolean defaultValue) {
		super(defaultValue);
		if (defaultValue) {
			this.taxRateStr="0%";
		}
	}
	
	public ScmInvInitBillEntry2() {
		super();
	}
	
}
