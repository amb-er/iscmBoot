package com.armitage.server.iscm.basedata.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 

@XmlRootElement(name = "pubSysBasicInfo")  
public class PubSysBasicInfo extends BaseModel {
	public static final String FN_ID = "id";
	public static final String FN_GUID = "guId";
	public static final String FN_FREGNO = "fRegNo";
	public static final String FN_FINFONO = "fInfoNo";
	public static final String FN_FINFONAME = "fInfoName";
	public static final String FN_FVALUE = "fValue";
	public static final String FN_FMEMO = "fMemo";
	public static final String FN_FSTATUS = "fStatus";
	
	private long id;
	private String guId;
	private String fRegNo;
	private String fInfoNo;
	private String fInfoName;
	private String fValue;
	private String fMemo;
	private String fStatus;

	public long getId() {
		return id;
	}

	public void setId(long val) {
		this.id = val;
	}
	public String getGuId() {
		return guId;
	}

	public void setGuId(String val) {
		this.guId = val;
	}
	public String getFRegNo() {
		return fRegNo;
	}

	public void setFRegNo(String val) {
		this.fRegNo = val;
	}
	public String getFInfoNo() {
		return fInfoNo;
	}

	public void setFInfoNo(String val) {
		this.fInfoNo = val;
	}
	public String getFInfoName() {
		return fInfoName;
	}

	public void setFInfoName(String val) {
		this.fInfoName = val;
	}
	public String getFValue() {
		return fValue;
	}

	public void setFValue(String val) {
		this.fValue = val;
	}
	public String getFMemo() {
		return fMemo;
	}

	public void setFMemo(String val) {
		this.fMemo = val;
	}
	public String getFStatus() {
		return fStatus;
	}

	public void setFStatus(String val) {
		this.fStatus = val;
	}
	
	public PubSysBasicInfo(boolean defaultValue) {
		if (defaultValue) {
		}
	}
  	public PubSysBasicInfo() {
	}

	public String[] getFieldNames(){
		return new String[]{
			FN_ID,
			FN_GUID,
			FN_FREGNO,
			FN_FINFONO,
			FN_FINFONAME,
			FN_FVALUE,
			FN_FMEMO,
			FN_FSTATUS,
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
		map.put(FN_GUID, 120);
		map.put(FN_FREGNO, 36);
		map.put(FN_FINFONO, 36);
		map.put(FN_FINFONAME, 180);
		map.put(FN_FVALUE, 180);
		map.put(FN_FMEMO, 765);
		map.put(FN_FSTATUS, 3);
		return map; 
	}
	
	public String getPkKey() {
		return FN_ID;
	}
 
	public long getPK() {
		return id;
	}
}

