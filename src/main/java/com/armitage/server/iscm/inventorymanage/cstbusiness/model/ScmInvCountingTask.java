package com.armitage.server.iscm.inventorymanage.cstbusiness.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmInvCountingTask")  
public class ScmInvCountingTask extends BaseModel  {
     
    public static final String FN_TASKID ="taskId";
    public static final String FN_TASKNO ="taskNo";
    public static final String FN_BIZDATE ="bizDate";
    public static final String FN_COUNTINGBEGTIME ="countingBegTime";
    public static final String FN_COUNTINGENDTIME ="countingEndTime";
    public static final String FN_FINORGUNITNO ="finOrgUnitNo";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_COSTCENTER ="costCenter";
    public static final String FN_FROMWHNO ="fromWhNo";
    public static final String FN_TOWHNO ="toWhNo";
    public static final String FN_FROMDEPTNO ="fromDeptNo";
    public static final String FN_TODEPTNO ="toDeptNo";
    public static final String FN_FROMITEMNO ="fromItemNo";
    public static final String FN_TOITEMNO ="toItemNo";
    public static final String FN_ASSIGNLOCATION ="assignLocation";
    public static final String FN_ASSIGNITEM ="assignItem";
    public static final String FN_FREEITEM ="freeItem";
    public static final String FN_GENTABLEFORZERO ="genTableForZero";
    public static final String FN_GENACCQTY ="genAccQty";
    public static final String FN_PERIODID ="periodId";
    public static final String FN_ACCOUNTYEAR ="accountYear";
    public static final String FN_ACCOUNTPERIOD ="accountPeriod";
    public static final String FN_CREATOR ="creator";
    public static final String FN_CREATEDATE ="createDate";
    public static final String FN_EDITOR ="editor";
    public static final String FN_EDITDATE ="editDate";
    public static final String FN_CHECKER ="checker";
    public static final String FN_CHECKDATE ="checkDate";
    public static final String FN_STATUS ="status";
    public static final String FN_REMARKS ="remarks";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    public static final String FN_CONFIRMEDBY ="confirmedBy";
    public static final String FN_PLANBIZDATE ="planBizDate";
    public static final String FN_ASSIGNITEMGROUP ="assignItemGroup";
    
    private long taskId;
    private String taskNo;
    private Date bizDate;
    private Date countingBegTime;
    private Date countingEndTime;
    private String finOrgUnitNo;
    private String orgUnitNo;
    private boolean costCenter;
    private String fromWhNo;
    private String toWhNo;
    private String fromDeptNo;
    private String toDeptNo;
    private String fromItemNo;
    private String toItemNo;
    private boolean assignLocation;
    private boolean assignItem;
    private boolean assignItemGroup;
    private boolean freeItem;
    private boolean genTableForZero;
    private boolean genAccQty;
    private long periodId;
    private int accountYear;
    private int accountPeriod;
    private String creator;
    private Date createDate;
    private String editor;
    private Date editDate;
    private String checker;
    private Date checkDate;
    private String status;
    private String remarks;
    private String controlUnitNo;
    private String confirmedBy;
    private Date planBizDate;
    
