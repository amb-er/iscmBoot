package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;

import java.util.Date;
import java.util.List;

public class ScmInvSalePriceAdd {
	private String pmNo;
	private Date pmDate;
	private Date begDate;
	private Date endDate;
	private String remarks;
	private List<ScmInvSalePriceAddDetail> detailList;
	
	public String getPmNo() {
		return pmNo;
	}
	public void setPqNo(String pmNo) {
		this.pmNo = pmNo;
	}
	public Date getPmDate() {
		return pmDate;
	}
	public void setPmDate(Date pmDate) {
		this.pmDate = pmDate;
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
	public List<ScmInvSalePriceAddDetail> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<ScmInvSalePriceAddDetail> detailList) {
		this.detailList = detailList;
	}
	
}
