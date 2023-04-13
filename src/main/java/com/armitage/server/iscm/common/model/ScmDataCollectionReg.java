package com.armitage.server.iscm.common.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "dataCollectionReg")  
public class ScmDataCollectionReg extends BaseModel  {
	 
    public static final String FN_ID ="id";
	public static final String FN_CATEGORY = "category";
    public static final String FN_CODE ="code";
    public static final String FN_NAME ="name";
    public static final String FN_SEQUENCEID ="sequenceId";
    public static final String FN_INVOKE ="invoke";
    
    private int id;
	private String category;
    private String code;
    private String name;
    private int sequenceId;
    private String invoke;

    public int getId() {
        return id;
    }

    public void setId(int val) {
        this.id = val;
    }
    public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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
    public int getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(int val) {
        this.sequenceId = val;
    }
    public String getInvoke() {
        return invoke;
    }

    public void setInvoke(String val) {
        this.invoke = val;
    }

    public ScmDataCollectionReg(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmDataCollectionReg() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_CATEGORY,
            FN_CODE,
            FN_NAME,
            FN_SEQUENCEID,
            FN_INVOKE,
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
        map.put(FN_CODE, 32);
        map.put(FN_CATEGORY,16);
        map.put(FN_NAME, 60);
        map.put(FN_INVOKE, 60);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
