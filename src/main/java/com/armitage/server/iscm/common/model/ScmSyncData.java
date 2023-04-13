package com.armitage.server.iscm.common.model;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmSyncData")  
public class ScmSyncData extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_TASKCODE ="taskCode";
    public static final String FN_DATACLASS ="dataClass";
    public static final String FN_DATAID ="dataId";
    public static final String FN_PLATSUPPLIERID ="platSupplierId";
    public static final String FN_BILLTYPE ="billType";
    public static final String FN_BILLNO ="billNo";
    public static final String FN_SYNCTYPE ="syncType";
    public static final String FN_OPERATOR ="operator";
    public static final String FN_CREATEDATE ="createDate";
    public static final String FN_UPDATETIME ="updateTime";
    public static final String FN_LASTTASKTIME ="lastTaskTime";
    public static final String FN_TASKCOUNT ="taskCount";
    public static final String FN_SYNCTIME ="syncTime";
    public static final String FN_SYNCHRON ="synchron";
    public static final String FN_ERRORMESSAGE ="errorMessage";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    
    private long id;
    private String orgUnitNo;
    private String taskCode;
    private String dataClass;
    private long dataId;
    private long platSupplierId;
    private String billType;
    private String billNo;
    private String syncType;
    private String operator;
    private Date createDate;
    private Date updateTime;
    private Date lastTaskTime;
    private int taskCount;
    private Date syncTime;
    private String synchron;
    private String errorMessage;
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
    public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public String getDataClass() {
        return dataClass;
    }

    public void setDataClass(String val) {
        this.dataClass = val;
    }
    public long getDataId() {
        return dataId;
    }

    public void setDataId(long val) {
        this.dataId = val;
    }
    public long getPlatSupplierId() {
		return platSupplierId;
	}

	public void setPlatSupplierId(long platSupplierId) {
		this.platSupplierId = platSupplierId;
	}

	public String getBillType() {
        return billType;
    }

    public void setBillType(String val) {
        this.billType = val;
    }
    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String val) {
        this.billNo = val;
    }
    public String getSyncType() {
        return syncType;
    }

    public void setSyncType(String val) {
        this.syncType = val;
    }
    public String getOperator() {
        return operator;
    }

    public void setOperator(String val) {
        this.operator = val;
    }
    
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getLastTaskTime() {
        return lastTaskTime;
    }

    public void setLastTaskTime(Date val) {
        this.lastTaskTime = val;
    }
    public int getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(int val) {
        this.taskCount = val;
    }
    public Date getSyncTime() {
        return syncTime;
    }

    public void setSyncTime(Date val) {
        this.syncTime = val;
    }
    
	public String getSynchron() {
		return synchron;
	}

	public void setSynchron(String synchron) {
		this.synchron = synchron;
	}

	public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String val) {
        this.errorMessage = val;
    }
    public String getControlUnitNo() {
        return controlUnitNo;
    }

    public void setControlUnitNo(String val) {
        this.controlUnitNo = val;
    }

    public ScmSyncData(boolean defaultValue){
       if(defaultValue){
    	   this.platSupplierId=0;
       }
    }
  	public ScmSyncData() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_ORGUNITNO,
            FN_TASKCODE,
            FN_DATACLASS,
            FN_DATAID,
            FN_PLATSUPPLIERID,
            FN_BILLTYPE,
            FN_BILLNO,
            FN_SYNCTYPE,
            FN_OPERATOR,
            FN_CREATEDATE,
            FN_UPDATETIME,
            FN_LASTTASKTIME,
            FN_TASKCOUNT,
            FN_SYNCTIME,
            FN_SYNCHRON,
            FN_ERRORMESSAGE,
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
        map.put(FN_TASKCODE, 16);
        map.put(FN_DATACLASS, 16);
        map.put(FN_BILLTYPE, 16);
        map.put(FN_BILLNO, 16);
        map.put(FN_SYNCTYPE, 32);
        map.put(FN_OPERATOR, 16);
        map.put(FN_ERRORMESSAGE, 255);
        map.put(FN_CONTROLUNITNO, 32);
        map.put(FN_SYNCHRON, 1);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
