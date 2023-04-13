package com.armitage.server.iscm.inventorymanage.inventorydata.model;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmDeptConsumeQuery extends BaseModel{
	
	private Date begDate;
	private Date endDate;
	private String finOrgUnitNo;
	private int summaryLevel;
	private String type;
	
	public Date getBegDate() {
		return begDate;
	}

	public void setBegDate(Date begDate) {
		this.begDate = begDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFinOrgUnitNo() {
		return finOrgUnitNo;
	}

	public void setFinOrgUnitNo(String finOrgUnitNo) {
		this.finOrgUnitNo = finOrgUnitNo;
	}

	public int getSummaryLevel() {
		return summaryLevel;
	}

	public void setSummaryLevel(int summaryLevel) {
		this.summaryLevel = summaryLevel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getPkKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getPK() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String[] getRequiredFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getFieldNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String[]> getUniqueKeys() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
