package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import java.math.BigDecimal;
import java.util.List;

public class ScmPurReceive2 extends ScmPurReceive {
	public static final String FN_CHOOSED = "choosed";
	public static final String FN_WAREHOUSENAME = "wareHouseName";
	public static final String FN_USEORGUNITNAME = "useOrgUnitName";
	public static final String FN_PENDINGUSRNAME = "pendingUsrName";
	public static final String FN_TAXAMOUNT = "taxAmount";

	private boolean choosed;
	private String statusName;
	private String vendorNo;
	private String vendorName;
	private String receiverName;
	private String purOrgUnitName;
	private String buyerCode;
	private String buyerName;
	private String creatorName;
	private String wareHouseName;
	private String useOrgUnitName;
	private String pendingUsrName;
	private BigDecimal taxAmount;
	private String checkerName;
	private int editType;//0:没做过任何修改 1：修改金额  2：修改数量 3:修改单价
	private List<ScmPurReceiveEntry2> scmPurReceiveEntryList;
	
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

	public String getVendorNo() {
		return vendorNo;
	}

	public void setVendorNo(String vendorNo) {
		this.vendorNo = vendorNo;
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

	public String getWareHouseName() {
		return wareHouseName;
	}

	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
	}

	public String getUseOrgUnitName() {
		return useOrgUnitName;
	}

	public void setUseOrgUnitName(String useOrgUnitName) {
		this.useOrgUnitName = useOrgUnitName;
	}

	public String getPendingUsrName() {
		return pendingUsrName;
	}

	public void setPendingUsrName(String pendingUsrName) {
		this.pendingUsrName = pendingUsrName;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public String getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}

	public int getEditType() {
		return editType;
	}

	public void setEditType(int editType) {
		this.editType = editType;
	}

	public List<ScmPurReceiveEntry2> getScmPurReceiveEntryList() {
		return scmPurReceiveEntryList;
	}

	public void setScmPurReceiveEntryList(
			List<ScmPurReceiveEntry2> scmPurReceiveEntryList) {
		this.scmPurReceiveEntryList = scmPurReceiveEntryList;
	}

	public ScmPurReceive2(boolean defaultValue) {
		super(defaultValue);
		editType = 0;
	}

	public ScmPurReceive2() {
		super();
	}
}
