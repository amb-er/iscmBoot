package com.armitage.server.iscm.basedata.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmAuditMsgTemplet")  
public class ScmAuditMsgTemplet extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_TEMPLETCODE ="templetCode";
    public static final String FN_TEMPLETNAME ="templetName";
    public static final String FN_TITLE ="title";
    public static final String FN_CONTENT ="content";
    public static final String FN_TEMPLETTYPE ="templetType";
    public static final String FN_FLAG ="flag";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    
    private long id;
    private String templetCode;
    private String templetName;
    private String title;
    private String content;
    private String templetType;
    private boolean flag;
    private String controlUnitNo;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public String getTempletCode() {
        return templetCode;
    }

    public void setTempletCode(String val) {
        this.templetCode = val;
    }
    public String getTempletName() {
        return templetName;
    }

    public void setTempletName(String val) {
        this.templetName = val;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String val) {
        this.title = val;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String val) {
        this.content = val;
    }
    public String getTempletType() {
        return templetType;
    }

    public void setTempletType(String val) {
        this.templetType = val;
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

    public ScmAuditMsgTemplet(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmAuditMsgTemplet() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_TEMPLETCODE,
            FN_TEMPLETNAME,
            FN_TITLE,
            FN_CONTENT,
            FN_TEMPLETTYPE,
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
        map.put(FN_TEMPLETCODE, 16);
        map.put(FN_TEMPLETNAME, 64);
        map.put(FN_TITLE, 64);
        map.put(FN_CONTENT, 512);
        map.put(FN_TEMPLETTYPE, 16);
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
