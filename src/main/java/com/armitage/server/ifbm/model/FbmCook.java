package com.armitage.server.ifbm.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 

@XmlRootElement(name = "fbmCook")  
public class FbmCook extends BaseModel {
	public static final String FN_ID = "id";
	public static final String FN_COOKTYPEID = "cookTypeId";
	public static final String FN_CODE = "code";
	public static final String FN_NAME = "name";
	public static final String FN_MNEMONIC = "mnemonic";
	public static final String FN_PRICE = "price";
	public static final String FN_MODE = "mode";
	public static final String FN_SORT = "sort";
	public static final String FN_PERMIT = "permit";
	public static final String FN_REMARKS = "remarks";
	public static final String FN_ISYNCMODFLAG = "iSyncModFlag";
    public static final String FN_BELONGORGUNITNO ="belongOrgUnitNo";
	public static final String FN_CONTROLUNITNO = "controlUnitNo";
	
	private long id;
	private long cookTypeId;
	private String code;
	private String name;
	private String mnemonic;
	private BigDecimal price;
	private String mode;
	private int sort;
	private boolean permit;
	private String remarks;
	private String iSyncModFlag;
    private String belongOrgUnitNo;
	private String controlUnitNo;

	public long getId() {
		return id;
	}

	public void setId(long val) {
		this.id = val;
	}
	public long getCookTypeId() {
		return cookTypeId;
	}

	public void setCookTypeId(long val) {
		this.cookTypeId = val;
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
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal val) {
		this.price = val;
	}
	public String getMode() {
		return mode;
	}

	public void setMode(String val) {
		this.mode = val;
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
	
	public String getiSyncModFlag() {
		return iSyncModFlag;
	}

	public void setiSyncModFlag(String iSyncModFlag) {
		this.iSyncModFlag = iSyncModFlag;
	}

	public String getBelongOrgUnitNo() {
		return belongOrgUnitNo;
	}

	public void setBelongOrgUnitNo(String belongOrgUnitNo) {
		this.belongOrgUnitNo = belongOrgUnitNo;
	}

	public String getControlUnitNo() {
		return controlUnitNo;
	}

	public void setControlUnitNo(String val) {
		this.controlUnitNo = val;
	}
	
	public FbmCook(boolean defaultValue) {
		if (defaultValue) {
			this.price = BigDecimal.ZERO;
			this.mode = "0";
			this.sort = 0;
			this.permit = false;
			this.iSyncModFlag = "SELF";
		}
	}
  	public FbmCook() {
	}

	public String[] getFieldNames(){
		return new String[]{
			FN_ID,
			FN_COOKTYPEID,
			FN_CODE,
			FN_NAME,
			FN_MNEMONIC,
			FN_PRICE,
			FN_MODE,
			FN_SORT,
			FN_PERMIT,
			FN_REMARKS,
			FN_ISYNCMODFLAG,
			FN_BELONGORGUNITNO,
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
		map.put(FN_CODE, 16);
		map.put(FN_NAME, 30);
		map.put(FN_MNEMONIC, 30);
		map.put(FN_MODE, 1);
		map.put(FN_REMARKS, 60);
		map.put(FN_ISYNCMODFLAG, 20);
		map.put(FN_BELONGORGUNITNO, 32);
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
