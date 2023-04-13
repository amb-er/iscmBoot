package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 

@XmlRootElement(name = "scmPurReceiveEntry")  
public class ScmPurReceiveEntry extends BaseModel {
	public static final String FN_ID = "id";
	public static final String FN_PVID = "pvId";
	public static final String FN_LINEID = "lineId";
	public static final String FN_ITEMID = "itemId";
	public static final String FN_PURUNIT = "purUnit";
	public static final String FN_UNIT = "unit";
	public static final String FN_PIEUNIT = "pieUnit";
	public static final String FN_BASEUNIT = "baseUnit";
	public static final String FN_ORDERQTY = "orderQty";
	public static final String FN_RECEIVEQTY = "receiveQty";
	public static final String FN_DELIVERYQTY = "deliveryQty";
	public static final String FN_QTY = "qty";
	public static final String FN_UNQUALIFIEDQTY = "unqualifiedQty";
	public static final String FN_CONCESSIVERECRATE = "concessiveRecRate";
	public static final String FN_CONCESSIVERECQTY = "concessiveRecQty";
	public static final String FN_INVQTY = "invQty";
	public static final String FN_PIEQTY = "pieQty";
	public static final String FN_BASEQTY = "baseQty";
	public static final String FN_PRICE = "price";
	public static final String FN_AMT = "amt";
	public static final String FN_CHECKAMT = "checkAmt";
	public static final String FN_TAXRATE = "taxRate";
	public static final String FN_TAXPRICE = "taxPrice";
	public static final String FN_TAXAMT = "taxAmt";
	public static final String FN_CHECKTAXAMT = "checkTaxAmt";
	public static final String FN_ADDINQTY = "addInQty";
	public static final String FN_RETURNQTY = "returnQty";
	public static final String FN_PRODUCTDATE = "productDate";
	public static final String FN_EXPDATE = "expDate";
	public static final String FN_USEORGUNITNO = "useOrgUnitNo";
	public static final String FN_INVORGUNITNO = "invOrgUnitNo";
	public static final String FN_WAREHOUSEID = "wareHouseId";
	public static final String FN_FINORGUNITNO = "finOrgUnitNo";
	public static final String FN_BALANCESUPPLIERID = "balanceSupplierId";
	public static final String FN_STORAGEORGUNITNO = "storageOrgUnitNo";
	public static final String FN_REQORGUNITNO = "reqOrgUnitNo";
	public static final String FN_PURORGUNITNO = "purOrgUnitNo";
	public static final String FN_BUYERID = "buyerId";
	public static final String FN_PURGROUPID = "purGroupId";
	public static final String FN_PODTLID = "poDtlId";
	public static final String FN_PONO = "poNo";
	public static final String FN_PRICEBILLID = "priceBillId";
	public static final String FN_REFPRICESTATUS = "refPriceStatus";
	public static final String FN_ROWSTATUS = "rowStatus";
	public static final String FN_COSTUSETYPEID = "costUseTypeId";
	public static final String FN_CHECKER = "checker";
	public static final String FN_CHECKDATE = "checkDate";
	public static final String FN_REMARKS = "remarks";
	
	private long id;
	private long pvId;
	private int lineId;
	private long itemId;
	private long purUnit;
	private long unit;
	private long pieUnit;
	private long baseUnit;
	private BigDecimal orderQty;
	private BigDecimal receiveQty;
	private BigDecimal deliveryQty;
	private BigDecimal qty;
	private BigDecimal unqualifiedQty;
	private BigDecimal concessiveRecRate;
	private BigDecimal concessiveRecQty;
	private BigDecimal invQty;
	private BigDecimal pieQty;
	private BigDecimal baseQty;
	private BigDecimal price;
	private BigDecimal amt;
	private BigDecimal checkAmt;
	private BigDecimal taxRate;
	private BigDecimal taxPrice;
	private BigDecimal taxAmt;
	private BigDecimal checkTaxAmt;
	private BigDecimal addInQty;
	private BigDecimal returnQty;
	private Date productDate;
	private Date expDate;
	private String useOrgUnitNo;
	private String invOrgUnitNo;
	private long wareHouseId;
	private String finOrgUnitNo;
	private long balanceSupplierId;
	private String storageOrgUnitNo;
	private String reqOrgUnitNo;
	private String purOrgUnitNo;
	private long buyerId;
	private long purGroupId;
	private long poDtlId;
	private String poNo;
	private long priceBillId;
	private String refPriceStatus;
	private String rowStatus;
	private long costUseTypeId;
	private String checker;
	private Date checkDate;
	private String remarks;

	public long getId() {
		return id;
	}

	public void setId(long val) {
		this.id = val;
	}
	public long getPvId() {
		return pvId;
	}

