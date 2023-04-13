package com.armitage.server.ifbc.costcard.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmStandardRatioDetail")  
public class ScmStandardRatioDetail extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_RATEID ="rateId";
    public static final String FN_ITEMID ="itemId";
    public static final String FN_INVUNIT ="invUnit";
    public static final String FN_QTY ="qty";
    public static final String FN_NETRATE ="netRate";
    
    private long id;
    private long rateId;
    private long itemId;
    private long invUnit;
    private BigDecimal qty;
    private BigDecimal netRate;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public long getRateId() {
        return rateId;
    }

    public void setRateId(long val) {
        this.rateId = val;
    }
    public long getItemId() {
        return itemId;
    }

    public void setItemId(long val) {
        this.itemId = val;
    }
    public long getInvUnit() {
        return invUnit;
    }

    public void setInvUnit(long val) {
        this.invUnit = val;
    }
    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal val) {
        this.qty = val;
    }
    public BigDecimal getNetRate() {
        return netRate;
    }

    public void setNetRate(BigDecimal val) {
        this.netRate = val;
    }

    public ScmStandardRatioDetail(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmStandardRatioDetail() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_RATEID,
            FN_ITEMID,
            FN_INVUNIT,
            FN_QTY,
            FN_NETRATE,
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
