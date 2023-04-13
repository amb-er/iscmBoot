package com.armitage.server.iscm.inventorymanage.AllocationApplication.model;

import java.math.BigDecimal;

public class ScmInvMoveReqBillEntry2 extends ScmInvMoveReqBillEntry{
	public static final String FN_ITEMNO = "itemNo";
	public static final String FN_ITEMNAME = "itemName";
	public static final String FN_SPEC = "spec";
	public static final String FN_UNITNAME = "unitName";
	public static final String FN_BASEUNITNAME = "baseUnitName";
	public static final String FN_CONVRATE = "convrate";
	public static final String FN_REQNO = "reqNo";
	public static final String FN_GROUPNAME = "groupName";
	public static final String FN_REQORGUNITNO = "reqOrgUnitNo";
	public static final String FN_INVUNITNAME = "invUnitName";
	public static final String FN_INVQTY = "invQty";
	
	
	private String itemNo;
	private String itemName;
	private String spec;
	private String unitName;
	private String baseUnitName;
	private BigDecimal convrate;
	private String reqNo;
	private String groupName;
	private String finOrgUnitNo;
	private String reqOrgUnitNo;
	private String invUnitName;
	private BigDecimal invQty;
	private String currencyNo;
	private BigDecimal exchangeRate;

	public String getReqNo() {
		return reqNo;
	}
	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getReqOrgUnitNo() {
		return reqOrgUnitNo;
	}
	public void setReqOrgUnitNo(String reqOrgUnitNo) {
		this.reqOrgUnitNo = reqOrgUnitNo;
	}
	public String getInvUnitName() {
		return invUnitName;
	}
	public void setInvUnitName(String invUnitName) {
		this.invUnitName = invUnitName;
	}
	public BigDecimal getInvQty() {
		return invQty;
	}
	public void setInvQty(BigDecimal invQty) {
		this.invQty = invQty;
	}
	public BigDecimal getConvrate() {
		return convrate;
	}
	public void setConvrate(BigDecimal convrate) {
		this.convrate = convrate;
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
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	public String getFinOrgUnitNo() {
		return finOrgUnitNo;
	}
	public void setFinOrgUnitNo(String finOrgUnitNo) {
		this.finOrgUnitNo = finOrgUnitNo;
	}
	public String getCurrencyNo() {
		return currencyNo;
	}
	public void setCurrencyNo(String currencyNo) {
		this.currencyNo = currencyNo;
	}
	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public ScmInvMoveReqBillEntry2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue){
			this.convrate=BigDecimal.ZERO;
			this.invQty=BigDecimal.ZERO;
		}
	}

	public ScmInvMoveReqBillEntry2() {
		super();
	}
}
