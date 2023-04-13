
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
 
@XmlRootElement(name = "scmMaterialCompanyInfo")  
public class ScmMaterialCompanyInfo extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_GUID ="guId";
    public static final String FN_ITEMID ="itemId";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_KACLASSID ="kaClassId";
    public static final String FN_PRICE ="price";
    public static final String FN_NEEDPRICING ="needPricing";
    public static final String FN_VENDORID ="vendorId";
    public static final String FN_CREATOR ="creator";
    public static final String FN_CREATEDATE ="createDate";
    public static final String FN_EDITOR ="editor";
    public static final String FN_EDITDATE ="editDate";
    public static final String FN_FREEZEORGUNIT ="freezeOrgUnit";
    public static final String FN_FREEZETIME ="freezeTime";
    public static final String FN_STATUS ="status";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    public static final String FN_COSTING ="costing";
    public static final String FN_CSTUNITID ="cstUnitId";
    public static final String FN_TYPE ="type";
    
    private long id;
    private String guId;
    private long itemId;
    private String orgUnitNo;
    private String kaClassId;
    private BigDecimal price;
    private String needPricing;
    private long vendorId;
    private String creator;
    private Date createDate;
    private String editor;
    private Date editDate;
    private String freezeOrgUnit;
    private Date freezeTime;
    private String status;
    private String controlUnitNo;
    private String costing;
    private long cstUnitId;
    private String type;
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
    public long getItemId() {
        return itemId;
    }

    public void setItemId(long val) {
        this.itemId = val;
    }
    public String getOrgUnitNo() {
        return orgUnitNo;
    }

    public void setOrgUnitNo(String val) {
        this.orgUnitNo = val;
    }
    public String getKaClassId() {
        return kaClassId;
    }

    public void setKaClassId(String val) {
        this.kaClassId = val;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal val) {
        this.price = val;
    }
    public String getNeedPricing() {
        return needPricing;
    }

    public void setNeedPricing(String val) {
        this.needPricing = val;
    }
    public long getVendorId() {
        return vendorId;
    }

    public void setVendorId(long val) {
        this.vendorId = val;
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

    public String getCosting() {
		return costing;
	}

	public void setCosting(String costing) {
		this.costing = costing;
	}

	public long getCstUnitId() {
		return cstUnitId;
	}

	public void setCstUnitId(long cstUnitId) {
		this.cstUnitId = cstUnitId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ScmMaterialCompanyInfo(boolean defaultValue){
       if(defaultValue){
    	   guId="";
    	   price=BigDecimal.ZERO;
    	   vendorId=0;
    	   costing="FIFO";
    	   needPricing="N";
    	   type="3";
       }
    }
  	public ScmMaterialCompanyInfo() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_GUID,
            FN_ITEMID,
            FN_ORGUNITNO,
            FN_KACLASSID,
            FN_PRICE,
            FN_NEEDPRICING,
            FN_VENDORID,
            FN_CREATOR,
            FN_CREATEDATE,
            FN_EDITOR,
            FN_EDITDATE,
            FN_FREEZEORGUNIT,
            FN_FREEZETIME,
            FN_STATUS,
            FN_CONTROLUNITNO,
            FN_COSTING,
            FN_CSTUNITID,
            FN_TYPE
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
		return new String[] {FN_ORGUNITNO,FN_COSTING,FN_NEEDPRICING};
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
        map.put(FN_ORGUNITNO, 32);
        map.put(FN_CREATOR, 16);
        map.put(FN_EDITOR, 16);
        map.put(FN_FREEZEORGUNIT, 32);
        map.put(FN_STATUS, 16);
        map.put(FN_CONTROLUNITNO, 32);
        map.put(FN_COSTING, 16);
        map.put(FN_TYPE, 16);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
