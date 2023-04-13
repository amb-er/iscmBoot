package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 

@XmlRootElement(name = "scmInvPurInWarehsBill")  
public class ScmInvPurInWarehsBill extends BaseModel {
	public static final String FN_WRID = "wrId";
	public static final String FN_WRNO = "wrNo";
	public static final String FN_BIZTYPE = "bizType";
	public static final String FN_FINORGUNITNO = "finOrgUnitNo";
	public static final String FN_ORGUNITNO = "orgUnitNo";
	public static final String FN_VENDORID = "vendorId";
	public static final String FN_BIZDATE = "bizDate";
	public static final String FN_PURORGUNITNO = "purOrgUnitNo";
	public static final String FN_BUYERID = "buyerId";
	public static final String FN_PURGROUPID = "purGroupId";
	public static final String FN_SYSBILL = "sysBill";
	public static final String FN_OFFSET = "offset";
	public static final String FN_UNITEDBILL = "unitedBill";
	public static final String FN_UNITEDBILLID = "unitedBillId";
	public static final String FN_VOUCHERED = "vouchered";
	public static final String FN_VOUCHERID = "voucherId";
	public static final String FN_BILLTYPE = "billType";
	public static final String FN_UNIFIED = "unified";
	public static final String FN_RECEIVER = "receiver";
	public static final String FN_CURRENCYNO = "currencyNo";
	public static final String FN_EXCHANGERATE = "exchangeRate";
	public static final String FN_AMT = "amt";
	public static final String FN_TAXAMT = "taxAmt";
	public static final String FN_PERIODID = "periodId";
	public static final String FN_ACCOUNTYEAR = "accountYear";
	public static final String FN_ACCOUNTPERIOD = "accountPeriod";
	public static final String FN_CREATOR = "creator";
	public static final String FN_CREATEDATE = "createDate";
	public static final String FN_CREATEORGUNITNO = "createOrgUnitNo";
    public static final String FN_SUBMITTER ="submitter";
    public static final String FN_SUBMITDATE ="submitDate";
	public static final String FN_EDITOR = "editor";
	public static final String FN_EDITDATE = "editDate";
	public static final String FN_CHECKER = "checker";
	public static final String FN_CHECKDATE = "checkDate";
	public static final String FN_POSTDATE = "postDate";
	public static final String FN_PRTCOUNT = "prtcount";
	public static final String FN_STATUS = "status";
	public static final String FN_REMARKS = "remarks";
	public static final String FN_WORKFLOWNO = "workFlowNo";
	public static final String FN_CONTROLUNITNO = "controlUnitNo";
	public static final String FN_STEPNO = "stepNo";
	public static final String FN_PUSHED = "pushed";
	public static final String FN_CONFIRMSTATUS = "confirmStatus";
	public static final String FN_VERSION = "version";
	public static final String FN_UPDATETIMESTAMP = "updateTimeStamp";
	public static final String FN_PENDINGUSR = "pendingUsr";
	public static final String FN_PVNOS = "pvNos";
	public static final String FN_USEORGUNITNOS = "useOrgUnitNos";
	public static final String FN_WAREHOUSEIDS = "wareHouseIds";
	
	private long wrId;
	private String wrNo;
	private String bizType;
	private String finOrgUnitNo;
	private String orgUnitNo;
	private long vendorId;
	private Date bizDate;
	private String purOrgUnitNo;
	private long buyerId;
	private long purGroupId;
	private boolean sysBill;
	private boolean offset;
	private boolean unitedBill;
	private long unitedBillId;
	private boolean vouchered;
	private long voucherId;
	private String billType;
	private boolean unified;
	private String receiver;
	private String currencyNo;
	private BigDecimal exchangeRate;
	private BigDecimal amt;
	private BigDecimal taxAmt;
	private long periodId;
	private int accountYear;
	private int accountPeriod;
	private String creator;
	private Date createDate;
	private String createOrgUnitNo;
    private String submitter;
    private Date submitDate;
	private String editor;
	private Date editDate;
	private String checker;
	private Date checkDate;
	private Date postDate;
	private int prtcount;
	private String status;
	private String remarks;
	private String workFlowNo;
	private String controlUnitNo;
	private String stepNo;
	private boolean pushed;
	private String confirmStatus;
	private int version;
	private Date updateTimeStamp;
	private String pendingUsr;
	private String pvNos;
	private String useOrgUnitNos;
	private String wareHouseIds;

	public long getWrId() {
		return wrId;
	}

	public void setWrId(long val) {
		this.wrId = val;
	}
	public String getWrNo() {
		return wrNo;
	}

