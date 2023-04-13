package com.armitage.server.iscm.basedata.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmPurchaseTypeOrg")  
public class ScmPurchaseTypeOrg extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_TYPEID ="typeId";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_FLAG ="flag";
	public static final String FN_SORT = "sort";
   
    private long id;
    private long typeId;
    private String orgUnitNo;
    private boolean flag;
	private int sort;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long val) {
        this.typeId = val;
    }
    public String getOrgUnitNo() {
        return orgUnitNo;
    }

    public void setOrgUnitNo(String val) {
        this.orgUnitNo = val;
    }
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean val) {
        this.flag = val;
    }

    public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public ScmPurchaseTypeOrg(boolean defaultValue){
       if(defaultValue){
    	   this.flag=true;
       }
    }
  	public ScmPurchaseTypeOrg() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_TYPEID,
            FN_ORGUNITNO,
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
        map.put(FN_ORGUNITNO, 32);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
