package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.armitage.server.iscm.common.model.ScmAuditDetailHistory2;

public class ScmInvMaterialRequestBillEntry2 extends ScmInvMaterialRequestBillEntry{
	public static final String FN_ITEMNO = "itemNo";
	public static final String FN_ITEMNAME = "itemName";
	public static final String FN_SPEC = "spec";
	public static final String FN_GROUPNAME = "groupName";
	public static final String FN_UNITNAME = "unitName";
	public static final String FN_CHOOSED = "choosed";
	public static final String FN_BASEUNITNAME = "baseUnitName";
	public static final String FN_PIEUNITNAME = "pieUnitName";
	
    private String otNo;
    private String bizType;
    private Date bizDate;
    private String creator;
	private String itemNo;
	private String itemName;
	private String spec;
	private String groupName;
	private String unitName;
	private boolean choosed;
	private String baseUnitName;
	private String classCode;
	private String opinion;
	private String rowAuditRemarks;
	private long groupId;
	private String pieUnitName;
	public String getPieUnitName() {
		return pieUnitName;
	}
	public void setPieUnitName(String pieUnitName) {
		this.pieUnitName = pieUnitName;
	}
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	private List<ScmAuditDetailHistory2> auditDetailHistoryList;
	
	public List<ScmAuditDetailHistory2> getAuditDetailHistoryList() {
		return auditDetailHistoryList;
	}
	public void setAuditDetailHistoryList(List<ScmAuditDetailHistory2> auditDetailHistoryList) {
		this.auditDetailHistoryList = auditDetailHistoryList;
	}
	public String getRowAuditRemarks() {
		return rowAuditRemarks;
	}
	public void setRowAuditRemarks(String rowAuditRemarks) {
		this.rowAuditRemarks = rowAuditRemarks;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public String getOtNo() {
		return otNo;
	}
	public void setOtNo(String otNo) {
		this.otNo = otNo;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public Date getBizDate() {
		return bizDate;
	}
	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getBaseUnitName() {
		return baseUnitName;
	}
	public void setBaseUnitName(String baseUnitName) {
		this.baseUnitName = baseUnitName;
	}
	public boolean isChoosed() {
		return choosed;
	}
	public void setChoosed(boolean choosed) {
		this.choosed = choosed;
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
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public ScmInvMaterialRequestBillEntry2(boolean defaultValue) {
		super(defaultValue);
		if (defaultValue) {
			
		}
	}

	public ScmInvMaterialRequestBillEntry2() {
		super();
	}
}
