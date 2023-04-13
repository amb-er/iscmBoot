package com.armitage.server.iscm.purchasemanage.pricemanage.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmPurQuotationPlanEntry")  
public class ScmPurQuotationPlanEntry extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_PLANID ="planId";
    public static final String FN_LINEID ="lineId";
    public static final String FN_ITEMID ="itemId";
    public static final String FN_PURUNIT ="purUnit";
    public static final String FN_PLACEREQUIRE ="placeRequire";
    public static final String FN_REMARKS ="remarks";
    
    private long id;
    private long planId;
    private int lineId;
    private long itemId;
    private long purUnit;
    private String placeRequire;
    private String remarks;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public long getPlanId() {
        return planId;
    }

    public void setPlanId(long val) {
        this.planId = val;
    }
    public int getLineId() {
        return lineId;
    }

    public void setLineId(int val) {
        this.lineId = val;
    }
    public long getItemId() {
        return itemId;
    }

    public void setItemId(long val) {
        this.itemId = val;
    }
    public long getPurUnit() {
        return purUnit;
    }

    public void setPurUnit(long val) {
        this.purUnit = val;
    }

    public String getPlaceRequire() {
		return placeRequire;
	}

	public void setPlaceRequire(String placeRequire) {
		this.placeRequire = placeRequire;
	}

	public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String val) {
        this.remarks = val;
    }

    public ScmPurQuotationPlanEntry(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmPurQuotationPlanEntry() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_PLANID,
            FN_LINEID,
            FN_ITEMID,
            FN_PURUNIT,
            FN_PLACEREQUIRE,
            FN_REMARKS,
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
        map.put(FN_REMARKS, 255);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
