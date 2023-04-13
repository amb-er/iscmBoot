package com.armitage.server.iscm.inventorymanage.AllocationApplication.model;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 

@XmlRootElement(name = "scmInvMoveReqBill")  
public class ScmInvMoveReqBill extends BaseModel {
	public static final String FN_REQID = "reqId";
	public static final String FN_REQNO = "reqNo";
	public static final String FN_BIZTYPE = "bizType";
	public static final String FN_BIZDATE = "bizDate";
	public static final String FN_FINORGUNITNO = "finOrgUnitNo";
	public static final String FN_REQORGUNITNO = "reqOrgUnitNo";
	public static final String FN_OUTORGUNITNO = "outOrgUnitNo";
	public static final String FN_CURRENCYNO = "currencyNo";
	public static final String FN_EXCHANGERATE = "exchangeRate";
	public static final String FN_PERIODID = "periodId";
	public static final String FN_ACCOUNTYEAR = "accountYear";
	public static final String FN_ACCOUNTPERIOD = "accountPeriod";
	public static final String FN_CREATOR = "creator";
	public static final String FN_CREATEDATE = "createDate";
    public static final String FN_SUBMITTER ="submitter";
    public static final String FN_SUBMITDATE ="submitDate";
	public static final String FN_EDITOR = "editor";
	public static final String FN_EDITDATE = "editDate";
	public static final String FN_CHECKER = "checker";
	public static final String FN_CHECKDATE = "checkDate";
	public static final String FN_PRTCOUNT = "prtcount";
	public static final String FN_STATUS = "status";
	public static final String FN_REMARKS = "remarks";
	public static final String FN_CONTROLUNITNO = "controlUnitNo";
	public static final String FN_REQWAREHOUSEID = "reqWareHouseId";
	public static final String FN_WORKFLOWNO = "workFlowNo";
	public static final String FN_STEPNO = "stepNo";
	
	private long reqId;
	private String reqNo;
	private String bizType;
	private Date bizDate;
	private String finOrgUnitNo;
	private String reqOrgUnitNo;
	private String outOrgUnitNo;
	private String currencyNo;
	private BigDecimal exchangeRate;
	private long periodId;
	private int accountYear;
	private int accountPeriod;
	private String creator;
	private Date createDate;
    private String submitter;
    private Date submitDate;
	private String editor;
	private Date editDate;
	private String checker;
	private Date checkDate;
	private int prtcount;
	private String status;
	private String remarks;
	private String controlUnitNo;
	private long reqWareHouseId;
	private String workFlowNo;
	private String stepNo;

	public long getReqId() {
		return reqId;
	}

	public void setReqId(long val) {
		this.reqId = val;
	}
	public String getReqNo() {
		return reqNo;
	}

	public void setReqNo(String val) {
		this.reqNo = val;
	}
	public String getBizType() {
		return bizType;
	}

	public void setBizType(String val) {
		this.bizType = val;
	}
	public Date getBizDate() {
		return bizDate;
	}

	public void setBizDate(Date val) {
		this.bizDate = val;
	}
	public String getFinOrgUnitNo() {
		return finOrgUnitNo;
	}

	public void setFinOrgUnitNo(String val) {
		this.finOrgUnitNo = val;
	}
	public String getReqOrgUnitNo() {
		return reqOrgUnitNo;
	}

	public void setReqOrgUnitNo(String val) {
		this.reqOrgUnitNo = val;
	}
	public String getOutOrgUnitNo() {
		return outOrgUnitNo;
	}

	public void setOutOrgUnitNo(String val) {
		this.outOrgUnitNo = val;
	}
	public String getCurrencyNo() {
		return currencyNo;
	}

	public void setCurrencyNo(String val) {
		this.currencyNo = val;
	}
	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(BigDecimal val) {
		this.exchangeRate = val;
	}
	public long getPeriodId() {
		return periodId;
	}

	public void setPeriodId(long val) {
		this.periodId = val;
	}
	public int getAccountYear() {
		return accountYear;
	}

	public void setAccountYear(int val) {
		this.accountYear = val;
	}
	public int getAccountPeriod() {
		return accountPeriod;
	}

	public void setAccountPeriod(int val) {
		this.accountPeriod = val;
	}
	public String getCreator() {
		return creator;
	}

