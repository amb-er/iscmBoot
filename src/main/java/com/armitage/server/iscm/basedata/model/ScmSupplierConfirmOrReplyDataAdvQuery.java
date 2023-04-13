package com.armitage.server.iscm.basedata.model;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmSupplierConfirmOrReplyDataAdvQuery extends BaseModel{
	
	public static final String FN_BEGDATE = "begDate";
	public static final String FN_ENDDATE = "endDate";
	public static final String FN_VENDORID = "vendorId";
	public static final String FN_BILLTYPE = "billType";
	public static final String FN_BILLNO = "billNo";
	public static final String FN_MSGTYPE = "msgType";
	public static final String FN_STATUS = "status";
	public static final String FN_PLATVENDORID = "platVendorId";
	
	private Date begDate;
	private Date endDate;
	private long vendorId;
	private String billType;
	private String billNo;
	private String msgType;
	private String status;
	private long platVendorId;
	
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
	public long getVendorId() {
		return vendorId;
	}
	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getPlatVendorId() {
		return platVendorId;
	}
	public void setPlatVendorId(long platVendorId) {
		this.platVendorId = platVendorId;
	}
	@Override
	public String[] getFieldNames() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long getPK() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getPkKey() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String[] getRequiredFields() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<String[]> getUniqueKeys() {
		// TODO Auto-generated method stub
		return null;
	}
}
