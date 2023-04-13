package com.armitage.server.ifbc.report.model;

import java.math.BigDecimal;

public class ScmDeptAndOutltProfit {
	
	String name;
	Long outletId;
	BigDecimal realSaleAmt;
	BigDecimal stdAmt;
	BigDecimal realAmt;
	Long itemId;
	
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public BigDecimal getRealAmt() {
		return realAmt;
	}
	public void setRealAmt(BigDecimal realAmt) {
		this.realAmt = realAmt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getOutletId() {
		return outletId;
	}
	public void setOutletId(Long outletId) {
		this.outletId = outletId;
	}
	public BigDecimal getRealSaleAmt() {
		return realSaleAmt;
	}
	public void setRealSaleAmt(BigDecimal realSaleAmt) {
		this.realSaleAmt = realSaleAmt;
	}
	public BigDecimal getStdAmt() {
		return stdAmt;
	}
	public void setStdAmt(BigDecimal stdAmt) {
		this.stdAmt = stdAmt;
	}

	
}
