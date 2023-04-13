package com.armitage.server.iscm.basedata.model;

import java.util.Date;

public class ScmSupplierConfirmData2 extends ScmSupplierConfirmData {
	public static final String FN_CONFIRMSTATUS = "confirmStatus";
	public static final String FN_READTIME = "readTime";
	public static final String FN_REPLY = "reply";
	public static final String FN_BILLCONFIRMSTATUS = "billConfirmStatus";

	private String confirmStatus;
	private Date readTime;
	private boolean	reply;
	private String billConfirmStatus;

	public String getConfirmStatus() {
		return confirmStatus;
	}

	public void setConfirmStatus(String confirmStatus) {
		this.confirmStatus = confirmStatus;
	}

	public Date getReadTime() {
		return readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}

	public boolean isReply() {
		return reply;
	}

	public void setReply(boolean reply) {
		this.reply = reply;
	}

	public String getBillConfirmStatus() {
		return billConfirmStatus;
	}

	public void setBillConfirmStatus(String billConfirmStatus) {
		this.billConfirmStatus = billConfirmStatus;
	}

	public ScmSupplierConfirmData2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue){
		}
	}
	
	public ScmSupplierConfirmData2(){
		super();
	}
}
