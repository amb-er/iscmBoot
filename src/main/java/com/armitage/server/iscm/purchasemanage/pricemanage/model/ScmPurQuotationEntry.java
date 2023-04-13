package com.armitage.server.iscm.purchasemanage.pricemanage.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 

@XmlRootElement(name = "scmPurQuotationEntry")  
public class ScmPurQuotationEntry extends BaseModel {
	public static final String FN_ID = "id";
	public static final String FN_PQID = "pqId";
	public static final String FN_LINEID = "lineId";
	public static final String FN_ITEMID = "itemId";
	public static final String FN_PURUNIT = "purUnit";
	public static final String FN_PREPRICE = "prePrice";
	public static final String FN_PRETAXPRICE = "preTaxPrice";
	public static final String FN_PRICE = "price";
	public static final String FN_TAXRATE = "taxRate";
	public static final String FN_TAXPRICE = "taxPrice";
	public static final String FN_ROWSTATUS = "rowStatus";
	public static final String FN_CHECKER = "checker";
	public static final String FN_CHECKDATE = "checkDate";
	public static final String FN_REMARKS = "remarks";
	
	private long id;
	private long pqId;
	private int lineId;
	private long itemId;
	private long purUnit;
	private BigDecimal prePrice;
	private BigDecimal preTaxPrice;
	private BigDecimal price;
	private BigDecimal taxRate;
	private BigDecimal taxPrice;
	private String rowStatus;
	private String checker;
	private Date checkDate;
	private String remarks;

	public long getId() {
		return id;
	}

	public void setId(long val) {
		this.id = val;
	}
	public long getPqId() {
		return pqId;
	}

	public void setPqId(long val) {
		this.pqId = val;
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
	public BigDecimal getPrePrice() {
		return prePrice;
	}

	public void setPrePrice(BigDecimal val) {
		this.prePrice = val;
	}
	public BigDecimal getPreTaxPrice() {
		return preTaxPrice;
	}

	public void setPreTaxPrice(BigDecimal val) {
		this.preTaxPrice = val;
	}
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal val) {
		this.price = val;
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
	
	public ScmPurQuotationEntry(boolean defaultValue) {
		if (defaultValue) {
			this.prePrice=BigDecimal.ZERO;
			this.preTaxPrice=BigDecimal.ZERO;
			this.taxRate=BigDecimal.ZERO;
			this.taxPrice=BigDecimal.ZERO;
			this.price=BigDecimal.ZERO;
			this.rowStatus="I";
		}
	}
  	public ScmPurQuotationEntry() {
	}

	public String[] getFieldNames(){
		return new String[]{
			FN_ID,
			FN_PQID,
			FN_LINEID,
			FN_ITEMID,
			FN_PURUNIT,
			FN_PREPRICE,
			FN_PRETAXPRICE,
			FN_PRICE,
			FN_TAXRATE,
			FN_TAXPRICE,
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
		//return new String[] {FN_ITEMID,FN_PURUNIT,FN_PRICE};
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
