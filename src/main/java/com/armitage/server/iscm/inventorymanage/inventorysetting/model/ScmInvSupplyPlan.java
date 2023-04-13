
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
 
@XmlRootElement(name = "scmInvSupplyPlan")  
public class ScmInvSupplyPlan extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_RULEID ="ruleId";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_WAREHOUSEID ="wareHouseId";
    public static final String FN_REQORGUNITNO ="reqOrgUnitNo";
    public static final String FN_BIZTYPE ="bizType";
    public static final String FN_PURORGUNITNO ="purOrgUnitNo";
    public static final String FN_EXECUTOR ="executor";
    public static final String FN_EXECTIME ="execTime";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    
    private long id;
    private long ruleId;
    private String orgUnitNo;
    private long wareHouseId;
    private String reqOrgUnitNo;
    private String bizType;
    private String purOrgUnitNo;
    private String executor;
    private Date execTime;
    private String controlUnitNo;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public long getRuleId() {
        return ruleId;
    }

    public void setRuleId(long val) {
        this.ruleId = val;
    }
    public String getOrgUnitNo() {
        return orgUnitNo;
    }

    public void setOrgUnitNo(String val) {
        this.orgUnitNo = val;
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
    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String val) {
        this.executor = val;
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

    public ScmInvSupplyPlan(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmInvSupplyPlan() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_RULEID,
            FN_ORGUNITNO,
            FN_WAREHOUSEID,
            FN_REQORGUNITNO,
            FN_BIZTYPE,
            FN_PURORGUNITNO,
            FN_EXECUTOR,
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
        map.put(FN_REQORGUNITNO, 32);
        map.put(FN_BIZTYPE, 24);
        map.put(FN_PURORGUNITNO, 32);
        map.put(FN_EXECUTOR, 24);
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
