package com.armitage.server.iscm.inventorymanage.inventorysetting.model;

public class ScmInvSupplyPlanEntry2 extends ScmInvSupplyPlanEntry {
	public static final String FN_ITEMNO ="itemNo";
	public static final String FN_ITEMNAME ="itemName";
	public static final String FN_PURUNITNAME = "purUnitName";
	public static final String FN_PIEUNITNAME = "pieUnitName";
	public static final String FN_PURUNITID = "purUnitId";
	public static final String FN_CLASSNAME = "className";
	
	private String itemNo;
	private String itemName;
	private String spec;
	private String purUnitName;
	private String pieUnitName;
	private long purUnitId;
	private String className;
	
	
	public String getPieUnitName() {
		return pieUnitName;
	}
	public void setPieUnitName(String pieUnitName) {
		this.pieUnitName = pieUnitName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public long getPurUnitId() {
		return purUnitId;
	}
	public void setPurUnitId(long purUnitId) {
		this.purUnitId = purUnitId;
	}
	public String getPurUnitName() {
		return purUnitName;
	}
	public void setPurUnitName(String purUnitName) {
		this.purUnitName = purUnitName;
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
