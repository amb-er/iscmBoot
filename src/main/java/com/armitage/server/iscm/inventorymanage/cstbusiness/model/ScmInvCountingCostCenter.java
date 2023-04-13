package com.armitage.server.iscm.inventorymanage.cstbusiness.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmInvCountingCostCenter")  
public class ScmInvCountingCostCenter extends BaseModel  {
     
    public static final String FN_TABLEID ="tableId";
    public static final String FN_TASKID ="taskId";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_USEORGUNITNO ="useOrgUnitNo";
    public static final String FN_BIZDATE ="bizDate";
    public static final String FN_TASKBEGTIME ="taskBegTime";
    public static final String FN_TASKENDTIME ="taskEndTime";
    public static final String FN_COUNTINGPERSON ="countingPerson";
    public static final String FN_COUNTINGAGAINPERSON ="countingAgainPerson";
    public static final String FN_COUNTINGMONITORER ="countingMonitorer";
    public static final String FN_CREATOR ="creator";
    public static final String FN_CREATEDATE ="createDate";
    public static final String FN_EDITOR ="editor";
    public static final String FN_EDITDATE ="editDate";
    public static final String FN_STATUS ="status";
    public static final String FN_REMARKS ="remarks";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    
    private long tableId;
    private long taskId;
    private String orgUnitNo;
    private String useOrgUnitNo;
    private Date bizDate;
    private Date taskBegTime;
    private Date taskEndTime;
    private String countingPerson;
    private String countingAgainPerson;
    private String countingMonitorer;
    private String creator;
    private Date createDate;
    private String editor;
    private Date editDate;
    private String status;
    private String remarks;
    private String controlUnitNo;

    public long getTableId() {
        return tableId;
    }

    public void setTableId(long val) {
        this.tableId = val;
    }
    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long val) {
        this.taskId = val;
    }
    public String getOrgUnitNo() {
        return orgUnitNo;
    }

    public void setOrgUnitNo(String val) {
        this.orgUnitNo = val;
    }
    public String getUseOrgUnitNo() {
        return useOrgUnitNo;
    }

    public void setUseOrgUnitNo(String val) {
        this.useOrgUnitNo = val;
    }
    public Date getBizDate() {
        return bizDate;
    }

    public void setBizDate(Date val) {
        this.bizDate = val;
    }
    public Date getTaskBegTime() {
        return taskBegTime;
    }

    public void setTaskBegTime(Date val) {
        this.taskBegTime = val;
    }
    public Date getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(Date val) {
        this.taskEndTime = val;
    }
    public String getCountingPerson() {
        return countingPerson;
    }

    public void setCountingPerson(String val) {
        this.countingPerson = val;
    }
    public String getCountingAgainPerson() {
        return countingAgainPerson;
    }

    public void setCountingAgainPerson(String val) {
        this.countingAgainPerson = val;
    }
    public String getCountingMonitorer() {
        return countingMonitorer;
    }

    public void setCountingMonitorer(String val) {
        this.countingMonitorer = val;
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

    public ScmInvCountingCostCenter(boolean defaultValue){
       if(defaultValue){
       }
    }
    public ScmInvCountingCostCenter() {

    }
    public String[] getFieldNames(){
        return new String[]{
            FN_TABLEID,
            FN_TASKID,
            FN_ORGUNITNO,
            FN_USEORGUNITNO,
            FN_BIZDATE,
            FN_TASKBEGTIME,
            FN_TASKENDTIME,
            FN_COUNTINGPERSON,
            FN_COUNTINGAGAINPERSON,
            FN_COUNTINGMONITORER,
            FN_CREATOR,
            FN_CREATEDATE,
            FN_EDITOR,
            FN_EDITDATE,
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
        map.put(FN_ORGUNITNO, 32);
        map.put(FN_USEORGUNITNO, 32);
        map.put(FN_COUNTINGPERSON, 16);
        map.put(FN_COUNTINGAGAINPERSON, 16);
        map.put(FN_COUNTINGMONITORER, 16);
        map.put(FN_CREATOR, 16);
        map.put(FN_EDITOR, 16);
        map.put(FN_STATUS, 16);
        map.put(FN_REMARKS, 250);
        map.put(FN_CONTROLUNITNO, 32);
        return map; 
    }
     public String getPkKey() {
         
        return FN_TABLEID;
    }
 
    public long getPK() {
         
        return tableId;
    }


}
