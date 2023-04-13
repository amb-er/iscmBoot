package com.armitage.server.iscm.inventorymanage.cstbusiness.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;

@XmlRootElement(name = "scmCstFrmLossEntry")  
public class ScmCstFrmLossEntry extends BaseModel {
	public static final String FN_ID ="id";
    public static final String FN_BILLID ="billId";
    public static final String FN_LINEID ="lineId";
    public static final String FN_ITEMID ="itemId";
    public static final String FN_UNIT ="unit";
    public static final String FN_PIEUNIT ="pieUnit";
    public static final String FN_BASEUNIT ="baseUnit";
    public static final String FN_LOT ="lot";
    public static final String FN_QTY ="qty";
    public static final String FN_PIEQTY ="pieQty";
    public static final String FN_BASEQTY ="baseQty";
    public static final String FN_PRICE ="price";
    public static final String FN_AMT ="amt";
    public static final String FN_TAXRATE ="taxRate";
    public static final String FN_TAXPRICE ="taxPrice";
    public static final String FN_TAXAMT ="taxAmt";
    public static final String FN_EXPDATE ="expDate";
    public static final String FN_STOCKID = "stockId";
    public static final String FN_INVDATE = "invDate";
    public static final String FN_ORGSOURCEID = "orgSourceId";
    public static final String FN_ORGSOURCEVENDORID = "orgSourceVendorId";
    public static final String FN_REMARKS ="remarks";
    public static final String FN_ATTACHMENTID ="attachmentId";
    
    
    private long id;
    private long billId;
    private int lineId;
    private long itemId;
    private long unit;
    private long pieUnit;
    private long baseUnit;
    private String lot;
    private BigDecimal qty;
    private BigDecimal pieQty;
    private BigDecimal baseQty;
    private BigDecimal price;
    private BigDecimal amt;
    private BigDecimal taxRate;
    private BigDecimal taxPrice;
    private BigDecimal taxAmt;
    private Date expDate;
    private long stockId;
    private Date invDate;
	private long orgSourceId;
	private long orgSourceVendorId;
    private String remarks;
    private long attachmentId;
    
    

    public long getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(long attachmentId) {
		this.attachmentId = attachmentId;
	}

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
    public long getStockId() {
		return stockId;
	}

	public void setStockId(long stockId) {
		this.stockId = stockId;
	}

	public Date getInvDate() {
		return invDate;
	}

	public void setInvDate(Date invDate) {
		this.invDate = invDate;
	}

	public long getOrgSourceId() {
		return orgSourceId;
	}

	public void setOrgSourceId(long orgSourceId) {
		this.orgSourceId = orgSourceId;
	}

	public long getOrgSourceVendorId() {
		return orgSourceVendorId;
	}

	public void setOrgSourceVendorId(long orgSourceVendorId) {
		this.orgSourceVendorId = orgSourceVendorId;
	}

	public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String val) {
        this.remarks = val;
    }
    
    public ScmCstFrmLossEntry(boolean defaultValue){
        if(defaultValue){
            this.pieQty=BigDecimal.ZERO;
            this.qty=BigDecimal.ZERO;
            this.price=BigDecimal.ZERO;
            this.amt=BigDecimal.ZERO;
            this.taxRate=BigDecimal.ZERO;
            this.taxPrice=BigDecimal.ZERO;
            this.taxAmt=BigDecimal.ZERO;
            this.baseQty=BigDecimal.ZERO;
        }
     }
    
    public ScmCstFrmLossEntry() {

	}

  	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_BILLID,
            FN_LINEID,
            FN_ITEMID,
            FN_UNIT,
            FN_PIEUNIT,
            FN_BASEUNIT,
            FN_LOT,
            FN_QTY,
            FN_PIEQTY,
            FN_BASEQTY,
            FN_PRICE,
            FN_AMT,
            FN_TAXRATE,
            FN_TAXPRICE,
            FN_TAXAMT,
            FN_EXPDATE,
            FN_STOCKID,
            FN_INVDATE,
            FN_ORGSOURCEID,
            FN_ORGSOURCEVENDORID,
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
        map.put(FN_LOT, 96);
        map.put(FN_REMARKS, 765);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}
}
