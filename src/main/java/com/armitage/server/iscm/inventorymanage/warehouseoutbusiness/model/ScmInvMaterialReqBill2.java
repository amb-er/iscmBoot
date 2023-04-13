package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;

import java.math.BigDecimal;
import java.util.List;

public class ScmInvMaterialReqBill2 extends ScmInvMaterialReqBill{
	public static final String FN_CHOOSED = "choosed";
	public static final String FN_AMT = "Amt";
	public static final String FN_NETAMT = "netAmt";
	public static final String FN_COSTCENTERTYPE = "costCenterType";
	public static final String FN_TASKID = "taskId";
    public static final String FN_FREEITEM = "freeItem";
    public static final String FN_ORGUNITNAME = "orgUnitName";
    public static final String FN_WAREHOUSECODE = "wareHouseCode";
    public static final String FN_WAREHOUSENAME = "wareHouseName";
    public static final String FN_USEORGUNITNAME = "useOrgUnitName";
    public static final String FN_CREATORNAME = "creatorName";
    public static final String FN_EDITORNAME = "editorName";
    public static final String FN_CHECKERNAME = "checkerName";
    public static final String FN_STATUSNAME = "statusName";
    public static final String FN_BIZTYPENAME = "bizTypeName";
    public static final String FN_REQNO = "reqNo";
    public static final String FN_PENDINGUSR = "pendingUsr";
    public static final String FN_PENDINGUSRNAME = "pendingUsrName";
    public static final String FN_REQUESTPERSONNAME = "requestPersonName";

	private boolean choosed;
	private BigDecimal amt;
	private BigDecimal netAmt;
	private String costCenterType;
	private long taskId;
    private boolean freeItem;
    private BigDecimal taxAmount;
    private BigDecimal taxAmt;
    private String orgUnitName;
    private String wareHouseCode;
    private String wareHouseName;
    private String useOrgUnitName;
    private String creatorName;
    private String editorName;
    private BigDecimal typeAmt;
    private BigDecimal typeTaxAmt;
	private String checkerName;
    private String statusName;
    private String bizTypeName;
    private String reqNo;
    private String pendingUsr;
    private String pendingUsrName;
    private boolean post;
    private String requestPersonName;
    private List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryList;
    
	public boolean isPost() {
		return post;
	}

	public void setPost(boolean post) {
		this.post = post;
	}

	public String getCostCenterType() {
		return costCenterType;
	}

	public void setCostCenterType(String costCenterType) {
		this.costCenterType = costCenterType;
	}

	public boolean isChoosed() {
		return choosed;
	}

	public void setChoosed(boolean choosed) {
		this.choosed = choosed;
	}

	public BigDecimal getNetAmt() {
		return netAmt;
	}

	public void setNetAmt(BigDecimal netAmt) {
		this.netAmt = netAmt;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
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
	
	public String getOrgUnitName() {
		return orgUnitName;
	}

	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}
	
	public String getWareHouseCode() {
		return wareHouseCode;
	}

	public void setWareHouseCode(String wareHouseCode) {
		this.wareHouseCode = wareHouseCode;
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

	public String getBizTypeName() {
		return bizTypeName;
	}

	public void setBizTypeName(String bizTypeName) {
		this.bizTypeName = bizTypeName;
	}
	
	public String getReqNo() {
		return reqNo;
	}

	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
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

	public String getRequestPersonName() {
		return requestPersonName;
	}

	public void setRequestPersonName(String requestPersonName) {
		this.requestPersonName = requestPersonName;
	}

	public List<ScmInvMaterialReqBillEntry2> getScmInvMaterialReqBillEntryList() {
		return scmInvMaterialReqBillEntryList;
	}

	public void setScmInvMaterialReqBillEntryList(
			List<ScmInvMaterialReqBillEntry2> scmInvMaterialReqBillEntryList) {
		this.scmInvMaterialReqBillEntryList = scmInvMaterialReqBillEntryList;
	}

	public BigDecimal getTypeAmt() {
		return typeAmt;
	}

	public void setTypeAmt(BigDecimal typeAmt) {
		this.typeAmt = typeAmt;
	}

	public BigDecimal getTypeTaxAmt() {
		return typeTaxAmt;
	}

	public void setTypeTaxAmt(BigDecimal typeTaxAmt) {
		this.typeTaxAmt = typeTaxAmt;
	}

	public ScmInvMaterialReqBill2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue){
			this.netAmt = BigDecimal.ZERO;
			this.amt = BigDecimal.ZERO;
			this.post=false;
			this.taxAmount=BigDecimal.ZERO;
			this.amt=BigDecimal.ZERO;
		}
	}

	public ScmInvMaterialReqBill2() {
		super();
	}
}
