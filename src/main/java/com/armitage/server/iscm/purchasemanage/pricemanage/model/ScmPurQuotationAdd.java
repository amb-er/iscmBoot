package com.armitage.server.iscm.purchasemanage.pricemanage.model;

import java.util.Date;
import java.util.List;

public class ScmPurQuotationAdd {
	private String pqNo;
	private String vendorCode;
	private String buyerCode;
	private Date pqDate;
	private Date begDate;
	private Date endDate;
	private String remarks;
	private List<ScmPurQuotaionDetail> detailList;
	
	public String getPqNo() {
		return pqNo;
	}
	public void setPqNo(String pqNo) {
		this.pqNo = pqNo;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	public String getBuyerCode() {
		return buyerCode;
	}
	public void setBuyerCode(String buyerCode) {
		this.buyerCode = buyerCode;
	}
	public Date getPqDate() {
		return pqDate;
	}
	public void setPqDate(Date pqDate) {
		this.pqDate = pqDate;
	}
	public Date getBegDate() {
		return begDate;
	}
	public void setBegDate(Date begDate) {
		this.begDate = begDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public List<ScmPurQuotaionDetail> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<ScmPurQuotaionDetail> detailList) {
		this.detailList = detailList;
	}
	
}
