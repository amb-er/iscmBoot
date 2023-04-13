package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 

@XmlRootElement(name = "scmPurRequireEntry")  
public class ScmPurRequireEntry extends BaseModel {
	public static final String FN_ID = "Id";
	public static final String FN_PRID = "prId";
	public static final String FN_LINEID = "lineId";
	public static final String FN_ORGUNITNO = "orgUnitNo";
	public static final String FN_ITEMID = "itemId";
	public static final String FN_PURUNIT = "purUnit";
	public static final String FN_BASEUNIT = "baseUnit";
	public static final String FN_BASEQTY = "baseQty";
	public static final String FN_PURORGUNITNO = "purOrgUnitNo";
	public static final String FN_RECSTORAGEORGUNITNO = "recStorageOrgUnitNo";
	public static final String FN_ENTRUSTED = "entrusted";
	public static final String FN_MSRECSTORAGEORGUNITNO = "msRecStorageOrgUnitNo";
	public static final String FN_WAREHOUSEID = "wareHouseId";
	public static final String FN_BUYERID = "buyerId";
	public static final String FN_PURGROUPID = "purGroupId";
	public static final String FN_VENDORID = "vendorId";
    public static final String FN_DIRECTPURCHASE ="directPurchase";
	public static final String FN_QTY = "qty";
	public static final String FN_REQDATE = "reqDate";
	public static final String FN_ORDERQTY = "orderQty";
	public static final String FN_PRICE = "price";
	public static final String FN_AMT = "amt";
	public static final String FN_SOURCEDTLID = "sourceDtlId";
	public static final String FN_PRICEBILLID = "priceBillId";
	public static final String FN_PRICEBILLNO ="priceBillNo";
	public static final String FN_REFPRICESTATUS = "refPriceStatus";
	public static final String FN_ROWSTATUS = "rowStatus";
	public static final String FN_CHECKER = "checker";
	public static final String FN_CHECKDATE = "checkDate";
	public static final String FN_FROMSUPPLYINFO = "fromSupplyInfo";
	public static final String FN_REMARKS = "remarks";
	public static final String FN_ATTACHMENTID ="attachmentId";
	public static final String FN_PURREQUIREENTRYAUDIT = "purRequireEntryAudit";
    public static final String FN_SUPPLYCYCLE ="supplyCycle";
    public static final String FN_IDLE ="idle";
    public static final String FN_RECENTPRICE = "recentPrice";
    public static final String FN_STOCKQTY = "stockQty";
    public static final String FN_PIEQTY = "pieQty";
    public static final String FN_PIEUNIT = "pieUnit";
	
	private long id;
	private long prId;
	private int lineId;
	private String orgUnitNo;
	private long itemId;
	private long purUnit;
	private long baseUnit;
	private BigDecimal baseQty;
	private String purOrgUnitNo;
	private String recStorageOrgUnitNo;
	private boolean entrusted;
	private String msRecStorageOrgUnitNo;
	private long wareHouseId;
	private long buyerId;
	private long purGroupId;
	private long vendorId;
    private boolean directPurchase;
	private BigDecimal qty;
	private Date reqDate;
	private BigDecimal orderQty;
	private BigDecimal price;
	private BigDecimal amt;
	private long sourceDtlId;
	private long priceBillId;
	private String priceBillNo;
	private String refPriceStatus;
	private String rowStatus;
	private String checker;
	private Date checkDate;
	private boolean fromSupplyInfo;
	private String remarks;
	private long attachmentId;
	private boolean purRequireEntryAudit;
    private int supplyCycle;
    private boolean idle;
    private BigDecimal pieQty;
    private long pieUnit;
    
    //采购入库单价，库存量
    private BigDecimal recentPrice;
    private BigDecimal stockQty;
    
	public BigDecimal getRecentPrice() {
	    return recentPrice;
    }
    public void setRecentPrice(BigDecimal recentPrice) {
	    this.recentPrice = recentPrice;
    }
    public BigDecimal getStockQty() {
	    return stockQty;
    }
    public void setStockQty(BigDecimal stockQty) {
	    this.stockQty = stockQty;
    }
	
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
	public long getPrId() {
		return prId;
	}

	public void setPrId(long val) {
		this.prId = val;
	}
	public int getLineId() {
		return lineId;
	}

