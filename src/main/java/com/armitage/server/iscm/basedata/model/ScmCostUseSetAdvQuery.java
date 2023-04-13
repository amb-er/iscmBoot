package com.armitage.server.iscm.basedata.model;

import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmCostUseSetAdvQuery extends BaseModel{
	public static final String FN_CLASSID = "classId";//物资类别
	public static final String FN_SCMCOSTUSETYPEID = "scmCostUseTypeId";//成本用途
	public static final String FN_COSTORGUNITNO = "costOrgUnitNo"; //成本中心
    public static final String FN_SIZER = "sizer"; //筛选
	public static final String FN_SCMCOSTUSETYPENAME = "scmCostUseTypeName"; //成本用途名称
	public static final String FN_STANDBY = "standby";//备用字段
	
	private long classId;
	private long scmCostUseTypeId;
	private String costOrgUnitNo;
	private String sizer;
	private String scmCostUseTypeName;
	private String standby;
	



	public long getClassId() {
		return classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
	}

	public long getScmCostUseTypeId() {
		return scmCostUseTypeId;
	}

	public void setScmCostUseTypeId(long scmCostUseTypeId) {
		this.scmCostUseTypeId = scmCostUseTypeId;
	}

	public String getCostOrgUnitNo() {
		return costOrgUnitNo;
	}

	public void setCostOrgUnitNo(String costOrgUnitNo) {
		this.costOrgUnitNo = costOrgUnitNo;
	}

	public String getSizer() {
		return sizer;
	}

	public void setSizer(String sizer) {
		this.sizer = sizer;
	}

	public String getScmCostUseTypeName() {
		return scmCostUseTypeName;
	}

	public void setScmCostUseTypeName(String scmCostUseTypeName) {
		this.scmCostUseTypeName = scmCostUseTypeName;
	}

	public String getStandby() {
		return standby;
	}

	public void setStandby(String standby) {
		this.standby = standby;
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