	public void setPvId(long val) {
		this.pvId = val;
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
	public BigDecimal getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(BigDecimal val) {
		this.orderQty = val;
	}
	public BigDecimal getReceiveQty() {
		return receiveQty;
	}

	public void setReceiveQty(BigDecimal val) {
		this.receiveQty = val;
	}
	public BigDecimal getDeliveryQty() {
		return deliveryQty;
	}

	public void setDeliveryQty(BigDecimal deliveryQty) {
		this.deliveryQty = deliveryQty;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal val) {
		this.qty = val;
	}
	public BigDecimal getUnqualifiedQty() {
		return unqualifiedQty;
	}

	public void setUnqualifiedQty(BigDecimal unqualifiedQty) {
		this.unqualifiedQty = unqualifiedQty;
	}

	public BigDecimal getConcessiveRecRate() {
		return concessiveRecRate;
	}

	public void setConcessiveRecRate(BigDecimal concessiveRecRate) {
		this.concessiveRecRate = concessiveRecRate;
	}

	public BigDecimal getConcessiveRecQty() {
		return concessiveRecQty;
	}

	public void setConcessiveRecQty(BigDecimal concessiveRecQty) {
		this.concessiveRecQty = concessiveRecQty;
	}

	public BigDecimal getInvQty() {
		return invQty;
	}

	public void setInvQty(BigDecimal val) {
		this.invQty = val;
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
	public BigDecimal getAddInQty() {
		return addInQty;
	}

	public void setAddInQty(BigDecimal val) {
		this.addInQty = val;
	}
	public BigDecimal getReturnQty() {
		return returnQty;
	}

	public void setReturnQty(BigDecimal val) {
		this.returnQty = val;
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
	public String getUseOrgUnitNo() {
		return useOrgUnitNo;
	}

	public void setUseOrgUnitNo(String val) {
		this.useOrgUnitNo = val;
	}
	public String getInvOrgUnitNo() {
		return invOrgUnitNo;
	}

	public void setInvOrgUnitNo(String val) {
		this.invOrgUnitNo = val;
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
	public long getBalanceSupplierId() {
		return balanceSupplierId;
	}

	public void setBalanceSupplierId(long val) {
		this.balanceSupplierId = val;
	}
	public String getStorageOrgUnitNo() {
		return storageOrgUnitNo;
	}

	public void setStorageOrgUnitNo(String val) {
		this.storageOrgUnitNo = val;
	}
	public String getReqOrgUnitNo() {
		return reqOrgUnitNo;
	}

	public void setReqOrgUnitNo(String val) {
		this.reqOrgUnitNo = val;
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
	public long getPoDtlId() {
		return poDtlId;
	}

	public void setPoDtlId(long val) {
		this.poDtlId = val;
	}
	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
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
	public String getRowStatus() {
		return rowStatus;
	}

	public void setRowStatus(String val) {
		this.rowStatus = val;
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
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String val) {
		this.remarks = val;
	}
	
	public BigDecimal getCheckAmt() {
		return checkAmt;
	}

	public void setCheckAmt(BigDecimal checkAmt) {
		this.checkAmt = checkAmt;
	}

	public BigDecimal getCheckTaxAmt() {
		return checkTaxAmt;
	}

	public void setCheckTaxAmt(BigDecimal checkTaxAmt) {
		this.checkTaxAmt = checkTaxAmt;
	}

	public long getCostUseTypeId() {
		return costUseTypeId;
	}

	public void setCostUseTypeId(long costUseTypeId) {
		this.costUseTypeId = costUseTypeId;
	}

	public ScmPurReceiveEntry(boolean defaultValue) {
		if (defaultValue) {
			this.orderQty=BigDecimal.ZERO;
			this.receiveQty=BigDecimal.ZERO;
			this.invQty=BigDecimal.ZERO;
			this.pieQty=BigDecimal.ZERO;
			this.baseQty=BigDecimal.ZERO;
			this.qty=BigDecimal.ZERO;
			this.price=BigDecimal.ZERO;
			this.amt=BigDecimal.ZERO;
			this.taxRate=BigDecimal.ZERO;
			this.taxPrice=BigDecimal.ZERO;
			this.taxAmt=BigDecimal.ZERO;
			this.addInQty=BigDecimal.ZERO;
			this.returnQty=BigDecimal.ZERO;
			this.balanceSupplierId=0;
			this.priceBillId=0;
			this.refPriceStatus="0";
			this.deliveryQty=BigDecimal.ZERO;
			this.unqualifiedQty=BigDecimal.ZERO;
			this.concessiveRecRate=BigDecimal.ZERO;
			this.concessiveRecQty=BigDecimal.ZERO;
			this.checkAmt=BigDecimal.ZERO;
			this.checkTaxAmt=BigDecimal.ZERO;
			this.costUseTypeId=0;
		}
	}
  	public ScmPurReceiveEntry() {
	}

	public String[] getFieldNames(){
		return new String[]{
			FN_ID,
			FN_PVID,
			FN_LINEID,
			FN_ITEMID,
			FN_PURUNIT,
			FN_UNIT,
			FN_PIEUNIT,
			FN_BASEUNIT,
			FN_ORDERQTY,
			FN_RECEIVEQTY,
			FN_QTY,
			FN_INVQTY,
			FN_PIEQTY,
			FN_BASEQTY,
			FN_PRICE,
			FN_AMT,
			FN_TAXRATE,
			FN_TAXPRICE,
			FN_TAXAMT,
			FN_ADDINQTY,
			FN_RETURNQTY,
			FN_PRODUCTDATE,
			FN_EXPDATE,
			FN_USEORGUNITNO,
			FN_INVORGUNITNO,
			FN_WAREHOUSEID,
			FN_FINORGUNITNO,
			FN_BALANCESUPPLIERID,
			FN_STORAGEORGUNITNO,
			FN_REQORGUNITNO,
			FN_PURORGUNITNO,
			FN_BUYERID,
			FN_PURGROUPID,
			FN_PODTLID,
			FN_PRICEBILLID,
			FN_REFPRICESTATUS,
			FN_ROWSTATUS,
			FN_CHECKER,
			FN_CHECKDATE,
			FN_REMARKS,
			FN_COSTUSETYPEID,
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
		map.put(FN_USEORGUNITNO, 32);
		map.put(FN_INVORGUNITNO, 32);
		map.put(FN_FINORGUNITNO, 32);
		map.put(FN_STORAGEORGUNITNO, 32);
		map.put(FN_REQORGUNITNO, 32);
		map.put(FN_PURORGUNITNO, 32);
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