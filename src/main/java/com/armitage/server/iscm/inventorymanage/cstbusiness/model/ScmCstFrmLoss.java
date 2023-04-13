package com.armitage.server.iscm.inventorymanage.cstbusiness.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;

@XmlRootElement(name = "scmCstFrmLoss")
public class ScmCstFrmLoss extends BaseModel {
	
	public static final String FN_ID ="id";
    public static final String FN_BILLNO ="billNo";
    public static final String FN_COSTORGUNITNO ="costOrgUnitNo";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_FINORGUNITNO ="finOrgUnitNo";
    public static final String FN_BIZDATE ="bizDate";
    public static final String FN_CURRENCYNO ="currencyNo";
    public static final String FN_EXCHANGERATE ="exchangeRate";
    public static final String FN_PERIODID ="periodId";
    public static final String FN_ACCOUNTYEAR ="accountYear";
    public static final String FN_ACCOUNTPERIOD ="accountPeriod";
    public static final String FN_CREATOR ="creator";
    public static final String FN_CREATEDATE ="createDate";
    public static final String FN_SUBMITTER ="submitter";
    public static final String FN_SUBMITDATE ="submitDate";
    public static final String FN_EDITOR ="editor";
    public static final String FN_EDITDATE ="editDate";
    public static final String FN_CHECKER ="checker";
    public static final String FN_CHECKDATE ="checkDate";
    public static final String FN_POSTDATE ="postDate";
    public static final String FN_WORKFLOWNO ="workFlowNo";
    public static final String FN_STEPNO ="stepNo";
    public static final String FN_PRTCOUNT ="prtcount";
    public static final String FN_STATUS ="status";
    public static final String FN_REMARKS ="remarks";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    
    private long id;
    private String billNo;
    private String costOrgUnitNo;
    private String orgUnitNo;
    private String finOrgUnitNo;
    private Date bizDate;
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
    private Date postDate;
    private String workFlowNo;
    private String stepNo;
    private int prtcount;
    private String status;
    private String remarks;
    private String controlUnitNo;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String val) {
        this.billNo = val;
    }
    public String getCostOrgUnitNo() {
        return costOrgUnitNo;
    }

    public void setCostOrgUnitNo(String val) {
        this.costOrgUnitNo = val;
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

    public void setFinOrgUnitNo(String val) {
        this.finOrgUnitNo = val;
    }
    public Date getBizDate() {
        return bizDate;
    }

    public void setBizDate(Date val) {
        this.bizDate = val;
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

    public void setSubmitter(String val) {
        this.submitter = val;
    }
    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date val) {
        this.submitDate = val;
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

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public String getWorkFlowNo() {
        return workFlowNo;
    }

    public void setWorkFlowNo(String val) {
        this.workFlowNo = val;
    }
    public String getStepNo() {
        return stepNo;
    }

    public void setStepNo(String val) {
        this.stepNo = val;
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
    
    public ScmCstFrmLoss(boolean defaultValue){
       if(defaultValue){
    	   this.currencyNo="";
           this.exchangeRate=BigDecimal.ONE;
           this.periodId=0;
           this.prtcount=0;
       }
    }
  	public ScmCstFrmLoss() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_BILLNO,
            FN_COSTORGUNITNO,
            FN_ORGUNITNO,
            FN_FINORGUNITNO,
            FN_BIZDATE,
            FN_CURRENCYNO,
            FN_EXCHANGERATE,
            FN_PERIODID,
            FN_ACCOUNTYEAR,
            FN_ACCOUNTPERIOD,
            FN_CREATOR,
            FN_CREATEDATE,
            FN_SUBMITTER,
            FN_SUBMITDATE,
            FN_EDITOR,
            FN_EDITDATE,
            FN_CHECKER,
            FN_CHECKDATE,
            FN_POSTDATE,
            FN_WORKFLOWNO,
            FN_STEPNO,
            FN_PRTCOUNT,
            FN_STATUS,
            FN_REMARKS,
            FN_CONTROLUNITNO,
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
		return new String[] {FN_COSTORGUNITNO,FN_BIZDATE};
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
        map.put(FN_BILLNO, 96);
        map.put(FN_COSTORGUNITNO, 96);
        map.put(FN_ORGUNITNO, 96);
        map.put(FN_FINORGUNITNO, 96);
        map.put(FN_CURRENCYNO, 48);
        map.put(FN_CREATOR, 48);
        map.put(FN_SUBMITTER, 48);
        map.put(FN_EDITOR, 48);
        map.put(FN_CHECKER, 48);
        map.put(FN_WORKFLOWNO, 192);
        map.put(FN_STEPNO, 96);
        map.put(FN_STATUS, 48);
        map.put(FN_REMARKS, 750);
        map.put(FN_CONTROLUNITNO, 96);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}
}
