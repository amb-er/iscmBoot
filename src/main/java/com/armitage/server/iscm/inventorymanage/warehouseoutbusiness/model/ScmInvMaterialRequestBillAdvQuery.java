package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
 
@XmlRootElement(name = "scmInvMaterialRequestbBill")  
public class ScmInvMaterialRequestBillAdvQuery extends BaseModel  {
	public static final String FN_USEDEPTNO = "useDeptNo";
	public static final String FN_REQUESTPERSON = "requestPerson";
	public static final String FN_BIZDATEFROM = "bizDateFrom";
	public static final String FN_BIZDATETO = "bizDateTo";
    public static final String FN_CREATEDATEFROM = "createDateFrom"; //创建日期起
    public static final String FN_CREATEDATETO = "createDateTo"; //创建日期止
    public static final String FN_ORGUNITNO = "orgUnitNo";
    public static final String FN_CREATOR = "creator";
    public static final String FN_WAREHOUSEID = "wareHouseId";

    private String reqNo;
	private String useDeptNo;
	private String requestPerson;
	private Date bizDateFrom;
	private Date bizDateTo;
    private Date createDateFrom;
    private Date createDateTo;
	private String status;
	private boolean fromInterface;	//从接口调用
	private String orgUnitNo;
	private String creator;
	private long wareHouseId;
	
	public String getReqNo() {
		return reqNo;
	}

	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
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

	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}


	public long getWareHouseId() {
		return wareHouseId;
	}

	public void setWareHouseId(long wareHouseId) {
		this.wareHouseId = wareHouseId;
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

