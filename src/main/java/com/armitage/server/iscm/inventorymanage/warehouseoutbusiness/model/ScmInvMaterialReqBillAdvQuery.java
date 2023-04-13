package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmInvMaterialReqBillAdvQuery extends BaseModel{
	public static final String FN_WAREHOUSEID = "wareHouseId";
	public static final String FN_USEORGUNITNO = "useOrgUnitNo";
	public static final String FN_BIZDATEFROM = "bizDateFrom";
	public static final String FN_BIZDATETO = "bizDateTo";
    public static final String FN_CREATEDATEFROM = "createDateFrom"; //创建日期起
    public static final String FN_CREATEDATETO = "createDateTo"; //创建日期止
	
    private long wareHouseId;
	private String useOrgUnitNo;
	private Date bizDateFrom;
	private Date bizDateTo;
    private Date createDateFrom;
    private Date createDateTo;
    
    private String reqBillNo;//领料出库单号
    private String wareHouseNo;//领料仓库
    private String useDeptNo;//领料部门
    private String requestPerson;//领料人
    private String status;//状态
    private String bizType;//业务类型
	
	public long getWareHouseId() {
		return wareHouseId;
	}

	public void setWareHouseId(long wareHouseId) {
		this.wareHouseId = wareHouseId;
	}

	public String getUseOrgUnitNo() {
		return useOrgUnitNo;
	}

	public void setUseOrgUnitNo(String useOrgUnitNo) {
		this.useOrgUnitNo = useOrgUnitNo;
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

	public String getReqBillNo() {
		return reqBillNo;
	}

	public void setReqBillNo(String reqBillNo) {
		this.reqBillNo = reqBillNo;
	}

	public String getWareHouseNo() {
		return wareHouseNo;
	}

	public void setWareHouseNo(String wareHouseNo) {
		this.wareHouseNo = wareHouseNo;
	}

	public String getUseDeptNo() {
		return useDeptNo;
	}

	public void setUseDeptNo(String useDeptNo) {
		this.useDeptNo = useDeptNo;
	}

	public String getRequestPerson() {
		return requestPerson;
	}

	public void setRequestPerson(String requestPerson) {
		this.requestPerson = requestPerson;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
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
