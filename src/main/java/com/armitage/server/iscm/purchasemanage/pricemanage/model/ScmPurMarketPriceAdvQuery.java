package com.armitage.server.iscm.purchasemanage.pricemanage.model;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmPurMarketPriceAdvQuery  extends BaseModel{
	private String piNo;
	private String enquiryCode;
	private Date bizDateFrom;
	private Date bizDateTo;
	private String status;
	private boolean fromInterface;

	public String getPiNo() {
		return piNo;
	}
	public void setPiNo(String piNo) {
		this.piNo = piNo;
	}
	public String getEnquiryCode() {
		return enquiryCode;
	}
	public void setEnquiryCode(String enquiryCode) {
		this.enquiryCode = enquiryCode;
	}
	public Date getBizDateFrom() {
		return bizDateFrom;
	}
	public void setBizDateFrom(Date bizDateFrom) {
		this.bizDateFrom = bizDateFrom;
	}
	public Date getBizDateTo() {
		return bizDateTo;
	}
	public void setBizDateTo(Date bizDateTo) {
		this.bizDateTo = bizDateTo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isFromInterface() {
		return fromInterface;
	}
	public void setFromInterface(boolean fromInterface) {
		this.fromInterface = fromInterface;
	}
	@Override
	public String getPkKey() {
		return null;
	}
	@Override
	public long getPK() {
		return 0;
	}
	@Override
	public String[] getRequiredFields() {
		return null;
	}
	@Override
	public String[] getFieldNames() {
		return null;
	}
	@Override
	public List<String[]> getUniqueKeys() {
		return null;
	}
	
}
