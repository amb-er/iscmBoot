package com.armitage.server.iscm.inventorymanage.countbusiness.model;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class ScmInvCountingTable2 extends ScmInvCountingTable{
	public static final String FN_TASKNO = "taskNo";
	public static final String FN_WHNAME = "whName";
	
	public String requireFields;
	
	private String taskNo;
	private String wareHouseNo;
	private String whName;
	private String countingPerson;
	private String countingPersonName;
	private String countingAgainPerson;
	private String countingAgainPersonName;
	private String countingMonitorer;
	private String countingMonitorerName;
	private String creatorName;
	private BigDecimal amt;
	private BigDecimal taxAmt;
	
	private List<ScmInvCountingTableEntry2> scmInvCountingTableEntryList;
	
	public String getTaskNo() {
		return taskNo;
	}

	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}

	public String getWareHouseNo() {
		return wareHouseNo;
	}

	public void setWareHouseNo(String wareHouseNo) {
		this.wareHouseNo = wareHouseNo;
	}

	public String getWhName() {
		return whName;
	}

	public void setWhName(String whName) {
		this.whName = whName;
	}

	public String getCountingPerson() {
		return countingPerson;
	}

	public void setCountingPerson(String countingPerson) {
		this.countingPerson = countingPerson;
	}

	public String getCountingPersonName() {
		return countingPersonName;
	}

	public void setCountingPersonName(String countingPersonName) {
		this.countingPersonName = countingPersonName;
	}

	public String getCountingAgainPerson() {
		return countingAgainPerson;
	}

	public void setCountingAgainPerson(String countingAgainPerson) {
		this.countingAgainPerson = countingAgainPerson;
	}

	public String getCountingAgainPersonName() {
		return countingAgainPersonName;
	}

	public void setCountingAgainPersonName(String countingAgainPersonName) {
		this.countingAgainPersonName = countingAgainPersonName;
	}

	public String getCountingMonitorer() {
		return countingMonitorer;
	}

	public void setCountingMonitorer(String countingMonitorer) {
		this.countingMonitorer = countingMonitorer;
	}

	public String getCountingMonitorerName() {
		return countingMonitorerName;
	}

	public void setCountingMonitorerName(String countingMonitorerName) {
		this.countingMonitorerName = countingMonitorerName;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}

	public List<ScmInvCountingTableEntry2> getScmInvCountingTableEntryList() {
		return scmInvCountingTableEntryList;
	}

	public void setScmInvCountingTableEntryList(
			List<ScmInvCountingTableEntry2> scmInvCountingTableEntryList) {
		this.scmInvCountingTableEntryList = scmInvCountingTableEntryList;
	}
	public void setRequiredFields(String requireFields) {
		this.requireFields = requireFields;
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
	
	public ScmInvCountingTable2(boolean defaultValue) {
		super(defaultValue);
	}

	public ScmInvCountingTable2() {
		super();
	}
}
