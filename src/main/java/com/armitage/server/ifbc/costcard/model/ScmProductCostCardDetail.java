
package com.armitage.server.ifbc.costcard.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmProductCostCardDetail")  
public class ScmProductCostCardDetail extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_CARDID ="cardId";
    public static final String FN_TYPE ="type";
    public static final String FN_ITEMID ="itemId";
    public static final String FN_CSTUNIT ="cstUnit";
    public static final String FN_QTY ="qty";
    public static final String FN_CREATOR ="creator";
    public static final String FN_CHECKER ="checker";
    public static final String FN_PRICE ="price";
    public static final String FN_CONVRATE ="convrate";
    public static final String FN_INVUNIT ="invUnit";
    
    private int id;
    private long cardId;
    private String type;
    private long itemId;
    private long cstUnit;
    private BigDecimal qty;
    private String creator;
    private String checker;
    private BigDecimal price;
    private BigDecimal convrate;
    private long invUnit;

    public int getId() {
        return id;
    }

    public void setId(int val) {
        this.id = val;
    }
    public long getCardId() {
        return cardId;
    }

    public void setCardId(long val) {
        this.cardId = val;
    }
    public String getType() {
        return type;
    }

    public void setType(String val) {
        this.type = val;
    }
    public long getItemId() {
        return itemId;
    }

    public void setItemId(long val) {
        this.itemId = val;
    }
    public long getCstUnit() {
        return cstUnit;
    }

    public void setCstUnit(long val) {
        this.cstUnit = val;
    }
    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal val) {
        this.qty = val;
    }
    public String getCreator() {
        return creator;
    }

    public void setCreator(String val) {
        this.creator = val;
    }
    public String getChecker() {
        return checker;
    }

    public void setChecker(String val) {
        this.checker = val;
    }

    public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getConvrate() {
		return convrate;
	}

	public void setConvrate(BigDecimal convrate) {
		this.convrate = convrate;
	}

	public long getInvUnit() {
		return invUnit;
	}

	public void setInvUnit(long invUnit) {
		this.invUnit = invUnit;
	}

	public ScmProductCostCardDetail(boolean defaultValue){
       if(defaultValue){
    	   this.qty=BigDecimal.ZERO;
    	   this.price=BigDecimal.ZERO;
    	   this.convrate=BigDecimal.ZERO;
    	   this.invUnit=0;
       }
    }
  	public ScmProductCostCardDetail() {
  		
	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_CARDID,
            FN_TYPE,
            FN_ITEMID,
            FN_CSTUNIT,
            FN_QTY,
            FN_CREATOR,
            FN_CHECKER,
            FN_PRICE,
            FN_CONVRATE,
            FN_INVUNIT,
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
        map.put(FN_TYPE, 16);
        map.put(FN_CREATOR, 16);
        map.put(FN_CHECKER, 16);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
