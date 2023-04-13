package com.armitage.server.iscm.basedata.model;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmIdleItemsAdvQuery extends BaseModel{
	public static final String FN_BIZBEGINDATE = "bizBeginDate";
	public static final String FN_BIZENDDATE = "bizEndDate";
	public static final String FN_ORGUNITNO = "orgUnitNo";
	public static final String FN_USEORGUNITNO = "useOrgUnitNo";
	public static final String FN_ITEMCLASSID = "itemClassId";
	public static final String FN_IDLESTATUS = "idleStatus";
	public static final String FN_NEWIDLEYSTATE = "newIdleState";
	
	private Date bizBeginDate;
	private Date bizEndDate;
	private String orgUnitNo;
	private String useOrgUnitNo;
	private long itemClassId;
	private String idleStatus;
	private String newIdleState;

	
	public Date getBizBeginDate() {
		return bizBeginDate;
	}

	public void setBizBeginDate(Date bizBeginDate) {
		this.bizBeginDate = bizBeginDate;
	}

	public Date getBizEndDate() {
		return bizEndDate;
	}

	public void setBizEndDate(Date bizEndDate) {
		this.bizEndDate = bizEndDate;
	}

	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}

	public String getUseOrgUnitNo() {
		return useOrgUnitNo;
	}

	public void setUseOrgUnitNo(String useOrgUnitNo) {
		this.useOrgUnitNo = useOrgUnitNo;
	}

	public long getItemClassId() {
		return itemClassId;
	}

	public void setItemClassId(long itemClassId) {
		this.itemClassId = itemClassId;
	}

	public String getIdleStatus() {
		return idleStatus;
	}

	public void setIdleStatus(String idleStatus) {
		this.idleStatus = idleStatus;
	}

	public String getNewIdleState() {
		return newIdleState;
	}

	public void setNewIdleState(String newIdleState) {
		this.newIdleState = newIdleState;
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
	
	public ScmIdleItemsAdvQuery() {
		
	}

	public ScmIdleItemsAdvQuery(boolean defaultValue) {
		if (defaultValue) {
		}
	}
}
