package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmPurReceiveAdvQuery extends BaseModel{
	public static final String FN_VENDORID = "vendorId";
	public static final String FN_PURORGUNITNO = "purOrgUnitNo";
	public static final String FN_RECDATEFROM = "recDateFrom";
	public static final String FN_RECDATETO = "recDateTo";
    public static final String FN_CREATEDATEFROM = "createDateFrom"; //创建日期起
    public static final String FN_CREATEDATETO = "createDateTo"; //创建日期止
    public static final String FN_DEPTORWAREHS = "deptOrWarehs";
    public static final String FN_STORETYPE = "storeType";
    public static final String FN_PONO = "poNo";
	
	private String pvNo;
	private String purOrgUnitNo;
	private long vendorId;
	private String vendorCode;
	private String receiver;
	private Date recDateFrom;
	private Date recDateTo;
    private Date createDateFrom;
    private Date createDateTo;
	private String status;
	private boolean fromInterface;
	private String storeType;
	private String deptOrWarehs;
	private String poNo;
	
	public String getPvNo() {
		return pvNo;
	}

	public void setPvNo(String pvNo) {
		this.pvNo = pvNo;
	}

	public String getPurOrgUnitNo() {
		return purOrgUnitNo;
	}

	public void setPurOrgUnitNo(String purOrgUnitNo) {
		this.purOrgUnitNo = purOrgUnitNo;
	}

	public long getVendorId() {
		return vendorId;
	}

	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public Date getRecDateFrom() {
		return recDateFrom;
	}

	public void setRecDateFrom(Date recDateFrom) {
		this.recDateFrom = recDateFrom;
	}

	public Date getRecDateTo() {
		return recDateTo;
	}

	public void setRecDateTo(Date recDateTo) {
		this.recDateTo = recDateTo;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isFromInterface() {
		return fromInterface;
	}

	public void setFromInterface(boolean fromInterface) {
		this.fromInterface = fromInterface;
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

	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
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
