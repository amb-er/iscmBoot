package com.armitage.server.iscm.inventorymanage.cstbusiness.model;

import java.util.Date;

public class ScmInvCountingCostCenterEntry2 extends ScmInvCountingCostCenterEntry{

	public static final String FN_ITEMNO = "itemNo";
	public static final String FN_ITEMNAME = "itemName";
	public static final String FN_SPEC = "spec";
	public static final String FN_GROUPNAME = "groupName";
	public static final String FN_UNITNAME = "unitName";
	public static final String FN_ORGUNITNO ="orgUnitNo";
	public static final String FN_USEORGUNITNO ="useOrgUnitNo";
    public static final String FN_TAXRATESTR = "taxRateStr";
    public static final String FN_GROUPID = "groupId";
    public static final String FN_GROUPCODE = "groupCode";
    public static final String FN_LONGNO = "longNo";
    public static final String FN_IDLECAUSE = "idleCause";
	private String finOrgUnitNo;
	private String itemNo;
	private String itemName;
	private String spec;
	private String groupCode;
   	private String groupName;
	private String unitName;
	private String orgUnitNo;
	private String useOrgUnitNo;
	private Date bizDate;
	private String taskNo;
	private String status;
	private Date postDate;
    private String taxRateStr;
    private String longNo;//物资所属类别级别串
    private long groupId;
    private long periodId;
    private String pieUnitName;
    private String idleCause;
    private long attachmentId;
    private String attachmentName;
    
    public String getFinOrgUnitNo() {
		return finOrgUnitNo;
	}

	public void setFinOrgUnitNo(String finOrgUnitNo) {
		this.finOrgUnitNo = finOrgUnitNo;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

    public String getLongNo() {
		return longNo;
	}

	public void setLongNo(String longNo) {
		this.longNo = longNo;
	}

	public String getUseOrgUnitNo() {
		return useOrgUnitNo;
	}

	public void setUseOrgUnitNo(String useOrgUnitNo) {
		this.useOrgUnitNo = useOrgUnitNo;
	}

	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Date getBizDate() {
		return bizDate;
	}

	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}

	public String getTaskNo() {
		return taskNo;
	}

	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public String getTaxRateStr() {
		return taxRateStr;
	}

	public void setTaxRateStr(String taxRateStr) {
		this.taxRateStr = taxRateStr;
	}

	public long getPeriodId() {
		return periodId;
	}

	public void setPeriodId(long periodId) {
		this.periodId = periodId;
	}

	public String getPieUnitName() {
		return pieUnitName;
	}

	public void setPieUnitName(String pieUnitName) {
		this.pieUnitName = pieUnitName;
	}

	public String getIdleCause() {
		return idleCause;
	}

	public void setIdleCause(String idleCause) {
		this.idleCause = idleCause;
	}

	public ScmInvCountingCostCenterEntry2() {
        super();
    }

	public long getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(long attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public ScmInvCountingCostCenterEntry2(boolean defaultValue) {
        super(defaultValue);
        if(defaultValue){
        	
        }
    }

}
