package com.armitage.server.ifbc.costcard.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmItemCostPriceLogDetail")  
public class ScmItemCostPriceLogDetail extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_LOGID ="logId";
    public static final String FN_ITEMID ="itemId";
    public static final String FN_INVUNITID ="invUnitId";
    public static final String FN_INVPRICE ="invPrice";
    public static final String FN_CONVRATE ="convrate";
    public static final String FN_CSTUNITID ="cstUnitId";
    public static final String FN_PRICE ="price";
    public static final String FN_PRICESOURCEBILLTYPE ="priceSourceBillType";
    public static final String FN_PRICESOURCEBILLID ="priceSourceBillId";
    public static final String FN_BILLNO ="billNo";
    
    private long id;
    private long logId;
    private long itemId;
    private long invUnitId;
    private BigDecimal invPrice;
    private BigDecimal convrate;
    private long cstUnitId;
    private BigDecimal price;
    private String priceSourceBillType;
    private long priceSourceBillId;
    private String billNo;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public long getLogId() {
        return logId;
    }

    public void setLogId(long val) {
        this.logId = val;
    }
    public long getItemId() {
        return itemId;
    }

    public void setItemId(long val) {
        this.itemId = val;
    }
    public long getInvUnitId() {
        return invUnitId;
    }

    public void setInvUnitId(long val) {
        this.invUnitId = val;
    }
    public BigDecimal getInvPrice() {
        return invPrice;
    }

    public void setInvPrice(BigDecimal val) {
        this.invPrice = val;
    }
    public BigDecimal getConvrate() {
        return convrate;
    }

    public void setConvrate(BigDecimal val) {
        this.convrate = val;
    }
    public long getCstUnitId() {
        return cstUnitId;
    }

    public void setCstUnitId(long val) {
        this.cstUnitId = val;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal val) {
        this.price = val;
    }
    public String getPriceSourceBillType() {
        return priceSourceBillType;
    }

    public void setPriceSourceBillType(String val) {
        this.priceSourceBillType = val;
    }
    public long getPriceSourceBillId() {
        return priceSourceBillId;
    }

    public void setPriceSourceBillId(long val) {
        this.priceSourceBillId = val;
    }
    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String val) {
        this.billNo = val;
    }

    public ScmItemCostPriceLogDetail(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmItemCostPriceLogDetail() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_LOGID,
            FN_ITEMID,
            FN_INVUNITID,
            FN_INVPRICE,
            FN_CONVRATE,
            FN_CSTUNITID,
            FN_PRICE,
            FN_PRICESOURCEBILLTYPE,
            FN_PRICESOURCEBILLID,
            FN_BILLNO,
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
        map.put(FN_PRICESOURCEBILLTYPE, 16);
        map.put(FN_BILLNO, 32);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
