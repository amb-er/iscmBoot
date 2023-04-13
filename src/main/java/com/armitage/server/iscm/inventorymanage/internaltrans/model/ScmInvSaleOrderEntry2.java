package com.armitage.server.iscm.inventorymanage.internaltrans.model;

import java.math.BigDecimal;

public class ScmInvSaleOrderEntry2 extends ScmInvSaleOrderEntry {
	public static final String FN_ITEMNO = "itemNo";
	public static final String FN_ITEMNAME = "itemName";
	public static final String FN_SPEC = "spec";
	public static final String FN_BASEUNITNAME = "baseUnitName";
	public static final String FN_UNITNAME = "unitName";
	public static final String FN_CONVRATE = "convrate";
	public static final String FN_CHOOSED = "choosed";
	public static final String FN_CONCESSIVERECRATESTR = "concessiveRecRateStr";
	
	private String classCode;
	private String itemNo;
	private String itemName;
	private String spec;
	private String baseUnitName;
	private String unitName;
	private BigDecimal convrate;
	private boolean choosed;
	private String concessiveRecRateStr;
	private String pieUnitName;

	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getBaseUnitName() {
		return baseUnitName;
	}
	public void setBaseUnitName(String baseUnitName) {
		this.baseUnitName = baseUnitName;
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
	public BigDecimal getConvrate() {
		return convrate;
	}
	public void setConvrate(BigDecimal convrate) {
		this.convrate = convrate;
	}
	public boolean isChoosed() {
		return choosed;
	}
	public void setChoosed(boolean choosed) {
		this.choosed = choosed;
	}
	
	public String getPieUnitName() {
		return pieUnitName;
	}
	public void setPieUnitName(String pieUnitName) {
		this.pieUnitName = pieUnitName;
	}
	public String getConcessiveRecRateStr() {
		return concessiveRecRateStr;
	}
	public void setConcessiveRecRateStr(String concessiveRecRateStr) {
		this.concessiveRecRateStr = concessiveRecRateStr;
	}
	public ScmInvSaleOrderEntry2(boolean defaultValue) {
		super(defaultValue);
		if (defaultValue) {
			this.convrate=BigDecimal.ZERO;
		}
	}

	public ScmInvSaleOrderEntry2() {
		super();
	}
}