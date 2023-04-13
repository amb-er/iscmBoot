package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;

import java.math.BigDecimal;
import java.util.List;

public class ScmInvMaterialRequestBill2 extends ScmInvMaterialRequestBill{
	private String useDeptName;	
	private String creatorName;
	private String editorName;
	private String checkerName;
	private String statusName;
	private String requestPersonName;
	private String invOrgUnitName;
	private String wareHouseNo;
	private String wareHouseName;
	private BigDecimal totalAmt;
	private BigDecimal totalTaxAmt;
	private BigDecimal taxAmount;
	private String pendingUsr;
	private String pendingUsrName;
	
	private List<ScmInvMaterialRequestBillEntry2> scmInvMaterialRequestBillEntryList;

	public String getUseDeptName() {
		return useDeptName;
	}

	public void setUseDeptName(String useDeptName) {
		this.useDeptName = useDeptName;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
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

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getRequestPersonName() {
		return requestPersonName;
	}

	public void setRequestPersonName(String requestPersonName) {
		this.requestPersonName = requestPersonName;
	}

	public String getInvOrgUnitName() {
		return invOrgUnitName;
	}

	public void setInvOrgUnitName(String invOrgUnitName) {
		this.invOrgUnitName = invOrgUnitName;
	}

	public String getWareHouseNo() {
		return wareHouseNo;
	}

	public void setWareHouseNo(String wareHouseNo) {
		this.wareHouseNo = wareHouseNo;
	}

	public String getWareHouseName() {
		return wareHouseName;
	}

	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
	}

	public BigDecimal getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
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

	public BigDecimal getTotalTaxAmt() {
		return totalTaxAmt;
	}

	public void setTotalTaxAmt(BigDecimal totalTaxAmt) {
		this.totalTaxAmt = totalTaxAmt;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public List<ScmInvMaterialRequestBillEntry2> getScmInvMaterialRequestBillEntryList() {
		return scmInvMaterialRequestBillEntryList;
	}

	public void setScmInvMaterialRequestBillEntryList(
			List<ScmInvMaterialRequestBillEntry2> scmInvMaterialRequestBillEntryList) {
		this.scmInvMaterialRequestBillEntryList = scmInvMaterialRequestBillEntryList;
	}

	public ScmInvMaterialRequestBill2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue){
		}
	}

	public ScmInvMaterialRequestBill2() {
		super();
	}
}
