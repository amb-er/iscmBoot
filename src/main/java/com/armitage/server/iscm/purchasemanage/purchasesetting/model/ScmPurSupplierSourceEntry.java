package com.armitage.server.iscm.purchasemanage.purchasesetting.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmPurSupplierSourceEntry")  
public class ScmPurSupplierSourceEntry extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_BILLID ="billId";
    public static final String FN_LINEID ="lineId";
    public static final String FN_ITEMID ="itemId";
    public static final String FN_PURUNIT ="purUnit";
    public static final String FN_PRICE ="price";
    public static final String FN_TAXRATE ="taxRate";
    public static final String FN_TAXPRICE ="taxPrice";
    public static final String FN_PRICEBILLID ="priceBillId";
    public static final String FN_PRICEBILLNO ="priceBillNo";
    public static final String FN_REFPRICESTATUS ="refPriceStatus";
    public static final String FN_REMARKS ="remarks";
    
    private long id;
    private long billId;
    private int lineId;
    private long itemId;
    private long purUnit;
    private BigDecimal price;
    private BigDecimal taxRate;
    private BigDecimal taxPrice;
    private long priceBillId;
    private String priceBillNo;
    private String refPriceStatus;
    private String remarks;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public long getBillId() {
        return billId;
    }

    public void setBillId(long val) {
        this.billId = val;
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
    public long getPurUnit() {
        return purUnit;
    }

    public void setPurUnit(long val) {
        this.purUnit = val;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal val) {
        this.price = val;
    }
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal val) {
        this.taxRate = val;
    }
    public BigDecimal getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(BigDecimal val) {
        this.taxPrice = val;
    }
    public long getPriceBillId() {
        return priceBillId;
    }

    public void setPriceBillId(long val) {
        this.priceBillId = val;
    }
    public String getPriceBillNo() {
        return priceBillNo;
    }

    public void setPriceBillNo(String val) {
        this.priceBillNo = val;
    }
    public String getRefPriceStatus() {
        return refPriceStatus;
    }

    public void setRefPriceStatus(String val) {
        this.refPriceStatus = val;
    }
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String val) {
        this.remarks = val;
    }

    public ScmPurSupplierSourceEntry(boolean defaultValue){
       if(defaultValue){
    	   this.itemId=0;
    	   this.purUnit=0;
    	   this.price=BigDecimal.ZERO;
    	   this.taxPrice=BigDecimal.ZERO;
    	   this.taxRate=BigDecimal.ZERO;
    	   this.priceBillId=0;
    	   this.refPriceStatus="0";
       }
    }
  	public ScmPurSupplierSourceEntry() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_BILLID,
            FN_LINEID,
            FN_ITEMID,
            FN_PURUNIT,
            FN_PRICE,
            FN_TAXRATE,
            FN_TAXPRICE,
            FN_PRICEBILLID,
            FN_PRICEBILLNO,
            FN_REFPRICESTATUS,
            FN_REMARKS,
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
        map.put(FN_PRICEBILLNO, 32);
        map.put(FN_REFPRICESTATUS, 16);
        map.put(FN_REMARKS, 255);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