	public void setWrNo(String val) {
		this.wrNo = val;
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
	public Date getBizDate() {
		return bizDate;
	}

	public void setBizDate(Date val) {
		this.bizDate = val;
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

	public boolean isSysBill() {
		return sysBill;
	}

	public void setSysBill(boolean sysBill) {
		this.sysBill = sysBill;
	}

	public boolean isOffset() {
		return offset;
	}

	public void setOffset(boolean offset) {
		this.offset = offset;
	}

	public boolean isUnitedBill() {
		return unitedBill;
	}

	public void setUnitedBill(boolean unitedBill) {
		this.unitedBill = unitedBill;
	}

	public long getUnitedBillId() {
		return unitedBillId;
	}

	public void setUnitedBillId(long val) {
		this.unitedBillId = val;
	}
	
	public boolean isVouchered() {
		return vouchered;
	}

	public void setVouchered(boolean vouchered) {
		this.vouchered = vouchered;
	}

	public long getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(long val) {
		this.voucherId = val;
	}
	public String getBillType() {
		return billType;
	}

	public void setBillType(String val) {
		this.billType = val;
	}
	public boolean isUnified() {
		return unified;
	}

	public void setUnified(boolean unified) {
		this.unified = unified;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
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

	public void setAmt(BigDecimal val) {
		this.amt = val;
	}
	public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(BigDecimal val) {
		this.taxAmt = val;
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
	public String getCreateOrgUnitNo() {
		return createOrgUnitNo;
	}

	public void setCreateOrgUnitNo(String val) {
		this.createOrgUnitNo = val;
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
	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date val) {
		this.postDate = val;
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

	public boolean isPushed() {
		return pushed;
	}

	public void setPushed(boolean pushed) {
		this.pushed = pushed;
	}

	public String getConfirmStatus() {
		return confirmStatus;
	}

	public void setConfirmStatus(String confirmStatus) {
		this.confirmStatus = confirmStatus;
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

	public String getPvNos() {
		return pvNos;
	}

	public void setPvNos(String pvNos) {
		this.pvNos = pvNos;
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

	public ScmInvPurInWarehsBill(boolean defaultValue) {
		if (defaultValue) {
			this.sysBill=false;
			this.offset=false;
			this.unitedBill=false;
			this.unitedBillId=0;
			this.vouchered=false;
			this.voucherId=0;
			this.amt=BigDecimal.ZERO;
			this.taxAmt=BigDecimal.ZERO;
			this.currencyNo="";
			this.exchangeRate=BigDecimal.ONE;
			this.periodId=0;
			this.prtcount=0;
			this.bizType="1";
			this.unified=false;
			this.pushed=false;
			this.version=1;
		}
	}
  	public ScmInvPurInWarehsBill() {
	}

	public String[] getFieldNames(){
		return new String[]{
			FN_WRID,
			FN_WRNO,
			FN_BIZTYPE,
			FN_FINORGUNITNO,
			FN_ORGUNITNO,
			FN_VENDORID,
			FN_BIZDATE,
			FN_PURORGUNITNO,
			FN_BUYERID,
			FN_PURGROUPID,
			FN_SYSBILL,
			FN_OFFSET,
			FN_UNITEDBILL,
			FN_UNITEDBILLID,
			FN_VOUCHERED,
			FN_VOUCHERID,
			FN_BILLTYPE,
			FN_CURRENCYNO,
			FN_EXCHANGERATE,
			FN_AMT,
			FN_TAXAMT,
			FN_PERIODID,
			FN_ACCOUNTYEAR,
			FN_ACCOUNTPERIOD,
			FN_CREATOR,
			FN_CREATEDATE,
			FN_CREATEORGUNITNO,
			FN_EDITOR,
			FN_EDITDATE,
			FN_CHECKER,
			FN_CHECKDATE,
			FN_POSTDATE,
			FN_PRTCOUNT,
			FN_STATUS,
			FN_REMARKS,
			FN_WORKFLOWNO,
			FN_CONTROLUNITNO,
			FN_STEPNO,
			FN_PUSHED,
			FN_CONFIRMSTATUS,
			FN_VERSION,
			FN_PENDINGUSR,
			FN_PVNOS,
			FN_USEORGUNITNOS,
			FN_WAREHOUSEIDS
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
		return new String[] {FN_VENDORID,FN_BIZTYPE,FN_BIZDATE,FN_PURORGUNITNO};
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
		map.put(FN_WRNO, 32);
		map.put(FN_BIZTYPE, 16);
		map.put(FN_FINORGUNITNO, 32);
		map.put(FN_ORGUNITNO, 32);
		map.put(FN_BILLTYPE, 16);
		map.put(FN_CURRENCYNO, 16);
		map.put(FN_CREATOR, 16);
		map.put(FN_CREATEORGUNITNO, 32);
		map.put(FN_EDITOR, 16);
		map.put(FN_CHECKER, 16);
		map.put(FN_STATUS, 16);
		map.put(FN_REMARKS, 250);
		map.put(FN_CONTROLUNITNO, 32);
		map.put(FN_WORKFLOWNO, 64);
		map.put(FN_STEPNO, 32);
		map.put(FN_CONFIRMSTATUS, 16);
		map.put(FN_PVNOS, 250);
		map.put(FN_USEORGUNITNOS, 250);
		map.put(FN_WAREHOUSEIDS, 250);
		map.put(FN_PENDINGUSR, 250);
		return map; 
	}
	
	public String getPkKey() {
		return FN_WRID;
	}
 
	public long getPK() {
		return wrId;
	}
}

