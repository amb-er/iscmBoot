package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 

@XmlRootElement(name = "scmInvSalePriceentry")  
public class ScmInvSalePriceentry extends BaseModel {
	public static final String FN_ID = "id";
	public static final String FN_PMID = "pmId";
	public static final String FN_LINEID = "lineId";
	public static final String FN_ITEMID = "itemId";
	public static final String FN_UNIT = "unit";
	public static final String FN_SALETAXPRICE = "saleTaxPrice";
	public static final String FN_PRESALETAXPRICE = "preSaleTaxPrice";
	public static final String FN_ROWSTATUS = "rowStatus";
	public static final String FN_CHECKER = "checker";
	public static final String FN_CHECKDATE = "checkDate";
	public static final String FN_REMARKS = "remarks";
	
	private long id;
	private long pmId;
	private int lineId;
	private long itemId;
	private long unit;
	private BigDecimal saleTaxPrice;
	private BigDecimal preSaleTaxPrice;
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
	public long getPmId() {
		return pmId;
	}

	public void setPmId(long val) {
		this.pmId = val;
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
	
	public BigDecimal getSaleTaxPrice() {
		return saleTaxPrice;
	}

	public void setSaleTaxPrice(BigDecimal saleTaxPrice) {
		this.saleTaxPrice = saleTaxPrice;
	}

	public BigDecimal getPreSaleTaxPrice() {
		return preSaleTaxPrice;
	}

	public void setPreSaleTaxPrice(BigDecimal preSaleTaxPrice) {
		this.preSaleTaxPrice = preSaleTaxPrice;
	}

	public long getUnit() {
		return unit;
	}

	public void setUnit(long val) {
		this.unit = val;
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
	
	public ScmInvSalePriceentry(boolean defaultValue) {
		if (defaultValue) {
			this.saleTaxPrice=BigDecimal.ZERO;
			this.preSaleTaxPrice=BigDecimal.ZERO;
			this.unit=0;
			this.rowStatus="I";
		}
	}
  	public ScmInvSalePriceentry() {
	}

	public String[] getFieldNames(){
		return new String[]{
			FN_ID,
			FN_PMID,
			FN_LINEID,
			FN_ITEMID,
			FN_SALETAXPRICE,
			FN_PRESALETAXPRICE,
			FN_UNIT,
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