    public long getTaskId() {
        return taskId;
    }
    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }
    public String getTaskNo() {
        return taskNo;
    }
    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }
    public Date getBizDate() {
        return bizDate;
    }
    public void setBizDate(Date bizDate) {
        this.bizDate = bizDate;
    }
    public Date getCountingBegTime() {
        return countingBegTime;
    }
    public void setCountingBegTime(Date countingBegTime) {
        this.countingBegTime = countingBegTime;
    }
    public Date getCountingEndTime() {
        return countingEndTime;
    }
    public void setCountingEndTime(Date countingEndTime) {
        this.countingEndTime = countingEndTime;
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
    public boolean isCostCenter() {
        return costCenter;
    }
    public void setCostCenter(boolean costCenter) {
        this.costCenter = costCenter;
    }
    public String getFromWhNo() {
        return fromWhNo;
    }
    public void setFromWhNo(String fromWhNo) {
        this.fromWhNo = fromWhNo;
    }
    public String getToWhNo() {
        return toWhNo;
    }
    public void setToWhNo(String toWhNo) {
        this.toWhNo = toWhNo;
    }
    public String getFromDeptNo() {
        return fromDeptNo;
    }
    public void setFromDeptNo(String fromDeptNo) {
        this.fromDeptNo = fromDeptNo;
    }
    public String getToDeptNo() {
        return toDeptNo;
    }
    public void setToDeptNo(String toDeptNo) {
        this.toDeptNo = toDeptNo;
    }
    public String getFromItemNo() {
        return fromItemNo;
    }
    public void setFromItemNo(String fromItemNo) {
        this.fromItemNo = fromItemNo;
    }
    public String getToItemNo() {
        return toItemNo;
    }
    public void setToItemNo(String toItemNo) {
        this.toItemNo = toItemNo;
    }
    public boolean isAssignLocation() {
        return assignLocation;
    }
    public void setAssignLocation(boolean assignLocation) {
        this.assignLocation = assignLocation;
    }
    public boolean isAssignItem() {
        return assignItem;
    }
    public void setAssignItem(boolean assignItem) {
        this.assignItem = assignItem;
    }
    public boolean isFreeItem() {
        return freeItem;
    }
    public void setFreeItem(boolean freeItem) {
        this.freeItem = freeItem;
    }
    public boolean isGenTableForZero() {
        return genTableForZero;
    }
    public void setGenTableForZero(boolean genTableForZero) {
        this.genTableForZero = genTableForZero;
    }
    public boolean isGenAccQty() {
        return genAccQty;
    }
    public void setGenAccQty(boolean genAccQty) {
        this.genAccQty = genAccQty;
    }
    public long getPeriodId() {
        return periodId;
    }
    public void setPeriodId(long periodId) {
        this.periodId = periodId;
    }
    public int getAccountYear() {
        return accountYear;
    }
    public void setAccountYear(int accountYear) {
        this.accountYear = accountYear;
    }
    public int getAccountPeriod() {
        return accountPeriod;
    }
    public void setAccountPeriod(int accountPeriod) {
        this.accountPeriod = accountPeriod;
    }
    public String getCreator() {
        return creator;
    }
    public void setCreator(String creator) {
        this.creator = creator;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public String getEditor() {
        return editor;
    }
    public void setEditor(String editor) {
        this.editor = editor;
    }
    public Date getEditDate() {
        return editDate;
    }
    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }
    public String getChecker() {
        return checker;
    }
    public void setChecker(String checker) {
        this.checker = checker;
    }
    public Date getCheckDate() {
        return checkDate;
    }
    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public String getControlUnitNo() {
        return controlUnitNo;
    }
    public void setControlUnitNo(String controlUnitNo) {
        this.controlUnitNo = controlUnitNo;
    }
    public String getConfirmedBy() {
		return confirmedBy;
	}
	public void setConfirmedBy(String confirmedBy) {
		this.confirmedBy = confirmedBy;
	}
	public Date getPlanBizDate() {
		return planBizDate;
	}
	public void setPlanBizDate(Date planBizDate) {
		this.planBizDate = planBizDate;
	}
	public boolean isAssignItemGroup() {
		return assignItemGroup;
	}
	public void setAssignItemGroup(boolean assignItemGroup) {
		this.assignItemGroup = assignItemGroup;
	}
	public ScmInvCountingTask(boolean defaultValue){
       if(defaultValue){
    	   this.costCenter=false;
           this.assignLocation=false;
           this.assignItem=false;
           this.freeItem=false;
           this.genTableForZero=false;
           this.genAccQty=false;
           this.periodId=0;
           this.assignItemGroup=false;
       }
    }
    public ScmInvCountingTask() {

    }
    public String[] getFieldNames(){
        return new String[]{
            FN_TASKID,
            FN_TASKNO,
            FN_BIZDATE,
            FN_COUNTINGBEGTIME,
            FN_COUNTINGENDTIME,
            FN_FINORGUNITNO,
            FN_ORGUNITNO,
            FN_COSTCENTER,
            FN_FROMWHNO,
            FN_TOWHNO,
            FN_FROMDEPTNO,
            FN_TODEPTNO,
            FN_FROMITEMNO,
            FN_TOITEMNO,
            FN_ASSIGNLOCATION,
            FN_ASSIGNITEM,
            FN_FREEITEM,
            FN_GENTABLEFORZERO,
            FN_GENACCQTY,
            FN_PERIODID,
            FN_ACCOUNTYEAR,
            FN_ACCOUNTPERIOD,
            FN_CREATOR,
            FN_CREATEDATE,
            FN_EDITOR,
            FN_EDITDATE,
            FN_CHECKER,
            FN_CHECKDATE,
            FN_STATUS,
            FN_REMARKS,
            FN_CONTROLUNITNO,
            FN_CONFIRMEDBY
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
        map.put(FN_TASKNO, 16);
        map.put(FN_ORGUNITNO, 32);
        map.put(FN_ORGUNITNO, 32);
        map.put(FN_FROMWHNO, 16);
        map.put(FN_TOWHNO, 16);
        map.put(FN_FROMDEPTNO, 32);
        map.put(FN_TODEPTNO, 32);
        map.put(FN_FROMITEMNO, 16);
        map.put(FN_TOITEMNO, 16);
        map.put(FN_CREATOR, 16);
        map.put(FN_EDITOR, 16);
        map.put(FN_CHECKER, 16);
        map.put(FN_STATUS, 16);
        map.put(FN_REMARKS, 250);
        map.put(FN_CONTROLUNITNO, 32);
        map.put(FN_CONFIRMEDBY,16);
        return map; 
    }
     public String getPkKey() {
         
        return FN_TASKID;
    }
 
    public long getPK() {
         
        return taskId;
    }


}
