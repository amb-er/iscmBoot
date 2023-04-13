
package com.armitage.server.iscm.purchasemanage.purchasesetting.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmPurGroup")  
public class ScmPurGroup extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_PURGROUPNO ="purGroupNo";
    public static final String FN_PURGROUPNAME ="purGroupName";
    public static final String FN_GROUPTEL ="groupTel";
    public static final String FN_GROUPFAX ="groupFax";
    public static final String FN_DESCRIPTION ="description";
    public static final String FN_PARENTID ="parentId";
    public static final String FN_CREATOR ="creator";
    public static final String FN_CREATEDATE ="createDate";
    public static final String FN_EDITOR ="editor";
    public static final String FN_EDITDATE ="editDate";
    public static final String FN_STATUS ="status";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    
    private long id;
    private String orgUnitNo;
    private String purGroupNo;
    private String purGroupName;
    private String groupTel;
    private String groupFax;
    private String description;
    private long parentId;
    private String creator;
    private Date createDate;
    private String editor;
    private Date editDate;
    private String status;
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
    public String getPurGroupNo() {
        return purGroupNo;
    }

    public void setPurGroupNo(String val) {
        this.purGroupNo = val;
    }
    public String getPurGroupName() {
        return purGroupName;
    }

    public void setPurGroupName(String val) {
        this.purGroupName = val;
    }
    public String getGroupTel() {
        return groupTel;
    }

    public void setGroupTel(String val) {
        this.groupTel = val;
    }
    public String getGroupFax() {
        return groupFax;
    }

    public void setGroupFax(String val) {
        this.groupFax = val;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String val) {
        this.description = val;
    }
    public long getParentId() {
        return parentId;
    }

    public void setParentId(long val) {
        this.parentId = val;
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
    public String getStatus() {
        return status;
    }

    public void setStatus(String val) {
        this.status = val;
    }
    public String getControlUnitNo() {
        return controlUnitNo;
    }

    public void setControlUnitNo(String val) {
        this.controlUnitNo = val;
    }

    public ScmPurGroup(boolean defaultValue){
       if(defaultValue){
    	   this.status="Y";
       }
    }
  	public ScmPurGroup() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_ORGUNITNO,
            FN_PURGROUPNO,
            FN_PURGROUPNAME,
            FN_GROUPTEL,
            FN_GROUPFAX,
            FN_DESCRIPTION,
            FN_PARENTID,
            FN_CREATOR,
            FN_CREATEDATE,
            FN_EDITOR,
            FN_EDITDATE,
            FN_STATUS,
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
		list.add(new String[] { FN_ORGUNITNO,FN_PURGROUPNO });
		return list;
	}
	 
	public String[] getRequiredFields() {
	    /*DEMO:
		return new String[] {FN_CODE };
        */
		return new String[] {FN_ID,FN_ORGUNITNO,FN_PURGROUPNO,FN_PURGROUPNAME };
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
        map.put(FN_PURGROUPNO, 16);
        map.put(FN_PURGROUPNAME, 80);
        map.put(FN_GROUPTEL, 30);
        map.put(FN_GROUPFAX, 30);
        map.put(FN_DESCRIPTION, 200);
        map.put(FN_CREATOR, 16);
        map.put(FN_EDITOR, 16);
        map.put(FN_STATUS, 16);
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
	public String toString() {
		return "ScmPurGroup [id=" + id + ", orgUnitNo=" + orgUnitNo + ", purGroupNo=" + purGroupNo + ", purGroupName="
				+ purGroupName + ", groupTel=" + groupTel + ", groupFax=" + groupFax + ", description=" + description
				+ ", parentId=" + parentId + ", creator=" + creator + ", createDate=" + createDate + ", editor="
				+ editor + ", editDate=" + editDate + ", status=" + status + ", controlUnitNo=" + controlUnitNo + "]";
	}


}