	public void setLineId(int val) {
		this.lineId = val;
	}
	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String val) {
		this.orgUnitNo = val;
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
	public long getBaseUnit() {
		return baseUnit;
	}

	public void setBaseUnit(long val) {
		this.baseUnit = val;
	}
	public BigDecimal getBaseQty() {
		return baseQty;
	}

	public void setBaseQty(BigDecimal val) {
		this.baseQty = val;
	}
	public String getPurOrgUnitNo() {
		return purOrgUnitNo;
	}

	public void setPurOrgUnitNo(String val) {
		this.purOrgUnitNo = val;
	}
	public String getRecStorageOrgUnitNo() {
		return recStorageOrgUnitNo;
	}

	public void setRecStorageOrgUnitNo(String val) {
		this.recStorageOrgUnitNo = val;
	}
	
	public boolean isEntrusted() {
		return entrusted;
	}

	public void setEntrusted(boolean entrusted) {
		this.entrusted = entrusted;
	}

	public String getMsRecStorageOrgUnitNo() {
		return msRecStorageOrgUnitNo;
	}

	public void setMsRecStorageOrgUnitNo(String msRecStorageOrgUnitNo) {
		this.msRecStorageOrgUnitNo = msRecStorageOrgUnitNo;
	}

	public long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(long val) {
		this.buyerId = val;
	}
	public long getPurGroupId() {
		return purGroupId;
	}

	public void setPurGroupId(long val) {
		this.purGroupId = val;
	}
	public long getVendorId() {
		return vendorId;
	}

	public void setVendorId(long val) {
		this.vendorId = val;
	}
	public boolean isDirectPurchase() {
		return directPurchase;
	}
	public void setDirectPurchase(boolean directPurchase) {
		this.directPurchase = directPurchase;
	}
	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal val) {
		this.qty = val;
	}
	public Date getReqDate() {
		return reqDate;
	}

	public void setReqDate(Date val) {
		this.reqDate = val;
	}
	public BigDecimal getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(BigDecimal val) {
		this.orderQty = val;
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
	public long getSourceDtlId() {
		return sourceDtlId;
	}

	public void setSourceDtlId(long val) {
		this.sourceDtlId = val;
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

	public void setPriceBillNo(String priceBillNo) {
		this.priceBillNo = priceBillNo;
	}

	public String getRefPriceStatus() {
		return refPriceStatus;
	}

	public void setRefPriceStatus(String val) {
		this.refPriceStatus = val;
	}
	public String getRowStatus() {
		return rowStatus;
	}

	public void setRowStatus(String val) {
		this.rowStatus = val;
	}
	public long getWareHouseId() {
		return wareHouseId;
	}

	public void setWareHouseId(long val) {
		this.wareHouseId = val;
	}
	public String getChecker() {
		return checker;
	}

	public void setChecker(String val) {
		this.checker = val;
	}
	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date val) {
		this.checkDate = val;
	}
	public boolean isFromSupplyInfo() {
		return fromSupplyInfo;
	}

	public void setFromSupplyInfo(boolean fromSupplyInfo) {
		this.fromSupplyInfo = fromSupplyInfo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String val) {
		this.remarks = val;
	}
	
	public boolean isPurRequireEntryAudit() {
		return purRequireEntryAudit;
	}

	public void setPurRequireEntryAudit(boolean purRequireEntryAudit) {
		this.purRequireEntryAudit = purRequireEntryAudit;
	}
	
	public int getSupplyCycle() {
		return supplyCycle;
	}
	
	public void setSupplyCycle(int supplyCycle) {
		this.supplyCycle = supplyCycle;
	}
	public boolean isIdle() {
		return idle;
	}
	public void setIdle(boolean idle) {
		this.idle = idle;
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
	public ScmPurRequireEntry(boolean defaultValue) {
		if (defaultValue) {
			this.pieQty=BigDecimal.ZERO;
			this.baseQty=BigDecimal.ZERO;
			this.baseQty=BigDecimal.ZERO;
			this.qty=BigDecimal.ZERO;
			this.orderQty=BigDecimal.ZERO;
			this.price=BigDecimal.ZERO;
			this.amt=BigDecimal.ZERO;
			this.refPriceStatus="0";
			this.entrusted=false;
			this.fromSupplyInfo=false;
			this.purRequireEntryAudit=false;
			this.supplyCycle=0;
			this.idle=false;
			this.stockQty=BigDecimal.ZERO;
			this.recentPrice=BigDecimal.ZERO;
			this.directPurchase=false;
		}
	}
  	public ScmPurRequireEntry() {
	}

	public String[] getFieldNames(){
		return new String[]{
			FN_ID,
			FN_PRID,
			FN_LINEID,
			FN_ORGUNITNO,
			FN_ITEMID,
			FN_PURUNIT,
			FN_BASEUNIT,
			FN_BASEQTY,
			FN_PURORGUNITNO,
			FN_RECSTORAGEORGUNITNO,
			FN_BUYERID,
			FN_PURGROUPID,
			FN_VENDORID,
			FN_DIRECTPURCHASE,
			FN_QTY,
			FN_REQDATE,
			FN_ORDERQTY,
			FN_PRICE,
			FN_AMT,
			FN_SOURCEDTLID,
			FN_PRICEBILLID,
			FN_REFPRICESTATUS,
			FN_ROWSTATUS,
			FN_WAREHOUSEID,
			FN_CHECKER,
			FN_CHECKDATE,
			FN_REMARKS,
			FN_IDLE,
			FN_RECENTPRICE,
			FN_STOCKQTY,
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
		map.put(FN_ORGUNITNO, 32);
		map.put(FN_PURORGUNITNO, 32);
		map.put(FN_RECSTORAGEORGUNITNO, 32);
		map.put(FN_REFPRICESTATUS, 16);
		map.put(FN_ROWSTATUS, 16);
		map.put(FN_CHECKER, 16);
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

