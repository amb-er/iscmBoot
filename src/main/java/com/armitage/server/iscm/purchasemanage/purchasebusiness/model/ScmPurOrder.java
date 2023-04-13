package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 

@XmlRootElement(name = "scmPurOrder")  
public class ScmPurOrder extends BaseModel {
	public static final String FN_ID = "id";
	public static final String FN_PONO = "poNo";
	public static final String FN_BIZTYPE = "bizType";
	public static final String FN_FINORGUNITNO = "finOrgUnitNo";
	public static final String FN_ORGUNITNO = "orgUnitNo";
	public static final String FN_VENDORID = "vendorId";
	public static final String FN_ORDERDATE = "orderDate";
	public static final String FN_REQDATE = "reqDate";
	public static final String FN_PAYMENT = "payment";
	public static final String FN_SETTLEMENT = "settlement";
	public static final String FN_STORAGEORGUNITNO = "storageOrgUnitNo";
	public static final String FN_CENTERALBALANCE = "centeralBalance";
    public static final String FN_DIRECTPURCHASE ="directPurchase";
	public static final String FN_UNIFIED = "unified";
	public static final String FN_BUYERID = "buyerId";
	public static final String FN_PURGROUPID = "purGroupId";
	public static final String FN_CURRENCYNO = "currencyNo";
	public static final String FN_EXCHANGERATE = "exchangeRate";
	public static final String FN_AMT = "amt";
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
	public static final String FN_STATUS = "status";
	public static final String FN_SENDED = "sended";
	public static final String FN_REMARKS = "remarks";
	public static final String FN_CONTROLUNITNO = "controlUnitNo";
	public static final String FN_WORKFLOWNO = "workFlowNo";
	public static final String FN_CONTRACTNO = "contractNo";
    public static final String FN_PRNOS = "prNos";
	public static final String FN_LOCKSTATUS = "lockStatus";
	public static final String FN_STEPNO = "stepNo";
	public static final String FN_SENDDATE = "sendDate";
	public static final String FN_PUSHED = "pushed";
	public static final String FN_VERSION = "version";
	public static final String FN_UPDATETIMESTAMP = "updateTimeStamp";
    public static final String FN_PENDINGUSR = "pendingUsr";
    public static final String FN_RELEASEDATE = "releaseDate";
	
