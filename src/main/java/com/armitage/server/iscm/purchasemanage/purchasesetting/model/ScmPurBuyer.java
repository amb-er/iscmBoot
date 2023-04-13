
package com.armitage.server.iscm.purchasemanage.purchasesetting.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmPurBuyer")  
public class ScmPurBuyer extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_EMPID ="empId";
    public static final String FN_BUYERCODE ="buyerCode";
    public static final String FN_BUYERNAME ="buyerName";
    public static final String FN_PURGROUPID ="purGroupId";
    public static final String FN_CREATOR ="creator";
    public static final String FN_CREATEDATE ="createDate";
    public static final String FN_EDITOR ="editor";
    public static final String FN_EDITDATE ="editDate";
    public static final String FN_STATUS ="status";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    
    private long id;
    private long empId;
    private String buyerCode;
    private String buyerName;
    private long purGroupId;
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
    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long val) {
        this.empId = val;
    }
    public String getBuyerCode() {
		return buyerCode;
	}

	public void setBuyerCode(String buyerCode) {
		this.buyerCode = buyerCode;
	}

	public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String val) {
        this.buyerName = val;
    }
    public long getPurGroupId() {
        return purGroupId;
    }

    public void setPurGroupId(long val) {
        this.purGroupId = val;
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

    public ScmPurBuyer(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmPurBuyer() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_EMPID,
            FN_BUYERNAME,
            FN_PURGROUPID,
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
		return list;
	}
	 
	public String[] getRequiredFields() {
	    /*DEMO:
		return new String[] {FN_CODE };
        */
		return new String[] {FN_ID,FN_EMPID,FN_BUYERNAME,FN_PURGROUPID };
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
        map.put(FN_BUYERNAME, 150);
        map.put(FN_CREATOR, 48);
        map.put(FN_EDITOR, 48);
        map.put(FN_STATUS, 48);
        map.put(FN_CONTROLUNITNO, 96);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
