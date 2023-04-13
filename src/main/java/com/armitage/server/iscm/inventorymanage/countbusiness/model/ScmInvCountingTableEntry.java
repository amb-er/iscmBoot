package com.armitage.server.iscm.inventorymanage.countbusiness.model;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 

@XmlRootElement(name = "scmInvCountingTableEntry")  
public class ScmInvCountingTableEntry extends BaseModel {
	public static final String FN_ID = "id";
	public static final String FN_TABLEID = "tableId";
	public static final String FN_LINEID = "lineId";
	public static final String FN_ITEMID = "itemId";
	public static final String FN_UNIT = "unit";
	public static final String FN_PIEUNIT = "pieUnit";
	public static final String FN_LOT = "lot";
	public static final String FN_ORGUNITNO = "orgUnitNo";
	public static final String FN_WAREHOUSEID = "wareHouseId";
	public static final String FN_ORIGACCQTY = "origAccQty";
	public static final String FN_ACCOUNTQTY = "accountQty";
	public static final String FN_ACCOUNTAMT = "accountAmt";
	public static final String FN_PRICE = "price";
	public static final String FN_TAXRATE = "taxRate";
	public static final String FN_TAXPRICE = "taxPrice";
	public static final String FN_QTY = "qty";
	public static final String FN_AMT = "amt";
	public static final String FN_DIFFERQTY = "differQty";
	public static final String FN_DIFFERAMT = "differAmt";
	public static final String FN_TAXAMT = "taxAmt";
	public static final String FN_ACCOUNTTAXAMT = "accountTaxAmt";
	public static final String FN_DIFFERTAXAMT = "differTaxAmt";
	public static final String FN_ACCOUNTPIEQTY = "accountPieQty";
	public static final String FN_PIEQTY = "pieQty";
	public static final String FN_DIFFERPIEQTY = "differPieQty";
	public static final String FN_EXPDATE = "expDate";
	public static final String FN_VENDORID = "vendorId";
	public static final String FN_USRADD = "usrAdd";
	public static final String FN_STOCKID = "stockId";
	public static final String FN_COUNTED = "counted";
	
	private long id;
	private long tableId;
	private int lineId;
	private long itemId;
	private long unit;
	private long pieUnit;
	private String lot;
	private String orgUnitNo;
	private long wareHouseId;
	private BigDecimal origAccQty;
	private BigDecimal accountQty;
	private BigDecimal accountAmt;
	private BigDecimal price;
	private BigDecimal taxRate;
	private BigDecimal taxPrice;
	private BigDecimal qty;
	private BigDecimal amt;
	private BigDecimal differQty;
	private BigDecimal differAmt;
	private BigDecimal taxAmt;
	private BigDecimal accountTaxAmt;
	private BigDecimal differTaxAmt;
	private BigDecimal accountPieQty;
	private BigDecimal pieQty;
	private BigDecimal differPieQty;
	private Date expDate;
	private long vendorId;
	private boolean usrAdd;
	private long stockId;
	private boolean counted;

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public long getId() {
		return id;
	}

	public void setId(long val) {
		this.id = val;
	}
	public long getTableId() {
		return tableId;
	}

