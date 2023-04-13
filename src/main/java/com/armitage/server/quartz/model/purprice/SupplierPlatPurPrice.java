package com.armitage.server.quartz.model.purprice;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class SupplierPlatPurPrice {

	private boolean assignOrg;
	private boolean businessSelfQuotation ;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date begDate;
	private String bizType;
	private String buyerOrgUnitNo;
	private String buyerOrgName;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date checkDate;
	private String checker;
	private String confirmStatus;
	private String currency;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date deGetTime;
	private long demanderId;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date endDate;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date quotationExpiryDate;
	private BigDecimal exchangeRate;
	private String finOrgUnitNo;
	private String finOrgName;
	private boolean inclueTax;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date pmDate;
	private String priceName;
	private String refPmNo;
	private String remarks;
	private String status;
	private long selVndrId;
	private long vendorId1;
	private long vendorId2;
	private long vendorId3;
	
	public boolean isAssignOrg() {
		return assignOrg;
	}
	public void setAssignOrg(boolean assignOrg) {
		this.assignOrg = assignOrg;
	}
	public boolean isBusinessSelfQuotation() {
		return businessSelfQuotation;
	}
	public void setBusinessSelfQuotation(boolean businessSelfQuotation) {
		this.businessSelfQuotation = businessSelfQuotation;
	}
	public Date getBegDate() {
		return begDate;
	}
	public void setBegDate(Date begDate) {
		this.begDate = begDate;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
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
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public String getChecker() {
		return checker;
	}
	public void setChecker(String checker) {
		this.checker = checker;
	}
	public String getConfirmStatus() {
		return confirmStatus;
	}
	public void setConfirmStatus(String confirmStatus) {
		this.confirmStatus = confirmStatus;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Date getDeGetTime() {
		return deGetTime;
	}
	public void setDeGetTime(Date deGetTime) {
		this.deGetTime = deGetTime;
	}
	public long getDemanderId() {
		return demanderId;
	}
	public void setDemanderId(long demanderId) {
		this.demanderId = demanderId;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getQuotationExpiryDate() {
		return quotationExpiryDate;
	}
	public void setQuotationExpiryDate(Date quotationExpiryDate) {
		this.quotationExpiryDate = quotationExpiryDate;
	}
	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public String getFinOrgUnitNo() {
		return finOrgUnitNo;
	}
	public void setFinOrgUnitNo(String finOrgUnitNo) {
		this.finOrgUnitNo = finOrgUnitNo;
	}
	public String getFinOrgName() {
		return finOrgName;
	}
	public void setFinOrgName(String finOrgName) {
		this.finOrgName = finOrgName;
	}
	public boolean isInclueTax() {
		return inclueTax;
	}
	public void setInclueTax(boolean inclueTax) {
		this.inclueTax = inclueTax;
	}
	public Date getPmDate() {
		return pmDate;
	}
	public void setPmDate(Date pmDate) {
		this.pmDate = pmDate;
	}
	public String getPriceName() {
		return priceName;
	}
	public void setPriceName(String priceName) {
		this.priceName = priceName;
	}
	public String getRefPmNo() {
		return refPmNo;
	}
	public void setRefPmNo(String refPmNo) {
		this.refPmNo = refPmNo;
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
	public long getSelVndrId() {
		return selVndrId;
	}
	public void setSelVndrId(long selVndrId) {
		this.selVndrId = selVndrId;
	}
	public long getVendorId1() {
		return vendorId1;
	}
	public void setVendorId1(long vendorId1) {
		this.vendorId1 = vendorId1;
	}
	public long getVendorId2() {
		return vendorId2;
	}
	public void setVendorId2(long vendorId2) {
		this.vendorId2 = vendorId2;
	}
	public long getVendorId3() {
		return vendorId3;
	}
	public void setVendorId3(long vendorId3) {
		this.vendorId3 = vendorId3;
	}
	private List<SupplierPlatPurPriceEntry> entryList;
	
	public List<SupplierPlatPurPriceEntry> getEntryList() {
		return entryList;
	}
	public void setEntryList(List<SupplierPlatPurPriceEntry> entryList) {
		this.entryList = entryList;
	}
}
