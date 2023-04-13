package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmPurCheckAdvQuery extends BaseModel{
    public static final String FN_CKNO ="ckNo";
    public static final String FN_INVORGUNITNO = "invOrgUnitNo";
    public static final String FN_VENDORCLASSID = "vendorClassId";
    public static final String FN_VENDORID = "vendorId";
	public static final String FN_CHECKDATEFROM ="checkDateFrom"; 	//单据日期起
    public static final String FN_CHECKDATETO ="checkDateTo"; 		//单据日期止
    public static final String FN_CREATEDATEFROM = "createDateFrom"; //创建日期起
    public static final String FN_CREATEDATETO = "createDateTo"; //创建日期止

    private String ckNo;
	private String invOrgUnitNo;
	private long vendorClassId;
	private long vendorId;
	private Date checkDateFrom;
	private Date checkDateTo;
    private Date createDateFrom;
    private Date createDateTo;
	
	public String getCkNo() {
		return ckNo;
	}

	public void setCkNo(String ckNo) {
		this.ckNo = ckNo;
	}

	public String getInvOrgUnitNo() {
		return invOrgUnitNo;
	}

	public void setInvOrgUnitNo(String invOrgUnitNo) {
		this.invOrgUnitNo = invOrgUnitNo;
	}

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

	public Date getCheckDateFrom() {
		return checkDateFrom;
	}

	public void setCheckDateFrom(Date checkDateFrom) {
		this.checkDateFrom = checkDateFrom;
	}

	public Date getCheckDateTo() {
		return checkDateTo;
	}

	public void setCheckDateTo(Date checkDateTo) {
		this.checkDateTo = checkDateTo;
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
