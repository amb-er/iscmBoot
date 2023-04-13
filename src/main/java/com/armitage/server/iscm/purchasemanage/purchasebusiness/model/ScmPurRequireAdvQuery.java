package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmPurRequireAdvQuery extends BaseModel{
    public static final String FN_REQORGUNITNO ="reqOrgUnitNo"; //申购组织
    public static final String FN_APPLICANTS = "applicants"; //采购员
    public static final String FN_VENDORID = "vendorId"; //供应商
    public static final String FN_REQDATEFROM = "reqDateFrom"; //需求日期起
    public static final String FN_REQDATETO = "reqDateTo"; //需求日期止
    public static final String FN_CREATEDATEFROM = "createDateFrom"; //创建日期起
    public static final String FN_CREATEDATETO = "createDateTo"; //创建日期止
    public static final String FN_PRNO = "prNo"; //请购单号
    public static final String FN_CREATOR = "creator"; //创建人
    public static final String FN_BIZTYPE = "bizType";  //采购类型

    private String prNo;
	private String reqOrgUnitNo;
	private String applicants;
	private Date reqDateFrom;
	private Date reqDateTo;
    private Date createDateFrom;
    private Date createDateTo;
	private String status;
	private String receiveWareHouseNo;	//接口传入的是编号
	private long receiveWareHouseId;
	private boolean fromInterface;
	private String creator;
	private String bizType;
    private String mixParam;		//混合查询条件
	private boolean	templete;
	
	public String getPrNo() {
		return prNo;
	}

	public void setPrNo(String prNo) {
		this.prNo = prNo;
	}

	public String getReqOrgUnitNo() {
		return reqOrgUnitNo;
	}

	public void setReqOrgUnitNo(String reqOrgUnitNo) {
		this.reqOrgUnitNo = reqOrgUnitNo;
	}

	public String getApplicants() {
		return applicants;
	}

	public void setApplicants(String applicants) {
		this.applicants = applicants;
	}

	public Date getReqDateFrom() {
		return reqDateFrom;
	}

	public void setReqDateFrom(Date reqDateFrom) {
		this.reqDateFrom = reqDateFrom;
	}

	public Date getReqDateTo() {
		return reqDateTo;
	}

	public void setReqDateTo(Date reqDateTo) {
		this.reqDateTo = reqDateTo;
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

	public String getReceiveWareHouseNo() {
		return receiveWareHouseNo;
	}

	public void setReceiveWareHouseNo(String receiveWareHouseNo) {
		this.receiveWareHouseNo = receiveWareHouseNo;
	}

	public long getReceiveWareHouseId() {
		return receiveWareHouseId;
	}

	public void setReceiveWareHouseId(long receiveWareHouseId) {
		this.receiveWareHouseId = receiveWareHouseId;
	}

	public boolean isFromInterface() {
		return fromInterface;
	}

	public void setFromInterface(boolean fromInterface) {
		this.fromInterface = fromInterface;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getMixParam() {
		return mixParam;
	}

	public void setMixParam(String mixParam) {
		this.mixParam = mixParam;
	}

	public boolean isTemplete() {
		return templete;
	}

	public void setTemplete(boolean templete) {
		this.templete = templete;
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
