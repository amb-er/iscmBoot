package com.armitage.server.iscm.inventorymanage.inventorysetting.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 

@XmlRootElement(name = "scmInvMaterialAssign")  
public class ScmInvMaterialAssign extends BaseModel {
	public static final String FN_ID = "id";
	public static final String FN_WAREHOUSEID = "wareHouseId";
	public static final String FN_ORGUNITNO = "orgUnitNo";
	public static final String FN_ASSIGNTYPE = "assignType";
	public static final String FN_ITEMCLSID = "itemClsId";
	public static final String FN_ITEMID = "itemId";
	public static final String FN_MAXQTY = "maxQty";
	public static final String FN_CREATOR = "creator";
	public static final String FN_CREATEDATE = "createDate";
	public static final String FN_EDITOR = "editor";
	public static final String FN_EDITDATE = "editDate";
	
	private long id;
	private long wareHouseId;
	private String orgUnitNo;
	private String assignType;
	private long itemClsId;
	private long itemId;
	private BigDecimal maxQty;
	private String creator;
	private Date createDate;
	private String editor;
	private Date editDate;

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
	public String getAssignType() {
		return assignType;
	}

	public void setAssignType(String val) {
		this.assignType = val;
	}
	public long getItemClsId() {
		return itemClsId;
	}

	public void setItemClsId(long val) {
		this.itemClsId = val;
	}
	public long getItemId() {
		return itemId;
	}

	public void setItemId(long val) {
		this.itemId = val;
	}
	public BigDecimal getMaxQty() {
		return maxQty;
	}

	public void setMaxQty(BigDecimal val) {
		this.maxQty = val;
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
	
	public ScmInvMaterialAssign(boolean defaultValue) {
		if (defaultValue) {
			this.maxQty=BigDecimal.ZERO;
		}
	}
  	public ScmInvMaterialAssign() {
	}

	public String[] getFieldNames(){
		return new String[]{
			FN_ID,
			FN_WAREHOUSEID,
			FN_ORGUNITNO,
			FN_ASSIGNTYPE,
			FN_ITEMCLSID,
			FN_ITEMID,
			FN_MAXQTY,
			FN_CREATOR,
			FN_CREATEDATE,
			FN_EDITOR,
			FN_EDITDATE,
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
		map.put(FN_ASSIGNTYPE, 16);
		map.put(FN_CREATOR, 16);
		map.put(FN_EDITOR, 16);
		return map; 
	}
	
	public String getPkKey() {
		return FN_ID;
	}
 
	public long getPK() {
		return id;
	}
}

