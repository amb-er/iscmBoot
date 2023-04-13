package com.armitage.server.iscm.basedata.model;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmBillPendingAdvQuery  extends BaseModel {
	public static final String FN_SUBMITDATEFROM = "submitDateFrom";
	public static final String FN_SUBMITDATETO = "submitDateTo";
	public static final String FN_BILLDATEFROM = "billDateFrom"; 
    public static final String FN_BILLDATETO = "billDateTo"; 
	public static final String FN_BILLTYPE = "billType"; 
	public static final String FN_REMARKS = "remarks";

	private String usrCode;
	private Date submitDateFrom;
	private Date submitDateTo;
	private String billType;
	private String type;
	private Date billDateFrom;
	private Date billDateTo;
	private String remarks;

	public String getUsrCode() {
		return usrCode;
	}

	public void setUsrCode(String usrCode) {
		this.usrCode = usrCode;
	}

	public Date getSubmitDateFrom() {
		return submitDateFrom;
	}

	public void setSubmitDateFrom(Date submitDateFrom) {
		this.submitDateFrom = submitDateFrom;
	}

	public Date getSubmitDateTo() {
		return submitDateTo;
	}

	public void setSubmitDateTo(Date submitDateTo) {
		this.submitDateTo = submitDateTo;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Date getBillDateFrom() {
		return billDateFrom;
	}

	public void setBillDateFrom(Date billDateFrom) {
		this.billDateFrom = billDateFrom;
	}

	public Date getBillDateTo() {
		return billDateTo;
	}

	public void setBillDateTo(Date billDateTo) {
		this.billDateTo = billDateTo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
