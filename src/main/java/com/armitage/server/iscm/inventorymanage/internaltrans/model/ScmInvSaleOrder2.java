package com.armitage.server.iscm.inventorymanage.internaltrans.model;

import java.math.BigDecimal;
import java.util.List;

public class ScmInvSaleOrder2 extends ScmInvSaleOrder {
	public static final String FN_CHOOSED = "choosed";
	public static final String FN_CUSTNAME = "custName";
	public static final String FN_EDITORNAME = "editorName";
	public static final String FN_CHECKERNAME = "checkerName";
	public static final String FN_BILLTYPENAME = "billTypeName";
	public static final String FN_PENDINGUSR = "pendingUsr";
	public static final String FN_PENDINGUSRNAME = "pendingUsrName";
	public static final String FN_SALETAXAMt = "saleTaxAmt";

	private boolean choosed;
	private String statusName;
	private String vendorName;
	private String receiverName;
	private String purOrgUnitName;
	private String buyerName;
	private String creatorName;
	private String custName;
	private String editorName;
	private String checkerName;
	private String billTypeName;
	private String pendingUsr;
	private String pendingUsrName;
	private BigDecimal saleTaxAmt;
	private List<ScmInvSaleOrderEntry2> scmInvSaleOrderEntryList;
	
	public boolean isChoosed() {
		return choosed;
	}

	public void setChoosed(boolean choosed) {
		this.choosed = choosed;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getPurOrgUnitName() {
		return purOrgUnitName;
	}

	public void setPurOrgUnitName(String purOrgUnitName) {
		this.purOrgUnitName = purOrgUnitName;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	
	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getEditorName() {
		return editorName;
	}

	public void setEditorName(String editorName) {
		this.editorName = editorName;
	}

	public String getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}

	public String getBillTypeName() {
		return billTypeName;
	}

	public void setBillTypeName(String billTypeName) {
		this.billTypeName = billTypeName;
	}

	public String getPendingUsr() {
		return pendingUsr;
	}

	public void setPendingUsr(String pendingUsr) {
		this.pendingUsr = pendingUsr;
	}

	public String getPendingUsrName() {
		return pendingUsrName;
	}

	public void setPendingUsrName(String pendingUsrName) {
		this.pendingUsrName = pendingUsrName;
	}
	
	public BigDecimal getSaleTaxAmt() {
		return saleTaxAmt;
	}

	public void setSaleTaxAmt(BigDecimal saleTaxAmt) {
		this.saleTaxAmt = saleTaxAmt;
	}

	public List<ScmInvSaleOrderEntry2> getScmInvSaleOrderEntryList() {
		return scmInvSaleOrderEntryList;
	}

	public void setScmInvSaleOrderEntryList(
			List<ScmInvSaleOrderEntry2> scmInvSaleOrderEntryList) {
		this.scmInvSaleOrderEntryList = scmInvSaleOrderEntryList;
	}

	public ScmInvSaleOrder2(boolean defaultValue) {
		super(defaultValue);
	}

	public ScmInvSaleOrder2() {
		super();
	}
}
