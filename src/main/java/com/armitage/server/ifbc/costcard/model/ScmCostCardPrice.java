package com.armitage.server.ifbc.costcard.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 

@XmlRootElement(name = "scmCostCardPrice")  
public class ScmCostCardPrice extends BaseModel {
	public static final String FN_ID = "id";
	public static final String FN_ORGUNITNO = "orgUnitNo";
	public static final String FN_DISHID = "dishId";
    public static final String FN_DISHSPECID ="dishSpecId";
	public static final String FN_ACCDATE = "accDate";
	public static final String FN_SALEPRICE = "salePrice";
	public static final String FN_COSTPRICE = "costPrice";
	
	private long id;
	private String orgUnitNo;
	private long dishId;
	private long dishSpecId;
	private Date accDate;
	private BigDecimal salePrice;
	private BigDecimal costPrice;

	public long getId() {
		return id;
	}

	public void setId(long val) {
		this.id = val;
	}
	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String val) {
		this.orgUnitNo = val;
	}
	public long getDishId() {
		return dishId;
	}

	public void setDishId(long val) {
		this.dishId = val;
	}
	public long getDishSpecId() {
		return dishSpecId;
	}

	public void setDishSpecId(long dishSpecId) {
		this.dishSpecId = dishSpecId;
	}

	public Date getAccDate() {
		return accDate;
	}

	public void setAccDate(Date val) {
		this.accDate = val;
	}
	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal val) {
		this.salePrice = val;
	}
	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal val) {
		this.costPrice = val;
	}
	
	public ScmCostCardPrice(boolean defaultValue) {
		if (defaultValue) {
			this.salePrice=BigDecimal.ZERO;
			this.costPrice=BigDecimal.ZERO;
		}
	}
  	public ScmCostCardPrice() {
	}

	public String[] getFieldNames(){
		return new String[]{
			FN_ID,
			FN_ORGUNITNO,
			FN_DISHID,
			FN_DISHSPECID,
			FN_ACCDATE,
			FN_SALEPRICE,
			FN_COSTPRICE,
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
		map.put(FN_ORGUNITNO, 96);
		return map; 
	}
	
	public String getPkKey() {
		return FN_ID;
	}
 
	public long getPK() {
		return id;
	}
}
