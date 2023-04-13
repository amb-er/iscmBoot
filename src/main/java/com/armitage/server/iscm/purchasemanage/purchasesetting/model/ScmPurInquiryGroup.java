package com.armitage.server.iscm.purchasemanage.purchasesetting.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmPurInquiryGroup")  
public class ScmPurInquiryGroup extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_GROUPNO ="groupNo";
    public static final String FN_GROUPNAME ="groupName";
    public static final String FN_CHARGEBY ="chargeBy";
    public static final String FN_CREATOR ="creator";
    public static final String FN_CREATEDATE ="createDate";
    public static final String FN_EDITOR ="editor";
    public static final String FN_EDITDATE ="editDate";
    public static final String FN_FLAG ="flag";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    
    private long id;
    private String orgUnitNo;
    private String groupNo;
    private String groupName;
    private String chargeBy;
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
    public String getOrgUnitNo() {
        return orgUnitNo;
    }

    public void setOrgUnitNo(String val) {
        this.orgUnitNo = val;
    }
    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String val) {
        this.groupNo = val;
    }
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String val) {
        this.groupName = val;
    }
    public String getChargeBy() {
        return chargeBy;
    }

    public void setChargeBy(String val) {
        this.chargeBy = val;
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

    public ScmPurInquiryGroup(boolean defaultValue){
       if(defaultValue){
    	   this.flag=true;
       }
    }
  	public ScmPurInquiryGroup() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_ORGUNITNO,
            FN_GROUPNO,
            FN_GROUPNAME,
            FN_CHARGEBY,
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
		list.add(new String[] { FN_ORGUNITNO, FN_GROUPNO });
		return list;
	}
	 
	public String[] getRequiredFields() {
		return new String[] {FN_GROUPNO,FN_GROUPNAME,FN_CHARGEBY};
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
        map.put(FN_GROUPNO, 16);
        map.put(FN_GROUPNAME, 80);
        map.put(FN_CHARGEBY, 16);
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
