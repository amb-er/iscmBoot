package com.armitage.server.iscm.report.purchase.model;

import java.math.BigDecimal;
import java.sql.Date;

public class OrderTransTotalAPI{

	private long vendorId;
	private String vendorNo;
	private String vendorName;
	private String purOrgUnitNo;
	private String purOrgUnitName;
	private String storageOrgUnitNo;
	private String storageOrgUnitName;
	private Date minOrderDate;
	private Date maxOrderDate;
	private BigDecimal amt;
	private BigDecimal taxAmt;
	private BigDecimal receiveAmt;
	private BigDecimal receiveTaxAmt;
	private BigDecimal addInAmt;
	private BigDecimal addInTaxAmt;
	private BigDecimal returnAmt;
	private BigDecimal returnTaxAmt;
	private BigDecimal outAmt;
	private BigDecimal outTaxAmt;
	private BigDecimal notAddInAmt;
	private BigDecimal notAddInTaxAmt;
	private BigDecimal realAddInAmt;
	private BigDecimal realAddInTaxAmt;
	public long getVendorId() {
		return vendorId;
	}
	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorNo() {
		return vendorNo;
	}
	public void setVendorNo(String vendorNo) {
		this.vendorNo = vendorNo;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getPurOrgUnitNo() {
		return purOrgUnitNo;
	}
	public void setPurOrgUnitNo(String purOrgUnitNo) {
		this.purOrgUnitNo = purOrgUnitNo;
	}
	public String getPurOrgUnitName() {
		return purOrgUnitName;
	}
	public void setPurOrgUnitName(String purOrgUnitName) {
		this.purOrgUnitName = purOrgUnitName;
	}
	public String getStorageOrgUnitNo() {
		return storageOrgUnitNo;
	}
	public void setStorageOrgUnitNo(String storageOrgUnitNo) {
		this.storageOrgUnitNo = storageOrgUnitNo;
	}
	public String getStorageOrgUnitName() {
		return storageOrgUnitName;
	}
	public void setStorageOrgUnitName(String storageOrgUnitName) {
		this.storageOrgUnitName = storageOrgUnitName;
	}
	public Date getMinOrderDate() {
		return minOrderDate;
	}
	public void setMinOrderDate(Date minOrderDate) {
		this.minOrderDate = minOrderDate;
	}
	public Date getMaxOrderDate() {
		return maxOrderDate;
	}
	public void setMaxOrderDate(Date maxOrderDate) {
		this.maxOrderDate = maxOrderDate;
	}
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	public BigDecimal getTaxAmt() {
		return taxAmt;
	}
	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}
	public BigDecimal getReceiveAmt() {
		return receiveAmt;
	}
	public void setReceiveAmt(BigDecimal receiveAmt) {
		this.receiveAmt = receiveAmt;
	}
	public BigDecimal getReceiveTaxAmt() {
		return receiveTaxAmt;
	}
	public void setReceiveTaxAmt(BigDecimal receiveTaxAmt) {
		this.receiveTaxAmt = receiveTaxAmt;
	}
	public BigDecimal getAddInAmt() {
		return addInAmt;
	}
	public void setAddInAmt(BigDecimal addInAmt) {
		this.addInAmt = addInAmt;
	}
	public BigDecimal getAddInTaxAmt() {
		return addInTaxAmt;
	}
	public void setAddInTaxAmt(BigDecimal addInTaxAmt) {
		this.addInTaxAmt = addInTaxAmt;
	}
	public BigDecimal getReturnAmt() {
		return returnAmt;
	}
	public void setReturnAmt(BigDecimal returnAmt) {
		this.returnAmt = returnAmt;
	}
	public BigDecimal getReturnTaxAmt() {
		return returnTaxAmt;
	}
	public void setReturnTaxAmt(BigDecimal returnTaxAmt) {
		this.returnTaxAmt = returnTaxAmt;
	}
	public BigDecimal getOutAmt() {
		return outAmt;
	}
	public void setOutAmt(BigDecimal outAmt) {
		this.outAmt = outAmt;
	}
	public BigDecimal getOutTaxAmt() {
		return outTaxAmt;
	}
	public void setOutTaxAmt(BigDecimal outTaxAmt) {
		this.outTaxAmt = outTaxAmt;
	}
	public BigDecimal getNotAddInAmt() {
		return notAddInAmt;
	}
	public void setNotAddInAmt(BigDecimal notAddInAmt) {
		this.notAddInAmt = notAddInAmt;
	}
	public BigDecimal getNotAddInTaxAmt() {
		return notAddInTaxAmt;
	}
	public void setNotAddInTaxAmt(BigDecimal notAddInTaxAmt) {
		this.notAddInTaxAmt = notAddInTaxAmt;
	}
	public BigDecimal getRealAddInAmt() {
		return realAddInAmt;
	}
	public void setRealAddInAmt(BigDecimal realAddInAmt) {
		this.realAddInAmt = realAddInAmt;
	}
	public BigDecimal getRealAddInTaxAmt() {
		return realAddInTaxAmt;
	}
	public void setRealAddInTaxAmt(BigDecimal realAddInTaxAmt) {
		this.realAddInTaxAmt = realAddInTaxAmt;
	}
	
	
}

