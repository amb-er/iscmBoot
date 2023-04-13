package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 

@XmlRootElement(name = "scmInvPurInWarehsBillEntry")  
public class ScmInvPurInWarehsBillEntry extends BaseModel {
	public static final String FN_ID = "id";
	public static final String FN_WRID = "wrId";
	public static final String FN_LINEID = "lineId";
	public static final String FN_ITEMID = "itemId";
	public static final String FN_UNIT = "unit";
	public static final String FN_PIEUNIT = "pieUnit";
	public static final String FN_BASEUNIT = "baseUnit";
	public static final String FN_LOT = "lot";
	public static final String FN_QTY = "qty";
	public static final String FN_PIEQTY = "pieQty";
	public static final String FN_BASEQTY = "baseQty";
	public static final String FN_PRICE = "price";
	public static final String FN_AMT = "amt";
	public static final String FN_TAXRATE = "taxRate";
	public static final String FN_TAXPRICE = "taxPrice";
	public static final String FN_TAXAMT = "taxAmt";
	public static final String FN_PRODUCTDATE = "productDate";
	public static final String FN_EXPDATE = "expDate";
	public static final String FN_OFFSET = "offset";
	public static final String FN_USEORGUNITNO = "useOrgUnitNo";
	public static final String FN_COSTORGUNITNO = "costOrgUnitNo";
	public static final String FN_ORGUNITNO = "orgUnitNo";
	public static final String FN_WAREHOUSEID = "wareHouseId";
	public static final String FN_FINORGUNITNO = "finOrgUnitNo";
	public static final String FN_BALANCEVENDORID = "balanceVendorId";
	public static final String FN_STORAGEORGUNITNO = "storageOrgUnitNo";
	public static final String FN_REQUIREORGUNITNO = "requireOrgUnitNo";
	public static final String FN_PURORGUNITNO = "purOrgUnitNo";
	public static final String FN_BUYERID = "buyerId";
	public static final String FN_PURGROUPID = "purGroupId";
	public static final String FN_SOURCEBILLDTLID = "sourceBillDtlId";
	public static final String FN_PVNO = "pvNo";
	public static final String FN_PRICEBILLID = "priceBillId";
	public static final String FN_REFPRICESTATUS = "refPriceStatus";
	public static final String FN_STOCKID = "stockId";
	public static final String FN_INVDATE = "invDate";
	public static final String FN_ORGSOURCEID = "orgSourceId";
	public static final String FN_ORGSOURCEVENDORID = "orgSourceVendorId";
	public static final String FN_REQRETURNBASEQTY = "reqReturnBaseQty";
	public static final String FN_BUILDAP = "buildAP";
	public static final String FN_REMARKS = "remarks";
	public static final String FN_COSTUSETYPEID = "costUseTypeId";
	
	private long id;
	private long wrId;
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
	private Date productDate;
	private Date expDate;
	private boolean offset;
	private String useOrgUnitNo;
	private String costOrgUnitNo;
	private String orgUnitNo;
	private long wareHouseId;
	private String finOrgUnitNo;
	private long balanceVendorId;
	private String storageOrgUnitNo;
	private String requireOrgUnitNo;
	private String purOrgUnitNo;
	private long buyerId;
	private long purGroupId;
	private long sourceBillDtlId;
	private String pvNo;
	private long priceBillId;
	private String refPriceStatus;
	private long stockId;
	private Date invDate;
	private long orgSourceId;
	private long orgSourceVendorId;
	private BigDecimal reqReturnBaseQty;
	private boolean buildAP;
	private String remarks;
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
	public long getWrId() {
		return wrId;
	}

