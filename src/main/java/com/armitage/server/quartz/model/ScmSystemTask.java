package com.armitage.server.quartz.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmSystemTask")  
public class ScmSystemTask extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_TASKTYPE ="taskType";
    public static final String FN_TASKNAME ="taskName";
    public static final String FN_TASKGROUP ="taskGroup";
    public static final String FN_CRONEXPRESSION ="cronExpression";
    public static final String FN_TASKCLASS ="taskClass";
    public static final String FN_FLAG ="flag";
    public static final String FN_UPDATETIME ="updateTime";
    public static final String FN_SIZE ="size";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    
    private long id;
    private String orgUnitNo;
    private String taskType;
    private String taskName;
    private String taskGroup;
    private String cronExpression;
    private String taskClass;
    private boolean flag;
    private Date updateTime;
    private int size;
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
    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String val) {
        this.taskType = val;
    }
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String val) {
        this.taskName = val;
    }
    public String getTaskGroup() {
        return taskGroup;
    }

    public void setTaskGroup(String val) {
        this.taskGroup = val;
    }
    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String val) {
        this.cronExpression = val;
    }
    public String getTaskClass() {
        return taskClass;
    }

    public void setTaskClass(String val) {
        this.taskClass = val;
    }
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean val) {
        this.flag = val;
    }
    public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getControlUnitNo() {
        return controlUnitNo;
    }

    public void setControlUnitNo(String val) {
        this.controlUnitNo = val;
    }

    public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public ScmSystemTask(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmSystemTask() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_ORGUNITNO,
            FN_TASKTYPE,
            FN_TASKNAME,
            FN_TASKGROUP,
            FN_CRONEXPRESSION,
            FN_TASKCLASS,
            FN_FLAG,
            FN_UPDATETIME,
            FN_SIZE,
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
        map.put(FN_TASKTYPE, 32);
        map.put(FN_TASKNAME, 100);
        map.put(FN_TASKGROUP, 32);
        map.put(FN_CRONEXPRESSION, 64);
        map.put(FN_TASKCLASS, 100);
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
