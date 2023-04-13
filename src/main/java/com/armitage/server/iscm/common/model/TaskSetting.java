package com.armitage.server.iscm.common.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "taskSetting")  
public class TaskSetting extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_TASKID ="taskId";
    public static final String FN_STARTED ="started";
    public static final String FN_CYCLESETTING ="cycleSetting";
    public static final String FN_CYCLETIME ="cycleTime";
    public static final String FN_MAINTAINCYCLETIME ="maintainCycleTime";
    public static final String FN_DATASCOPE ="dataScope";
    public static final String FN_MAXDAYS ="maxDays";
    public static final String FN_TASKDAYS ="taskDays";
    public static final String FN_LOTQTY ="lotQty";
    public static final String FN_CREATOR ="creator";
    public static final String FN_CREATEDATE ="createDate";
    public static final String FN_EDITOR ="editor";
    public static final String FN_EDITDATE ="editDate";
    public static final String FN_REMARKS ="remarks";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    
    private long id;
    private long taskId;
    private boolean started;
    private boolean cycleSetting;
    private int cycleTime;
    private int maintainCycleTime;
    private String dataScope;
    private int maxDays;
    private int taskDays;
    private int lotQty;
    private String creator;
    private Date createDate;
    private String editor;
    private Date editDate;
    private String remarks;
    private String controlUnitNo;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long val) {
        this.taskId = val;
    }
    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean val) {
        this.started = val;
    }
    public boolean isCycleSetting() {
        return cycleSetting;
    }

    public void setCycleSetting(boolean val) {
        this.cycleSetting = val;
    }
    public int getCycleTime() {
        return cycleTime;
    }

    public void setCycleTime(int val) {
        this.cycleTime = val;
    }
    public int getMaintainCycleTime() {
        return maintainCycleTime;
    }

    public void setMaintainCycleTime(int val) {
        this.maintainCycleTime = val;
    }
    public String getDataScope() {
        return dataScope;
    }

    public void setDataScope(String val) {
        this.dataScope = val;
    }
    public int getMaxDays() {
		return maxDays;
	}

	public void setMaxDays(int maxDays) {
		this.maxDays = maxDays;
	}

	public int getTaskDays() {
        return taskDays;
    }

    public void setTaskDays(int val) {
        this.taskDays = val;
    }
    public int getLotQty() {
        return lotQty;
    }

    public void setLotQty(int val) {
        this.lotQty = val;
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

    public TaskSetting(boolean defaultValue){
       if(defaultValue){
    	   this.started=true;
    	   this.cycleSetting=true;
    	   this.cycleTime=300;
    	   this.maintainCycleTime=300;
    	   this.dataScope="1";
    	   this.maxDays=30;
    	   this.taskDays=30;
    	   this.lotQty=10;
       }
    }
  	public TaskSetting() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_TASKID,
            FN_STARTED,
            FN_CYCLESETTING,
            FN_CYCLETIME,
            FN_MAINTAINCYCLETIME,
            FN_DATASCOPE,
            FN_MAXDAYS,
            FN_TASKDAYS,
            FN_LOTQTY,
            FN_CREATOR,
            FN_CREATEDATE,
            FN_EDITOR,
            FN_EDITDATE,
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
        map.put(FN_DATASCOPE, 16);
        map.put(FN_CREATOR, 16);
        map.put(FN_EDITOR, 16);
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
