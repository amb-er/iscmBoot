package com.armitage.server.iscm.purchasemanage.pricemanage.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmPurQuotationPlan")  
public class ScmPurQuotationPlan extends BaseModel  {
	 
    public static final String FN_ID ="Id";
    public static final String FN_PLANNO ="planNo";
    public static final String FN_ORGUNITNO ="orgUnitNo";
	public static final String FN_FINORGUNITNO = "finOrgUnitNo";
	public static final String FN_BIZDATE = "bizDate";
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
   
    private long id;
    private String planNo;
    private String orgUnitNo;
	private String finOrgUnitNo;
	private Date bizDate;
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

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public String getPlanNo() {
        return planNo;
    }

    public void setPlanNo(String val) {
        this.planNo = val;
    }
    public String getOrgUnitNo() {
        return orgUnitNo;
    }

    public String getFinOrgUnitNo() {
		return finOrgUnitNo;
	}

	public void setFinOrgUnitNo(String finOrgUnitNo) {
		this.finOrgUnitNo = finOrgUnitNo;
	}

	public Date getBizDate() {
		return bizDate;
	}

	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}

	public void setOrgUnitNo(String val) {
        this.orgUnitNo = val;
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

	public ScmPurQuotationPlan(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmPurQuotationPlan() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_PLANNO,
            FN_ORGUNITNO,
            FN_FINORGUNITNO,
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
		return null;
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
        map.put(FN_PLANNO, 32);
        map.put(FN_ORGUNITNO, 32);
        map.put(FN_FINORGUNITNO, 32);
        map.put(FN_CREATOR, 16);
        map.put(FN_EDITOR, 16);
        map.put(FN_CHECKER, 16);
        map.put(FN_STATUS, 16);
        map.put(FN_REMARKS, 250);
        map.put(FN_CONTROLUNITNO, 32);
        map.put(FN_WORKFLOWNO,64);
        map.put(FN_STEPNO, 32);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
