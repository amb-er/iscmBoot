
package com.armitage.server.iscm.basedata.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmMaterialAssign")  
public class ScmMaterialAssign extends BaseModel  {
	 
    public static final String FN_ID ="Id";
    public static final String FN_ITEMID ="itemId";
    public static final String FN_TOCTRUNITNO ="toCtrUnitNo";
    public static final String FN_FROMCTRUNITNO ="fromCtrUnitNo";
    
    private long id;
    private long itemId;
    private String toCtrUnitNo;
    private String fromCtrUnitNo;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public long getItemId() {
        return itemId;
    }

    public void setItemId(long val) {
        this.itemId = val;
    }
    public String getToCtrUnitNo() {
        return toCtrUnitNo;
    }

    public void setToCtrUnitNo(String val) {
        this.toCtrUnitNo = val;
    }
    public String getFromCtrUnitNo() {
        return fromCtrUnitNo;
    }

    public void setFromCtrUnitNo(String val) {
        this.fromCtrUnitNo = val;
    }

    public ScmMaterialAssign(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmMaterialAssign() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_ITEMID,
            FN_TOCTRUNITNO,
            FN_FROMCTRUNITNO,
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
        map.put(FN_TOCTRUNITNO, 32);
        map.put(FN_FROMCTRUNITNO, 32);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
