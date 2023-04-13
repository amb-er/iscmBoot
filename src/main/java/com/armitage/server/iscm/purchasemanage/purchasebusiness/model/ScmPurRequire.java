package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 

@XmlRootElement(name = "scmPurRequire")  
public class ScmPurRequire extends BaseModel {
	public static final String FN_ID = "id";
	public static final String FN_PRNO = "prNo";
	public static final String FN_ORGUNITNO = "orgUnitNo";
	public static final String FN_APPLICANTS = "applicants";
	public static final String FN_APPLYDATE = "applyDate";
	public static final String FN_REQDATE = "reqDate";
	public static final String FN_BIZTYPE = "bizType";
	public static final String FN_BILLTYPE = "billType";
	public static final String FN_FINORGUNITNO = "finOrgUnitNo";
	public static final String FN_TOWAREHOUSE = "toWareHouse";
	public static final String FN_RECEIVEWAREHOUSEID = "receiveWareHouseId";
	public static final String FN_PURORGUNITNO = "purOrgUnitNo";
	public static final String FN_CURRENCYNO = "currencyNo";
	public static final String FN_EXCHANGERATE = "exchangeRate";
	public static final String FN_TAXAMT = "taxAmt";
	public static final String FN_CREATOR = "creator";
	public static final String FN_CREATEDATE = "createDate";
    public static final String FN_SUBMITTER ="submitter";
    public static final String FN_SUBMITDATE ="submitDate";
	public static final String FN_EDITOR = "editor";
	public static final String FN_EDITDATE = "editDate";
	public static final String FN_CHECKER = "checker";
	public static final String FN_CHECKDATE = "checkDate";
	public static final String FN_PRTCOUNT = "prtcount";
	public static final String FN_REQNO = "reqNo";
	public static final String FN_STATUS = "status";
	public static final String FN_REMARKS = "remarks";
	public static final String FN_CONTROLUNITNO = "controlUnitNo";
	public static final String FN_WORKFLOWNO = "workFlowNo";
	public static final String FN_TEMPLETE = "templete";
	public static final String FN_STEPNO = "stepNo";
	public static final String FN_OTHERAUDITNO = "otherAuditNo";
	public static final String FN_OUTAUDITTYPE = "outAuditType";
	public static final String FN_SUBSCRIBEREASON = "subscribeReason";
	public static final String FN_PURREQUIRETHEME = "purRequireTheme";
	
	private long id;
	private String prNo;
	private String orgUnitNo;
	private String applicants;
	private Date applyDate;
	private Date reqDate;
	private String bizType;
	private String billType;
	private String finOrgUnitNo;
	private boolean toWareHouse;
	private long receiveWareHouseId;
	private String purOrgUnitNo;
	private String currencyNo;
	private BigDecimal exchangeRate;
	private BigDecimal taxAmt;
	private String creator;
	private Date createDate;
    private String submitter;
    private Date submitDate;
	private String editor;
	private Date editDate;
	private String checker;
	private Date checkDate;
	private int prtcount;
	private String reqNo;
	private String status;
	private String remarks;
	private String controlUnitNo;
	private String workFlowNo;
	private boolean templete;
	private String stepNo;
	private String otherAuditNo;
	private String outAuditType;
	private String subscribeReason;
	private String purRequireTheme;
	
	public long getId() {
		return id;
	}

	public void setId(long val) {
		this.id = val;
	}
	public String getPrNo() {
		return prNo;
	}

