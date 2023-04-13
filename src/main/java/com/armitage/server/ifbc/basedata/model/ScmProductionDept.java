package com.armitage.server.ifbc.basedata.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 

@XmlRootElement(name = "scmProductionDept")  
public class ScmProductionDept extends BaseModel {
	public static final String FN_ID = "id";
	public static final String FN_ORGUNITNO = "orgUnitNo";
	public static final String FN_CODE = "code";
	public static final String FN_NAME = "name";
	public static final String FN_RESORGUNITNO = "resOrgUnitNo";
	public static final String FN_FBMDEPTNO = "fbmDeptNo";
	public static final String FN_OUTLETID = "outletId";
	public static final String FN_FLAG = "flag";
	public static final String FN_REMARKS = "remarks";
	public static final String FN_CONTROLUNITNO = "controlUnitNo";
	
	private long id;
	private String orgUnitNo;
	private String code;
	private String name;
	private String resOrgUnitNo;
	private String fbmDeptNo;
	private long outletId;
	private boolean flag;
	private String remarks;
	private String controlUnitNo;

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
	public String getResOrgUnitNo() {
		return resOrgUnitNo;
	}

	public void setResOrgUnitNo(String val) {
		this.resOrgUnitNo = val;
	}
	public String getFbmDeptNo() {
		return fbmDeptNo;
	}

	public void setFbmDeptNo(String val) {
		this.fbmDeptNo = val;
	}

	public long getOutletId() {
		return outletId;
	}

	public void setOutletId(long outletId) {
		this.outletId = outletId;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String val) {
		this.remarks = val;
	}
	public String getControlUnitNo() {
		return controlUnitNo;
	}

	public void setControlUnitNo(String val) {
		this.controlUnitNo = val;
	}
	
	public ScmProductionDept(boolean defaultValue) {
		if (defaultValue) {
			this.flag=true;
		}
	}
  	public ScmProductionDept() {
	}

	public String[] getFieldNames(){
		return new String[]{
			FN_ID,
			FN_ORGUNITNO,
			FN_CODE,
			FN_NAME,
			FN_RESORGUNITNO,
			FN_FBMDEPTNO,
			FN_OUTLETID,
			FN_FLAG,
			FN_REMARKS,
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
		list.add(new String[] { FN_CODE, FN_ORGUNITNO });
		return list;
	}
	 
	public String[] getRequiredFields() {
		return new String[] {FN_CODE,FN_NAME };
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
		map.put(FN_CODE, 16);
		map.put(FN_NAME, 30);
		map.put(FN_RESORGUNITNO, 32);
		map.put(FN_FBMDEPTNO, 16);
		map.put(FN_REMARKS, 255);
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