	public void setWrId(long val) {
		this.wrId = val;
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
	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date val) {
		this.productDate = val;
	}
	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date val) {
		this.expDate = val;
	}
	
	public boolean isOffset() {
		return offset;
	}

	public void setOffset(boolean offset) {
		this.offset = offset;
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
	public String getFinOrgUnitNo() {
		return finOrgUnitNo;
	}

	public void setFinOrgUnitNo(String val) {
		this.finOrgUnitNo = val;
	}
	public long getBalanceVendorId() {
		return balanceVendorId;
	}

	public void setBalanceVendorId(long val) {
		this.balanceVendorId = val;
	}
	public String getStorageOrgUnitNo() {
		return storageOrgUnitNo;
	}

	public void setStorageOrgUnitNo(String val) {
		this.storageOrgUnitNo = val;
	}
	public String getRequireOrgUnitNo() {
		return requireOrgUnitNo;
	}

	public void setRequireOrgUnitNo(String val) {
		this.requireOrgUnitNo = val;
	}
	public String getPurOrgUnitNo() {
		return purOrgUnitNo;
	}

	public void setPurOrgUnitNo(String val) {
		this.purOrgUnitNo = val;
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
	public long getSourceBillDtlId() {
		return sourceBillDtlId;
	}

	public void setSourceBillDtlId(long val) {
		this.sourceBillDtlId = val;
	}
	public long getPriceBillId() {
		return priceBillId;
	}

	public void setPriceBillId(long val) {
		this.priceBillId = val;
	}
	public String getRefPriceStatus() {
		return refPriceStatus;
	}

	public void setRefPriceStatus(String val) {
		this.refPriceStatus = val;
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
	public BigDecimal getReqReturnBaseQty() {
		return reqReturnBaseQty;
	}

	public void setReqReturnBaseQty(BigDecimal val) {
		this.reqReturnBaseQty = val;
	}
	
	public boolean isBuildAP() {
		return buildAP;
	}

	public void setBuildAP(boolean buildAP) {
		this.buildAP = buildAP;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String val) {
		this.remarks = val;
	}
	
	public String getPvNo() {
		return pvNo;
	}

	public void setPvNo(String pvNo) {
		this.pvNo = pvNo;
	}

	public ScmInvPurInWarehsBillEntry(boolean defaultValue) {
		if (defaultValue) {
			this.pieQty=BigDecimal.ZERO;
			this.baseQty=BigDecimal.ZERO;
			this.qty=BigDecimal.ZERO;
			this.price=BigDecimal.ZERO;
			this.amt=BigDecimal.ZERO;
			this.taxRate=BigDecimal.ZERO;
			this.taxPrice=BigDecimal.ZERO;
			this.taxAmt=BigDecimal.ZERO;
			this.offset=false;
			this.wareHouseId=0;
			this.balanceVendorId=0;
			this.buyerId=0;
			this.purGroupId=0;
			this.stockId=0;
			this.refPriceStatus="3";
			this.priceBillId=0;
			this.sourceBillDtlId=0;
			this.orgSourceId=0;
			this.orgSourceVendorId=0;
			this.reqReturnBaseQty=BigDecimal.ZERO;
		}
	}
  	public ScmInvPurInWarehsBillEntry() {
	}

	public String[] getFieldNames(){
		return new String[]{
			FN_ID,
			FN_WRID,
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
			FN_PRODUCTDATE,
			FN_EXPDATE,
			FN_OFFSET,
			FN_USEORGUNITNO,
			FN_COSTORGUNITNO,
			FN_ORGUNITNO,
			FN_WAREHOUSEID,
			FN_FINORGUNITNO,
			FN_BALANCEVENDORID,
			FN_STORAGEORGUNITNO,
			FN_REQUIREORGUNITNO,
			FN_PURORGUNITNO,
			FN_BUYERID,
			FN_PURGROUPID,
			FN_SOURCEBILLDTLID,
			FN_PVNO,
			FN_PRICEBILLID,
			FN_REFPRICESTATUS,
			FN_STOCKID,
			FN_INVDATE,
			FN_ORGSOURCEID,
			FN_ORGSOURCEVENDORID,
			FN_REQRETURNBASEQTY,
			FN_BUILDAP,
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
		map.put(FN_USEORGUNITNO, 32);
		map.put(FN_COSTORGUNITNO, 32);
		map.put(FN_ORGUNITNO, 32);
		map.put(FN_FINORGUNITNO, 32);
		map.put(FN_STORAGEORGUNITNO, 32);
		map.put(FN_REQUIREORGUNITNO, 32);
		map.put(FN_PURORGUNITNO, 32);
		map.put(FN_REFPRICESTATUS, 32);
		map.put(FN_REMARKS, 255);
		map.put(FN_PVNO, 32);
		return map; 
	}
	
	public String getPkKey() {
		return FN_ID;
	}
 
	public long getPK() {
		return id;
	}
}

