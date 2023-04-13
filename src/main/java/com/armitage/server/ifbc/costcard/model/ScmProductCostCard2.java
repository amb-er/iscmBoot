package com.armitage.server.ifbc.costcard.model;

import java.util.Date;

public class ScmProductCostCard2 extends ScmProductCostCard {
	
	public static final String FN_ITEMNO = "itemNo";
	public static final String FN_ITEMNAME = "itemName";
	
	private String itemNo;
	private String itemName;
	private Date newEffectiveDate;
	
	
	
	public Date getNewEffectiveDate() {
		return newEffectiveDate;
	}
	public void setNewEffectiveDate(Date newEffectiveDate) {
		this.newEffectiveDate = newEffectiveDate;
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
	
}
