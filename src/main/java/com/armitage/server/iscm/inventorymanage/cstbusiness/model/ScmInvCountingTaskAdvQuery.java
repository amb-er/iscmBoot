package com.armitage.server.iscm.inventorymanage.cstbusiness.model;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmInvCountingTaskAdvQuery extends BaseModel{
	public static final String FN_BIZDATEFROM = "bizDateFrom";
	public static final String FN_BIZDATETO = "bizDateTo";
    public static final String FN_CREATEDATEFROM = "createDateFrom"; //创建日期起
    public static final String FN_CREATEDATETO = "createDateTo"; //创建日期止
    public static final String FN_USEORGUNITNO ="useOrgUnitNo"; //盘存部门
    public static final String FN_WAREHOUSENO = "wareHouseNo";//盘点仓库

    private String taskNo;
	private Date bizDateFrom;
	private Date bizDateTo;
    private Date createDateFrom;
    private Date createDateTo;
	private boolean costCenter;
	private boolean fromInterface;
	private String useOrgUnitNo;
	private String wareHouseNo;
	
	public String getUseOrgUnitNo() {
		return useOrgUnitNo;
	}

	public void setUseOrgUnitNo(String useOrgUnitNo) {
		this.useOrgUnitNo = useOrgUnitNo;
	}

	public String getTaskNo() {
		return taskNo;
	}

	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}

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

	public boolean isCostCenter() {
		return costCenter;
	}

	public void setCostCenter(boolean costCenter) {
		this.costCenter = costCenter;
	}

	public boolean isFromInterface() {
		return fromInterface;
	}

	public void setFromInterface(boolean fromInterface) {
		this.fromInterface = fromInterface;
	}

	public String getWareHouseNo() {
		return wareHouseNo;
	}

	public void setWareHouseNo(String wareHouseNo) {
		this.wareHouseNo = wareHouseNo;
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
