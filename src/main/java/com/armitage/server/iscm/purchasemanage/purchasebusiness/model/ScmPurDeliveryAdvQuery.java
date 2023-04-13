package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmPurDeliveryAdvQuery extends BaseModel{
    public static final String FN_VENDORCLASSID ="vendorClassId";
    public static final String FN_VENDORID ="vendorId";
    public static final String FN_BEGDATE ="begDate";
    public static final String FN_ENDDATE ="endDate";
    public static final String FN_REQORGUNITNO ="reqOrgUnitNo";
    public static final String FN_ITEMCLASSID ="itemClassId";
    public static final String FN_REQFINORGUNITNO ="reqFinOrgUnitNo";

	private long vendorClassId;
	private long vendorId;
	private Date begDate;
	private Date endDate;
	private String reqOrgUnitNo;
	private long itemClassId;
	private String reqFinOrgUnitNo;
	
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

	public String getReqOrgUnitNo() {
		return reqOrgUnitNo;
	}

	public void setReqOrgUnitNo(String reqOrgUnitNo) {
		this.reqOrgUnitNo = reqOrgUnitNo;
	}

	public long getItemClassId() {
		return itemClassId;
	}

	public void setItemClassId(long itemClassId) {
		this.itemClassId = itemClassId;
	}

	public String getReqFinOrgUnitNo() {
		return reqFinOrgUnitNo;
	}

	public void setReqFinOrgUnitNo(String reqFinOrgUnitNo) {
		this.reqFinOrgUnitNo = reqFinOrgUnitNo;
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
