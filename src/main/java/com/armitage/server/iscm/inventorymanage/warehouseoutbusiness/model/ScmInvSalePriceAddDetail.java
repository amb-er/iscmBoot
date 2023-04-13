package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;

import java.math.BigDecimal;

public class ScmInvSalePriceAddDetail {
	private int lineId;
	private String itemNo;
	private String unit;
	private BigDecimal saleTaxPrice;
	private BigDecimal preSaleTaxPrice;
	private String remarks;
	
	public int getLineId() {
		return lineId;
	}
	public void setLineId(int lineId) {
		this.lineId = lineId;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public BigDecimal getSaleTaxPrice() {
		return saleTaxPrice;
	}
	public void setSaleTaxPrice(BigDecimal saleTaxPrice) {
		this.saleTaxPrice = saleTaxPrice;
	}
	public BigDecimal getPreSaleTaxPrice() {
		return preSaleTaxPrice;
	}
	public void setPreSaleTaxPrice(BigDecimal preSaleTaxPrice) {
		this.preSaleTaxPrice = preSaleTaxPrice;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