	public void setPrNo(String val) {
		this.prNo = val;
	}
	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String val) {
		this.orgUnitNo = val;
	}
	public String getApplicants() {
		return applicants;
	}

	public void setApplicants(String val) {
		this.applicants = val;
	}
	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date val) {
		this.applyDate = val;
	}
	public Date getReqDate() {
		return reqDate;
	}

	public void setReqDate(Date val) {
		this.reqDate = val;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String val) {
		this.billType = val;
	}
	public String getFinOrgUnitNo() {
		return finOrgUnitNo;
	}

	public void setFinOrgUnitNo(String val) {
		this.finOrgUnitNo = val;
	}
	public boolean isToWareHouse() {
		return toWareHouse;
	}

	public void setToWareHouse(boolean toWareHouse) {
		this.toWareHouse = toWareHouse;
	}

	public long getReceiveWareHouseId() {
		return receiveWareHouseId;
	}

	public void setReceiveWareHouseId(long receiveWareHouseId) {
		this.receiveWareHouseId = receiveWareHouseId;
	}

	public String getPurOrgUnitNo() {
		return purOrgUnitNo;
	}

	public void setPurOrgUnitNo(String purOrgUnitNo) {
		this.purOrgUnitNo = purOrgUnitNo;
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
	public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
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
	public String getReqNo() {
		return reqNo;
	}

	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
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

	public String getWorkFlowNo() {
		return workFlowNo;
	}

	public void setWorkFlowNo(String workFlowNo) {
		this.workFlowNo = workFlowNo;
	}

	public boolean isTemplete() {
        return templete;
    }

    public void setTemplete(boolean templete) {
        this.templete = templete;
    }

    public String getStepNo() {
		return stepNo;
	}

	public void setStepNo(String stepNo) {
		this.stepNo = stepNo;
	}

	public String getOtherAuditNo() {
		return otherAuditNo;
	}

	public void setOtherAuditNo(String otherAuditNo) {
		this.otherAuditNo = otherAuditNo;
	}

	public String getOutAuditType() {
		return outAuditType;
	}

	public void setOutAuditType(String outAuditType) {
		this.outAuditType = outAuditType;
	}
	

	public String getSubscribeReason() {
		return subscribeReason;
	}

	public void setSubscribeReason(String subscribeReason) {
		this.subscribeReason = subscribeReason;
	}

	public String getPurRequireTheme() {
		return purRequireTheme;
	}

	public void setPurRequireTheme(String purRequireTheme) {
		this.purRequireTheme = purRequireTheme;
	}

	public ScmPurRequire(boolean defaultValue) {
		if (defaultValue) {
			this.currencyNo="";
			this.exchangeRate=BigDecimal.ONE;
			this.prtcount=0;
			this.toWareHouse=false;
			this.bizType="001";
		}
	}
  	public ScmPurRequire() {
	}

	public String[] getFieldNames(){
		return new String[]{
			FN_ID,
			FN_PRNO,
			FN_ORGUNITNO,
			FN_APPLICANTS,
			FN_APPLYDATE,
			FN_REQDATE,
			FN_BIZTYPE,
			FN_BILLTYPE,
			FN_FINORGUNITNO,
			FN_TOWAREHOUSE,
			FN_RECEIVEWAREHOUSEID,
			FN_PURORGUNITNO,
			FN_CURRENCYNO,
			FN_EXCHANGERATE,
			FN_CREATOR,
			FN_CREATEDATE,
			FN_EDITOR,
			FN_EDITDATE,
			FN_CHECKER,
			FN_CHECKDATE,
			FN_PRTCOUNT,
			FN_REQNO,
			FN_STATUS,
			FN_REMARKS,
			FN_CONTROLUNITNO,
			FN_WORKFLOWNO,
			FN_TEMPLETE,
			FN_STEPNO,
			FN_OTHERAUDITNO,
			FN_OUTAUDITTYPE,
			FN_SUBSCRIBEREASON,
			FN_PURREQUIRETHEME
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
		return new String[] {FN_ORGUNITNO,FN_APPLYDATE,FN_PURORGUNITNO,FN_REQDATE,FN_BIZTYPE};
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
		map.put(FN_PRNO, 32);
		map.put(FN_ORGUNITNO, 32);
		map.put(FN_APPLICANTS, 16);
		map.put(FN_BIZTYPE, 16);
		map.put(FN_BILLTYPE, 16);
		map.put(FN_FINORGUNITNO, 32);
		map.put(FN_CURRENCYNO, 16);
		map.put(FN_CREATOR, 16);
		map.put(FN_EDITOR, 16);
		map.put(FN_CHECKER, 16);
		map.put(FN_STATUS, 16);
		map.put(FN_REQNO,64);
		map.put(FN_REMARKS, 250);
		map.put(FN_CONTROLUNITNO, 32);
		map.put(FN_WORKFLOWNO, 64);
		map.put(FN_STEPNO, 32);
		map.put(FN_OTHERAUDITNO, 64);
		map.put(FN_OUTAUDITTYPE, 8);
		map.put(FN_SUBSCRIBEREASON, 250);
		map.put(FN_PURREQUIRETHEME, 250);
		return map; 
	}
	
	public String getPkKey() {
		return FN_ID;
	}
 
	public long getPK() {
		return id;
	}
}
