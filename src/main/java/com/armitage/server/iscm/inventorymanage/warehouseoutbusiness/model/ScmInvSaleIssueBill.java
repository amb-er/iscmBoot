package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmInvSaleIssueBill")  
public class ScmInvSaleIssueBill extends BaseModel  {
	 
    public static final String FN_OTID ="otId";
    public static final String FN_OTNO ="otNo";
    public static final String FN_FINORGUNITNO ="finOrgUnitNo";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_BIZTYPE ="bizType";
    public static final String FN_CUSTID ="custId";
    public static final String FN_CUSTOMERROLE ="customerRole";
    public static final String FN_BIZDATE ="bizDate";
    public static final String FN_SYSBILL ="sysBill";
    public static final String FN_OFFSET ="offset";
    public static final String FN_UNITEDBILL ="unitedBill";
    public static final String FN_UNITEDBILLID ="unitedBillId";
    public static final String FN_VOUCHERED ="vouchered";
    public static final String FN_VOUCHERID ="voucherId";
    public static final String FN_BILLTYPE ="billType";
    public static final String FN_SALEORGUNITNO ="saleOrgUnitNo";
    public static final String FN_SALESID ="salesId";
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
    public static final String FN_POSTDATE ="postDate";
    public static final String FN_PRTCOUNT ="prtcount";
    public static final String FN_BUILDAR ="buildAr";
    public static final String FN_STATUS ="status";
    public static final String FN_REMARKS ="remarks";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    public static final String FN_WORKFLOWNO = "workFlowNo";
	public static final String FN_STEPNO = "stepNo";
	public static final String FN_OTHERNO = "otherNo";
  
    private long otId;
    private String otNo;
    private String finOrgUnitNo;
    private String orgUnitNo;
    private String bizType;
    private long custId;
    private String customerRole;
    private Date bizDate;
    private boolean sysBill;
    private boolean offset;
    private boolean unitedBill;
    private long unitedBillId;
    private boolean vouchered;
    private long voucherId;
    private String billType;
    private String saleOrgUnitNo;
    private long salesId;
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
    private Date postDate;
    private int prtcount;
    private boolean buildAr;
    private String status;
    private String remarks;
    private String controlUnitNo;
    private String workFlowNo;
	private String stepNo;
	private String otherNo;

    public String getCustomerRole() {
		return customerRole;
	}

	public void setCustomerRole(String customerRole) {
		this.customerRole = customerRole;
	}

	public long getOtId() {
        return otId;
    }

    public void setOtId(long val) {
        this.otId = val;
    }
    public String getOtNo() {
        return otNo;
    }

    public void setOtNo(String val) {
        this.otNo = val;
    }

    public String getFinOrgUnitNo() {
		return finOrgUnitNo;
	}

	public void setFinOrgUnitNo(String finOrgUnitNo) {
		this.finOrgUnitNo = finOrgUnitNo;
	}

	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}

	public String getBizType() {
        return bizType;
    }

    public void setBizType(String val) {
        this.bizType = val;
    }
    public long getCustId() {
        return custId;
    }

    public void setCustId(long val) {
        this.custId = val;
    }
    public Date getBizDate() {
        return bizDate;
    }

    public void setBizDate(Date val) {
        this.bizDate = val;
    }
    public long getUnitedBillId() {
        return unitedBillId;
    }

    public void setUnitedBillId(long val) {
        this.unitedBillId = val;
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
    public String getSaleOrgUnitNo() {
		return saleOrgUnitNo;
	}

	public void setSaleOrgUnitNo(String saleOrgUnitNo) {
		this.saleOrgUnitNo = saleOrgUnitNo;
	}

	public long getSalesId() {
		return salesId;
	}

	public void setSalesId(long salesId) {
		this.salesId = salesId;
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

    public boolean isVouchered() {
        return vouchered;
    }

    public void setVouchered(boolean vouchered) {
        this.vouchered = vouchered;
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
    public boolean isBuildAr() {
		return buildAr;
	}

	public void setBuildAr(boolean buildAr) {
		this.buildAr = buildAr;
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

	public String getOtherNo() {
		return otherNo;
	}

	public void setOtherNo(String otherNo) {
		this.otherNo = otherNo;
	}

	public ScmInvSaleIssueBill(boolean defaultValue){
       if(defaultValue){
           this.offset = false;
           this.unitedBill = false;
           this.sysBill = false;
           this.unitedBillId = 0;
           this.currencyNo = "";
           this.exchangeRate = BigDecimal.ONE;
           this.periodId = 0;
           this.prtcount = 0;
           this.vouchered = false;
           this.voucherId = 0;
       }
    }
  	public ScmInvSaleIssueBill() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_OTID,
            FN_OTNO,
            FN_FINORGUNITNO,
            FN_ORGUNITNO,
            FN_BIZTYPE,
            FN_CUSTID,
            FN_CUSTOMERROLE,
            FN_BIZDATE,
            FN_SYSBILL,
            FN_OFFSET,
            FN_UNITEDBILL,
            FN_UNITEDBILLID,
            FN_VOUCHERED,
            FN_VOUCHERID,
            FN_BILLTYPE,
            FN_SALEORGUNITNO,
            FN_SALESID,
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
            FN_POSTDATE,
            FN_PRTCOUNT,
            FN_STATUS,
            FN_REMARKS,
            FN_CONTROLUNITNO,
            FN_WORKFLOWNO,
            FN_STEPNO,
            FN_OTHERNO
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
		return new String[] {FN_CUSTID};
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
        map.put(FN_OTNO, 32);
        map.put(FN_FINORGUNITNO, 32);
        map.put(FN_ORGUNITNO, 32);
        map.put(FN_BIZTYPE, 16);
        map.put(FN_BILLTYPE, 16);
        map.put(FN_SALEORGUNITNO,32);
        map.put(FN_CURRENCYNO, 16);
        map.put(FN_CREATOR, 16);
        map.put(FN_CREATEORGUNITNO, 32);
        map.put(FN_EDITOR, 16);
        map.put(FN_CHECKER, 16);
        map.put(FN_STATUS, 16);
        map.put(FN_REMARKS, 250);
        map.put(FN_CONTROLUNITNO, 32);
        map.put(FN_WORKFLOWNO, 64);
        map.put(FN_STEPNO, 64);
        map.put(FN_OTHERNO, 32);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_OTID;
	}
 
	public long getPK() {
		 
		return otId;
	}


}