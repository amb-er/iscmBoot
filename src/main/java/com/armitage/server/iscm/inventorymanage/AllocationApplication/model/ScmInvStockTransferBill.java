package com.armitage.server.iscm.inventorymanage.AllocationApplication.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmInvStockTransferBill")  
public class ScmInvStockTransferBill extends BaseModel  {
	 
    public static final String FN_WTID ="wtId";
    public static final String FN_WTNO ="wtNo";
    public static final String FN_BIZTYPE ="bizType";
    public static final String FN_BIZDATE ="bizDate";
    public static final String FN_BILLTYPE ="billType";
    public static final String FN_FINORGUNITNO ="finOrgUnitNo";
    public static final String FN_OUTORGUNITNO ="outOrgUnitNo";
    public static final String FN_INORGUNITNO ="inOrgUnitNo";
    public static final String FN_CURRENCYNO ="currencyNo";
    public static final String FN_EXCHANGERATE ="exchangeRate";
    public static final String FN_PERIODID ="periodId";
    public static final String FN_ACCOUNTYEAR ="accountYear";
    public static final String FN_ACCOUNTPERIOD ="accountPeriod";
    public static final String FN_CREATOR ="creator";
    public static final String FN_CREATEDATE ="createDate";
    public static final String FN_CREATEORGUNITNO ="createOrgUnitNo";
    public static final String FN_SUBMITTER ="submitter";
    public static final String FN_SUBMITDATE ="submitDate";
    public static final String FN_EDITOR ="editor";
    public static final String FN_EDITDATE ="editDate";
    public static final String FN_CHECKER ="checker";
    public static final String FN_CHECKDATE ="checkDate";
    public static final String FN_UNITEDBILL ="unitedBill";
    public static final String FN_PRTCOUNT ="prtcount";
    public static final String FN_STATUS ="status";
    public static final String FN_REMARKS ="remarks";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    public static final String FN_WAREHOUSEID ="wareHouseId";
    public static final String FN_RECEIPTWAREHOUSEID ="receiptWarehouseId";
    public static final String FN_WORKFLOWNO = "workFlowNo";
	public static final String FN_STEPNO = "stepNo";
    
    private long wtId;
    private String wtNo;
    private String bizType;
    private Date bizDate;
    private String billType;
    private String finOrgUnitNo;
    private String outOrgUnitNo;
    private long wareHouseId;
    private String inOrgUnitNo;
    private long receiptWarehouseId;
    private String currencyNo;
    private BigDecimal exchangeRate;
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
    private boolean unitedBill;
    private int prtcount;
    private String status;
    private String remarks;
    private String controlUnitNo;
    private String workFlowNo;
	private String stepNo;

    public long getWtId() {
        return wtId;
    }

    public void setWtId(long val) {
        this.wtId = val;
    }
    public String getWtNo() {
        return wtNo;
    }

    public void setWtNo(String val) {
        this.wtNo = val;
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
    public String getOutOrgUnitNo() {
        return outOrgUnitNo;
    }

    public void setOutOrgUnitNo(String val) {
        this.outOrgUnitNo = val;
    }
    public String getInOrgUnitNo() {
        return inOrgUnitNo;
    }

    public void setInOrgUnitNo(String val) {
        this.inOrgUnitNo = val;
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
    public boolean getUnitedBill() {
        return unitedBill;
    }

    public void setUnitedBill(boolean val) {
        this.unitedBill = val;
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

    public long getWareHouseId() {
		return wareHouseId;
	}

	public void setWareHouseId(long wareHouseId) {
		this.wareHouseId = wareHouseId;
	}

	public long getReceiptWarehouseId() {
		return receiptWarehouseId;
	}

	public void setReceiptWarehouseId(long receiptWarehouseId) {
		this.receiptWarehouseId = receiptWarehouseId;
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

	public ScmInvStockTransferBill(boolean defaultValue){
       if(defaultValue){
    	   this.finOrgUnitNo="";
    	   this.inOrgUnitNo="";
    	   this.currencyNo="";
    	   this.periodId=0;
    	   this.exchangeRate=BigDecimal.ONE;
       }
    }
  	public ScmInvStockTransferBill() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_WTID,
            FN_WTNO,
            FN_BIZTYPE,
            FN_BIZDATE,
            FN_BILLTYPE,
            FN_FINORGUNITNO,
            FN_OUTORGUNITNO,
            FN_INORGUNITNO,
            FN_CURRENCYNO,
            FN_EXCHANGERATE,
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
            FN_UNITEDBILL,
            FN_PRTCOUNT,
            FN_STATUS,
            FN_REMARKS,
            FN_CONTROLUNITNO,
            FN_WAREHOUSEID,
            FN_RECEIPTWAREHOUSEID,
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
		return new String[] {FN_OUTORGUNITNO,FN_INORGUNITNO,FN_WAREHOUSEID,FN_RECEIPTWAREHOUSEID};
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
        map.put(FN_WTNO, 32);
        map.put(FN_BIZTYPE, 16);
        map.put(FN_BILLTYPE, 16);
        map.put(FN_FINORGUNITNO, 32);
        map.put(FN_OUTORGUNITNO, 32);
        map.put(FN_INORGUNITNO, 32);
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
		return map; 
	}
     public String getPkKey() {
		 
		return FN_WTID;
	}
 
	public long getPK() {
		 
		return wtId;
	}


}

