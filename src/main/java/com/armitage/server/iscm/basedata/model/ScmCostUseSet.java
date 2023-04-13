package com.armitage.server.iscm.basedata.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 

@XmlRootElement(name = "scmCostUseSet")  
public class ScmCostUseSet extends BaseModel {
	public static final String FN_ID = "id";
	public static final String FN_ORGUNITNO = "orgUnitNo";
	public static final String FN_COSTUSETYPEID = "costUseTypeId";
	public static final String FN_CLASSID = "classId";
	public static final String FN_COSTORGUNITNO = "costOrgUnitNo";
	public static final String FN_FLAG = "flag";
	public static final String FN_CREATOR = "creator";
	public static final String FN_CREATEDATE = "createDate";
	public static final String FN_EDITOR = "editor";
	public static final String FN_EDITDATE = "editDate";
	public static final String FN_CONTROLUNITNO = "controlUnitNo";
	public static final String FN_ROWMD = "rowMD";
	
	private long id;
	private String orgUnitNo;
	private long costUseTypeId;
	private long classId;
	private String costOrgUnitNo;
	private boolean flag;
	private String creator;
	private Date createDate;
	private String editor;
	private Date editDate;
	private String controlUnitNo;
	private String rowMD;

	public String getRowMD() {
		return rowMD;
	}

	public void setRowMD(String rowMD) {
		this.rowMD = rowMD;
	}

	public long getId() {
		return id;
	}

	public void setId(long val) {
		this.id = val;
	}
	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}

	public long getCostUseTypeId() {
		return costUseTypeId;
	}

	public void setCostUseTypeId(long val) {
		this.costUseTypeId = val;
	}
	public long getClassId() {
		return classId;
	}

	public void setClassId(long val) {
		this.classId = val;
	}
	public String getCostOrgUnitNo() {
		return costOrgUnitNo;
	}

	public void setCostOrgUnitNo(String val) {
		this.costOrgUnitNo = val;
	}
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String val) {
		this.creator = val;
	}
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date val) {
		this.createDate = val;
	}
	public String getEditor() {
		return editor;
	}

	public void setEditor(String val) {
		this.editor = val;
	}
	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date val) {
		this.editDate = val;
	}
	public String getControlUnitNo() {
		return controlUnitNo;
	}

	public void setControlUnitNo(String val) {
		this.controlUnitNo = val;
	}
	
	public ScmCostUseSet(boolean defaultValue) {
		if (defaultValue) {
			this.flag=true;
		}
	}
  	public ScmCostUseSet() {
	}

	public String[] getFieldNames(){
		return new String[]{
			FN_ID,
			FN_COSTUSETYPEID,
			FN_CLASSID,
			FN_COSTORGUNITNO,
			FN_FLAG,
			FN_CREATOR,
			FN_CREATEDATE,
			FN_EDITOR,
			FN_EDITDATE,
			FN_CONTROLUNITNO,
			FN_ROWMD,
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
		map.put(FN_COSTORGUNITNO, 32);
		map.put(FN_CREATOR, 16);
		map.put(FN_EDITOR, 16);
		map.put(FN_CONTROLUNITNO, 32);
		map.put(FN_ROWMD,32);
		return map; 
	}
	
	public String getPkKey() {
		return FN_ID;
	}
 
	public long getPK() {
		return id;
	}
}

