package com.armitage.server.iscm.inventorymanage.inventorysetting.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmInvWareHouseUsr")  
public class ScmInvWareHouseUsr extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_WAREHOUSEID ="wareHouseId";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_USRCODE ="usrCode";
    
    private long id;
    private long wareHouseId;
    private String orgUnitNo;
    private String usrCode;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public long getWareHouseId() {
        return wareHouseId;
    }

    public void setWareHouseId(long val) {
        this.wareHouseId = val;
    }
    public String getOrgUnitNo() {
        return orgUnitNo;
    }

    public void setOrgUnitNo(String val) {
        this.orgUnitNo = val;
    }
    public String getUsrCode() {
        return usrCode;
    }

    public void setUsrCode(String val) {
        this.usrCode = val;
    }

    public ScmInvWareHouseUsr(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmInvWareHouseUsr() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_WAREHOUSEID,
            FN_ORGUNITNO,
            FN_USRCODE,
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
        map.put(FN_USRCODE, 16);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
