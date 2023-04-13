
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
 
@XmlRootElement(name = "scmMaterialInventory")  
public class ScmMaterialInventory extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_GUID ="guId";
    public static final String FN_ITEMID ="itemId";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_ABCTYPE ="abcType";
    public static final String FN_DEFAULTWHID ="defaultWhId";
    public static final String FN_UNITID ="unitId";
    public static final String FN_REORDER ="reorder";
    public static final String FN_MAXAMT ="maxAmt";
    public static final String FN_OQNUM ="oqnum";
    public static final String FN_MAXQTY ="maxQty";
    public static final String FN_MINQTY ="minQty";
    public static final String FN_MOVEDAYS ="movedays";
    public static final String FN_PERIODVALID ="periodValid";
    public static final String FN_PERIODVALIDDAYS ="periodValidDays";
    public static final String FN_AHNUM ="ahnum";
    public static final String FN_SALETAXRATE ="saleTaxRate";
    public static final String FN_CREATOR ="creator";
    public static final String FN_CREATEDATE ="createDate";
    public static final String FN_EDITOR ="editor";
    public static final String FN_EDITDATE ="editDate";
    public static final String FN_FREEZEORGUNIT ="freezeOrgUnit";
    public static final String FN_FREEZETIME ="freezeTime";
    public static final String FN_STATUS ="status";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    
    private long id;
    private String guId;
    private long itemId;
    private String orgUnitNo;
    private String abcType;
    private long defaultWhId;
    private long unitId;
    private BigDecimal reorder;
    private BigDecimal maxAmt;
    private BigDecimal oqnum;
    private BigDecimal maxQty;
    private BigDecimal minQty;
    private int movedays;
    private String periodValid;
    private int periodValidDays;
    private int ahnum;
    private BigDecimal saleTaxRate;
    private String creator;
    private Date createDate;
    private String editor;
    private Date editDate;
    private String freezeOrgUnit;
    private Date freezeTime;
    private String status;
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
    public String getAbcType() {
        return abcType;
    }

    public void setAbcType(String val) {
        this.abcType = val;
    }
    public long getDefaultWhId() {
        return defaultWhId;
    }

    public void setDefaultWhId(long val) {
        this.defaultWhId = val;
    }
    public long getUnitId() {
        return unitId;
    }

    public void setUnitId(long val) {
        this.unitId = val;
    }
    public BigDecimal getReorder() {
        return reorder;
    }

    public void setReorder(BigDecimal val) {
        this.reorder = val;
    }
    public BigDecimal getMaxAmt() {
        return maxAmt;
    }

    public void setMaxAmt(BigDecimal val) {
        this.maxAmt = val;
    }
    public BigDecimal getOqnum() {
        return oqnum;
    }

    public void setOqnum(BigDecimal val) {
        this.oqnum = val;
    }
    public BigDecimal getMaxQty() {
        return maxQty;
    }

    public void setMaxQty(BigDecimal val) {
        this.maxQty = val;
    }
    public BigDecimal getMinQty() {
        return minQty;
    }

    public void setMinQty(BigDecimal val) {
        this.minQty = val;
    }
    public int getMovedays() {
        return movedays;
    }

    public void setMovedays(int val) {
        this.movedays = val;
    }
    public String getPeriodValid() {
        return periodValid;
    }

    public void setPeriodValid(String val) {
        this.periodValid = val;
    }
    public int getPeriodValidDays() {
        return periodValidDays;
    }

    public void setPeriodValidDays(int val) {
        this.periodValidDays = val;
    }
    public int getAhnum() {
        return ahnum;
    }

    public void setAhnum(int val) {
        this.ahnum = val;
    }
    public BigDecimal getSaleTaxRate() {
		return saleTaxRate;
	}

	public void setSaleTaxRate(BigDecimal saleTaxRate) {
		this.saleTaxRate = saleTaxRate;
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

    public ScmMaterialInventory(boolean defaultValue){
       if(defaultValue){
    	   guId="";
    	   reorder=BigDecimal.ZERO;
    	   maxAmt=BigDecimal.ZERO;
    	   oqnum=BigDecimal.ZERO;
    	   maxQty=BigDecimal.ZERO;
    	   minQty=BigDecimal.ZERO;
    	   movedays=0;
    	   periodValidDays=0;
    	   ahnum=0;
    	   periodValid="N";
    	   saleTaxRate=BigDecimal.ZERO;
       }
    }
  	public ScmMaterialInventory() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_GUID,
            FN_ITEMID,
            FN_ORGUNITNO,
            FN_ABCTYPE,
            FN_DEFAULTWHID,
            FN_UNITID,
            FN_REORDER,
            FN_MAXAMT,
            FN_OQNUM,
            FN_MAXQTY,
            FN_MINQTY,
            FN_MOVEDAYS,
            FN_PERIODVALID,
            FN_PERIODVALIDDAYS,
            FN_AHNUM,
            FN_CREATOR,
            FN_CREATEDATE,
            FN_EDITOR,
            FN_EDITDATE,
            FN_FREEZEORGUNIT,
            FN_FREEZETIME,
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
		return new String[] {FN_ORGUNITNO,FN_UNITID};
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
        map.put(FN_ABCTYPE, 16);
        map.put(FN_CREATOR, 16);
        map.put(FN_EDITOR, 16);
        map.put(FN_FREEZEORGUNIT, 32);
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


}
