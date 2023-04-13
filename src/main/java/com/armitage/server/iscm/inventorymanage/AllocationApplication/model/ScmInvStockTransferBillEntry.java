package com.armitage.server.iscm.inventorymanage.AllocationApplication.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmInvStockTransferBillEntry")  
public class ScmInvStockTransferBillEntry extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_WTID ="wtId";
    public static final String FN_LINEID ="lineId";
    public static final String FN_ITEMID ="itemId";
    public static final String FN_UNIT ="unit";
    public static final String FN_PIEUNIT ="pieUnit";
    public static final String FN_BASEUNIT ="baseUnit";
    public static final String FN_ISSUEORGUNITNO ="issueOrgUnitNo";
    public static final String FN_WAREHOUSEID ="wareHouseId";
    public static final String FN_ISSUEMANAGEORGUNITNO ="issueManageOrgUnitNo";
    public static final String FN_RECEIVEORGUNITNO ="receiveOrgUnitNo";
    public static final String FN_RECEIPTWAREHOUSEID ="receiptWarehouseId";
    public static final String FN_RECEIVEMANAGEORGUNITNO ="receiveManageOrgUnitNo";
    public static final String FN_USEORGUNITNO ="useOrgUnitNo";
    public static final String FN_COSTORGUNITNO ="costOrgUnitNo";
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
    public static final String FN_STOCKID ="stockId";
    public static final String FN_INVDATE ="invDate";
    public static final String FN_ISSUEBASEQTY ="issueBaseQty";
    public static final String FN_RECEIPTBASEQTY ="receiptBaseQty";
    public static final String FN_SOURCEBILLDTLID ="sourceBillDtlId";
    public static final String FN_REMARKS ="remarks";
    
    private long id;
    private long wtId;
    private int lineId;
    private long itemId;
    private long unit;
    private long pieUnit;
    private long baseUnit;
    private String issueOrgUnitNo;
    private long wareHouseId;
    private String issueManageOrgUnitNo;
    private String receiveOrgUnitNo;
    private long receiptWarehouseId;
    private String receiveManageOrgUnitNo;
    private String useOrgUnitNo;
    private String costOrgUnitNo;
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
    private BigDecimal issueBaseQty;
    private BigDecimal receiptBaseQty;
    private long sourceBillDtlId;
    private String remarks;
    


    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public long getWtId() {
        return wtId;
    }

    public void setWtId(long val) {
        this.wtId = val;
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
    public String getIssueOrgUnitNo() {
        return issueOrgUnitNo;
    }

    public void setIssueOrgUnitNo(String val) {
        this.issueOrgUnitNo = val;
    }
    public long getWareHouseId() {
        return wareHouseId;
    }

    public void setWareHouseId(long val) {
        this.wareHouseId = val;
    }
    public String getIssueManageOrgUnitNo() {
        return issueManageOrgUnitNo;
    }

    public void setIssueManageOrgUnitNo(String val) {
        this.issueManageOrgUnitNo = val;
    }
    public String getReceiveOrgUnitNo() {
        return receiveOrgUnitNo;
    }

    public void setReceiveOrgUnitNo(String val) {
        this.receiveOrgUnitNo = val;
    }
    public long getReceiptWarehouseId() {
        return receiptWarehouseId;
    }

    public void setReceiptWarehouseId(long val) {
        this.receiptWarehouseId = val;
    }
    public String getReceiveManageOrgUnitNo() {
        return receiveManageOrgUnitNo;
    }

    public void setReceiveManageOrgUnitNo(String val) {
        this.receiveManageOrgUnitNo = val;
    }
    public String getUseOrgUnitNo() {
        return useOrgUnitNo;
    }

    public void setUseOrgUnitNo(String val) {
        this.useOrgUnitNo = val;
    }
    public String getCostOrgUnitNo() {
        return costOrgUnitNo;
    }

    public void setCostOrgUnitNo(String val) {
        this.costOrgUnitNo = val;
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

    public void setStockId(long val) {
        this.stockId = val;
    }
    public Date getInvDate() {
        return invDate;
    }

    public void setInvDate(Date val) {
        this.invDate = val;
    }
    public BigDecimal getIssueBaseQty() {
        return issueBaseQty;
    }

    public void setIssueBaseQty(BigDecimal val) {
        this.issueBaseQty = val;
    }
    public BigDecimal getReceiptBaseQty() {
        return receiptBaseQty;
    }

    public void setReceiptBaseQty(BigDecimal val) {
        this.receiptBaseQty = val;
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

    public ScmInvStockTransferBillEntry(boolean defaultValue){
       if(defaultValue){
    	   this.qty=BigDecimal.ZERO;
    	   this.baseQty=BigDecimal.ZERO;
    	   this.price=BigDecimal.ZERO;
    	   this.amt=BigDecimal.ZERO;
    	   this.issueBaseQty=BigDecimal.ZERO;
    	   this.receiptBaseQty=BigDecimal.ZERO;
    	   this.pieQty=BigDecimal.ZERO;
    	   this.taxRate=BigDecimal.ZERO;
    	   this.taxPrice=BigDecimal.ZERO;
    	   this.taxAmt=BigDecimal.ZERO;
    	   this.wareHouseId=0;
    	   this.receiptWarehouseId=0;
    	   this.sourceBillDtlId=0;  
       }
    }
  	public ScmInvStockTransferBillEntry() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_WTID,
            FN_LINEID,
            FN_ITEMID,
            FN_UNIT,
            FN_PIEUNIT,
            FN_BASEUNIT,
            FN_ISSUEORGUNITNO,
            FN_WAREHOUSEID,
            FN_ISSUEMANAGEORGUNITNO,
            FN_RECEIVEORGUNITNO,
            FN_RECEIPTWAREHOUSEID,
            FN_RECEIVEMANAGEORGUNITNO,
            FN_USEORGUNITNO,
            FN_COSTORGUNITNO,
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
            FN_ISSUEBASEQTY,
            FN_RECEIPTBASEQTY,
            FN_SOURCEBILLDTLID,
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
        map.put(FN_ISSUEORGUNITNO, 32);
        map.put(FN_ISSUEMANAGEORGUNITNO, 32);
        map.put(FN_RECEIVEORGUNITNO, 32);
        map.put(FN_RECEIVEMANAGEORGUNITNO, 32);
        map.put(FN_USEORGUNITNO, 32);
        map.put(FN_COSTORGUNITNO, 32);
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

