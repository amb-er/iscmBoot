package com.armitage.server.iscm.purchasemanage.pricemanage.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 

@XmlRootElement(name = "scmPurPricePrepareEntry")  
public class ScmPurPricePrepareEntry extends BaseModel {
	public static final String FN_ID = "id";
	public static final String FN_PMID = "pmId";
	public static final String FN_ITEMID = "itemId";
	public static final String FN_PURUNIT = "purUnit";
	public static final String FN_VENDORID1 = "vendorId1";
	public static final String FN_VENDORID2 = "vendorId2";
	public static final String FN_VENDORID3 = "vendorId3";
	public static final String FN_PRESELVNDRID = "preSelVndrId";
	public static final String FN_PRICE1 = "price1";
	public static final String FN_PRICE2 = "price2";
	public static final String FN_PRICE3 = "price3";
	public static final String FN_PREPRICE1 = "prePrice1";
	public static final String FN_PREPRICE2 = "prePrice2";
	public static final String FN_PREPRICE3 = "prePrice3";
	public static final String FN_PRETAXRATE1 = "preTaxRate1";
	public static final String FN_PRETAXRATE2 = "preTaxRate2";
	public static final String FN_PRETAXRATE3 = "preTaxRate3";
	public static final String FN_EDITOR = "editor";
	public static final String FN_EDITDATE = "editDate";
	public static final String FN_FLAG = "flag";
	public static final String FN_REMARKS = "remarks";
	
	private long id;
	private long pmId;
	private long itemId;
	private long purUnit;
	private long vendorId1;
	private long vendorId2;
	private long vendorId3;
	private long preSelVndrId;
	private BigDecimal price1;
	private BigDecimal price2;
	private BigDecimal price3;
	private BigDecimal prePrice1;
	private BigDecimal prePrice2;
	private BigDecimal prePrice3;
	private BigDecimal preTaxRate1;
	private BigDecimal preTaxRate2;
	private BigDecimal preTaxRate3;
	private String editor;
	private Date editDate;
	private boolean flag;
	private String remarks;

	public long getId() {
		return id;
	}

	public void setId(long val) {
		this.id = val;
	}
	public long getPmId() {
		return pmId;
	}

	public void setPmId(long val) {
		this.pmId = val;
	}
	public long getItemId() {
		return itemId;
	}

	public void setItemId(long val) {
		this.itemId = val;
	}
	public long getPurUnit() {
		return purUnit;
	}

	public void setPurUnit(long val) {
		this.purUnit = val;
	}
	public long getVendorId1() {
		return vendorId1;
	}

	public void setVendorId1(long val) {
		this.vendorId1 = val;
	}
	public long getVendorId2() {
		return vendorId2;
	}

	public void setVendorId2(long val) {
		this.vendorId2 = val;
	}
	public long getVendorId3() {
		return vendorId3;
	}

	public void setVendorId3(long val) {
		this.vendorId3 = val;
	}
	public long getPreSelVndrId() {
		return preSelVndrId;
	}

	public void setPreSelVndrId(long preSelVndrId) {
		this.preSelVndrId = preSelVndrId;
	}

	public BigDecimal getPrice1() {
		return price1;
	}

	public void setPrice1(BigDecimal val) {
		this.price1 = val;
	}
	public BigDecimal getPrice2() {
		return price2;
	}

	public void setPrice2(BigDecimal val) {
		this.price2 = val;
	}
	public BigDecimal getPrice3() {
		return price3;
	}

	public void setPrice3(BigDecimal val) {
		this.price3 = val;
	}
	public BigDecimal getPrePrice1() {
		return prePrice1;
	}

	public void setPrePrice1(BigDecimal val) {
		this.prePrice1 = val;
	}
	public BigDecimal getPrePrice2() {
		return prePrice2;
	}

	public void setPrePrice2(BigDecimal val) {
		this.prePrice2 = val;
	}
	public BigDecimal getPrePrice3() {
		return prePrice3;
	}

	public void setPrePrice3(BigDecimal val) {
		this.prePrice3 = val;
	}
	public BigDecimal getPreTaxRate1() {
		return preTaxRate1;
	}

	public void setPreTaxRate1(BigDecimal val) {
		this.preTaxRate1 = val;
	}
	public BigDecimal getPreTaxRate2() {
		return preTaxRate2;
	}

	public void setPreTaxRate2(BigDecimal val) {
		this.preTaxRate2 = val;
	}
	public BigDecimal getPreTaxRate3() {
		return preTaxRate3;
	}

	public void setPreTaxRate3(BigDecimal val) {
		this.preTaxRate3 = val;
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
	
	public ScmPurPricePrepareEntry(boolean defaultValue) {
		if (defaultValue) {
			this.flag=true;
	    	this.price1=BigDecimal.ZERO;     
	    	this.price2=BigDecimal.ZERO;     
	    	this.price3=BigDecimal.ZERO;     
	    	this.prePrice1=BigDecimal.ZERO;  
	    	this.prePrice2=BigDecimal.ZERO;  
	    	this.prePrice3=BigDecimal.ZERO;  
	    	this.preTaxRate1=BigDecimal.ZERO;
	    	this.preTaxRate2=BigDecimal.ZERO;
	    	this.preTaxRate3=BigDecimal.ZERO;
	    	this.preSelVndrId=0;
		}
	}
  	public ScmPurPricePrepareEntry() {
	}

	public String[] getFieldNames(){
		return new String[]{
			FN_ID,
			FN_PMID,
			FN_ITEMID,
			FN_PURUNIT,
			FN_VENDORID1,
			FN_VENDORID2,
			FN_VENDORID3,
			FN_PRESELVNDRID,
			FN_PRICE1,
			FN_PRICE2,
			FN_PRICE3,
			FN_PREPRICE1,
			FN_PREPRICE2,
			FN_PREPRICE3,
			FN_PRETAXRATE1,
			FN_PRETAXRATE2,
			FN_PRETAXRATE3,
			FN_EDITOR,
			FN_EDITDATE,
			FN_FLAG,
			FN_REMARKS,
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
		map.put(FN_EDITOR, 48);
		map.put(FN_REMARKS, 765);
		return map; 
	}
	
	public String getPkKey() {
		return FN_ID;
	}
 
	public long getPK() {
		return id;
	}
}


