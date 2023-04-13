package com.armitage.server.iscm.basedata.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmIdleItems")  
public class ScmIdleItems extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_TABLEID ="tableId";
    public static final String FN_BIZDATE ="bizDate";
    public static final String FN_FINORGUNITNO ="finOrgUnitNo";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_USEORGUNITNO ="useOrgUnitNo";
    public static final String FN_ITEMID ="itemId";
    public static final String FN_LOT ="lot";
    public static final String FN_UNIT ="unit";
    public static final String FN_IDLECAUSEID ="idleCauseId";
    public static final String FN_IDLESTATUS ="idleStatus";
    public static final String FN_IDLEBILLTYPE ="idleBillType";
    public static final String FN_IDLEBILLID ="idleBillId";
    public static final String FN_NEWIDLE ="newIdle";
    public static final String FN_REMARKS ="remarks";
    public static final String FN_CREATOR ="creator";
    public static final String FN_CREATEDATE ="createDate";
    public static final String FN_EDITOR ="editor";
    public static final String FN_EDITDATE ="editDate";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    
    private long id;
    private long tableId;
    private Date bizDate;
    private String finOrgUnitNo;
    private String orgUnitNo;
    private String useOrgUnitNo;
    private long itemId;
    private String lot;
    private long unit;
    private long idleCauseId;
    private boolean idleStatus;
    private String idleBillType;
    private long idleBillId;
    private boolean newIdle;
    private String remarks;
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
    public long getTableId() {
        return tableId;
    }

    public void setTableId(long val) {
        this.tableId = val;
    }
    public Date getBizDate() {
		return bizDate;
	}

	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}

	public String getFinOrgUnitNo() {
        return finOrgUnitNo;
    }

    public void setFinOrgUnitNo(String val) {
        this.finOrgUnitNo = val;
    }
    public String getOrgUnitNo() {
        return orgUnitNo;
    }

    public void setOrgUnitNo(String val) {
        this.orgUnitNo = val;
    }
    public String getUseOrgUnitNo() {
        return useOrgUnitNo;
    }

    public void setUseOrgUnitNo(String val) {
        this.useOrgUnitNo = val;
    }
    public long getItemId() {
        return itemId;
    }

    public void setItemId(long val) {
        this.itemId = val;
    }
    public String getLot() {
        return lot;
    }

    public void setLot(String val) {
        this.lot = val;
    }
    public long getUnit() {
        return unit;
    }

    public void setUnit(long val) {
        this.unit = val;
    }
    public long getIdleCauseId() {
        return idleCauseId;
    }

    public void setIdleCauseId(long val) {
        this.idleCauseId = val;
    }
    public boolean isIdleStatus() {
        return idleStatus;
    }

    public void setIdleStatus(boolean val) {
        this.idleStatus = val;
    }
    public String getIdleBillType() {
        return idleBillType;
    }

    public void setIdleBillType(String val) {
        this.idleBillType = val;
    }
    public long getIdleBillId() {
        return idleBillId;
    }

    public void setIdleBillId(long val) {
        this.idleBillId = val;
    }
    public boolean isNewIdle() {
        return newIdle;
    }

    public void setNewIdle(boolean val) {
        this.newIdle = val;
    }
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String val) {
        this.remarks = val;
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

    public ScmIdleItems(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmIdleItems() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_TABLEID,
            FN_BIZDATE,
            FN_FINORGUNITNO,
            FN_ORGUNITNO,
            FN_USEORGUNITNO,
            FN_ITEMID,
            FN_LOT,
            FN_UNIT,
            FN_IDLECAUSEID,
            FN_IDLESTATUS,
            FN_IDLEBILLTYPE,
            FN_IDLEBILLID,
            FN_NEWIDLE,
            FN_REMARKS,
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
        map.put(FN_FINORGUNITNO, 32);
        map.put(FN_ORGUNITNO, 32);
        map.put(FN_USEORGUNITNO, 32);
        map.put(FN_LOT, 32);
        map.put(FN_IDLEBILLTYPE, 32);
        map.put(FN_REMARKS, 250);
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
