package com.armitage.server.ifbc.costcard.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;

@XmlRootElement(name = "scmDishCostRatio")  
public class ScmDishCostRatio extends BaseModel {
	public static final String FN_ID = "id";
	public static final String FN_CARDID = "cardId";
	public static final String FN_LINEID = "lineId";
	public static final String FN_DISHSPECID = "dishSpecId";
	public static final String FN_COSTRATIO = "costRatio";
	
	private long id;
	private long cardId;
	private int lineId;
	private long dishSpecId;
	private BigDecimal costRatio;

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
	public int getLineId() {
		return lineId;
	}

	public void setLineId(int val) {
		this.lineId = val;
	}
	
	public long getDishSpecId() {
		return dishSpecId;
	}

	public void setDishSpecId(long dishSpecId) {
		this.dishSpecId = dishSpecId;
	}

	public BigDecimal getCostRatio() {
		return costRatio;
	}

	public void setCostRatio(BigDecimal val) {
		this.costRatio = val;
	}
	
	public ScmDishCostRatio(boolean defaultValue) {
		if (defaultValue) {
			this.lineId = 0;
			this.costRatio = BigDecimal.ONE;
		}
	}
  	public ScmDishCostRatio() {
	}

	public String[] getFieldNames(){
		return new String[]{
			FN_ID,
			FN_CARDID,
			FN_LINEID,
			FN_DISHSPECID,
			FN_COSTRATIO,
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
		return map; 
	}
	
	public String getPkKey() {
		return FN_ID;
	}
 
	public long getPK() {
		return id;
	}
}