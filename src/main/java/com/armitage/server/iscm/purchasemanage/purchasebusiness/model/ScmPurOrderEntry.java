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

@XmlRootElement(name = "scmPurOrderEntry")  
public class ScmPurOrderEntry extends BaseModel {
	public static final String FN_ID = "id";
	public static final String FN_POID = "poId";
	public static final String FN_LINEID = "lineId";
	public static final String FN_ITEMID = "itemId";
	public static final String FN_PURUNIT = "purUnit";
	public static final String FN_REQDATE = "reqDate";
	public static final String FN_DELIVERYDATE = "deliveryDate";
	public static final String FN_REQQTY = "reqQty";
	public static final String FN_BASEUNIT = "baseUnit";
	public static final String FN_BASEQTY = "baseQty";
	public static final String FN_QTY = "qty";
	public static final String FN_PRICE = "price";
	public static final String FN_AMT = "amt";
	public static final String FN_TAXRATE = "taxRate";
	public static final String FN_TAXPRICE = "taxPrice";
	public static final String FN_TAXAMT = "taxAmt";
	public static final String FN_PRICENEW = "priceNew";
	public static final String FN_RECEIVEQTY = "receiveQty";
	public static final String FN_MOVEDQTY = "movedQty";
	public static final String FN_RETURNQTY = "returnQty";
	public static final String FN_INVQTY = "invQty";
	public static final String FN_REQORGUNITNO = "reqOrgUnitNo";
	public static final String FN_REQSTORAGEORGUNITNO = "reqStorageOrgUnitNo";
	public static final String FN_REQFINORGUNITNO = "reqFinOrgUnitNo";
	public static final String FN_RECEIVEORGUNITNO = "receiveOrgUnitNo";
	public static final String FN_RECEIVEFINORGUNITNO = "receiveFinOrgUnitNo";
	public static final String FN_INSTEAD = "instead";
	public static final String FN_RECEIVEWAREHOUSEID = "receiveWareHouseId";
	public static final String FN_MSTORAGEORGUNITNO = "mstorageOrgUnitNo";
	public static final String FN_BALANCESUPPLIERID = "balanceSupplierId";
	public static final String FN_STORAGEORGUNITNO = "storageOrgUnitNo";
	public static final String FN_BUYERID = "buyerId";
	public static final String FN_PURGROUPID = "purGroupId";
	public static final String FN_PRDTLID = "prDtlId";
	public static final String FN_PRNO = "prNo";
	public static final String FN_PRICEBILLID = "priceBillId";
	public static final String FN_REFPRICESTATUS = "refPriceStatus";
	public static final String FN_APPRPRICEBYPO = "apprPriceByPo";
	public static final String FN_ROWSTATUS = "rowStatus";
	public static final String FN_CHECKER = "checker";
	public static final String FN_CHECKDATE = "checkDate";
	public static final String FN_REMARKS = "remarks";
	public static final String FN_SUPPLYCYCLE ="supplyCycle";
	public static final String FN_ATTACHMENTID="attachmentId";
	
	public static final String FN_PIEQTY = "pieQty";
    public static final String FN_PIEUNIT = "pieUnit";
	
	public static final String FN_RECENTPRICE = "recentPrice";
    public static final String FN_STOCKQTY = "stockQty";
	
	private long id;
	private long poId;
	private int lineId;
	private long itemId;
	private long purUnit;
	private Date reqDate;
	private Date deliveryDate;
	private BigDecimal reqQty;
	private long baseUnit;
	private BigDecimal baseQty;
	private BigDecimal qty;
	private BigDecimal price;
	private BigDecimal amt;
	private BigDecimal taxRate;
	private BigDecimal taxPrice;
	private BigDecimal taxAmt;
	private BigDecimal priceNew;
	private BigDecimal receiveQty;
	private BigDecimal movedQty;
	private BigDecimal returnQty;
	private BigDecimal invQty;
	private String reqOrgUnitNo;
	private String reqStorageOrgUnitNo;
	private String reqFinOrgUnitNo;
	private String receiveOrgUnitNo;
	private String receiveFinOrgUnitNo;
	private boolean instead;
	private long receiveWareHouseId;
	private String mstorageOrgUnitNo;
	private long balanceSupplierId;
	private String storageOrgUnitNo;
	private long buyerId;
	private long purGroupId;
	private long attachmentId;
	private long prDtlId;
	private String prNo;
	private long priceBillId;
	private String refPriceStatus;
	private boolean apprPriceByPo;
	private int supplyCycle;
	private String rowStatus;
	private String checker;
	private Date checkDate;
	private String remarks;
	
	//辅助数量，单位
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

	

	public long getId() {
		return id;
	}
	
	public void setId(long val) {
		this.id = val;
	}
	public long getPoId() {
		return poId;
	}


