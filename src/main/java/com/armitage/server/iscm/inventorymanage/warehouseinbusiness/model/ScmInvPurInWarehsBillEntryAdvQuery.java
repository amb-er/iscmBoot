package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmInvPurInWarehsBillEntryAdvQuery extends BaseModel{
	public static final String FN_VENDORID = "vendorId";
	public static final String FN_CLASSID = "classId";
	public static final String FN_BIZDATEFROM = "bizDateFrom";
	public static final String FN_BIZDATETO = "bizDateTo";
    public static final String FN_CREATEDATEFROM = "createDateFrom"; //创建日期起
    public static final String FN_CREATEDATETO = "createDateTo"; //创建日期止
    public static final String FN_BIZTYPE = "bizType"; //创建日期止
    public static final String FN_DEPTORWAREHS = "deptOrWarehs"; //创建日期止
    public static final String FN_ITEMID = "itemId"; //创建日期止
	
	private String classId;
	private long vendorId;
	private Date bizDateFrom;
	private Date bizDateTo;
    private Date createDateFrom;
    private Date createDateTo;
	private String bizType;
	private String storeType;
	private String deptOrWarehs;
	private String itemId;
	
	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public long getVendorId() {
		return vendorId;
	}

	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
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

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

	public String getDeptOrWarehs() {
		return deptOrWarehs;
	}

	public void setDeptOrWarehs(String deptOrWarehs) {
		this.deptOrWarehs = deptOrWarehs;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
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
