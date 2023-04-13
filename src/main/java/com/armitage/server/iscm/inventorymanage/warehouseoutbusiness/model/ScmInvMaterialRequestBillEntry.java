package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmInvMaterialRequestBillEntry")  
public class ScmInvMaterialRequestBillEntry extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_REQID ="reqId";
    public static final String FN_LINEID ="lineId";
    public static final String FN_ITEMID ="itemId";
    public static final String FN_UNIT ="unit";
    public static final String FN_PIEUNIT ="pieUnit";
    public static final String FN_BASEUNIT ="baseUnit";
    public static final String FN_QTY ="qty";
    public static final String FN_PIEQTY ="pieQty";
    public static final String FN_BASEQTY ="baseQty";
    public static final String FN_OUTQTY ="outQty";
    public static final String FN_PRICE ="price";
    public static final String FN_AMT ="amt";
    public static final String FN_TAXRATE ="taxRate";
    public static final String FN_TAXPRICE ="taxPrice";
    public static final String FN_TAXAMT ="taxAmt";
    public static final String FN_REMARKS ="remarks";
    public static final String FN_ROWSTATUS ="rowStatus";
    public static final String FN_COSTUSETYPEID = "costUseTypeId";
    
    private long id;
    private long reqId;
    private int lineId;
    private long itemId;
    private long unit;
    private long pieUnit;
    private long baseUnit;
    private BigDecimal qty;
    private BigDecimal pieQty;
    private BigDecimal baseQty;
    private BigDecimal outQty;
    private BigDecimal price;
    private BigDecimal amt;
    private BigDecimal taxRate;
    private BigDecimal taxPrice;
    private BigDecimal taxAmt;
    private String remarks;
    private String rowStatus;
    private String checker;
    private Date checkDate;
    private long costUseTypeId;
    
    public long getCostUseTypeId() {
		return costUseTypeId;
	}

	public void setCostUseTypeId(long costUseTypeId) {
		this.costUseTypeId = costUseTypeId;
	}

	public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public long getReqId() {
        return reqId;
    }

    public void setReqId(long val) {
        this.reqId = val;
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
    public BigDecimal getPieQty() {
        return pieQty;
    }

    public void setPieQty(BigDecimal val) {
        this.pieQty = val;
    }
    public BigDecimal getBaseQty() {
        return baseQty;
    }

    public void setBaseQty(BigDecimal val) {
        this.baseQty = val;
    }
    public BigDecimal getOutQty() {
        return outQty;
    }

    public void setOutQty(BigDecimal val) {
        this.outQty = val;
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
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String val) {
        this.remarks = val;
    }

    public String getRowStatus() {
		return rowStatus;
	}

	public void setRowStatus(String rowStatus) {
		this.rowStatus = rowStatus;
	}

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public ScmInvMaterialRequestBillEntry(boolean defaultValue){
		if (defaultValue) {
			this.pieQty = BigDecimal.ZERO;
			this.baseQty = BigDecimal.ZERO;
			this.qty = BigDecimal.ZERO;
			this.price = BigDecimal.ZERO;
			this.amt = BigDecimal.ZERO;
			this.taxRate = BigDecimal.ZERO;
			this.taxPrice = BigDecimal.ZERO;
			this.taxAmt = BigDecimal.ZERO;
			this.outQty = BigDecimal.ZERO;
			this.rowStatus="I";
		}
    }
  	public ScmInvMaterialRequestBillEntry() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_REQID,
            FN_LINEID,
            FN_ITEMID,
            FN_UNIT,
            FN_PIEUNIT,
            FN_BASEUNIT,
            FN_QTY,
            FN_PIEQTY,
            FN_BASEQTY,
            FN_OUTQTY,
            FN_PRICE,
            FN_AMT,
            FN_TAXRATE,
            FN_TAXPRICE,
            FN_TAXAMT,
            FN_REMARKS,
            FN_ROWSTATUS,
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
        map.put(FN_ROWSTATUS, 16);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
