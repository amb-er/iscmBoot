package com.armitage.server.iscm.purchasemanage.pricemanage.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmPurPriceAssign")  
public class ScmPurPriceAssign extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_PMID ="pmId";
    public static final String FN_FINORGUNITNO ="finOrgUnitNo";
    public static final String FN_FINORGUNITNAME ="finOrgUnitName";
	public static final String FN_ISBIZUNIT ="isBizUnit";
    
    private long id;
    private long pmId;
    private String finOrgUnitNo;
    private String finOrgUnitName;
	private boolean isBizUnit;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public long getPmId() {
        return pmId;
    }

    public void setPmId(long val) {
        this.pmId = val;
    }
    public String getFinOrgUnitNo() {
        return finOrgUnitNo;
    }

    public void setFinOrgUnitNo(String val) {
        this.finOrgUnitNo = val;
    }
    
    public String getFinOrgUnitName() {
		return finOrgUnitName;
	}

	public void setFinOrgUnitName(String finOrgUnitName) {
		this.finOrgUnitName = finOrgUnitName;
	}

	public boolean isIsBizUnit() {
		return isBizUnit;
	}

	public void setIsBizUnit(boolean isBizUnit) {
		this.isBizUnit = isBizUnit;
	}

    public ScmPurPriceAssign(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmPurPriceAssign() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_PMID,
            FN_FINORGUNITNO,
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
        map.put(FN_FINORGUNITNO, 96);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}