	public void setPoId(long val) {
		this.poId = val;
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
	public Date getReqDate() {
		return reqDate;
	}

	public void setReqDate(Date val) {
		this.reqDate = val;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date val) {
		this.deliveryDate = val;
	}
	public BigDecimal getReqQty() {
		return reqQty;
	}

	public void setReqQty(BigDecimal val) {
		this.reqQty = val;
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
	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal val) {
		this.qty = val;
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
	public BigDecimal getPriceNew() {
		return priceNew;
	}

	public void setPriceNew(BigDecimal val) {
		this.priceNew = val;
	}
	public BigDecimal getReceiveQty() {
		return receiveQty;
	}

	public void setReceiveQty(BigDecimal val) {
		this.receiveQty = val;
	}
	public BigDecimal getMovedQty() {
		return movedQty;
	}

	public void setMovedQty(BigDecimal val) {
		this.movedQty = val;
	}
	public BigDecimal getReturnQty() {
		return returnQty;
	}

	public void setReturnQty(BigDecimal val) {
		this.returnQty = val;
	}
	public BigDecimal getInvQty() {
		return invQty;
	}

	public void setInvQty(BigDecimal invQty) {
		this.invQty = invQty;
	}

	public String getReqOrgUnitNo() {
		return reqOrgUnitNo;
	}

	public void setReqOrgUnitNo(String val) {
		this.reqOrgUnitNo = val;
	}
	public String getReqStorageOrgUnitNo() {
		return reqStorageOrgUnitNo;
	}

	public void setReqStorageOrgUnitNo(String val) {
		this.reqStorageOrgUnitNo = val;
	}
	public String getReqFinOrgUnitNo() {
		return reqFinOrgUnitNo;
	}

	public void setReqFinOrgUnitNo(String val) {
		this.reqFinOrgUnitNo = val;
	}
	public String getReceiveOrgUnitNo() {
		return receiveOrgUnitNo;
	}

	public void setReceiveOrgUnitNo(String val) {
		this.receiveOrgUnitNo = val;
	}
	public String getReceiveFinOrgUnitNo() {
		return receiveFinOrgUnitNo;
	}

	public void setReceiveFinOrgUnitNo(String val) {
		this.receiveFinOrgUnitNo = val;
	}
	
	public boolean isInstead() {
		return instead;
	}

	public void setInstead(boolean instead) {
		this.instead = instead;
	}

	public long getReceiveWareHouseId() {
		return receiveWareHouseId;
	}

	public void setReceiveWareHouseId(long val) {
		this.receiveWareHouseId = val;
	}
	public String getMstorageOrgUnitNo() {
		return mstorageOrgUnitNo;
	}

	public void setMstorageOrgUnitNo(String val) {
		this.mstorageOrgUnitNo = val;
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
	public long getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(long attachmentId) {
		this.attachmentId = attachmentId;
	}

	public long getPrDtlId() {
		return prDtlId;
	}

	public void setPrDtlId(long val) {
		this.prDtlId = val;
	}
	public String getPrNo() {
		return prNo;
	}

	public void setPrNo(String prNo) {
		this.prNo = prNo;
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
	
	public boolean isApprPriceByPo() {
		return apprPriceByPo;
	}

	public void setApprPriceByPo(boolean apprPriceByPo) {
		this.apprPriceByPo = apprPriceByPo;
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
	public int getSupplyCycle() {
		return supplyCycle;
	}
	public void setSupplyCycle(int supplyCycle) {
		this.supplyCycle = supplyCycle;
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
	public ScmPurOrderEntry(boolean defaultValue) {
		if (defaultValue) {
			this.pieQty=BigDecimal.ZERO;
			this.baseQty=BigDecimal.ZERO;
			this.reqQty=BigDecimal.ZERO;
			this.baseQty=BigDecimal.ZERO;
			this.qty=BigDecimal.ZERO;
			this.price=BigDecimal.ZERO;
			this.amt=BigDecimal.ZERO;
			this.taxRate=BigDecimal.ZERO;
			this.taxPrice=BigDecimal.ZERO;
			this.taxAmt=BigDecimal.ZERO;
			this.priceNew=BigDecimal.ZERO;
			this.receiveQty=BigDecimal.ZERO;
			this.movedQty=BigDecimal.ZERO;
			this.returnQty=BigDecimal.ZERO;
			this.invQty=BigDecimal.ZERO;
			this.instead=false;
			this.refPriceStatus="0";
			this.apprPriceByPo=true;
			this.supplyCycle=0;
			this.recentPrice=BigDecimal.ZERO;
			this.stockQty=BigDecimal.ZERO;
		}
	}
  	public ScmPurOrderEntry() {
	}

	public String[] getFieldNames(){
		return new String[]{
			FN_ID,
			FN_POID,
			FN_LINEID,
			FN_ITEMID,
			FN_PURUNIT,
			FN_REQDATE,
			FN_DELIVERYDATE,
			FN_REQQTY,
			FN_BASEUNIT,
			FN_BASEQTY,
			FN_QTY,
			FN_PRICE,
			FN_AMT,
			FN_TAXRATE,
			FN_TAXPRICE,
			FN_TAXAMT,
			FN_PRICENEW,
			FN_RECEIVEQTY,
			FN_MOVEDQTY,
			FN_RETURNQTY,
			FN_REQORGUNITNO,
			FN_REQSTORAGEORGUNITNO,
			FN_REQFINORGUNITNO,
			FN_RECEIVEORGUNITNO,
			FN_RECEIVEFINORGUNITNO,
			FN_INSTEAD,
			FN_RECEIVEWAREHOUSEID,
			FN_MSTORAGEORGUNITNO,
			FN_BALANCESUPPLIERID,
			FN_STORAGEORGUNITNO,
			FN_BUYERID,
			FN_PURGROUPID,
			FN_PRDTLID,
			FN_PRNO,
			FN_PRICEBILLID,
			FN_REFPRICESTATUS,
			FN_APPRPRICEBYPO,
			FN_ROWSTATUS,
			FN_CHECKER,
			FN_CHECKDATE,
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
		map.put(FN_REQORGUNITNO, 32);
		map.put(FN_REQSTORAGEORGUNITNO, 32);
		map.put(FN_REQFINORGUNITNO, 32);
		map.put(FN_RECEIVEORGUNITNO, 32);
		map.put(FN_RECEIVEFINORGUNITNO, 32);
		map.put(FN_MSTORAGEORGUNITNO, 32);
		map.put(FN_STORAGEORGUNITNO, 32);
		map.put(FN_REFPRICESTATUS, 16);
		map.put(FN_ROWSTATUS, 16);
		map.put(FN_CHECKER, 16);
		map.put(FN_PRNO, 32);
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

