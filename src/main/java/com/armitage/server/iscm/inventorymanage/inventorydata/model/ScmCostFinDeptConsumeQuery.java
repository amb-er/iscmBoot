package com.armitage.server.iscm.inventorymanage.inventorydata.model;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmCostFinDeptConsumeQuery extends BaseModel{
	
	private String finOrgUnitNo;
	private Date begDate;
	private Date endDate;
	private int summaryLevel;
	private String materialClass;
	private String costOrgUnitNos;
	private boolean fromApi;
	
	public String getFinOrgUnitNo() {
		return finOrgUnitNo;
	}

	public void setFinOrgUnitNo(String finOrgUnitNo) {
		this.finOrgUnitNo = finOrgUnitNo;
	}

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

	public int getSummaryLevel() {
		return summaryLevel;
	}

	public void setSummaryLevel(int summaryLevel) {
		this.summaryLevel = summaryLevel;
	}

	public String getMaterialClass() {
		return materialClass;
	}

	public void setMaterialClass(String materialClass) {
		this.materialClass = materialClass;
	}

	public String getCostOrgUnitNos() {
		return costOrgUnitNos;
	}

	public void setCostOrgUnitNos(String costOrgUnitNos) {
		this.costOrgUnitNos = costOrgUnitNos;
	}

	public boolean isFromApi() {
		return fromApi;
	}

	public void setFromApi(boolean fromApi) {
		this.fromApi = fromApi;
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