	private long id;
	private String poNo;
	private String bizType;
	private String finOrgUnitNo;
	private String orgUnitNo;
	private long vendorId;
	private Date orderDate;
    private Date reqDate;
	private String payment;
	private String settlement;
	private String storageOrgUnitNo;
	private boolean centeralBalance;
    private boolean directPurchase;
	private boolean unified;
	private long buyerId;
	private long purGroupId;
	private String currencyNo;
	private BigDecimal exchangeRate;
	private BigDecimal amt;
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
	private String status;
	private boolean sended;
	private String remarks;
	private String controlUnitNo;
	private String workFlowNo;
	private String contractNo;
    private String prNos;
	private String lockStatus;
	private String stepNo;
	private Date sendDate;
	private boolean pushed;
	private int version;
	private Date updateTimeStamp;
    private String pendingUsr;
    private Date releaseDate;
	
	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long val) {
		this.id = val;
	}
	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String val) {
		this.poNo = val;
	}
	public String getBizType() {
		return bizType;
	}

	public void setBizType(String val) {
		this.bizType = val;
	}
	public String getFinOrgUnitNo() {
		return finOrgUnitNo;
	}

	public void setFinOrgUnitNo(String val) {
		this.finOrgUnitNo = val;
	}
	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String val) {
		this.orgUnitNo = val;
	}
	public long getVendorId() {
		return vendorId;
	}

	public void setVendorId(long val) {
		this.vendorId = val;
	}
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date val) {
		this.orderDate = val;
	}
	public Date getReqDate() {
		return reqDate;
	}

	public void setReqDate(Date reqDate) {
		this.reqDate = reqDate;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String val) {
		this.payment = val;
	}
	public String getSettlement() {
		return settlement;
	}

	public void setSettlement(String val) {
		this.settlement = val;
	}
	public String getStorageOrgUnitNo() {
		return storageOrgUnitNo;
	}

	public void setStorageOrgUnitNo(String val) {
		this.storageOrgUnitNo = val;
	}
	
	public boolean isCenteralBalance() {
		return centeralBalance;
	}

	public void setCenteralBalance(boolean centeralBalance) {
		this.centeralBalance = centeralBalance;
	}

	public boolean isDirectPurchase() {
		return directPurchase;
	}

	public void setDirectPurchase(boolean directPurchase) {
		this.directPurchase = directPurchase;
	}

	public boolean isUnified() {
		return unified;
	}

	public void setUnified(boolean unified) {
		this.unified = unified;
	}

	public long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(long buyerId) {
		this.buyerId = buyerId;
	}

	public long getPurGroupId() {
		return purGroupId;
	}

	public void setPurGroupId(long purGroupId) {
		this.purGroupId = purGroupId;
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
	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
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
	public String getStatus() {
		return status;
	}

	public void setStatus(String val) {
		this.status = val;
	}
	public boolean isSended() {
		return sended;
	}

	public void setSended(boolean sended) {
		this.sended = sended;
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

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getPrNos() {
		return prNos;
	}

	public void setPrNos(String prNos) {
		this.prNos = prNos;
	}

	public String getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}

	public String getStepNo() {
		return stepNo;
	}

	public void setStepNo(String stepNo) {
		this.stepNo = stepNo;
	}

	public boolean isPushed() {
		return pushed;
	}

	public void setPushed(boolean pushed) {
		this.pushed = pushed;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Date getUpdateTimeStamp() {
		return updateTimeStamp;
	}

	public void setUpdateTimeStamp(Date updateTimeStamp) {
		this.updateTimeStamp = updateTimeStamp;
	}

	public String getPendingUsr() {
		return pendingUsr;
	}

	public void setPendingUsr(String pendingUsr) {
		this.pendingUsr = pendingUsr;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public ScmPurOrder(boolean defaultValue) {
		if (defaultValue) {
			this.centeralBalance=false;
			this.currencyNo="";
			this.exchangeRate=BigDecimal.ONE;
			this.prtcount=0;
			this.unified = false;
			this.sended=false;
			this.pushed=false;
			this.version=1;
			this.directPurchase=false;
		}
	}
  	public ScmPurOrder() {
	}

	public String[] getFieldNames(){
		return new String[]{
			FN_ID,
			FN_PONO,
			FN_BIZTYPE,
			FN_FINORGUNITNO,
			FN_ORGUNITNO,
			FN_VENDORID,
			FN_ORDERDATE,
			FN_REQDATE,
			FN_PAYMENT,
			FN_SETTLEMENT,
			FN_STORAGEORGUNITNO,
			FN_CENTERALBALANCE,
			FN_DIRECTPURCHASE,
			FN_CURRENCYNO,
			FN_EXCHANGERATE,
			FN_CREATOR,
			FN_CREATEDATE,
			FN_EDITOR,
			FN_EDITDATE,
			FN_CHECKER,
			FN_CHECKDATE,
			FN_SENDDATE,
			FN_PRTCOUNT,
			FN_STATUS,
			FN_REMARKS,
			FN_CONTROLUNITNO,
			FN_WORKFLOWNO,
			FN_STEPNO,
			FN_VERSION,
			FN_PRNOS,
			FN_PENDINGUSR
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
		return new String[] {FN_ORDERDATE,FN_BIZTYPE,FN_VENDORID,FN_PAYMENT,FN_SETTLEMENT};
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
		map.put(FN_PONO, 32);
		map.put(FN_BIZTYPE, 16);
		map.put(FN_FINORGUNITNO, 32);
		map.put(FN_ORGUNITNO, 32);
		map.put(FN_PAYMENT, 16);
		map.put(FN_SETTLEMENT, 16);
		map.put(FN_STORAGEORGUNITNO, 32);
		map.put(FN_CURRENCYNO, 16);
		map.put(FN_CREATOR, 16);
		map.put(FN_EDITOR, 16);
		map.put(FN_CHECKER, 16);
		map.put(FN_STATUS, 16);
		map.put(FN_REMARKS, 250);
		map.put(FN_CONTROLUNITNO, 32);
		map.put(FN_WORKFLOWNO, 64);
		map.put(FN_STEPNO, 32);
		map.put(FN_PENDINGUSR,250);
		return map; 
	}
	
	public String getPkKey() {
		return FN_ID;
	}
 
	public long getPK() {
		return id;
	}
}

