
package com.armitage.server.iscm.basedata.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmIndustryGroupQualifyType")  
public class ScmIndustryGroupQualifyType extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_CLASSID ="classId";
    public static final String FN_TYPEID ="typeId";
    public static final String FN_REQUIRED ="required";
    
    private long id;
    private long classId;
    private long typeId;
    private boolean required;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public long getClassId() {
        return classId;
    }

    public void setClassId(long val) {
        this.classId = val;
    }
    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long val) {
        this.typeId = val;
    }

    public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public ScmIndustryGroupQualifyType(boolean defaultValue){
       if(defaultValue){
    	   this.required=false;
       }
    }
  	public ScmIndustryGroupQualifyType() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_CLASSID,
            FN_TYPEID,
            FN_REQUIRED
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
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}
}
