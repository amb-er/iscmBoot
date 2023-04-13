package com.armitage.server.iscm.inventorymanage.inventorydata.model;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 

@XmlRootElement(name = "scmInvStockNo")  
public class ScmInvStockNo extends BaseModel {
	public static final String FN_ID = "id";
	public static final String FN_INVSTOCKNO = "invStockNo";
	public static final String FN_INVSTOCKID = "invStockId";
	public static final String FN_CONTROLUNITNO = "controlUnitNo";
	
	private long id;
	private String invStockNo;
	private long invStockId;
	private String controlUnitNo;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getInvStockNo() {
		return invStockNo;
	}
	public void setInvStockNo(String invStockNo) {
		this.invStockNo = invStockNo;
	}
	public long getInvStockId() {
		return invStockId;
	}
	public void setInvStockId(long invStockId) {
		this.invStockId = invStockId;
	}
	public String getControlUnitNo() {
		return controlUnitNo;
	}
	public void setControlUnitNo(String controlUnitNo) {
		this.controlUnitNo = controlUnitNo;
	}
	public ScmInvStockNo(long id) {
		this.id = id;
	}
  	public ScmInvStockNo() {
	}

	public String[] getFieldNames(){
		return new String[]{
			FN_ID,
			FN_INVSTOCKNO,
			FN_INVSTOCKID,
			FN_CONTROLUNITNO
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
		map.put(FN_INVSTOCKNO, 32);
		map.put(FN_CONTROLUNITNO, 32);
		return map; 
	}
	
	public String getPkKey() {
		return FN_ID;
	}
 
	public long getPK() {
		return id;
	}
}
