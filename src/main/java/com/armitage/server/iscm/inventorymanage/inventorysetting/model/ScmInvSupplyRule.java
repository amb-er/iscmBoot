
package com.armitage.server.iscm.inventorymanage.inventorysetting.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmInvSupplyRule")  
public class ScmInvSupplyRule extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_RULENAME ="ruleName";
    public static final String FN_WAREHOUSEID ="wareHouseId";
    public static final String FN_REQORGUNITNO ="reqOrgUnitNo";
    public static final String FN_INCLUDEPR ="includePr";
    public static final String FN_INCLUDEPO ="includePo";
    public static final String FN_INCLUDEWR ="includeWr";
    public static final String FN_INCLUDEWT ="includeWt";
    public static final String FN_INCLUDEOR ="includeOr";
    public static final String FN_INCLUDESO ="includeSo";
    public static final String FN_INCLUDERO ="includeRo";
    public static final String FN_INCLUDEWO ="includeWo";
    public static final String FN_INCLUDEOO ="includeOo";
    public static final String FN_BIZTYPE ="bizType";
    public static final String FN_PURORGUNITNO ="purOrgUnitNo";
    public static final String FN_CREATOR ="creator";
    public static final String FN_CREATEDATE ="createDate";
    public static final String FN_CREATEORGUNITNO ="createOrgUnitNo";
    public static final String FN_SUBMITTER ="submitter";
    public static final String FN_SUBMITDATE ="submitDate";
    public static final String FN_EDITOR ="editor";
    public static final String FN_EDITDATE ="editDate";
    public static final String FN_WORKFLOWNO ="workFlowNo";
    public static final String FN_STEPNO ="stepNo";
    public static final String FN_CHECKER ="checker";
    public static final String FN_CHECKDATE ="checkDate";
    public static final String FN_STATUS ="status";
    public static final String FN_REMARKS ="remarks";
    public static final String FN_EXECTIME ="execTime";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    
    private long id;
    private String orgUnitNo;
    private String ruleName;
    private long wareHouseId;
    private String reqOrgUnitNo;
    private boolean includePr;
    private boolean includePo;
    private boolean includeWr;
    private boolean includeWt;
    private boolean includeOr;
    private boolean includeSo;
    private boolean includeRo;
    private boolean includeWo;
    private boolean includeOo;
    private String bizType;
    private String purOrgUnitNo;
    private String creator;
    private Date createDate;
    private String createOrgUnitNo;
    private String submitter;
    private Date submitDate;
    private String editor;
    private Date editDate;
    private String workFlowNo;
    private String stepNo;
    private String checker;
    private Date checkDate;
    private String status;
    private String remarks;
    private Date execTime;
    private String controlUnitNo;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public String getOrgUnitNo() {
        return orgUnitNo;
    }

    public void setOrgUnitNo(String val) {
        this.orgUnitNo = val;
    }
    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String val) {
        this.ruleName = val;
    }
    public long getWareHouseId() {
        return wareHouseId;
    }

    public void setWareHouseId(long val) {
        this.wareHouseId = val;
    }
    public String getReqOrgUnitNo() {
        return reqOrgUnitNo;
    }

    public void setReqOrgUnitNo(String val) {
        this.reqOrgUnitNo = val;
    }
    public boolean getIncludePr() {
        return includePr;
    }

    public void setIncludePr(boolean val) {
        this.includePr = val;
    }
    public boolean getIncludePo() {
        return includePo;
    }

    public void setIncludePo(boolean val) {
        this.includePo = val;
    }
    public boolean getIncludeWr() {
        return includeWr;
    }

    public void setIncludeWr(boolean val) {
        this.includeWr = val;
    }
    public boolean getIncludeWt() {
        return includeWt;
    }

    public void setIncludeWt(boolean val) {
        this.includeWt = val;
    }
    public boolean getIncludeOr() {
        return includeOr;
    }

    public void setIncludeOr(boolean val) {
        this.includeOr = val;
    }
    public boolean getIncludeSo() {
        return includeSo;
    }

    public void setIncludeSo(boolean val) {
        this.includeSo = val;
    }
    public boolean getIncludeRo() {
        return includeRo;
    }

    public void setIncludeRo(boolean val) {
        this.includeRo = val;
    }
    public boolean getIncludeWo() {
        return includeWo;
    }

    public void setIncludeWo(boolean val) {
        this.includeWo = val;
    }
    public boolean getIncludeOo() {
        return includeOo;
    }

    public void setIncludeOo(boolean val) {
        this.includeOo = val;
    }
    public String getBizType() {
        return bizType;
    }

    public void setBizType(String val) {
        this.bizType = val;
    }
    public String getPurOrgUnitNo() {
        return purOrgUnitNo;
    }

    public void setPurOrgUnitNo(String val) {
        this.purOrgUnitNo = val;
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
    public Date getExecTime() {
        return execTime;
    }

    public void setExecTime(Date val) {
        this.execTime = val;
    }
    public String getControlUnitNo() {
        return controlUnitNo;
    }

    public void setControlUnitNo(String val) {
        this.controlUnitNo = val;
    }

    public ScmInvSupplyRule(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmInvSupplyRule() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_ORGUNITNO,
            FN_RULENAME,
            FN_WAREHOUSEID,
            FN_REQORGUNITNO,
            FN_INCLUDEPR,
            FN_INCLUDEPO,
            FN_INCLUDEWR,
            FN_INCLUDEWT,
            FN_INCLUDEOR,
            FN_INCLUDESO,
            FN_INCLUDERO,
            FN_INCLUDEWO,
            FN_INCLUDEOO,
            FN_BIZTYPE,
            FN_PURORGUNITNO,
            FN_CREATOR,
            FN_CREATEDATE,
            FN_CREATEORGUNITNO,
            FN_SUBMITTER,
            FN_SUBMITDATE,
            FN_EDITOR,
            FN_EDITDATE,
            FN_WORKFLOWNO,
            FN_STEPNO,
            FN_CHECKER,
            FN_CHECKDATE,
            FN_STATUS,
            FN_REMARKS,
            FN_EXECTIME,
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
		return new String[] {FN_RULENAME,FN_WAREHOUSEID,FN_REQORGUNITNO,FN_BIZTYPE,FN_PURORGUNITNO};
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
        map.put(FN_ORGUNITNO, 32);
        map.put(FN_RULENAME, 60);
        map.put(FN_REQORGUNITNO, 32);
        map.put(FN_BIZTYPE, 16);
        map.put(FN_PURORGUNITNO, 32);
        map.put(FN_CREATOR, 16);
        map.put(FN_CREATEORGUNITNO, 32);
        map.put(FN_SUBMITTER, 16);
        map.put(FN_EDITOR, 16);
        map.put(FN_WORKFLOWNO, 164);
        map.put(FN_STEPNO, 32);
        map.put(FN_CHECKER, 16);
        map.put(FN_STATUS, 16);
        map.put(FN_REMARKS, 250);
        map.put(FN_CONTROLUNITNO, 32);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
