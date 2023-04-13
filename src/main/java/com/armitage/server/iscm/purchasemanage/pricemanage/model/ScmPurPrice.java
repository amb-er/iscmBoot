
package com.armitage.server.iscm.purchasemanage.pricemanage.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmPurPrice")  
public class ScmPurPrice extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_PMNO ="pmNo";
	public static final String FN_BIZTYPE = "bizType";
    public static final String FN_ORGUNITNO ="orgUnitNo";
	public static final String FN_FINORGUNITNO = "finOrgUnitNo";
    public static final String FN_PMDATE ="pmDate";
    public static final String FN_BEGDATE ="begDate";
    public static final String FN_ENDDATE ="endDate";
    public static final String FN_GROUPID1 ="groupId1";
    public static final String FN_GROUPID2 ="groupId2";
    public static final String FN_GROUPID3 ="groupId3";
    public static final String FN_VENDORID1 ="vendorId1";
    public static final String FN_VENDORID2 ="vendorId2";
    public static final String FN_VENDORID3 ="vendorId3";
    public static final String FN_PQID1 ="pqId1";
    public static final String FN_PQID2 ="pqId2";
    public static final String FN_PQID3 ="pqId3";
    public static final String FN_SELVNDRID ="selVndrId";
    public static final String FN_PRICENAME ="priceName";
    public static final String FN_INCLUETAX ="inclueTax";
    public static final String FN_CURRENCYNO ="currencyNo";
    public static final String FN_EXCHANGERATE ="exchangeRate";
    public static final String FN_CREATOR ="creator";
    public static final String FN_CREATEDATE ="createDate";
    public static final String FN_SUBMITTER ="submitter";
    public static final String FN_SUBMITDATE ="submitDate";
    public static final String FN_EDITOR ="editor";
    public static final String FN_EDITDATE ="editDate";
    public static final String FN_CHECKER ="checker";
    public static final String FN_CHECKDATE ="checkDate";
    public static final String FN_PRTCOUNT ="prtcount";
    public static final String FN_STATUS ="status";
    public static final String FN_REMARKS ="remarks";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    public static final String FN_WORKFLOWNO = "workFlowNo";
	public static final String FN_STEPNO = "stepNo";
	public static final String FN_ISASSIGNORG = "isAssignOrg";
	public static final String FN_BUSINESSAUTOQUOTATION = "businessAutoQuotation";
	public static final String FN_QUOTATIONEXPIRYDATE = "quotationExpiryDate";
	public static final String FN_VENDORPQDATE1 = "vendorPqDate1";
	public static final String FN_VENDORPQDATE2 = "vendorPqDate2";
	public static final String FN_VENDORPQDATE3 = "vendorPqDate3";
	public static final String FN_UPDATETIMESTAMP = "updateTimeStamp";
	public static final String FN_CONFIRMSTATUS = "confirmStatus";
    
    private long id;
    private String pmNo;
	private String bizType;
    private String orgUnitNo;
	private String finOrgUnitNo;
    private Date pmDate;
    private Date begDate;
    private Date endDate;
    private long groupId1;
    private long groupId2;
    private long groupId3;
    private long vendorId1;
    private long vendorId2;
    private long vendorId3;
    private long pqId1;
    private long pqId2;
    private long pqId3;
    private long selVndrId;
    private String priceName;
    private boolean inclueTax;
    private String currencyNo;
    private BigDecimal exchangeRate;
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
    private String workFlowNo;
	private String stepNo;
	private boolean isAssignOrg;
	private boolean businessAutoQuotation;
	private Date quotationExpiryDate;
	private Date vendorPqDate1;
	private Date vendorPqDate2;
	private Date vendorPqDate3;
	private Date updateTimeStamp;
	private String confirmStatus;

	public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public String getPmNo() {
        return pmNo;
    }

    public void setPmNo(String val) {
        this.pmNo = val;
    }
    public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getOrgUnitNo() {
        return orgUnitNo;
    }

    public void setOrgUnitNo(String val) {
        this.orgUnitNo = val;
    }
    public String getFinOrgUnitNo() {
		return finOrgUnitNo;
	}

	public void setFinOrgUnitNo(String finOrgUnitNo) {
		this.finOrgUnitNo = finOrgUnitNo;
	}

	public Date getPmDate() {
        return pmDate;
    }

    public void setPmDate(Date val) {
        this.pmDate = val;
    }
    public Date getBegDate() {
        return begDate;
    }

    public void setBegDate(Date val) {
        this.begDate = val;
    }
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date val) {
        this.endDate = val;
    }
    public long getGroupId1() {
		return groupId1;
	}

	public void setGroupId1(long groupId1) {
		this.groupId1 = groupId1;
	}

	public long getGroupId2() {
		return groupId2;
	}

	public void setGroupId2(long groupId2) {
		this.groupId2 = groupId2;
	}

	public long getGroupId3() {
		return groupId3;
	}

	public void setGroupId3(long groupId3) {
		this.groupId3 = groupId3;
	}

	public long getVendorId1() {
        return vendorId1;
    }

    public void setVendorId1(long val) {
        this.vendorId1 = val;
    }
    public long getVendorId2() {
        return vendorId2;
    }

    public void setVendorId2(long val) {
        this.vendorId2 = val;
    }
    public long getVendorId3() {
        return vendorId3;
    }

    public void setVendorId3(long val) {
        this.vendorId3 = val;
    }
    public long getPqId1() {
		return pqId1;
	}

	public void setPqId1(long pqId1) {
		this.pqId1 = pqId1;
	}

	public long getPqId2() {
		return pqId2;
	}

	public void setPqId2(long pqId2) {
		this.pqId2 = pqId2;
	}

	public long getPqId3() {
		return pqId3;
	}

	public void setPqId3(long pqId3) {
		this.pqId3 = pqId3;
	}

	public long getSelVndrId() {
        return selVndrId;
    }

    public void setSelVndrId(long val) {
        this.selVndrId = val;
    }
    public String getPriceName() {
        return priceName;
    }

    public void setPriceName(String val) {
        this.priceName = val;
    }
    public boolean isInclueTax() {
		return inclueTax;
	}

	public void setInclueTax(boolean inclueTax) {
		this.inclueTax = inclueTax;
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

	public boolean isIsAssignOrg() {
		return isAssignOrg;
	}

	public void setIsAssignOrg(boolean isAssignOrg) {
		this.isAssignOrg = isAssignOrg;
	}

	public boolean isBusinessAutoQuotation() {
		return businessAutoQuotation;
	}

	public void setBusinessAutoQuotation(boolean businessAutoQuotation) {
		this.businessAutoQuotation = businessAutoQuotation;
	}

	public Date getQuotationExpiryDate() {
		return quotationExpiryDate;
	}

	public void setQuotationExpiryDate(Date quotationExpiryDate) {
		this.quotationExpiryDate = quotationExpiryDate;
	}

	public Date getVendorPqDate1() {
		return vendorPqDate1;
	}

	public void setVendorPqDate1(Date vendorPqDate1) {
		this.vendorPqDate1 = vendorPqDate1;
	}

	public Date getVendorPqDate2() {
		return vendorPqDate2;
	}

	public void setVendorPqDate2(Date vendorPqDate2) {
		this.vendorPqDate2 = vendorPqDate2;
	}

	public Date getVendorPqDate3() {
		return vendorPqDate3;
	}

	public void setVendorPqDate3(Date vendorPqDate3) {
		this.vendorPqDate3 = vendorPqDate3;
	}

	public Date getUpdateTimeStamp() {
		return updateTimeStamp;
	}

	public void setUpdateTimeStamp(Date updateTimeStamp) {
		this.updateTimeStamp = updateTimeStamp;
	}

	public String getConfirmStatus() {
		return confirmStatus;
	}

	public void setConfirmStatus(String confirmStatus) {
		this.confirmStatus = confirmStatus;
	}

	public ScmPurPrice(boolean defaultValue){
       if(defaultValue){
			this.priceName="";
			this.currencyNo="";
			this.exchangeRate=BigDecimal.ONE;
			this.prtcount=0;
			this.inclueTax=true;
			this.bizType="1";
			this.isAssignOrg=false;
			this.businessAutoQuotation=false;
       }
    }
  	public ScmPurPrice() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_PMNO,
            FN_BIZTYPE,
            FN_ORGUNITNO,
            FN_PMDATE,
            FN_BEGDATE,
            FN_ENDDATE,
            FN_GROUPID1,
            FN_GROUPID2,
            FN_GROUPID3,
            FN_VENDORID1,
            FN_VENDORID2,
            FN_VENDORID3,
            FN_PQID1,
            FN_PQID2,
            FN_PQID3,
            FN_SELVNDRID,
            FN_PRICENAME,
            FN_CURRENCYNO,
            FN_EXCHANGERATE,
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
            FN_STEPNO,
            FN_ISASSIGNORG,
            FN_BUSINESSAUTOQUOTATION,
            FN_QUOTATIONEXPIRYDATE,
            FN_VENDORPQDATE1,
            FN_VENDORPQDATE2,
            FN_VENDORPQDATE3,
            FN_CONFIRMSTATUS,
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
	    
		return new String[] {FN_PMDATE,FN_STATUS,FN_BIZTYPE,
							FN_ORGUNITNO};
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
        map.put(FN_PMNO, 16);
        map.put(FN_BIZTYPE, 16);
        map.put(FN_ORGUNITNO, 32);
        map.put(FN_PRICENAME, 16);
        map.put(FN_CURRENCYNO, 16);
        map.put(FN_CREATOR, 16);
        map.put(FN_EDITOR, 16);
        map.put(FN_CHECKER, 16);
        map.put(FN_STATUS, 16);
        map.put(FN_REMARKS, 250);
        map.put(FN_CONTROLUNITNO, 32);
        map.put(FN_WORKFLOWNO, 64);
        map.put(FN_STEPNO, 32);
        map.put(FN_CONFIRMSTATUS, 16);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
