package com.armitage.server.iscm.inventorymanage.cstbusiness.model;

import java.math.BigDecimal;

public class ScmInvCountingCostCenterProduct2 extends ScmInvCountingCostCenterProduct{
	
	public static final String FN_ITEMNO = "itemNo";
	public static final String FN_ITEMNAME = "itemName";
	public static final String FN_UNITNAME = "unitName";
	public static final String FN_GROUPNAME = "groupName";
	public static final String FN_SPEC = "spec";
	
	private String itemNo;
	private String itemName;
	private String unitName;
	private String groupName;
	private String spec;

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

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public ScmInvCountingCostCenterProduct2(boolean defaultValue) {
		super(defaultValue);
		if (defaultValue) {
		}
	}
	
	public ScmInvCountingCostCenterProduct2() {
		super();
	}
}
