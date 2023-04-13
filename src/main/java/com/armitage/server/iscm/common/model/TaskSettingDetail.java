package com.armitage.server.iscm.common.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "taskSettingDetail")  
public class TaskSettingDetail extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_SETID ="setId";
    public static final String FN_DATACLASS ="dataClass";
    public static final String FN_INTERACTIONMODE ="interactionMode";
    public static final String FN_TASKOBJECT ="taskObject";
    public static final String FN_TASKCREATEMODE ="taskCreateMode";
    public static final String FN_EXTENDEDPARAM1 ="extendedParam1";
    public static final String FN_EXTENDEDPARAM2 ="extendedParam2";
    public static final String FN_EXTENDEDPARAM3 ="extendedParam3";
    public static final String FN_EXTENDEDPARAM4 ="extendedParam4";
    public static final String FN_EXTENDEDPARAM5 ="extendedParam5";
    public static final String FN_FLAG ="flag";
    
    private long id;
    private long setId;
    private String dataClass;
    private String interactionMode;
    private String taskObject;
    private String taskCreateMode;
    private String extendedParam1;
    private String extendedParam2;
    private String extendedParam3;
    private String extendedParam4;
    private String extendedParam5;
    private boolean flag;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public long getSetId() {
        return setId;
    }

    public void setSetId(long val) {
        this.setId = val;
    }
    public String getDataClass() {
        return dataClass;
    }

    public void setDataClass(String val) {
        this.dataClass = val;
    }
    public String getInteractionMode() {
        return interactionMode;
    }

    public void setInteractionMode(String val) {
        this.interactionMode = val;
    }
    public String getTaskObject() {
        return taskObject;
    }

    public void setTaskObject(String val) {
        this.taskObject = val;
    }
    public String getTaskCreateMode() {
        return taskCreateMode;
    }

    public void setTaskCreateMode(String val) {
        this.taskCreateMode = val;
    }
    public String getExtendedParam1() {
        return extendedParam1;
    }

    public void setExtendedParam1(String val) {
        this.extendedParam1 = val;
    }
    public String getExtendedParam2() {
        return extendedParam2;
    }

    public void setExtendedParam2(String val) {
        this.extendedParam2 = val;
    }
    public String getExtendedParam3() {
        return extendedParam3;
    }

    public void setExtendedParam3(String val) {
        this.extendedParam3 = val;
    }
    public String getExtendedParam4() {
        return extendedParam4;
    }

    public void setExtendedParam4(String val) {
        this.extendedParam4 = val;
    }
    public String getExtendedParam5() {
        return extendedParam5;
    }

    public void setExtendedParam5(String val) {
        this.extendedParam5 = val;
    }
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean val) {
        this.flag = val;
    }

    public TaskSettingDetail(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public TaskSettingDetail() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_SETID,
            FN_DATACLASS,
            FN_INTERACTIONMODE,
            FN_TASKOBJECT,
            FN_TASKCREATEMODE,
            FN_EXTENDEDPARAM1,
            FN_EXTENDEDPARAM2,
            FN_EXTENDEDPARAM3,
            FN_EXTENDEDPARAM4,
            FN_EXTENDEDPARAM5,
            FN_FLAG,
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
        map.put(FN_DATACLASS, 16);
        map.put(FN_INTERACTIONMODE, 16);
        map.put(FN_TASKOBJECT, 64);
        map.put(FN_TASKCREATEMODE, 16);
        map.put(FN_EXTENDEDPARAM1, 16);
        map.put(FN_EXTENDEDPARAM2, 16);
        map.put(FN_EXTENDEDPARAM3, 16);
        map.put(FN_EXTENDEDPARAM4, 16);
        map.put(FN_EXTENDEDPARAM5, 16);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
