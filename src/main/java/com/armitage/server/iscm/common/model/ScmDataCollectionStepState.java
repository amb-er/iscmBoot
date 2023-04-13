package com.armitage.server.iscm.common.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "dataCollectionStepState")  
public class ScmDataCollectionStepState extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_REQNO ="reqNo";
    public static final String FN_STEPID ="stepId";
    public static final String FN_STATE ="state";
    public static final String FN_LASTRUNTIME ="lastRunTime";
    public static final String FN_MSGINFO ="msgInfo";
	public static final String SATATE_WAIT = "W";
	public static final String SATATE_RUN = "R";
	public static final String SATATE_SUCCESS = "S";
	public static final String SATATE_FAIL = "F";

	private int id;
    private String orgUnitNo;
    private String reqNo;
    private int stepId;
    private String state;
    private Date lastRunTime;
    private String msgInfo;

    public int getId() {
        return id;
    }

    public void setId(int val) {
        this.id = val;
    }
    public String getOrgUnitNo() {
        return orgUnitNo;
    }

    public void setOrgUnitNo(String val) {
        this.orgUnitNo = val;
    }
    public String getReqNo() {
		return reqNo;
	}

	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}

	public int getStepId() {
        return stepId;
    }

    public void setStepId(int val) {
        this.stepId = val;
    }
    public String getState() {
        return state;
    }

    public void setState(String val) {
        this.state = val;
    }
    public Date getLastRunTime() {
        return lastRunTime;
    }

    public void setLastRunTime(Date val) {
        this.lastRunTime = val;
    }
    

    public String getMsgInfo() {
		return msgInfo;
	}

	public void setMsgInfo(String msgInfo) {
		this.msgInfo = msgInfo;
	}

	public ScmDataCollectionStepState(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmDataCollectionStepState() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_ORGUNITNO,
            FN_STEPID,
            FN_STATE,
            FN_LASTRUNTIME,
            FN_MSGINFO,
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
        map.put(FN_STATE, 16);
        map.put(FN_MSGINFO, 500);
 		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