	public void setCreator(String val) {
		this.creator = val;
	}
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date val) {
		this.createDate = val;
	}
	public String getSubmitter() {
		return submitter;
	}

	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String val) {
		this.editor = val;
	}
	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date val) {
		this.editDate = val;
	}
	public String getChecker() {
		return checker;
	}

	public void setChecker(String val) {
		this.checker = val;
	}
	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date val) {
		this.checkDate = val;
	}
	public int getPrtcount() {
		return prtcount;
	}

	public void setPrtcount(int val) {
		this.prtcount = val;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String val) {
		this.status = val;
	}
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String val) {
		this.remarks = val;
	}
	public String getControlUnitNo() {
		return controlUnitNo;
	}

	public void setControlUnitNo(String val) {
		this.controlUnitNo = val;
	}
	
	public long getReqWareHouseId() {
		return reqWareHouseId;
	}

	public void setReqWareHouseId(long reqWareHouseId) {
		this.reqWareHouseId = reqWareHouseId;
	}

	public String getWorkFlowNo() {
		return workFlowNo;
	}

	public void setWorkFlowNo(String workFlowNo) {
		this.workFlowNo = workFlowNo;
	}

	public String getStepNo() {
		return stepNo;
	}

	public void setStepNo(String stepNo) {
		this.stepNo = stepNo;
	}

	public ScmInvMoveReqBill(boolean defaultValue) {
		if (defaultValue) {
			this.finOrgUnitNo="";
			this.currencyNo="";
			this.exchangeRate=BigDecimal.ONE;
			this.periodId=0;
			this.prtcount=0;
		}
	}
  	public ScmInvMoveReqBill() {
	}

	public String[] getFieldNames(){
		return new String[]{
			FN_REQID,
			FN_REQNO,
			FN_BIZTYPE,
			FN_BIZDATE,
			FN_FINORGUNITNO,
			FN_REQORGUNITNO,
			FN_REQWAREHOUSEID,
			FN_OUTORGUNITNO,
			FN_CURRENCYNO,
			FN_EXCHANGERATE,
			FN_PERIODID,
			FN_ACCOUNTYEAR,
			FN_ACCOUNTPERIOD,
			FN_CREATOR,
			FN_CREATEDATE,
			FN_EDITOR,
			FN_EDITDATE,
			FN_CHECKER,
			FN_CHECKDATE,
			FN_PRTCOUNT,
			FN_STATUS,
			FN_REMARKS,
			FN_CONTROLUNITNO,
			FN_WORKFLOWNO,
			FN_STEPNO
		};
	}
	
	public Map<String, RelationModel> getForeignMap() {
		/*
		DEMO:
		HashMap<String, RelationModel> map = new HashMap<String, RelationModel>();
		map.put(FN_STATUS, new RelationModel(Code.class, Code.FN_CODE)
				.addFilter(Code.FN_CATEGORY, "UserStatus"));
		return map;
		*/
		return null;
	}
	
	public List<String[]> getUniqueKeys() {
		List<String[]> list = new ArrayList<String[]>();
		//list.add(new String[] { FN_CODE, FN_CATEGORY });
		return list;
	}
	 
	public String[] getRequiredFields() {
		/*DEMO:
		return new String[] {FN_CODE };
		*/
		return new String[] {FN_BIZDATE,FN_OUTORGUNITNO,FN_REQWAREHOUSEID};
	}
 
	public Map<String, List<RelationModel>> getReferenceMap() {
		/*
		DEMO:
		HashMap<String, List<RelationModel>> map = new HashMap<String, List<RelationModel>>();

		List<RelationModel> list = new ArrayList<RelationModel>();
		list.add(new RelationModel(Code.class, Code.FN_CODE).addFilter(
				Code.FN_CATEGORY, "UserStatus"));

		map.put(this.FN_STATUS, list);
		return map;*/
		
		return null;
	}
	
	public Map<String, Integer> getDataLengthMap() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();  
		map.put(FN_REQNO, 32);
		map.put(FN_BIZTYPE, 16);
		map.put(FN_FINORGUNITNO, 32);
		map.put(FN_REQORGUNITNO, 32);
		map.put(FN_OUTORGUNITNO, 32);
		map.put(FN_CURRENCYNO, 16);
		map.put(FN_CREATOR, 16);
		map.put(FN_EDITOR, 16);
		map.put(FN_CHECKER, 16);
		map.put(FN_STATUS, 16);
		map.put(FN_REMARKS, 250);
		map.put(FN_CONTROLUNITNO, 32);
		map.put(FN_WORKFLOWNO, 64);
		map.put(FN_STEPNO, 32);
		return map; 
	}
	
	public String getPkKey() {
		return FN_REQID;
	}
 
	public long getPK() {
		return reqId;
	}
}
