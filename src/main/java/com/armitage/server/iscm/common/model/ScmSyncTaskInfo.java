package com.armitage.server.iscm.common.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmSyncTaskInfo")  
public class ScmSyncTaskInfo extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_BIZCODE ="bizCode";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_TASKCODE ="taskCode";
    public static final String FN_TASKACTION ="taskAction";
    public static final String FN_TASKTYPE ="taskType";
    public static final String FN_PRODUCTCODE ="productCode";
    public static final String FN_BEGDATE ="begDate";
    public static final String FN_ENDDATE ="endDate";
    public static final String FN_SYNCDATAID ="syncDataId";
    public static final String FN_TASKOWNER ="taskOwner";
    public static final String FN_TASKSTATUS ="taskStatus";
    public static final String FN_LOGTIME ="logtime";
    public static final String FN_TASKEXECUTOR ="taskExecutor";
    public static final String FN_STATUSMESSAGE ="statusMessage";
    public static final String FN_UPDATETIME ="updateTime";
    public static final String FN_CREATETIME ="createTime";
    public static final String FN_TASKSOURCE ="taskSource";
    public static final String FN_REMARKS ="remarks";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    
    private long id;
    private String bizCode;
    private String orgUnitNo;
    private String taskCode;
    private String taskAction;
    private String taskType;
    private String productCode;
    private Date begDate;
    private Date endDate;
    private long syncDataId;
    private String taskOwner;
    private String taskStatus;
    private Date logtime;
    private String taskExecutor;
    private String statusMessage;
    private Date updateTime;
    private Date createTime;
    private String taskSource;
    private String remarks;
    private String controlUnitNo;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	public String getOrgUnitNo() {
        return orgUnitNo;
    }

    public void setOrgUnitNo(String val) {
        this.orgUnitNo = val;
    }
    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String val) {
        this.taskCode = val;
    }
    public String getTaskAction() {
        return taskAction;
    }

    public void setTaskAction(String val) {
        this.taskAction = val;
    }
    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String val) {
        this.taskType = val;
    }
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String val) {
        this.productCode = val;
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
    public long getSyncDataId() {
        return syncDataId;
    }

    public void setSyncDataId(long val) {
        this.syncDataId = val;
    }
    public String getTaskOwner() {
        return taskOwner;
    }

    public void setTaskOwner(String val) {
        this.taskOwner = val;
    }
    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String val) {
        this.taskStatus = val;
    }
    public Date getLogtime() {
        return logtime;
    }

    public void setLogtime(Date val) {
        this.logtime = val;
    }
    public String getTaskExecutor() {
        return taskExecutor;
    }

    public void setTaskExecutor(String val) {
        this.taskExecutor = val;
    }
    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String val) {
        this.statusMessage = val;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date val) {
        this.updateTime = val;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date val) {
        this.createTime = val;
    }
    public String getTaskSource() {
        return taskSource;
    }

    public void setTaskSource(String val) {
        this.taskSource = val;
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

    public void setControlUnitNo(String val) {
        this.controlUnitNo = val;
    }

	public ScmSyncTaskInfo(boolean defaultValue){
       if(defaultValue){
    	   this.productCode="iSCM";
    	   this.taskStatus="W";
    	   this.taskSource="M";
    	   this.createTime=new Date();
       }
    }
  	public ScmSyncTaskInfo() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_BIZCODE,
            FN_ORGUNITNO,
            FN_TASKCODE,
            FN_TASKACTION,
            FN_TASKTYPE,
            FN_PRODUCTCODE,
            FN_BEGDATE,
            FN_ENDDATE,
            FN_SYNCDATAID,
            FN_TASKOWNER,
            FN_TASKSTATUS,
            FN_LOGTIME,
            FN_TASKEXECUTOR,
            FN_STATUSMESSAGE,
            FN_UPDATETIME,
            FN_CREATETIME,
            FN_TASKSOURCE,
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
//		list.add(new String[] { FN_TASKCODE });
		return list;
	}
	 
	public String[] getRequiredFields() {
		return new String[] {FN_TASKCODE };
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
        map.put(FN_BIZCODE, 16);
        map.put(FN_ORGUNITNO, 32);
        map.put(FN_TASKCODE, 16);
        map.put(FN_TASKACTION, 16);
        map.put(FN_TASKTYPE, 32);
        map.put(FN_PRODUCTCODE, 16);
        map.put(FN_TASKOWNER, 16);
        map.put(FN_TASKSTATUS, 16);
        map.put(FN_TASKEXECUTOR, 16);
        map.put(FN_STATUSMESSAGE, 255);
        map.put(FN_TASKSOURCE, 16);
        map.put(FN_REMARKS, 255);
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
