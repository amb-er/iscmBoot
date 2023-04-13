package com.armitage.server.quartz.model.invpurinwarehs;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class SupplierPlatInvPurInWarehsBill {
	
	private long id;
	private BigDecimal amt;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date bizDate;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date postDate;
	private String bizType;
	private String buyerName;
	private String buyerPhone;
	private String buyerOrgUnitNo;
	private String buyerOrgName;
	private String currency;
	private long demanderId;
	private BigDecimal exchangeRate;
	private String platformAppId;
	private String poNo;
	private String receiveAddress;
	private String receiveNo;
	private String receiverName;
	private String refPoNo;
	private String refPrNo;
	private String refWrNo;
	private String remarks;
	private String status;
	private String taskSource;
	private BigDecimal taxAmt;
	private long vendorId;
	private List<SupplierPlatInvPurInWarehsBillEntry> entryList;
	
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
	public Date getBizDate() {
		return bizDate;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getBuyerPhone() {
		return buyerPhone;
	}
	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
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
	public String getPlatformAppId() {
		return platformAppId;
	}
	public void setPlatformAppId(String platformAppId) {
		this.platformAppId = platformAppId;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getReceiveAddress() {
		return receiveAddress;
	}
	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}
	public String getReceiveNo() {
		return receiveNo;
	}
	public void setReceiveNo(String receiveNo) {
		this.receiveNo = receiveNo;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getRefPoNo() {
		return refPoNo;
	}
	public void setRefPoNo(String refPoNo) {
		this.refPoNo = refPoNo;
	}
	public String getRefPrNo() {
		return refPrNo;
	}
	public void setRefPrNo(String refPrNo) {
		this.refPrNo = refPrNo;
	}
	public String getRefWrNo() {
		return refWrNo;
	}
	public void setRefWrNo(String refWrNo) {
		this.refWrNo = refWrNo;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public long getVendorId() {
		return vendorId;
	}
	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}
	public List<SupplierPlatInvPurInWarehsBillEntry> getEntryList() {
		return entryList;
	}
	public void setEntryList(List<SupplierPlatInvPurInWarehsBillEntry> entryList) {
		this.entryList = entryList;
	}
}
