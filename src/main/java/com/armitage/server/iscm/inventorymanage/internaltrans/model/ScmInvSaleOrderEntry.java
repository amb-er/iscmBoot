package com.armitage.server.iscm.inventorymanage.internaltrans.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmInvSaleOrderEntry")  
public class ScmInvSaleOrderEntry extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_OTID ="otId";
    public static final String FN_LINEID ="lineId";
    public static final String FN_ITEMID ="itemId";
    public static final String FN_UNIT ="unit";
    public static final String FN_BASEUNIT ="baseUnit";
    public static final String FN_QTY ="qty";
    public static final String FN_BASEQTY ="baseQty";
    public static final String FN_SALETAXPRICE ="saleTaxPrice";
    public static final String FN_SALETAXAMT ="saleTaxAmt";
    public static final String FN_OUTQTY ="outQty";
	public static final String FN_PRICEBILLID = "priceBillId";
	public static final String FN_REFPRICESTATUS = "refPriceStatus";
    public static final String FN_SOURCEBILLDTLID ="sourceBillDtlId";
    public static final String FN_REMARKS ="remarks";
    public static final String FN_PIEQTY = "pieQty";
    public static final String FN_PIEUNIT = "pieUnit";
    
    private long id;
    private long otId;
    private int lineId;
    private long itemId;
    private long unit;
    private long baseUnit;
    private BigDecimal qty;
    private BigDecimal baseQty;
	private BigDecimal saleTaxPrice;
	private BigDecimal saleTaxAmt;
	private BigDecimal outQty;
	private long priceBillId;
	private String refPriceStatus;
	private long sourceBillDtlId;
	private String remarks;
	// 辅助数量，单位
	private BigDecimal pieQty;
	private long pieUnit;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public long getOtId() {
        return otId;
    }

    public void setOtId(long val) {
        this.otId = val;
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
    public long getBaseUnit() {
        return baseUnit;
    }

    public void setBaseUnit(long val) {
        this.baseUnit = val;
    }
    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal val) {
        this.qty = val;
    }
    public BigDecimal getBaseQty() {
        return baseQty;
    }

    public void setBaseQty(BigDecimal val) {
        this.baseQty = val;
    }
    public BigDecimal getSaleTaxPrice() {
        return saleTaxPrice;
    }

    public void setSaleTaxPrice(BigDecimal val) {
        this.saleTaxPrice = val;
    }
    public BigDecimal getSaleTaxAmt() {
        return saleTaxAmt;
    }

    public void setSaleTaxAmt(BigDecimal val) {
        this.saleTaxAmt = val;
    }
    public BigDecimal getOutQty() {
		return outQty;
	}

	public void setOutQty(BigDecimal outQty) {
		this.outQty = outQty;
	}

	public long getPriceBillId() {
		return priceBillId;
	}

	public void setPriceBillId(long priceBillId) {
		this.priceBillId = priceBillId;
	}

	public String getRefPriceStatus() {
		return refPriceStatus;
	}

	public void setRefPriceStatus(String refPriceStatus) {
		this.refPriceStatus = refPriceStatus;
	}

	public long getSourceBillDtlId() {
        return sourceBillDtlId;
    }

    public void setSourceBillDtlId(long val) {
        this.sourceBillDtlId = val;
    }
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String val) {
        this.remarks = val;
    }

    public BigDecimal getPieQty() {
		return pieQty;
	}

	public void setPieQty(BigDecimal pieQty) {
		this.pieQty = pieQty;
	}

	public long getPieUnit() {
		return pieUnit;
	}

	public void setPieUnit(long pieUnit) {
		this.pieUnit = pieUnit;
	}

	public ScmInvSaleOrderEntry(boolean defaultValue){
       if(defaultValue){
    	   this.pieQty=BigDecimal.ZERO;
           this.baseQty=BigDecimal.ZERO;
           this.qty=BigDecimal.ZERO;
           this.saleTaxPrice=BigDecimal.ZERO;
           this.saleTaxAmt=BigDecimal.ZERO;
           this.outQty=BigDecimal.ZERO;
       }
    }
  	public ScmInvSaleOrderEntry() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_OTID,
            FN_LINEID,
            FN_ITEMID,
            FN_UNIT,
            FN_BASEUNIT,
            FN_QTY,
            FN_BASEQTY,
            FN_SALETAXPRICE,
            FN_SALETAXAMT,
            FN_OUTQTY,
			FN_PRICEBILLID,
			FN_REFPRICESTATUS,
            FN_SOURCEBILLDTLID,
            FN_REMARKS,
            FN_PIEQTY,
			FN_PIEUNIT,
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
        map.put(FN_REMARKS, 255);
        map.put(FN_REFPRICESTATUS, 16);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
