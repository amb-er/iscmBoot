package com.armitage.server.iscm.purchasemanage.purchasesetting.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmPurSupplyRecOrg")  
public class ScmPurSupplyRecOrg extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_SUPPLYID ="supplyId";
    public static final String FN_RECEIVEORGUNITNO ="receiveOrgUnitNo";
    
    private long id;
    private long supplyId;
    private String receiveOrgUnitNo;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public long getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(long val) {
        this.supplyId = val;
    }
    public String getReceiveOrgUnitNo() {
        return receiveOrgUnitNo;
    }

    public void setReceiveOrgUnitNo(String val) {
        this.receiveOrgUnitNo = val;
    }

    public ScmPurSupplyRecOrg(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmPurSupplyRecOrg() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_SUPPLYID,
            FN_RECEIVEORGUNITNO,
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
        map.put(FN_RECEIVEORGUNITNO, 32);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
