package com.armitage.server.ifbc.costcard.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 

@XmlRootElement(name = "scmDishProfitRate")  
public class ScmDishProfitRate extends BaseModel {
	public static final String FN_ID = "id";
	public static final String FN_CARDID = "cardId";
	public static final String FN_ORGUNITNO = "orgUnitNo";
	public static final String FN_DISHCODE = "dishCode";
	public static final String FN_PROFITRATE = "profitRate";
	public static final String FN_BOTTOMRATE = "bottomRate";
	public static final String FN_TOPRATE = "topRate";
	
	private long id;
	private long cardId;
	private String orgUnitNo;
	private String dishCode;
	private BigDecimal profitRate;
	private BigDecimal bottomRate;
	private BigDecimal topRate;

	public long getId() {
		return id;
	}

	public void setId(long val) {
		this.id = val;
	}
	public long getCardId() {
		return cardId;
	}

	public void setCardId(long val) {
		this.cardId = val;
	}
	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String val) {
		this.orgUnitNo = val;
	}
	public String getDishCode() {
		return dishCode;
	}

	public void setDishCode(String val) {
		this.dishCode = val;
	}
	public BigDecimal getProfitRate() {
		return profitRate;
	}

	public void setProfitRate(BigDecimal val) {
		this.profitRate = val;
	}
	public BigDecimal getBottomRate() {
		return bottomRate;
	}

	public void setBottomRate(BigDecimal val) {
		this.bottomRate = val;
	}
	public BigDecimal getTopRate() {
		return topRate;
	}

	public void setTopRate(BigDecimal val) {
		this.topRate = val;
	}
	
	public ScmDishProfitRate(boolean defaultValue) {
		if (defaultValue) {
			profitRate = BigDecimal.ZERO;
			bottomRate = BigDecimal.ZERO;
			topRate = BigDecimal.ZERO;
		}
	}
  	public ScmDishProfitRate() {
	}

	public String[] getFieldNames(){
		return new String[]{
			FN_ID,
			FN_CARDID,
			FN_ORGUNITNO,
			FN_DISHCODE,
			FN_PROFITRATE,
			FN_BOTTOMRATE,
			FN_TOPRATE,
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
		map.put(FN_DISHCODE, 48);
		return map; 
	}
	
	public String getPkKey() {
		return FN_ID;
	}
 
	public long getPK() {
		return id;
	}
}
