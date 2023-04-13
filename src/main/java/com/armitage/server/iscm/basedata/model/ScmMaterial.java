
package com.armitage.server.iscm.basedata.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmMaterial")  
public class ScmMaterial extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_GUID ="guId";
    public static final String FN_ITEMNO ="itemNo";
    public static final String FN_ITEMNAME ="itemName";
    public static final String FN_SPEC ="spec";
    public static final String FN_SIMPLENAME ="simpleName";
    public static final String FN_BRANDID ="brandId";
    public static final String FN_PYM ="pym";
    public static final String FN_BARCODE ="barCode";
    public static final String FN_BASEUNITID ="baseUnitId";
    public static final String FN_PIEUNITID ="pieUnitId";
    public static final String FN_FBCITEM ="fbcItem";
    public static final String FN_REMARKS ="remarks";
    public static final String FN_CREATOR ="creator";
    public static final String FN_CREATEDATE ="createDate";
    public static final String FN_EDITOR ="editor";
    public static final String FN_EDITDATE ="editDate";
    public static final String FN_STATUS ="status";
    public static final String FN_FREEZEORGUNIT ="freezeOrgUnit";
    public static final String FN_FREEZETIME ="freezeTime";
    public static final String FN_ADMINCUNO ="adminCuNo";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    
    private long id;
    private String guId;
    private String itemNo;
    private String itemName;
    private String spec;
    private String simpleName;
    private long brandId;
    private String pym;
    private String barCode;
    private long baseUnitId;
    private long pieUnitId;
    private boolean fbcItem;
    private String remarks;
    private String creator;
    private Date createDate;
    private String editor;
    private Date editDate;
    private String status;
    private String freezeOrgUnit;
    private Date freezeTime;
    private String adminCuNo;
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
    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String val) {
        this.itemNo = val;
    }
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String val) {
        this.itemName = val;
    }
    public String getSpec() {
        return spec;
    }

    public void setSpec(String val) {
        this.spec = val;
    }
    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String val) {
        this.simpleName = val;
    }
    public long getBrandId() {
		return brandId;
	}

	public void setBrandId(long brandId) {
		this.brandId = brandId;
	}

	public String getPym() {
        return pym;
    }

    public void setPym(String val) {
        this.pym = val;
    }
    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String val) {
        this.barCode = val;
    }
    public long getBaseUnitId() {
        return baseUnitId;
    }

    public void setBaseUnitId(long val) {
        this.baseUnitId = val;
    }
    public long getPieUnitId() {
        return pieUnitId;
    }

    public void setPieUnitId(long val) {
        this.pieUnitId = val;
    }
    public boolean isFbcItem() {
        return fbcItem;
    }

    public void setFbcItem(boolean val) {
        this.fbcItem = val;
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
    public String getStatus() {
        return status;
    }

    public void setStatus(String val) {
        this.status = val;
    }
    public String getFreezeOrgUnit() {
        return freezeOrgUnit;
    }

    public void setFreezeOrgUnit(String val) {
        this.freezeOrgUnit = val;
    }
    public Date getFreezeTime() {
		return freezeTime;
	}

	public void setFreezeTime(Date freezeTime) {
		this.freezeTime = freezeTime;
	}

	public String getAdminCuNo() {
        return adminCuNo;
    }

    public void setAdminCuNo(String val) {
        this.adminCuNo = val;
    }
    public String getControlUnitNo() {
        return controlUnitNo;
    }

    public void setControlUnitNo(String val) {
        this.controlUnitNo = val;
    }

    public ScmMaterial(boolean defaultValue){
       if(defaultValue){
    	   this.guId="";
    	   this.fbcItem=false;
    	   this.spec="";
       }
    }
  	public ScmMaterial() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_GUID,
            FN_ITEMNO,
            FN_ITEMNAME,
            FN_SPEC,
            FN_SIMPLENAME,
            FN_BRANDID,
            FN_PYM,
            FN_BARCODE,
            FN_BASEUNITID,
            FN_PIEUNITID,
            FN_FBCITEM,
            FN_REMARKS,
            FN_CREATOR,
            FN_CREATEDATE,
            FN_EDITOR,
            FN_EDITDATE,
            FN_STATUS,
            FN_FREEZEORGUNIT,
            FN_FREEZETIME,
            FN_ADMINCUNO,
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
		return new String[] {FN_ITEMNAME,FN_BASEUNITID};
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
        map.put(FN_ITEMNO, 16);
        map.put(FN_ITEMNAME, 80);
        map.put(FN_SPEC, 80);
        map.put(FN_SIMPLENAME, 40);
        map.put(FN_PYM, 50);
        map.put(FN_BARCODE, 50);
        map.put(FN_REMARKS, 200);
        map.put(FN_CREATOR, 16);
        map.put(FN_EDITOR, 16);
        map.put(FN_STATUS, 16);
        map.put(FN_FREEZEORGUNIT, 32);
        map.put(FN_ADMINCUNO, 32);
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