	public void setTableId(long val) {
		this.tableId = val;
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
	public String getLot() {
		return lot;
	}

	public void setLot(String val) {
		this.lot = val;
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
	public BigDecimal getOrigAccQty() {
		return origAccQty;
	}

	public void setOrigAccQty(BigDecimal origAccQty) {
		this.origAccQty = origAccQty;
	}

	public BigDecimal getAccountQty() {
		return accountQty;
	}

	public void setAccountQty(BigDecimal val) {
		this.accountQty = val;
	}
	public BigDecimal getAccountAmt() {
		return accountAmt;
	}

	public void setAccountAmt(BigDecimal val) {
		this.accountAmt = val;
	}
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal val) {
		this.price = val;
	}
	public BigDecimal getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(BigDecimal val) {
		this.taxPrice = val;
	}
	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal val) {
		this.qty = val;
	}
	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal val) {
		this.amt = val;
	}
	public BigDecimal getDifferQty() {
		return differQty;
	}

	public void setDifferQty(BigDecimal val) {
		this.differQty = val;
	}
	public BigDecimal getDifferAmt() {
		return differAmt;
	}

	public void setDifferAmt(BigDecimal val) {
		this.differAmt = val;
	}
	public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(BigDecimal val) {
		this.taxAmt = val;
	}
	public BigDecimal getAccountTaxAmt() {
		return accountTaxAmt;
	}

	public void setAccountTaxAmt(BigDecimal val) {
		this.accountTaxAmt = val;
	}
	public BigDecimal getDifferTaxAmt() {
		return differTaxAmt;
	}

	public void setDifferTaxAmt(BigDecimal val) {
		this.differTaxAmt = val;
	}
	public BigDecimal getAccountPieQty() {
		return accountPieQty;
	}

	public void setAccountPieQty(BigDecimal val) {
		this.accountPieQty = val;
	}
	public BigDecimal getPieQty() {
		return pieQty;
	}

	public void setPieQty(BigDecimal val) {
		this.pieQty = val;
	}
	public BigDecimal getDifferPieQty() {
		return differPieQty;
	}

	public void setDifferPieQty(BigDecimal val) {
		this.differPieQty = val;
	}
	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public long getVendorId() {
		return vendorId;
	}

	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}

	public boolean isUsrAdd() {
		return usrAdd;
	}

	public void setUsrAdd(boolean usrAdd) {
		this.usrAdd = usrAdd;
	}

	public long getStockId() {
		return stockId;
	}

	public void setStockId(long val) {
		this.stockId = val;
	}
	
	public boolean isCounted() {
		return counted;
	}

	public void setCounted(boolean counted) {
		this.counted = counted;
	}

	public ScmInvCountingTableEntry(boolean defaultValue) {
		if (defaultValue) {
			this.origAccQty=BigDecimal.ZERO;
			this.accountQty=BigDecimal.ZERO;
			this.accountAmt=BigDecimal.ZERO;
			this.price=BigDecimal.ZERO;
			this.taxPrice=BigDecimal.ZERO;
			this.qty=BigDecimal.ZERO;
			this.amt=BigDecimal.ZERO;
			this.differQty=BigDecimal.ZERO;
			this.differAmt=BigDecimal.ZERO;
			this.taxAmt=BigDecimal.ZERO;
			this.accountTaxAmt=BigDecimal.ZERO;
			this.differTaxAmt=BigDecimal.ZERO;
			this.accountPieQty=BigDecimal.ZERO;
			this.pieQty=BigDecimal.ZERO;
			this.differPieQty=BigDecimal.ZERO;
			this.taxRate=BigDecimal.ZERO;
			this.stockId=0;
			this.counted=false;
		}
	}
  	public ScmInvCountingTableEntry() {
	}

	public String[] getFieldNames(){
		return new String[]{
			FN_ID,
			FN_TABLEID,
			FN_LINEID,
			FN_ITEMID,
			FN_UNIT,
			FN_PIEUNIT,
			FN_LOT,
			FN_ORGUNITNO,
			FN_WAREHOUSEID,
			FN_ORIGACCQTY,
			FN_ACCOUNTQTY,
			FN_ACCOUNTAMT,
			FN_PRICE,
			FN_TAXRATE,
			FN_TAXPRICE,
			FN_QTY,
			FN_AMT,
			FN_DIFFERQTY,
			FN_DIFFERAMT,
			FN_TAXAMT,
			FN_ACCOUNTTAXAMT,
			FN_DIFFERTAXAMT,
			FN_ACCOUNTPIEQTY,
			FN_PIEQTY,
			FN_DIFFERPIEQTY,
			FN_EXPDATE,
			FN_VENDORID,
			FN_USRADD,
			FN_STOCKID,
			FN_COUNTED
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
		map.put(FN_ORGUNITNO, 32);
		return map; 
	}
	
	public String getPkKey() {
		return FN_ID;
	}
 
	public long getPK() {
		return id;
	}
}
