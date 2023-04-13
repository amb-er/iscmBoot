package com.armitage.server.iscm.basedata.model;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmSupplierQualifieInfoBillAdvQuery  extends BaseModel{
    public static final String FN_BILLNO ="billNo";
	public static final String FN_CREATEDATEFROM = "createDateFrom"; 
    public static final String FN_CREATEDATETO = "createDateTo"; 
	public static final String FN_BIZDATEDATEFROM = "bizDateFrom"; 
    public static final String FN_BIZDATEDATETO = "bizDateTo"; 
    public static final String FN_VENDORID = "vendorId"; 	//供应商ID
    public static final String FN_VENDORCODE = "vendorCode";//供应商编号
    public static final String FN_STATUS ="status";
	
	private String billNo;
	private Date createDateFrom;
	private Date createDateTo;
	private Date bizDateFrom;
	private Date bizDateTo;
	private long vendorId;
	private String vendorCode;
	private String status;
	
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
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
	public long getVendorId() {
		return vendorId;
	}
	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
