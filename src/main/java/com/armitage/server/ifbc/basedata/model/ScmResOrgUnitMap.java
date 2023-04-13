package com.armitage.server.ifbc.basedata.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmResOrgUnitMap")  
public class ScmResOrgUnitMap extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_RESORGUNITNO ="resOrgUnitNo";
    public static final String FN_INVORGUNITNO ="invOrgUnitNo";
    public static final String FN_SHARED ="shared";
    public static final String FN_FBMRESORGUNITNO ="fbmResOrgUnitNo";
    public static final String FN_FBMCONTROLUNITNO ="fbmControlUnitNo";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    
    private long id;
    private String resOrgUnitNo;
    private String invOrgUnitNo;
    private boolean shared;
    private String fbmResOrgUnitNo;
    private String fbmControlUnitNo;
    private String controlUnitNo;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public String getResOrgUnitNo() {
        return resOrgUnitNo;
    }

    public void setResOrgUnitNo(String val) {
        this.resOrgUnitNo = val;
    }
    public String getInvOrgUnitNo() {
        return invOrgUnitNo;
    }

    public void setInvOrgUnitNo(String val) {
        this.invOrgUnitNo = val;
    }
    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean val) {
        this.shared = val;
    }
    public String getFbmResOrgUnitNo() {
        return fbmResOrgUnitNo;
    }

    public void setFbmResOrgUnitNo(String val) {
        this.fbmResOrgUnitNo = val;
    }
    public String getFbmControlUnitNo() {
        return fbmControlUnitNo;
    }

    public void setFbmControlUnitNo(String val) {
        this.fbmControlUnitNo = val;
    }
    public String getControlUnitNo() {
        return controlUnitNo;
    }

    public void setControlUnitNo(String val) {
        this.controlUnitNo = val;
    }

    public ScmResOrgUnitMap(boolean defaultValue){
       if(defaultValue){
    	   this.shared=true;
       }
    }
  	public ScmResOrgUnitMap() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_RESORGUNITNO,
            FN_INVORGUNITNO,
            FN_SHARED,
            FN_FBMRESORGUNITNO,
            FN_FBMCONTROLUNITNO,
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
		return new String[] {FN_RESORGUNITNO,FN_INVORGUNITNO };
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
        map.put(FN_RESORGUNITNO, 32);
        map.put(FN_INVORGUNITNO, 32);
        map.put(FN_FBMRESORGUNITNO, 32);
        map.put(FN_FBMCONTROLUNITNO, 32);
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
