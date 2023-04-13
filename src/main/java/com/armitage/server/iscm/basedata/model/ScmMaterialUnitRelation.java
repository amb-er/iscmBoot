
package com.armitage.server.iscm.basedata.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmMaterialUnitRelation")  
public class ScmMaterialUnitRelation extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_GUID ="guId";
    public static final String FN_ITEMID ="itemId";
    public static final String FN_LINEID ="lineId";
    public static final String FN_TARGETUNITID ="targetUnitId";
    public static final String FN_CONVRATE ="convrate";
    public static final String FN_BASEUNIT ="baseUnit";
    public static final String FN_QTYPRECISION ="qtyPrecision";
    public static final String FN_USECONVSUNIT ="useConvsUnit";
    public static final String FN_CONVSUNITID ="convsUnitId";
    public static final String FN_MEASUREUNITTYPE ="measureUnitType";
    
    private long id;
    private String guId;
    private long itemId;
    private int lineId;
    private long targetUnitId;
    private BigDecimal convrate;
    private boolean baseUnit;
    private int qtyPrecision;
    private boolean useConvsUnit;
    private long convsUnitId;
    private String measureUnitType;

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
    public int getLineId() {
        return lineId;
    }

    public void setLineId(int val) {
        this.lineId = val;
    }
    public long getTargetUnitId() {
        return targetUnitId;
    }

    public void setTargetUnitId(long val) {
        this.targetUnitId = val;
    }
    public BigDecimal getConvrate() {
        return convrate;
    }

    public void setConvrate(BigDecimal val) {
        this.convrate = val;
    }
    public boolean isBaseUnit() {
        return baseUnit;
    }

    public void setBaseUnit(boolean val) {
        this.baseUnit = val;
    }
    public int getQtyPrecision() {
        return qtyPrecision;
    }

    public void setQtyPrecision(int val) {
        this.qtyPrecision = val;
    }
    public boolean isUseConvsUnit() {
        return useConvsUnit;
    }

    public void setUseConvsUnit(boolean val) {
        this.useConvsUnit = val;
    }
    public long getConvsUnitId() {
        return convsUnitId;
    }

    public void setConvsUnitId(long val) {
        this.convsUnitId = val;
    }
    public String getMeasureUnitType() {
        return measureUnitType;
    }

    public void setMeasureUnitType(String val) {
        this.measureUnitType = val;
    }

    public ScmMaterialUnitRelation(boolean defaultValue){
       if(defaultValue){
    	   this.guId="";
    	   this.convrate=BigDecimal.ZERO;
    	   this.baseUnit=false;
    	   this.qtyPrecision=0;
    	   this.useConvsUnit=false;
    	   this.measureUnitType="1";
       }
    }
  	public ScmMaterialUnitRelation() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_GUID,
            FN_ITEMID,
            FN_LINEID,
            FN_TARGETUNITID,
            FN_CONVRATE,
            FN_BASEUNIT,
            FN_QTYPRECISION,
            FN_USECONVSUNIT,
            FN_CONVSUNITID,
            FN_MEASUREUNITTYPE,
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
        map.put(FN_GUID, 40);
        map.put(FN_MEASUREUNITTYPE, 16);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
