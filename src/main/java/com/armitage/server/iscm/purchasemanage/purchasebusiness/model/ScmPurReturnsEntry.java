
package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.validator.Var;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 

@XmlRootElement(name = "scmPurReturnsEntry")  
public class ScmPurReturnsEntry extends BaseModel {
	public static final String FN_ID = "id";
	public static final String FN_RTID = "rtId";
	public static final String FN_LINEID = "lineId";
	public static final String FN_ITEMID = "itemId";
	public static final String FN_PURUNIT = "purUnit";
	public static final String FN_UNIT = "unit";
	public static final String FN_PIEUNIT = "pieUnit";
	public static final String FN_BASEUNIT = "baseUnit";
	public static final String FN_RECEIVEQTY = "receiveQty";
	public static final String FN_RETURNQTY = "returnQty";
	public static final String FN_QTY = "qty";
	public static final String FN_INVQTY = "invQty";
	public static final String FN_BASEQTY = "baseQty";
	public static final String FN_PIEQTY = "pieQty";
	public static final String FN_PRICE = "price";
	public static final String FN_AMT = "amt";
	public static final String FN_TAXRATE = "taxRate";
	public static final String FN_TAXPRICE = "taxPrice";
	public static final String FN_TAXAMT = "taxAmt";
	public static final String FN_OUTQTY = "outQty";
	public static final String FN_PRODUCTDATE = "productDate";
	public static final String FN_EXPDATE = "expDate";
	public static final String FN_USEORGUNITNO = "useOrgUnitNo";
	public static final String FN_INVORGUNITNO = "invOrgUnitNo";
	public static final String FN_WAREHOUSEID = "wareHouseId";
	public static final String FN_FINORGUNITNO = "finOrgUnitNo";
	public static final String FN_BALANCESUPPLIERID = "balanceSupplierId";
	public static final String FN_STORAGEORGUNITNO = "storageOrgUnitNo";
	public static final String FN_PURORGUNITNO = "purOrgUnitNo";
	public static final String FN_SOURCEDTLID = "sourceDtlId";
	public static final String FN_ROWSTATUS = "rowStatus";
	public static final String FN_CHECKER = "checker";
	public static final String FN_CHECKDATE = "checkDate";
	public static final String FN_REASONCODE = "reasonCode";
	public static final String FN_REMARKS = "remarks";
	
	private long id;
	private long rtId;
	private int lineId;
	private long itemId;
	private long purUnit;
	private long unit;
	private long pieUnit;
	private long baseUnit;
	private BigDecimal receiveQty;
	private BigDecimal returnQty;
	private BigDecimal qty;
	private BigDecimal invQty;
	private BigDecimal baseQty;
	private BigDecimal pieQty;
	private BigDecimal price;
	private BigDecimal amt;
	private BigDecimal taxRate;
	private BigDecimal taxPrice;
	private BigDecimal taxAmt;
	private BigDecimal outQty;
	private Date productDate;
	private Date expDate;
	private String useOrgUnitNo;
	private String invOrgUnitNo;
	private long wareHouseId;
	private String finOrgUnitNo;
	private long balanceSupplierId;
	private String storageOrgUnitNo;
	private String purOrgUnitNo;
	private long sourceDtlId;
	private String rowStatus;
	private String checker;
	private Date checkDate;
	private String reasonCode;
	private String remarks;

	public long getId() {
		return id;
	}
	public void setId(long val) {
		this.id = val;
	}
	
	public long getRtId() {
		return rtId;
	}
	public void setRtId(long val) {
		this.rtId = val;
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
	public BigDecimal getReceiveQty() {
		return receiveQty;
	}

	public void setReceiveQty(BigDecimal val) {
		this.receiveQty = val;
	}
	public BigDecimal getReturnQty() {
		return returnQty;
	}

	public void setReturnQty(BigDecimal val) {
		this.returnQty = val;
	}
	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal val) {
		this.qty = val;
	}
	public BigDecimal getInvQty() {
		return invQty;
	}

	public void setInvQty(BigDecimal val) {
		this.invQty = val;
	}
	public BigDecimal getBaseQty() {
		return baseQty;
	}

	public void setBaseQty(BigDecimal val) {
		this.baseQty = val;
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
	public BigDecimal getOutQty() {
		return outQty;
	}

	public void setOutQty(BigDecimal val) {
		this.outQty = val;
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
	public String getPurOrgUnitNo() {
		return purOrgUnitNo;
	}

	public void setPurOrgUnitNo(String val) {
		this.purOrgUnitNo = val;
	}
	public long getSourceDtlId() {
		return sourceDtlId;
	}

	public void setSourceDtlId(long val) {
		this.sourceDtlId = val;
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
	public String getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String val) {
		this.remarks = val;
	}
	
	public ScmPurReturnsEntry(boolean defaultValue) {
		if (defaultValue) {
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
			this.outQty=BigDecimal.ZERO;
			this.returnQty=BigDecimal.ZERO;
			this.balanceSupplierId=0;
			this.sourceDtlId=0;
			this.reasonCode="3";//默认不合格
		}
	}
  	public ScmPurReturnsEntry() {
	}

	public String[] getFieldNames(){
		return new String[]{
			FN_ID,
			FN_RTID,
			FN_LINEID,
			FN_ITEMID,
			FN_PURUNIT,
			FN_UNIT,
			FN_PIEUNIT,
			FN_BASEUNIT,
			FN_RECEIVEQTY,
			FN_RETURNQTY,
			FN_QTY,
			FN_INVQTY,
			FN_BASEQTY,
			FN_PIEQTY,
			FN_PRICE,
			FN_AMT,
			FN_TAXRATE,
			FN_TAXPRICE,
			FN_TAXAMT,
			FN_OUTQTY,
			FN_PRODUCTDATE,
			FN_EXPDATE,
			FN_USEORGUNITNO,
			FN_INVORGUNITNO,
			FN_WAREHOUSEID,
			FN_FINORGUNITNO,
			FN_BALANCESUPPLIERID,
			FN_STORAGEORGUNITNO,
			FN_PURORGUNITNO,
			FN_SOURCEDTLID,
			FN_ROWSTATUS,
			FN_CHECKER,
			FN_CHECKDATE,
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
		map.put(FN_USEORGUNITNO, 32);
		map.put(FN_INVORGUNITNO, 32);
		map.put(FN_FINORGUNITNO, 32);
		map.put(FN_STORAGEORGUNITNO, 32);
		map.put(FN_PURORGUNITNO, 32);
		map.put(FN_ROWSTATUS, 1);
		map.put(FN_CHECKER, 48);
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
