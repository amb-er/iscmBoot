package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmInvSaleIssueBillEntry")  
public class ScmInvSaleIssueBillEntry extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_OTID ="otId";
    public static final String FN_LINEID ="lineId";
    public static final String FN_ITEMID ="itemId";
    public static final String FN_UNIT ="unit";
    public static final String FN_PIEUNIT ="pieUnit";
    public static final String FN_BASEUNIT ="baseUnit";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_OUTORGUNITNO ="outOrgUnitNo";
    public static final String FN_OUTCOSTORGUNITNO ="outCostOrgUnitNo";
    public static final String FN_WAREHOUSEID ="wareHouseId";
    public static final String FN_LOT ="lot";
    public static final String FN_QTY ="qty";
    public static final String FN_PIEQTY ="pieQty";
    public static final String FN_BASEQTY ="baseQty";
    public static final String FN_PRICE ="price";
    public static final String FN_AMT ="amt";
    public static final String FN_TAXPRICE ="taxPrice";
    public static final String FN_TAXRATE ="taxRate";
    public static final String FN_TAXAMT ="taxAmt";
    public static final String FN_SALEQTY ="saleQty";
    public static final String FN_SALETAXRATE ="saleTaxRate";
    public static final String FN_SALETAXPRICE ="saleTaxPrice";
    public static final String FN_SALETAXAMT ="saleTaxAmt";
    public static final String FN_PRODUCTDATE ="productDate";
    public static final String FN_OFFSET ="offset";
    public static final String FN_BALANCECUSTID ="balanceCustId";
    public static final String FN_SOURCEBILLDTLID ="sourceBillDtlId";
    public static final String FN_STOCKID ="stockId";
    public static final String FN_INVDATE ="invDate";
    public static final String FN_ORGSOURCEID ="orgSourceId";
    public static final String FN_ORGSOURCEVENDORID ="orgSourceVendorId";
    public static final String FN_REMARKS ="remarks";
    public static final String FN_PRICEBILLID = "priceBillId";
	public static final String FN_REFPRICESTATUS = "refPriceStatus";
    
    private long id;
    private long otId;
    private int lineId;
    private long itemId;
    private long unit;
    private long pieUnit;
    private long baseUnit;
    private String outOrgUnitNo;
    private String outCostOrgUnitNo;
    private String orgUnitNo;
    private long wareHouseId;
    private String lot;
    private BigDecimal qty;
    private BigDecimal pieQty;
    private BigDecimal baseQty;
    private BigDecimal price;
    private BigDecimal amt;
    private BigDecimal taxPrice;
    private BigDecimal taxRate;
    private BigDecimal taxAmt;
    private BigDecimal saleQty;
    private BigDecimal saleTaxPrice;
    private BigDecimal saleTaxRate;
    private BigDecimal saleTaxAmt;
    private Date productDate;
    private boolean offset;
    private long balanceCustId;
    private long sourceBillDtlId;
    private long stockId;
    private Date invDate;
    private long orgSourceId;
    private long orgSourceVendorId;
    private String remarks;
    private long priceBillId;
    private String refPriceStatus;

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
    public String getOutOrgUnitNo() {
		return outOrgUnitNo;
	}

	public void setOutOrgUnitNo(String outOrgUnitNo) {
		this.outOrgUnitNo = outOrgUnitNo;
	}

	public String getOutCostOrgUnitNo() {
		return outCostOrgUnitNo;
	}

	public void setOutCostOrgUnitNo(String outCostOrgUnitNo) {
		this.outCostOrgUnitNo = outCostOrgUnitNo;
	}

	public String getOrgUnitNo() {
        return orgUnitNo;
    }

    public void setOrgUnitNo(String val) {
        this.orgUnitNo = val;
    }
    public long getWareHouseId() {
        return wareHouseId;
    }

    public void setWareHouseId(long val) {
        this.wareHouseId = val;
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
    public BigDecimal getBaseQty() {
        return baseQty;
    }

    public void setBaseQty(BigDecimal val) {
        this.baseQty = val;
    }

    public BigDecimal getSaleTaxRate() {
		return saleTaxRate;
	}

	public void setSaleTaxRate(BigDecimal saleTaxRate) {
		this.saleTaxRate = saleTaxRate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public BigDecimal getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}

	public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}

	public BigDecimal getSaleQty() {
        return saleQty;
    }

    public void setSaleQty(BigDecimal val) {
        this.saleQty = val;
    }
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal val) {
        this.taxRate = val;
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
    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date val) {
        this.productDate = val;
    }
    
    public boolean isOffset() {
        return offset;
    }

    public void setOffset(boolean offset) {
        this.offset = offset;
    }

    public long getBalanceCustId() {
        return balanceCustId;
    }

    public void setBalanceCustId(long val) {
        this.balanceCustId = val;
    }
    public long getSourceBillDtlId() {
        return sourceBillDtlId;
    }

    public void setSourceBillDtlId(long val) {
        this.sourceBillDtlId = val;
    }
    public long getStockId() {
        return stockId;
    }

    public void setStockId(long val) {
        this.stockId = val;
    }
    public Date getInvDate() {
        return invDate;
    }

    public void setInvDate(Date val) {
        this.invDate = val;
    }
    public long getOrgSourceId() {
        return orgSourceId;
    }

    public void setOrgSourceId(long val) {
        this.orgSourceId = val;
    }
    public long getOrgSourceVendorId() {
        return orgSourceVendorId;
    }

    public void setOrgSourceVendorId(long val) {
        this.orgSourceVendorId = val;
    }
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String val) {
        this.remarks = val;
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

	public ScmInvSaleIssueBillEntry(boolean defaultValue){
       if(defaultValue){
           this.saleQty=BigDecimal.ZERO;
           this.baseQty=BigDecimal.ZERO;
           this.pieQty=BigDecimal.ZERO;
           this.qty=BigDecimal.ZERO;
           this.price=BigDecimal.ZERO;
           this.amt=BigDecimal.ZERO;
           this.taxPrice=BigDecimal.ZERO;
           this.taxAmt=BigDecimal.ZERO;
           this.offset=false;
           this.stockId=0;
           this.sourceBillDtlId=0;
           this.orgSourceId=0;
           this.orgSourceVendorId=0;
           this.taxRate=BigDecimal.ZERO;
           this.saleTaxPrice=BigDecimal.ZERO;
           this.saleTaxAmt=BigDecimal.ZERO;
           this.saleTaxRate=BigDecimal.ZERO;
       }
    }
  	public ScmInvSaleIssueBillEntry() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_OTID,
            FN_LINEID,
            FN_ITEMID,
            FN_UNIT,
            FN_PIEUNIT,
            FN_BASEUNIT,
            FN_OUTORGUNITNO,
            FN_OUTCOSTORGUNITNO,
            FN_ORGUNITNO,
            FN_WAREHOUSEID,
            FN_LOT,
            FN_QTY,
            FN_PIEQTY,
            FN_BASEQTY,
            FN_PRICE,
            FN_TAXAMT,
            FN_TAXPRICE,
            FN_TAXAMT,
            FN_SALEQTY,
            FN_TAXRATE,
            FN_SALETAXPRICE,
            FN_SALETAXAMT,
            FN_PRODUCTDATE,
            FN_OFFSET,
            FN_BALANCECUSTID,
            FN_SOURCEBILLDTLID,
            FN_STOCKID,
            FN_INVDATE,
            FN_ORGSOURCEID,
            FN_ORGSOURCEVENDORID,
            FN_REMARKS,
            FN_PRICEBILLID,
            FN_REFPRICESTATUS,
            FN_SALETAXRATE
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
        map.put(FN_OUTORGUNITNO, 32);
        map.put(FN_OUTCOSTORGUNITNO, 32);
        map.put(FN_ORGUNITNO, 32);
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
