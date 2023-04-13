package com.armitage.server.iscm.basedata.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmsuppliergroupstandard")  
public class Scmsuppliergroupstandard extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_STANDARDNO ="standardNo";
    public static final String FN_STANDARDNAME ="standardName";
    public static final String FN_STANDARDTYPE ="standardType";
    public static final String FN_DESCRIPTION ="description";
    public static final String FN_CREATOR ="creator";
    public static final String FN_CREATEDATE ="createDate";
    public static final String FN_EDITOR ="editor";
    public static final String FN_EDITDATE ="editDate";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    
    private long id;
    private String standardNo;
    private String standardName;
    private String standardType;
    private String description;
    private String creator;
    private Date createDate;
    private String editor;
    private Date editDate;
    private String controlUnitNo;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public String getStandardNo() {
        return standardNo;
    }

    public void setStandardNo(String val) {
        this.standardNo = val;
    }
    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String val) {
        this.standardName = val;
    }
    public String getStandardType() {
        return standardType;
    }

    public void setStandardType(String val) {
        this.standardType = val;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String val) {
        this.description = val;
    }
    public String getCreator() {
        return creator;
    }

    public void setCreator(String val) {
        this.creator = val;
    }
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date val) {
        this.createDate = val;
    }
    public String getEditor() {
        return editor;
    }

    public void setEditor(String val) {
        this.editor = val;
    }
    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date val) {
        this.editDate = val;
    }
    public String getControlUnitNo() {
        return controlUnitNo;
    }

    public void setControlUnitNo(String val) {
        this.controlUnitNo = val;
    }

    public Scmsuppliergroupstandard(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public Scmsuppliergroupstandard() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_STANDARDNO,
            FN_STANDARDNAME,
            FN_STANDARDTYPE,
            FN_DESCRIPTION,
            FN_CREATOR,
            FN_CREATEDATE,
            FN_EDITOR,
            FN_EDITDATE,
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
        map.put(FN_STANDARDNO, 16);
        map.put(FN_STANDARDNAME, 50);
        map.put(FN_STANDARDTYPE, 16);
        map.put(FN_DESCRIPTION, 255);
        map.put(FN_CREATOR, 16);
        map.put(FN_EDITOR, 16);
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
