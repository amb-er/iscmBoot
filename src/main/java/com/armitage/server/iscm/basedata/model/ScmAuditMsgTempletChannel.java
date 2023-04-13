package com.armitage.server.iscm.basedata.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmAuditMsgTempletChannel")  
public class ScmAuditMsgTempletChannel extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_TEMPLETID ="templetId";
    public static final String FN_CHANNELCODE ="channelCode";
    public static final String FN_TEMPLATEID ="templateId";
    
    private long id;
    private long templetId;
    private String channelCode;
    private String templateId;
    
    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public long getTempletId() {
        return templetId;
    }

    public void setTempletId(long val) {
        this.templetId = val;
    }
    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String val) {
        this.channelCode = val;
    }

    public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public ScmAuditMsgTempletChannel(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmAuditMsgTempletChannel() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_TEMPLETID,
            FN_CHANNELCODE,
            FN_TEMPLATEID
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
        map.put(FN_CHANNELCODE, 16);
        map.put(FN_TEMPLATEID, 64);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
