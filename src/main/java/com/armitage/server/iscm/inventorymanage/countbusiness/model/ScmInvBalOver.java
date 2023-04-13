package com.armitage.server.iscm.inventorymanage.countbusiness.model;

import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmInvBalOver extends BaseModel{
	public static final String FN_ORGUNITNO = "orgUnitNo";
	public static final String FN_PERIODID = "periodId";
	public static final String FN_TYPE = "type";

	private String orgUnitNo;	//库存组织
	private long periodId;	//期间
	private String type;		//类型：1、结转；2、反结转
	
	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}

	public long getPeriodId() {
		return periodId;
	}

	public void setPeriodId(long periodId) {
		this.periodId = periodId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ScmInvBalOver() {
		super();
	}

	public ScmInvBalOver(boolean defaultValue) {
		if(defaultValue){
			type="1";
		}
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
