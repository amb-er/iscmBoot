package com.armitage.server.iscm.inventorymanage.cstbusiness.model;

import java.math.BigDecimal;
import java.util.List;

public class ScmInvCountingCostCenter2 extends ScmInvCountingCostCenter{

	public static final String FN_TASKNO = "taskNo";
	public static final String FN_USERORGUNITNAME = "userOrgUnitName";
	
	private String taskNo;
	private String countingPerson;
	private String countingPersonName;
	private String countingAgainPerson;
	private String countingAgainPersonName;
	private String countingMonitorer;
	private String countingMonitorerName;
	private String creatorName;
	private String userOrgUnitName;
	private BigDecimal amt;
	private BigDecimal taxAmt;
	private List<ScmInvCountingCostCenterEntry2> scmInvCountingCostCenterEntryList;

	public String getTaskNo() {
		return taskNo;
	}

	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
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

	public String getUserOrgUnitName() {
		return userOrgUnitName;
	}

	public void setUserOrgUnitName(String userOrgUnitName) {
		this.userOrgUnitName = userOrgUnitName;
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

	public List<ScmInvCountingCostCenterEntry2> getScmInvCountingCostCenterEntryList() {
		return scmInvCountingCostCenterEntryList;
	}

	public void setScmInvCountingCostCenterEntryList(
			List<ScmInvCountingCostCenterEntry2> scmInvCountingCostCenterEntryList) {
		this.scmInvCountingCostCenterEntryList = scmInvCountingCostCenterEntryList;
	}

	public ScmInvCountingCostCenter2() {
        super();
    }

    public ScmInvCountingCostCenter2(boolean defaultValue) {
        super(defaultValue);
    }
    

    
    
}
