package com.armitage.server.iscm.purchasemanage.pricemanage.model;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmPurQuotationAdvQuery extends BaseModel{
    public static final String FN_VENDORCLASSID = "vendorClassId"; //供应商类别
	public static final String FN_VENDORID = "vendorId";
	public static final String FN_BIZDATEFROM = "bizDateFrom";
	public static final String FN_BIZDATETO = "bizDateTo";
    public static final String FN_CREATEDATEFROM = "createDateFrom"; //创建日期起
    public static final String FN_CREATEDATETO = "createDateTo"; //创建日期止
	
    private long vendorClassId;
    private long vendorId;
	private Date bizDateFrom;
	private Date bizDateTo;
    private Date createDateFrom;
    private Date createDateTo;
	
	public long getVendorClassId() {
		return vendorClassId;
	}

	public void setVendorClassId(long vendorClassId) {
		this.vendorClassId = vendorClassId;
	}

	public long getVendorId() {
		return vendorId;
	}

	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
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

	public Date getCreateDateFrom() {
		return createDateFrom;
	}

	public void setCreateDateFrom(Date createDateFrom) {
		this.createDateFrom = createDateFrom;
	}

	public Date getCreateDateTo() {
		return createDateTo;
	}

	public void setCreateDateTo(Date createDateTo) {
		this.createDateTo = createDateTo;
	}

	@Override
	public String getPkKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getPK() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String[] getRequiredFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getFieldNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String[]> getUniqueKeys() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
