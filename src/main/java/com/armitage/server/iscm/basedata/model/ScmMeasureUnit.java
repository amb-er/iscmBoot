
package com.armitage.server.iscm.basedata.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmMeasureUnit")  
public class ScmMeasureUnit extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_GUID ="guId";
    public static final String FN_UNITNO ="unitNo";
    public static final String FN_UNITNAME ="unitName";
    public static final String FN_DESCRIPTION ="description";
    public static final String FN_GROUPID ="groupId";
    public static final String FN_FLAG ="flag";
    public static final String FN_DISABLEDDATE ="disabledDate";
    public static final String FN_CREATOR ="creator";
    public static final String FN_CREATEDATE ="createDate";
    public static final String FN_EDITOR ="editor";
    public static final String FN_EDITDATE ="editDate";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    
    private long id;
    private String guId;
    private String unitNo;
    private String unitName;
    private String description;
    private long groupId;
    private boolean flag;
    private Date disabledDate;
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
    public String getGuId() {
        return guId;
    }

    public void setGuId(String val) {
        this.guId = val;
    }
    public String getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(String val) {
        this.unitNo = val;
    }
    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String val) {
        this.unitName = val;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String val) {
        this.description = val;
    }
    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long val) {
        this.groupId = val;
    }
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean val) {
        this.flag = val;
    }
    public Date getDisabledDate() {
        return disabledDate;
    }

    public void setDisabledDate(Date val) {
        this.disabledDate = val;
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

    public ScmMeasureUnit(boolean defaultValue){
       if(defaultValue){
    	   this.guId="";
    	   this.flag=true;
       }
    }
  	public ScmMeasureUnit() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_GUID,
            FN_UNITNO,
            FN_UNITNAME,
            FN_DESCRIPTION,
            FN_GROUPID,
            FN_FLAG,
            FN_DISABLEDDATE,
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
		list.add(new String[] { FN_UNITNO, FN_CONTROLUNITNO });
		return list;
	}
	 
	public String[] getRequiredFields() {
	    /*DEMO:
		return new String[] {FN_CODE };
        */
		return new String[] {FN_UNITNO,FN_UNITNAME};
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
        map.put(FN_GUID, 40);
        map.put(FN_UNITNO, 16);
        map.put(FN_UNITNAME, 60);
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

	@Override
	public String getDisplayColumn() {
		return "unitName";
	}


}
