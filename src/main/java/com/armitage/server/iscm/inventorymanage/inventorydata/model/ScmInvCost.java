package com.armitage.server.iscm.inventorymanage.inventorydata.model;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 

@XmlRootElement(name = "scmInvCost")  
public class ScmInvCost extends BaseModel {
	public static final String FN_ID = "id";
	public static final String FN_FINORGUNITNO = "finOrgUnitNo";
	public static final String FN_ORGUNITNO = "orgUnitNo";
	public static final String FN_WAREHOUSEID = "wareHouseId";
	public static final String FN_COSTORGUNITNO = "costOrgUnitNo";
	public static final String FN_COSTCENTER = "costCenter";
	public static final String FN_ITEMID = "itemId";
	public static final String FN_QTY = "qty";
	public static final String FN_UNIT = "unit";
	public static final String FN_PRICE = "price";
	public static final String FN_AMT = "amt";
	public static final String FN_TAXRATE = "taxRate";
	public static final String FN_TAXPRICE = "taxPrice";
	public static final String FN_TAXAMT = "taxAmt";
	
	private long id;
	private String finOrgUnitNo;
	private String orgUnitNo;
	private long wareHouseId;
	private String costOrgUnitNo;
	private boolean costCenter;
	private long itemId;
	private BigDecimal qty;
	private long unit;
	private BigDecimal price;
	private BigDecimal amt;
	private BigDecimal taxRate;
	private BigDecimal taxPrice;
	private BigDecimal taxAmt;

	public long getId() {
		return id;
	}

	public void setId(long val) {
		this.id = val;
	}
	public String getFinOrgUnitNo() {
		return finOrgUnitNo;
	}

	public void setFinOrgUnitNo(String val) {
		this.finOrgUnitNo = val;
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
	public String getCostOrgUnitNo() {
		return costOrgUnitNo;
	}

	public void setCostOrgUnitNo(String val) {
		this.costOrgUnitNo = val;
	}
	
	public boolean isCostCenter() {
		return costCenter;
	}

	public void setCostCenter(boolean costCenter) {
		this.costCenter = costCenter;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long val) {
		this.itemId = val;
	}
	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal val) {
		this.qty = val;
	}
	public long getUnit() {
		return unit;
	}

	public void setUnit(long val) {
		this.unit = val;
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
	
	public ScmInvCost(boolean defaultValue) {
		if (defaultValue) {
		}
	}
  	public ScmInvCost() {
	}

	public String[] getFieldNames(){
		return new String[]{
			FN_ID,
			FN_FINORGUNITNO,
			FN_ORGUNITNO,
			FN_WAREHOUSEID,
			FN_COSTORGUNITNO,
			FN_COSTCENTER,
			FN_ITEMID,
			FN_QTY,
			FN_UNIT,
			FN_PRICE,
			FN_AMT,
			FN_TAXRATE,
			FN_TAXPRICE,
			FN_TAXAMT,
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
		map.put(FN_FINORGUNITNO, 32);
		map.put(FN_ORGUNITNO, 32);
		map.put(FN_COSTORGUNITNO, 32);
		return map; 
	}
	
	public String getPkKey() {
		return FN_ID;
	}
 
	public long getPK() {
		return id;
	}
}
