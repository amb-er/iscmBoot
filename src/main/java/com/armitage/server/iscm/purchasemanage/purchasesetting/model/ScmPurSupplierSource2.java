package com.armitage.server.iscm.purchasemanage.purchasesetting.model;

import java.util.List;

public class ScmPurSupplierSource2 extends ScmPurSupplierSource{

    public static final String FN_PENDINGUSR = "pendingUsr";
    public static final String FN_PENDINGUSRNAME = "pendingUsrName";
    public static final String FN_VENDORNAME = "vendorName";
	
	private String pendingUsr;
	private String pendingUsrName;
	private String vendorCode;
	private String vendorName;
	private String orgUnitName;
	private String buyerCode;
	private String buyerName;
	private String creatorName;
	private String editorName;
	private String checkerName;
	private String statusName;
	private List<ScmPurSupplierSourceEntry2> scmPurSupplierSourceEntryList;
	private List<ScmPurSupplierSourceRecOrg2> scmPurSupplierSourceRecOrgList;
	
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
	
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getOrgUnitName() {
		return orgUnitName;
	}
	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}
	public String getBuyerCode() {
		return buyerCode;
	}
	public void setBuyerCode(String buyerCode) {
		this.buyerCode = buyerCode;
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
	public List<ScmPurSupplierSourceEntry2> getScmPurSupplierSourceEntryList() {
		return scmPurSupplierSourceEntryList;
	}
	public void setScmPurSupplierSourceEntryList(List<ScmPurSupplierSourceEntry2> scmPurSupplierSourceEntryList) {
		this.scmPurSupplierSourceEntryList = scmPurSupplierSourceEntryList;
	}
	public List<ScmPurSupplierSourceRecOrg2> getScmPurSupplierSourceRecOrgList() {
		return scmPurSupplierSourceRecOrgList;
	}
	public void setScmPurSupplierSourceRecOrgList(List<ScmPurSupplierSourceRecOrg2> scmPurSupplierSourceRecOrgList) {
		this.scmPurSupplierSourceRecOrgList = scmPurSupplierSourceRecOrgList;
	}
	public ScmPurSupplierSource2() {
		super();
	}
	public ScmPurSupplierSource2(boolean defaultValue) {
		super(defaultValue);
	}
}
