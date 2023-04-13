package com.armitage.server.iscm.purchasemanage.pricemanage.model;

import java.math.BigDecimal;
import java.util.List;

public class ScmPurQuotation2 extends ScmPurQuotation{
	public static final String FN_VENDORNAME = "vendorName";
    public static final String FN_PRICE ="price";
    public static final String FN_ORGUNITNAME = "orgUnitNo";
    public static final String FN_BUYERCODE = "buyerCode";
    public static final String FN_BUYERNAME = "buyerName";
    public static final String FN_CREATORNAME = "creatorName";
    public static final String FN_EDITORNAME = "editorName";
    public static final String FN_CHECKERNAME = "checkerName";
    public static final String FN_STATUSNAME = "statusName";
    public static final String FN_PENDINGUSR = "pendingUsr";
    public static final String FN_PENDINGUSRNAME = "pendingUsrName";
	
	private String vendorName;
	private BigDecimal price;
	private String vendorCode;
	private String orgUnitName;
	private String buyerCode;
	private String buyerName;
	private String creatorName;
	private String editorName;
	private String checkerName;
	private String statusName;
	private String pendingUsr;
	private String pendingUsrName;
	private BigDecimal taxRate;
	private long itemId;

	private List<ScmPurQuotationEntry2> scmPurQuotationEntryList;

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public List<ScmPurQuotationEntry2> getScmPurQuotationEntryList() {
		return scmPurQuotationEntryList;
	}

	public void setScmPurQuotationEntryList(
			List<ScmPurQuotationEntry2> scmPurQuotationEntryList) {
		this.scmPurQuotationEntryList = scmPurQuotationEntryList;
	}

	public ScmPurQuotation2(boolean defaultValue) {
		super(defaultValue);
	}

	public ScmPurQuotation2() {
		super();
	}
}
