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
 

@XmlRootElement(name = "scmPurReceive")  
public class ScmPurReceive extends BaseModel {
	public static final String FN_ID = "id";
	public static final String FN_PVNO = "pvNo";
	public static final String FN_FINORGUNITNO = "finOrgUnitNo";
	public static final String FN_ORGUNITNO = "orgUnitNo";
	public static final String FN_PDNO = "pdNo";
	public static final String FN_PONOS = "poNos";
	public static final String FN_CKID = "ckId";
	public static final String FN_CKNO = "ckNo";
	public static final String FN_VENDORID = "vendorId";
	public static final String FN_RECEIVEDATE = "receiveDate";
	public static final String FN_RECEIVETIME = "receiveTime";
	public static final String FN_RECEIVER = "receiver";
	public static final String FN_DELIVERER = "deliverer";
	public static final String FN_UNIFIED = "unified";
	public static final String FN_PURORGUNITNO = "purOrgUnitNo";
	public static final String FN_BUYERID = "buyerId";
	public static final String FN_PURGROUPID = "purGroupId";
	public static final String FN_CURRENCYNO = "currencyNo";
	public static final String FN_EXCHANGERATE = "exchangeRate";
	public static final String FN_AMT = "amt";
	public static final String FN_TAXAMT = "taxAmt";
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
	public static final String FN_EXISTSSOURCE = "existsSource";
	public static final String FN_USEORGUNITNOS = "useOrgUnitNos";
	public static final String FN_WAREHOUSEIDS = "wareHouseIds";
	public static final String FN_STATUS = "status";
	public static final String FN_REMARKS = "remarks";
	public static final String FN_CONTROLUNITNO = "controlUnitNo";
	public static final String FN_WORKFLOWNO = "workFlowNo";
	public static final String FN_STEPNO = "stepNo";
    public static final String FN_PENDINGUSR = "pendingUsr";
	
	private long id;
	private String pvNo;
	private String finOrgUnitNo;
	private String orgUnitNo;
	private String pdNo;
	private String poNos;
	private long ckId;
	private String ckNo;
	private long vendorId;
	private Date receiveDate;
	private Date receiveTime;
	private String receiver;
	private String deliverer;
	private boolean unified;
	private String purOrgUnitNo;
	private long buyerId;
	private long purGroupId;
	private String currencyNo;
	private BigDecimal exchangeRate;
	private BigDecimal amt;
	private BigDecimal taxAmt;
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
	private boolean existsSource;
	private String useOrgUnitNos;
	private String wareHouseIds;
	private String status;
	private String remarks;
	private String controlUnitNo;
	private String workFlowNo;
	private String stepNo;
    private String pendingUsr;

	public long getId() {
		return id;
	}

	public void setId(long val) {
		this.id = val;
	}
	public String getPvNo() {
		return pvNo;
	}

	public void setPvNo(String val) {
		this.pvNo = val;
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
	public String getPdNo() {
		return pdNo;
	}

	public void setPdNo(String val) {
		this.pdNo = val;
	}
	public String getPoNos() {
		return poNos;
	}

	public void setPoNos(String poNos) {
		this.poNos = poNos;
	}

	public long getCkId() {
		return ckId;
	}

	public void setCkId(long ckId) {
		this.ckId = ckId;
	}

	public String getCkNo() {
		return ckNo;
	}

	public void setCkNo(String ckNo) {
		this.ckNo = ckNo;
	}

	public long getVendorId() {
		return vendorId;
	}

	public void setVendorId(long val) {
		this.vendorId = val;
	}
	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date val) {
		this.receiveDate = val;
	}
	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String val) {
		this.receiver = val;
	}
	public String getDeliverer() {
		return deliverer;
	}

	public void setDeliverer(String val) {
		this.deliverer = val;
	}
	public boolean isUnified() {
		return unified;
	}

	public void setUnified(boolean unified) {
		this.unified = unified;
	}

	public String getPurOrgUnitNo() {
		return purOrgUnitNo;
	}

	public void setPurOrgUnitNo(String purOrgUnitNo) {
		this.purOrgUnitNo = purOrgUnitNo;
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
	
	public boolean isExistsSource() {
		return existsSource;
	}

	public void setExistsSource(boolean existsSource) {
		this.existsSource = existsSource;
	}

	public String getUseOrgUnitNos() {
		return useOrgUnitNos;
	}

	public void setUseOrgUnitNos(String useOrgUnitNos) {
		this.useOrgUnitNos = useOrgUnitNos;
	}

	public String getWareHouseIds() {
		return wareHouseIds;
	}

	public void setWareHouseIds(String wareHouseIds) {
		this.wareHouseIds = wareHouseIds;
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

	public String getStepNo() {
		return stepNo;
	}

	public void setStepNo(String stepNo) {
		this.stepNo = stepNo;
	}

	public String getPendingUsr() {
		return pendingUsr;
	}

	public void setPendingUsr(String pendingUsr) {
		this.pendingUsr = pendingUsr;
	}

	public ScmPurReceive(boolean defaultValue) {
		if (defaultValue) {
			this.currencyNo="";
			this.exchangeRate=BigDecimal.ONE;
			this.periodId=0;
			this.prtcount=0;
			this.existsSource=false;
			this.unified=false;
		}
	}
  	public ScmPurReceive() {
	}

	public String[] getFieldNames(){
		return new String[]{
			FN_ID,
			FN_PVNO,
			FN_FINORGUNITNO,
			FN_ORGUNITNO,
			FN_PDNO,
			FN_PONOS,
			FN_VENDORID,
			FN_RECEIVEDATE,
			FN_RECEIVER,
			FN_DELIVERER,
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
			FN_EXISTSSOURCE,
			FN_USEORGUNITNOS,
			FN_WAREHOUSEIDS,
			FN_STATUS,
			FN_REMARKS,
			FN_CONTROLUNITNO,
			FN_WORKFLOWNO,
			FN_STEPNO,
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
		return new String[] {FN_VENDORID,FN_RECEIVEDATE,FN_PURORGUNITNO};
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
		map.put(FN_PVNO, 32);
		map.put(FN_FINORGUNITNO, 32);
		map.put(FN_ORGUNITNO, 32);
		map.put(FN_PDNO, 20);
		map.put(FN_PONOS, 250);
		map.put(FN_RECEIVER, 16);
		map.put(FN_DELIVERER, 50);
		map.put(FN_CURRENCYNO, 16);
		map.put(FN_CREATOR, 16);
		map.put(FN_EDITOR, 16);
		map.put(FN_CHECKER, 16);
		map.put(FN_STATUS, 16);
		map.put(FN_REMARKS, 250);
		map.put(FN_CONTROLUNITNO, 32);
		map.put(FN_WORKFLOWNO, 64);
		map.put(FN_STEPNO, 32);
		map.put(FN_USEORGUNITNOS, 250);
		map.put(FN_WAREHOUSEIDS, 250);
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
