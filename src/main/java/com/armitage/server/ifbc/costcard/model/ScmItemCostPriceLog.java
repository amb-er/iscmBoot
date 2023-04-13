package com.armitage.server.ifbc.costcard.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmItemCostPriceLog")  
public class ScmItemCostPriceLog extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_BEGINDATE ="beginDate";
    public static final String FN_ENDDATE ="endDate";
    public static final String FN_PRICEDATE ="priceDate";
    public static final String FN_CLASSID ="classId";
    public static final String FN_ITEMID ="itemId";
    public static final String FN_OPERDATE ="operDate";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    
    private long id;
    private String orgUnitNo;
    private Date beginDate;
    private Date endDate;
    private Date priceDate;
    private long classId;
    private long itemId;
    private Date operDate;
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
    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date val) {
        this.beginDate = val;
    }
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date val) {
        this.endDate = val;
    }
    public Date getPriceDate() {
        return priceDate;
    }

    public void setPriceDate(Date val) {
        this.priceDate = val;
    }
    public long getClassId() {
        return classId;
    }

    public void setClassId(long val) {
        this.classId = val;
    }
    public long getItemId() {
        return itemId;
    }

    public void setItemId(long val) {
        this.itemId = val;
    }
    public Date getOperDate() {
        return operDate;
    }

    public void setOperDate(Date val) {
        this.operDate = val;
    }
    public String getControlUnitNo() {
        return controlUnitNo;
    }

    public void setControlUnitNo(String val) {
        this.controlUnitNo = val;
    }

    public ScmItemCostPriceLog(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmItemCostPriceLog() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_ORGUNITNO,
            FN_BEGINDATE,
            FN_ENDDATE,
            FN_PRICEDATE,
            FN_CLASSID,
            FN_ITEMID,
            FN_OPERDATE,
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
