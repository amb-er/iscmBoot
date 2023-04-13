package com.armitage.server.iscm.inventorymanage.inventoryinitialization.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmInvInitBillEntry")  
public class ScmInvInitBillEntry extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_INITID ="initId";
    public static final String FN_LINEID ="lineId";
    public static final String FN_ITEMID ="itemId";
    public static final String FN_UNIT ="unit";
    public static final String FN_PIEUNIT ="pieUnit";
    public static final String FN_LOT ="lot";
    public static final String FN_QTY ="qty";
    public static final String FN_PIEQTY ="pieQty";
    public static final String FN_PRICE ="price";
    public static final String FN_AMT ="amt";
    public static final String FN_TAXRATE ="taxRate";
    public static final String FN_TAXPRICE ="taxPrice";
    public static final String FN_TAXAMT ="taxAmt";
    public static final String FN_EXPDATE ="expDate";
    public static final String FN_REMARKS ="remarks";
    
    private long id;
    private long initId;
    private int lineId;
    private long itemId;
    private long unit;
    private long pieUnit;
    private String lot;
    private BigDecimal qty;
    private BigDecimal pieQty;
    private BigDecimal price;
    private BigDecimal amt;
    private BigDecimal taxRate;
    private BigDecimal taxPrice;
    private BigDecimal taxAmt;
    private Date expDate;
    private String remarks;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public long getInitId() {
        return initId;
    }

    public void setInitId(long val) {
        this.initId = val;
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
    public long getUnit() {
        return unit;
    }

    public void setUnit(long val) {
        this.unit = val;
    }
    public long getPieUnit() {
        return pieUnit;
    }

    public void setPieUnit(long val) {
        this.pieUnit = val;
    }
    public String getLot() {
        return lot;
    }

    public void setLot(String val) {
        this.lot = val;
    }
    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal val) {
        this.qty = val;
    }
    public BigDecimal getPieQty() {
        return pieQty;
    }

    public void setPieQty(BigDecimal val) {
        this.pieQty = val;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal val) {
        this.price = val;
    }
    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal val) {
        this.amt = val;
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
    public BigDecimal getTaxAmt() {
        return taxAmt;
    }

    public void setTaxAmt(BigDecimal val) {
        this.taxAmt = val;
    }
    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date val) {
        this.expDate = val;
    }
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String val) {
        this.remarks = val;
    }

    public ScmInvInitBillEntry(boolean defaultValue){
       if(defaultValue){
    	   this.pieQty=BigDecimal.ZERO;
			this.qty=BigDecimal.ZERO;
			this.price=BigDecimal.ZERO;
			this.amt=BigDecimal.ZERO;
			this.taxRate=BigDecimal.ZERO;
			this.taxPrice=BigDecimal.ZERO;
			this.taxAmt=BigDecimal.ZERO;	
       }
    }
  	public ScmInvInitBillEntry() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_INITID,
            FN_LINEID,
            FN_ITEMID,
            FN_UNIT,
            FN_PIEUNIT,
            FN_LOT,
            FN_QTY,
            FN_PIEQTY,
            FN_PRICE,
            FN_AMT,
            FN_TAXRATE,
            FN_TAXPRICE,
            FN_TAXAMT,
            FN_EXPDATE,
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
        map.put(FN_LOT, 32);
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
