package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

public class ScmPurOrderEntryHistoryPrice {
	/*
	 * 采购历史价格查询表
	 */
	private String purGroupName;
	private String minOrderDate;
	private String maxOrderDate;
	private String vendorName;
	private String itemName;
	private String rowStatus;
	
	public String getPurGroupName() {
		return purGroupName;
	}
	public void setPurGroupName(String purGroupName) {
		this.purGroupName = purGroupName;
	}
	public String getMinOrderDate() {
		return minOrderDate;
	}
	public void setMinOrderDate(String minOrderDate) {
		this.minOrderDate = minOrderDate;
	}
	public String getMaxOrderDate() {
		return maxOrderDate;
	}
	public void setMaxOrderDate(String maxOrderDate) {
		this.maxOrderDate = maxOrderDate;
	}
	public String getVendorName() {
		return vendorName;
	}
	public String getRowStatus() {
		return rowStatus;
	}
	public void setRowStatus(String rowStatus) {
		this.rowStatus = rowStatus;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
}
