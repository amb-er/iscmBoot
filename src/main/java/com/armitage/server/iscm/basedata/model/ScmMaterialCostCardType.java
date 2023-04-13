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
 
@XmlRootElement(name = "scmMaterialCostCardType")  
public class ScmMaterialCostCardType extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_CLASSCODE ="classCode";
    public static final String FN_CLASSNAME ="className";
    public static final String FN_CSTUNITID ="cstUnitId";
    public static final String FN_CONVRATE ="convrate";
    public static final String FN_UNITID ="unitId";
    public static final String FN_PARENTID ="parentId";
    public static final String FN_LONGNO ="longNo";
    public static final String FN_DESCRIPTION ="description";
    public static final String FN_CREATOR ="creator";
    public static final String FN_CREATEDATE ="createDate";
    public static final String FN_EDITOR ="editor";
    public static final String FN_EDITDATE ="editDate";
    public static final String FN_FLAG ="flag";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    
    private long id;
    private String classCode;
    private String className;
    private long cstUnitId;
    private BigDecimal convrate;
    private long unitId;
    private long parentId;
    private String longNo;
    private String description;
    private String creator;
    private Date createDate;
    private String editor;
    private Date editDate;
    private boolean flag;
    private String controlUnitNo;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String val) {
        this.classCode = val;
    }
    public String getClassName() {
        return className;
    }

    public void setClassName(String val) {
        this.className = val;
    }
    public long getCstUnitId() {
        return cstUnitId;
    }

    public void setCstUnitId(long val) {
        this.cstUnitId = val;
    }
    public BigDecimal getConvrate() {
        return convrate;
    }

    public void setConvrate(BigDecimal val) {
        this.convrate = val;
    }
    public long getUnitId() {
        return unitId;
    }

    public void setUnitId(long val) {
        this.unitId = val;
    }
    public long getParentId() {
        return parentId;
    }

    public void setParentId(long val) {
        this.parentId = val;
    }
    public String getLongNo() {
        return longNo;
    }

    public void setLongNo(String val) {
        this.longNo = val;
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
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean val) {
        this.flag = val;
    }
    public String getControlUnitNo() {
        return controlUnitNo;
    }

    public void setControlUnitNo(String val) {
        this.controlUnitNo = val;
    }

    public ScmMaterialCostCardType(boolean defaultValue){
       if(defaultValue){
    	   this.convrate=BigDecimal.ZERO;
       }
    }
  	public ScmMaterialCostCardType() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_CLASSCODE,
            FN_CLASSNAME,
            FN_CSTUNITID,
            FN_CONVRATE,
            FN_UNITID,
            FN_PARENTID,
            FN_LONGNO,
            FN_DESCRIPTION,
            FN_CREATOR,
            FN_CREATEDATE,
            FN_EDITOR,
            FN_EDITDATE,
            FN_FLAG,
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
		return new String[] {FN_CLASSCODE,FN_CLASSNAME,FN_CSTUNITID,FN_CONVRATE,FN_UNITID };
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
        map.put(FN_CLASSCODE, 16);
        map.put(FN_CLASSNAME, 80);
        map.put(FN_LONGNO, 200);
        map.put(FN_DESCRIPTION, 100);
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
