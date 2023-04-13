package com.armitage.server.iscm.inventorymanage.cstbusiness.model;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmInvCostConsumeQuery extends BaseModel{

	
	public static final String FN_BIZDATEFROM = "bizDateFrom";
	public static final String FN_BIZDATETO = "bizDateTo";
    public static final String FN_CREATEDATEFROM = "createDateFrom"; //创建日期起
    public static final String FN_CREATEDATETO = "createDateTo"; //创建日期止
    public static final String FN_COSTORGUNITNO = "costOrgUnitNo";

	private Date bizDateFrom;
	private Date bizDateTo;
    private Date createDateFrom;
    private Date createDateTo;
	private String costOrgUnitNo;

	public Date getBizDateFrom() {
		return bizDateFrom;
	}

	public void setBizDateFrom(Date bizDateFrom) {
		this.bizDateFrom = bizDateFrom;
	}

	public Date getBizDateTo() {
		return bizDateTo;
	}

	public void setBizDateTo(Date bizDateTo) {
		this.bizDateTo = bizDateTo;
	}

	public Date getCreateDateFrom() {
		return createDateFrom;
	}

	public void setCreateDateFrom(Date createDateFrom) {
		this.createDateFrom = createDateFrom;
	}

	public Date getCreateDateTo() {
		return createDateTo;
	}

	public void setCreateDateTo(Date createDateTo) {
		this.createDateTo = createDateTo;
	}

	public String getCostOrgUnitNo() {
		return costOrgUnitNo;
	}

	public void setCostOrgUnitNo(String costOrgUnitNo) {
		this.costOrgUnitNo = costOrgUnitNo;
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
