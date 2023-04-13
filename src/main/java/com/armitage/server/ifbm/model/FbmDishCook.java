package com.armitage.server.ifbm.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 

@XmlRootElement(name = "fbmDishCook")  
public class FbmDishCook extends BaseModel {
	public static final String FN_ID = "id";
	public static final String FN_DISHRESINFOID = "dishResInfoId";
	public static final String FN_COOKID = "cookId";
	public static final String FN_ISYNCMODFLAG = "iSyncModFlag";
	
	private long id;
	private long dishResInfoId;
	private long cookId;
	private String iSyncModFlag;

	public long getId() {
		return id;
	}

	public void setId(long val) {
		this.id = val;
	}
	public long getDishResInfoId() {
		return dishResInfoId;
	}

	public void setDishResInfoId(long val) {
		this.dishResInfoId = val;
	}
	public long getCookId() {
		return cookId;
	}

	public void setCookId(long val) {
		this.cookId = val;
	}
	public String getISyncModFlag() {
		return iSyncModFlag;
	}

	public void setISyncModFlag(String val) {
		this.iSyncModFlag = val;
	}
	
	public FbmDishCook(boolean defaultValue) {
		if (defaultValue) {
			this.iSyncModFlag = "SELF";
		}
	}
  	public FbmDishCook() {
	}

	public String[] getFieldNames(){
		return new String[]{
			FN_ID,
			FN_DISHRESINFOID,
			FN_COOKID,
			FN_ISYNCMODFLAG,
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
		map.put(FN_ISYNCMODFLAG, 60);
		return map; 
	}
	
	public String getPkKey() {
		return FN_ID;
	}
 
	public long getPK() {
		return id;
	}
}

