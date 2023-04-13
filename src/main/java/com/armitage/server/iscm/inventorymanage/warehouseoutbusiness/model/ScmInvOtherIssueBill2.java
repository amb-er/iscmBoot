package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 

public class ScmInvOtherIssueBill2 extends ScmInvOtherIssueBill {
	public static final String FN_CHOOSED = "choosed";
	public static final String FN_AMT = "Amt";
	public static final String FN_NETAMT = "netAmt";
	public static final String FN_WAREHOUSECODE = "wareHouseCode";
	public static final String FN_WAREHOUSENAME = "wareHouseName";
	public static final String FN_BIZTYPENAME = "bizTypeName";
	public static final String FN_CREATORNAME = "creatorName";
	public static final String FN_EDITORNAME = "editorName";
	public static final String FN_CHECKERNAME = "checkerName";
	public static final String FN_STATUSNAME = "statusName";
	public static final String FN_TOTALAMT = "totalAmt";
	public static final String FN_TOTALTAXAMT = "totalTaxAmt";
	public static final String FN_PENDINGUSR = "pendingUsr";
	public static final String FN_PENDINGUSRNAME = "pendingUsrName";
	
	private boolean choosed;
	private BigDecimal amt;
	private BigDecimal netAmt;
	public String requireFields;
	private String wareHouseCode;
	private String wareHouseName;
	private String bizTypeName;
	private String creatorName;
	private String editorName;
	private String checkerName;
	private String statusName;
	private List<ScmInvOtherIssueBillEntry2> scmInvOtherIssueBillEntryList;
	private BigDecimal totalAmt;
	private BigDecimal totalTaxAmt;
	private String pendingUsr;
	private String pendingUsrName;
	private long taskId;
    private boolean freeItem;
	
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

	public void setRequiredFields(String requireFields) {
		this.requireFields = requireFields;
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

	public String getBizTypeName() {
		return bizTypeName;
	}

	public void setBizTypeName(String bizTypeName) {
		this.bizTypeName = bizTypeName;
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

	public List<ScmInvOtherIssueBillEntry2> getScmInvOtherIssueBillEntryList() {
		return scmInvOtherIssueBillEntryList;
	}

	public void setScmInvOtherIssueBillEntryList(
			List<ScmInvOtherIssueBillEntry2> scmInvOtherIssueBillEntryList) {
		this.scmInvOtherIssueBillEntryList = scmInvOtherIssueBillEntryList;
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

	@Override
	public String[] getRequiredFields() {
		StringBuffer reqFields = new StringBuffer(StringUtils.join(
				super.getRequiredFields(), ","));
		if (!StringUtils.isEmpty(requireFields)) {
			String[] requireField = StringUtils.split(requireFields, ",");
			String[] fieldNames = getFieldNames();
			for (String reqField : requireField) {
				for (String fiedName : fieldNames) {
					if (StringUtils.equalsIgnoreCase(reqField, fiedName)) {
						reqFields.append(",").append(fiedName);
						break;
					}
				}
			}
		}
		StringBuffer newReqFields = new StringBuffer("");
		String orgReqFields[] = StringUtils.split(reqFields.toString(), ",");
		for (int i = orgReqFields.length - 1; i >= 0; i--) {
			String filed = orgReqFields[i];
			if (StringUtils.isEmpty(newReqFields.toString())) {
				newReqFields.append(filed);
			} else {
				newReqFields.append(",").append(filed);
			}
		}
		return StringUtils.split(newReqFields.toString(), ",");
	}
	public ScmInvOtherIssueBill2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue){
			this.netAmt = BigDecimal.ZERO;
			this.amt = BigDecimal.ZERO;
		}
	}

	public ScmInvOtherIssueBill2() {
		super();
	}
}
