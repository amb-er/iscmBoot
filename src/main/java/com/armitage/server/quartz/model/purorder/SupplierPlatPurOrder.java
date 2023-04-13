package com.armitage.server.quartz.model.purorder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class SupplierPlatPurOrder {

	private long id;
	private BigDecimal amt;
	private String buyerName;
	private String buyerOrgUnitNo;
	private String buyerOrgName;
	private String buyerPhone;
	private boolean centeralBalance;
	private String currency;
	private long demanderId;
	private BigDecimal exchangeRate;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date orderDate;
	private String payment;
	private String platformAppId;
	private String refPoNo;
	private String remarks;
	private String settlement;
	private String status;
	private String taskSource;
	private BigDecimal taxAmt;
	private boolean unified;
	private long vendorId;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date releaseDate;
	private List<SupplierPlatPurOrderEntry> entryList;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getBuyerOrgUnitNo() {
		return buyerOrgUnitNo;
	}
	public void setBuyerOrgUnitNo(String buyerOrgUnitNo) {
		this.buyerOrgUnitNo = buyerOrgUnitNo;
	}
	public String getBuyerOrgName() {
		return buyerOrgName;
	}
	public void setBuyerOrgName(String buyerOrgName) {
		this.buyerOrgName = buyerOrgName;
	}
	public String getBuyerPhone() {
		return buyerPhone;
	}
	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}
	public boolean isCenteralBalance() {
		return centeralBalance;
	}
	public void setCenteralBalance(boolean centeralBalance) {
		this.centeralBalance = centeralBalance;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public long getDemanderId() {
		return demanderId;
	}
	public void setDemanderId(long demanderId) {
		this.demanderId = demanderId;
	}
	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getPlatformAppId() {
		return platformAppId;
	}
	public void setPlatformAppId(String platformAppId) {
		this.platformAppId = platformAppId;
	}
	public String getRefPoNo() {
		return refPoNo;
	}
	public void setRefPoNo(String refPoNo) {
		this.refPoNo = refPoNo;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getSettlement() {
		return settlement;
	}
	public void setSettlement(String settlement) {
		this.settlement = settlement;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTaskSource() {
		return taskSource;
	}
	public void setTaskSource(String taskSource) {
		this.taskSource = taskSource;
	}
	public BigDecimal getTaxAmt() {
		return taxAmt;
	}
	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}
	public boolean isUnified() {
		return unified;
	}
	public void setUnified(boolean unified) {
		this.unified = unified;
	}
	public long getVendorId() {
		return vendorId;
	}
	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public List<SupplierPlatPurOrderEntry> getEntryList() {
		return entryList;
	}
	public void setEntryList(List<SupplierPlatPurOrderEntry> entryList) {
		this.entryList = entryList;
	}
}
