package com.armitage.server.iscm.purchasemanage.purchasesetting.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmPurchaseCanuseSet")  
public class ScmPurchaseCanuseSet extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_NAME ="name";
    public static final String FN_DEPTSCOPE ="deptScope";
    public static final String FN_ITEMSCOPE ="itemScope";
    public static final String FN_BIZTYPESCOPE ="bizTypeScope";
    public static final String FN_DATESCOPE ="dateScope";
    public static final String FN_DATESLOT ="dateSlot";
    public static final String FN_TIMESCOPE ="timeScope";
    public static final String FN_TIMESLOT ="timeSlot";
    public static final String FN_CREATOR ="creator";
    public static final String FN_CREATEDATE ="createDate";
    public static final String FN_EDITOR ="editor";
    public static final String FN_EDITDATE ="editDate";
    public static final String FN_FLAG ="flag";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    
    private long id;
    private String orgUnitNo;
    private String name;
    private String deptScope;
    private String itemScope;
    private String bizTypeScope;
    private String dateScope;
    private String dateSlot;
    private String timeScope;
    private String timeSlot;
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
    public String getName() {
        return name;
    }

    public void setName(String val) {
        this.name = val;
    }
    public String getDeptScope() {
        return deptScope;
    }

    public void setDeptScope(String val) {
        this.deptScope = val;
    }
    public String getItemScope() {
        return itemScope;
    }

    public void setItemScope(String val) {
        this.itemScope = val;
    }
    public String getBizTypeScope() {
        return bizTypeScope;
    }

    public void setBizTypeScope(String val) {
        this.bizTypeScope = val;
    }
    public String getDateScope() {
        return dateScope;
    }

    public void setDateScope(String val) {
        this.dateScope = val;
    }
    public String getDateSlot() {
        return dateSlot;
    }

    public void setDateSlot(String val) {
        this.dateSlot = val;
    }
    public String getTimeScope() {
        return timeScope;
    }

    public void setTimeScope(String val) {
        this.timeScope = val;
    }
    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String val) {
        this.timeSlot = val;
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

    public ScmPurchaseCanuseSet(boolean defaultValue){
       if(defaultValue){
    	   this.deptScope="A";
    	   this.itemScope="A";
    	   this.bizTypeScope="A";
    	   this.dateScope="A";
    	   this.timeScope="A";
    	   this.flag=true;
       }
    }
  	public ScmPurchaseCanuseSet() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_ORGUNITNO,
            FN_NAME,
            FN_DEPTSCOPE,
            FN_ITEMSCOPE,
            FN_BIZTYPESCOPE,
            FN_DATESCOPE,
            FN_DATESLOT,
            FN_TIMESCOPE,
            FN_TIMESLOT,
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
		return new String[] {FN_NAME };
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
        map.put(FN_NAME, 80);
        map.put(FN_DEPTSCOPE, 16);
        map.put(FN_ITEMSCOPE, 16);
        map.put(FN_BIZTYPESCOPE, 16);
        map.put(FN_DATESCOPE, 16);
        map.put(FN_DATESLOT, 100);
        map.put(FN_TIMESCOPE, 16);
        map.put(FN_TIMESLOT, 100);
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
