package com.armitage.server.iscm.inventorymanage.cstbusiness.model;

import java.math.BigDecimal;
import java.util.List;

public class ScmCstFrmLoss2 extends ScmCstFrmLoss {
	
	public static final String FN_CREATORNAME = "creatorName";
	public static final String FN_EDITORNAME = "editorName";
	public static final String FN_CHECKERNAME = "checkerName";
	public static final String FN_PENDINGUSR = "pendingUsr";
	public static final String FN_PENDINGUSRNAME = "pendingUsrName";
	public static final String FN_STATUSNAME = "statusName";
	public static final String FN_ORGUNITNAME = "orgUnitName";
	public static final String FN_COSTORGUNITNAME = "costOrgUnitName";
	public static final String FN_TOTALAMT = "totalAmt";
	public static final String FN_TOTALTAXAMT ="totalTaxAmt";
	public static final String FN_TAXAMOUNT = "taxAmount";
	public static final String FN_TASKID = "taskId";
    public static final String FN_FREEITEM = "freeItem";
	
	private String creatorName;
	private String editorName;
	private String checkerName;
	private String pendingUsr;
	private String pendingUsrName;
	private String statusName;
	private String orgUnitName;
	private String costOrgUnitName;
	private BigDecimal totalAmt;
	private BigDecimal totalTaxAmt;
	private BigDecimal taxAmount;
	private long taskId;
    private boolean freeItem;
	
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

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getOrgUnitName() {
		return orgUnitName;
	}

	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}

	public String getCostOrgUnitName() {
		return costOrgUnitName;
	}

	public void setCostOrgUnitName(String costOrgUnitName) {
		this.costOrgUnitName = costOrgUnitName;
	}

	public BigDecimal getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
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

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public boolean isFreeItem() {
		return freeItem;
	}

	public void setFreeItem(boolean freeItem) {
		this.freeItem = freeItem;
	}

	private List<ScmCstFrmLossEntry2> scmCstFrmLossEntryList;
	
	public List<ScmCstFrmLossEntry2> getScmCstFrmLossEntryList() {
		return scmCstFrmLossEntryList;
	}

	public void setScmCstFrmLossEntryList(
			List<ScmCstFrmLossEntry2> scmCstFrmLossEntryList) {
		this.scmCstFrmLossEntryList = scmCstFrmLossEntryList;
	}

	public ScmCstFrmLoss2(boolean defaultValue) {
        super(defaultValue);
    }

    public ScmCstFrmLoss2() {
        super();
    }
}
