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
 
@XmlRootElement(name = "scmProductCostCardDetailHistory")  
public class ScmProductCostCardDetailHistory extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_CARDID ="cardId";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_BEGINDATE ="beginDate";
    public static final String FN_ENDDATE ="endDate";
    public static final String FN_PRODUCTID ="productId";
    public static final String FN_PRODUCTUNIT ="productUnit";
    public static final String FN_PRODUCTQTY ="productQty";
    public static final String FN_TYPE ="type";
    public static final String FN_ITEMID ="itemId";
    public static final String FN_CSTUNIT ="cstUnit";
    public static final String FN_QTY ="qty";
    public static final String FN_CREATOR ="creator";
    public static final String FN_CHECKER ="checker";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    public static final String FN_COSTPRICE ="costPrice";
    public static final String FN_PRICE ="price";
    public static final String FN_CONVRATE ="convrate";
    public static final String FN_INVUNIT ="invUnit";
    
    private long id;
    private long cardId;
    private String orgUnitNo;
    private Date beginDate;
    private Date endDate;
    private long productId;
    private long productUnit;
    private BigDecimal productQty;
    private String type;
    private long itemId;
    private long cstUnit;
    private BigDecimal qty;
    private String creator;
    private String checker;
    private String controlUnitNo;
    private BigDecimal costPrice;
    private BigDecimal price;
    private BigDecimal convrate;
    private long invUnit;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public long getCardId() {
        return cardId;
    }

    public void setCardId(long val) {
        this.cardId = val;
    }
    public String getOrgUnitNo() {
        return orgUnitNo;
    }

    public void setOrgUnitNo(String val) {
        this.orgUnitNo = val;
    }
    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date val) {
        this.beginDate = val;
    }
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date val) {
        this.endDate = val;
    }
    public long getProductId() {
        return productId;
    }

    public void setProductId(long val) {
        this.productId = val;
    }
    public long getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(long val) {
        this.productUnit = val;
    }
    public BigDecimal getProductQty() {
        return productQty;
    }

    public void setProductQty(BigDecimal val) {
        this.productQty = val;
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
    public String getControlUnitNo() {
        return controlUnitNo;
    }

    public void setControlUnitNo(String val) {
        this.controlUnitNo = val;
    }

    public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
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

	public ScmProductCostCardDetailHistory(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmProductCostCardDetailHistory() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_CARDID,
            FN_ORGUNITNO,
            FN_BEGINDATE,
            FN_ENDDATE,
            FN_PRODUCTID,
            FN_PRODUCTUNIT,
            FN_PRODUCTQTY,
            FN_TYPE,
            FN_ITEMID,
            FN_CSTUNIT,
            FN_QTY,
            FN_CREATOR,
            FN_CHECKER,
            FN_CONTROLUNITNO,
            FN_COSTPRICE,
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
        map.put(FN_ORGUNITNO, 32);
        map.put(FN_TYPE, 16);
        map.put(FN_CREATOR, 16);
        map.put(FN_CHECKER, 16);
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
