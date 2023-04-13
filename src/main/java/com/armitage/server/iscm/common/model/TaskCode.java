package com.armitage.server.iscm.common.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "taskCode")  
public class TaskCode extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_CODE ="code";
    public static final String FN_NAME ="name";
    public static final String FN_CHANNEL ="channel";
    public static final String FN_PRODUCTCODE ="productCode";
    
    private long id;
    private String code;
    private String name;
    private String channel;
    private String productCode;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String val) {
        this.code = val;
    }
    public String getName() {
        return name;
    }

    public void setName(String val) {
        this.name = val;
    }
    public String getChannel() {
        return channel;
    }

    public void setChannel(String val) {
        this.channel = val;
    }
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String val) {
        this.productCode = val;
    }

    public TaskCode(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public TaskCode() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_CODE,
            FN_NAME,
            FN_CHANNEL,
            FN_PRODUCTCODE,
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
        map.put(FN_CODE, 16);
        map.put(FN_NAME, 64);
        map.put(FN_CHANNEL, 16);
        map.put(FN_PRODUCTCODE, 16);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
