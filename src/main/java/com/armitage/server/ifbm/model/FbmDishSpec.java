package com.armitage.server.ifbm.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 

@XmlRootElement(name = "fbmDishSpec")  
public class FbmDishSpec extends BaseModel {
	public static final String FN_ID = "id";
	public static final String FN_CODE = "code";
	public static final String FN_NAME = "name";
	public static final String FN_MNEMONIC = "mnemonic";
	public static final String FN_SORT = "sort";
	public static final String FN_PERMIT = "permit";
	public static final String FN_REMARKS = "remarks";
	public static final String FN_ISYNCMODFLAG = "iSyncModFlag";
	public static final String FN_CONTROLUNITNO = "controlUnitNo";
	
	private long id;
	private String code;
	private String name;
	private String mnemonic;
	private int sort;
	private boolean permit;
	private String remarks;
	private String iSyncModFlag;
	private String controlUnitNo;

	public long getId() {
		return id;
	}

	public void setId(long val) {
		this.id = val;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String val) {
		this.code = val;
	}
	public String getName() {
		return name;
	}

	public void setName(String val) {
		this.name = val;
	}
	public String getMnemonic() {
		return mnemonic;
	}

	public void setMnemonic(String val) {
		this.mnemonic = val;
	}
	public int getSort() {
		return sort;
	}

	public void setSort(int val) {
		this.sort = val;
	}
	public boolean isPermit() {
		return permit;
	}

	public void setPermit(boolean permit) {
		this.permit = permit;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String val) {
		this.remarks = val;
	}
	public String getISyncModFlag() {
		return iSyncModFlag;
	}

	public void setISyncModFlag(String val) {
		this.iSyncModFlag = val;
	}
	public String getControlUnitNo() {
		return controlUnitNo;
	}

	public void setControlUnitNo(String val) {
		this.controlUnitNo = val;
	}
	
	public FbmDishSpec(boolean defaultValue) {
		if (defaultValue) {
			this.sort = 0;
			this.permit = true;
			this.iSyncModFlag = "SELF";
		}
	}
  	public FbmDishSpec() {
	}

	public String[] getFieldNames(){
		return new String[]{
			FN_ID,
			FN_CODE,
			FN_NAME,
			FN_MNEMONIC,
			FN_SORT,
			FN_PERMIT,
			FN_REMARKS,
			FN_ISYNCMODFLAG,
			FN_CONTROLUNITNO,
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
		map.put(FN_CODE, 48);
		map.put(FN_NAME, 90);
		map.put(FN_MNEMONIC, 90);
		map.put(FN_REMARKS, 180);
		map.put(FN_ISYNCMODFLAG, 60);
		map.put(FN_CONTROLUNITNO, 96);
		return map; 
	}
	
	public String getPkKey() {
		return FN_ID;
	}
 
	public long getPK() {
		return id;
	}
}