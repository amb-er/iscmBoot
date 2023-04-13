
package com.armitage.server.iscm.inventorymanage.inventorysetting.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmInvSupplyPlanEntry")  
public class ScmInvSupplyPlanEntry extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_PLANTID ="plantId";
    public static final String FN_LINEID ="lineId";
    public static final String FN_ITEMID ="itemId";
    public static final String FN_BASEUNIT ="baseUnit";
    public static final String FN_PIEUNITID ="pieUnitId";
    public static final String FN_QTY ="qty";
    
    
    private long id;
    private long plantId;
    private int lineId;
    private long itemId;
    private long baseUnit;
    private long pieUnitId;
    
    
    public long getPieUnitId() {
		return pieUnitId;
	}

	public void setPieUnitId(long pieUnitId) {
		this.pieUnitId = pieUnitId;
	}

	public long getBaseUnit() {
		return baseUnit;
	}

	public void setBaseUnit(long baseUnit) {
		this.baseUnit = baseUnit;
	}

	private BigDecimal qty;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public long getPlantId() {
        return plantId;
    }

    public void setPlantId(long val) {
        this.plantId = val;
    }
    public int getLineId() {
        return lineId;
    }

    public void setLineId(int val) {
        this.lineId = val;
    }
    public long getItemId() {
        return itemId;
    }

    public void setItemId(long val) {
        this.itemId = val;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal val) {
        this.qty = val;
    }

    public ScmInvSupplyPlanEntry(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmInvSupplyPlanEntry() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_PLANTID,
            FN_LINEID,
            FN_ITEMID,
            FN_BASEUNIT,
            FN_QTY,
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
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
