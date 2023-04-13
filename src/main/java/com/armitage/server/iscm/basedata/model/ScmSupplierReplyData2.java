package com.armitage.server.iscm.basedata.model;

public class ScmSupplierReplyData2 extends ScmSupplierReplyData{
	public static final String FN_BILLTYPE = "billType";
	public static final String FN_ORGUNITNO = "orgUnitNo";
	public static final String FN_REFBILLNO = "refBillNo";
	public static final String FN_PLATSUPPLIERID = "platSupplierId";
	public static final String FN_BILLCONFIRMSTATUS = "billConfirmStatus";

	private String billType;
	private String orgUnitNo;
	private String refBillNo;
	private long platSupplierId;
	private String billConfirmStatus;

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}

	public String getRefBillNo() {
		return refBillNo;
	}

	public void setRefBillNo(String refBillNo) {
		this.refBillNo = refBillNo;
	}

	public long getPlatSupplierId() {
		return platSupplierId;
	}

	public void setPlatSupplierId(long platSupplierId) {
		this.platSupplierId = platSupplierId;
	}

	public String getBillConfirmStatus() {
		return billConfirmStatus;
	}

	public void setBillConfirmStatus(String billConfirmStatus) {
		this.billConfirmStatus = billConfirmStatus;
	}

	public ScmSupplierReplyData2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue){
		}
	}
	
	public ScmSupplierReplyData2(){
		super();
	}
}
