package com.armitage.server.iscm.inventorymanage.cstbusiness.model;

import java.math.BigDecimal;
import java.util.List;

public class ScmInvMoveBill2 extends ScmInvMoveBill{
    
    public static final String FN_INVQTY ="invQty";
    public static final String FN_TASKID = "taskId";
    public static final String FN_FREEITEM = "freeItem";
    public static final String FN_AMT = "amt";
    public static final String FN_TAXAMOUNT = "taxAmount";
    public static final String FN_TAXAMT = "taxAmt";
    public static final String FN_CREATEORGUNITNAME ="createOrgUnitName";
    public static final String FN_OUTORGUNITNAME = "outOrgUnitName";
    public static final String FN_INORTUNITNAME = "inOrgUnitName";
    public static final String FN_CREATORNAME = "creatorName";
    public static final String FN_EDITORNAME = "editorName";
    public static final String FN_CHECKERNAME = "checkerName";
    public static final String FN_STATUSNAME = "statusName";
    public static final String FN_PENDINGUSR = "pendingUsr";
    public static final String FN_PENDINGUSRNAME = "pendingUsrName";
    
    private BigDecimal qty;
    private BigDecimal invQty;
    private long taskId;
    private boolean freeItem;
    private String itemName;
    private BigDecimal amt;
    private BigDecimal taxAmount;
    private BigDecimal taxAmt;
    private String createOrgUnitName;
    private String outOrgUnitName;
    private String inOrgUnitName;
    private String creatorName;
    private String editorName;
    private String checkerName;
    private String statusName;
    private String pendingUsr;
    private String pendingUsrName;
    private List<ScmInvMoveBillEntry2> scmInvMoveBillEntryList;
    
    public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getInvQty() {
        return invQty;
    }

    public void setInvQty(BigDecimal invQty) {
        this.invQty = invQty;
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

    public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}
	
	public String getCreateOrgUnitName() {
		return createOrgUnitName;
	}

	public void setCreateOrgUnitName(String createOrgUnitName) {
		this.createOrgUnitName = createOrgUnitName;
	}

	public String getOutOrgUnitName() {
		return outOrgUnitName;
	}

	public void setOutOrgUnitName(String outOrgUnitName) {
		this.outOrgUnitName = outOrgUnitName;
	}

	public String getInOrgUnitName() {
		return inOrgUnitName;
	}

	public void setInOrgUnitName(String inOrgUnitName) {
		this.inOrgUnitName = inOrgUnitName;
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

	public List<ScmInvMoveBillEntry2> getScmInvMoveBillEntryList() {
		return scmInvMoveBillEntryList;
	}

	public void setScmInvMoveBillEntryList(
			List<ScmInvMoveBillEntry2> scmInvMoveBillEntryList) {
		this.scmInvMoveBillEntryList = scmInvMoveBillEntryList;
	}

	public ScmInvMoveBill2(boolean defaultValue) {
        super(defaultValue);
        if(defaultValue){
			this.amt = BigDecimal.ZERO;
			this.taxAmount = BigDecimal.ZERO;
			this.taxAmt = BigDecimal.ZERO;
		}
    }

    public ScmInvMoveBill2() {
        super();
    }
}
