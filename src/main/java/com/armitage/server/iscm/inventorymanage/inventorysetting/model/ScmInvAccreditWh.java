package com.armitage.server.iscm.inventorymanage.inventorysetting.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 

@XmlRootElement(name = "scmInvAccreditWh")  
public class ScmInvAccreditWh extends BaseModel {
	public static final String FN_ID = "id";
	public static final String FN_WAREHOUSEID = "wareHouseId";
	public static final String FN_ORGUNITNO = "orgUnitNo";
	public static final String FN_MORGUNITNO = "morgUnitNo";
	public static final String FN_ENDINIT = "endInit";
	public static final String FN_PERIODID = "periodId";
	public static final String FN_STOCKTYPE = "stockType";
	public static final String FN_STATUS = "status";
	
	private long id;
	private long wareHouseId;
	private String orgUnitNo;
	private String morgUnitNo;
	private boolean endInit;
	private long periodId;
	private String stockType;
	private String status;

	public long getId() {
		return id;
	}

	public void setId(long val) {
		this.id = val;
	}
	public long getWareHouseId() {
		return wareHouseId;
	}

	public void setWareHouseId(long val) {
		this.wareHouseId = val;
	}
	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String val) {
		this.orgUnitNo = val;
	}
	public String getMorgUnitNo() {
		return morgUnitNo;
	}

	public void setMorgUnitNo(String val) {
		this.morgUnitNo = val;
	}
	
	public boolean isEndInit() {
		return endInit;
	}

	public void setEndInit(boolean endInit) {
		this.endInit = endInit;
	}

	public long getPeriodId() {
		return periodId;
	}

	public void setPeriodId(long val) {
		this.periodId = val;
	}
	public String getStockType() {
		return stockType;
	}

	public void setStockType(String val) {
		this.stockType = val;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String val) {
		this.status = val;
	}
	
	public ScmInvAccreditWh(boolean defaultValue) {
		if (defaultValue) {
			this.endInit = false;
			this.periodId = 0;
			this.stockType = "A";
			this.status = "N";
		}
	}
  	public ScmInvAccreditWh() {
	}

	public String[] getFieldNames(){
		return new String[]{
			FN_ID,
			FN_WAREHOUSEID,
			FN_ORGUNITNO,
			FN_MORGUNITNO,
			FN_ENDINIT,
			FN_PERIODID,
			FN_STOCKTYPE,
			FN_STATUS,
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
		map.put(FN_ORGUNITNO, 32);
		map.put(FN_MORGUNITNO, 32);
		map.put(FN_STOCKTYPE, 16);
		map.put(FN_STATUS, 16);
		return map; 
	}
	
	public String getPkKey() {
		return FN_ID;
	}
 
	public long getPK() {
		return id;
	}
}

