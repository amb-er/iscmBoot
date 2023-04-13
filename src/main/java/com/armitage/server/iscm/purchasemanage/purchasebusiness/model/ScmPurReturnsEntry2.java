package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import java.math.BigDecimal;

public class ScmPurReturnsEntry2 extends ScmPurReturnsEntry {
	public static final String FN_ITEMNO = "itemNo";
	public static final String FN_ITEMNAME = "itemName";
	public static final String FN_SPEC = "spec";
	public static final String FN_PURUNITNAME = "purUnitName";
	public static final String FN_BASEUNITNAME = "baseUnitName";
	public static final String FN_USEORGUNITNAME = "useOrgUnitName";
	public static final String FN_INVORGUNITNAME = "invOrgUnitName";
	public static final String FN_WAREHOUSENAME = "wareHouseName";
	public static final String FN_PURORGUNITNAME = "purOrgUnitName";
	public static final String FN_VENDORNO = "vendorNo";
	public static final String FN_VENDORNAME = "vendorName";
	public static final String FN_UNITNAME = "unitName";
	public static final String FN_CONVRATE = "convrate";
	public static final String FN_CHOOSED = "choosed";
	public static final String FN_TAXRATESTR = "taxRateStr";
	public static final String FN_MAXQTY = "maxQty";
	
	private String itemNo;
	private String itemName;
	private String spec;
	private String purUnitName;
	private String baseUnitName;
	private String useOrgUnitName;
	private String invOrgUnitName;
	private String wareHouseName;
	private String purOrgUnitName;
	private String vendorNo;
	private String vendorName;
	private String unitName;
	private BigDecimal convrate;
	private boolean choosed;
	private String taxRateStr;
	private BigDecimal maxQty;
	private String requireOrgUnitNo;	//退货申请组织
	private long costUseTypeId;
	private String pieUnitName;
	public String getItemNo() {
		return itemNo;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public String getSpec() {
		return spec;
	}
	
	public String getUseOrgUnitName() {
		return useOrgUnitName;
	}
	
	public String getPurUnitName() {
		return purUnitName;
	}
	
	public String getBaseUnitName() {
		return baseUnitName;
	}
	
	public String getInvOrgUnitName() {
		return invOrgUnitName;
	}
	
	public String getPurOrgUnitName() {
		return purOrgUnitName;
	}
	
	public String getWareHouseName() {
		return wareHouseName;
	}
	
	public String getVendorNo() {
		return vendorNo;
	}
	
	public String getVendorName() {
		return vendorName;
	}
	
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
	public void setVendorNo(String vendorNo) {
		this.vendorNo = vendorNo;
	}
	
	public void setItemNo(String itemNO) {
		this.itemNo = itemNO;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public void setSpec(String spec) {
		this.spec = spec;
	}
	
	public void setUseOrgUnitName(String useOrgUnitName) {
		this.useOrgUnitName = useOrgUnitName;
	}
	
	public void setPurUnitName(String purUnitName) {
		this.purUnitName = purUnitName;
	}
	
	public void setBaseUnitName(String baseUnitName) {
		this.baseUnitName = baseUnitName;
	}
	
	public void setInvOrgUnitName(String invOrgUnitName) {
		this.invOrgUnitName = invOrgUnitName;
	}
	
	public void setPurOrgUnitName(String purOrgUnitName) {
		this.purOrgUnitName = purOrgUnitName;
	}
	
	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
	}
	
	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public BigDecimal getConvrate() {
		return convrate;
	}

	public void setConvrate(BigDecimal convrate) {
		this.convrate = convrate;
	}

	public boolean isChoosed() {
		return choosed;
	}

	public void setChoosed(boolean choosed) {
		this.choosed = choosed;
	}

	public String getTaxRateStr() {
		return taxRateStr;
	}

	public void setTaxRateStr(String taxRateStr) {
		this.taxRateStr = taxRateStr;
	}

	public BigDecimal getMaxQty() {
		return maxQty;
	}

	public void setMaxQty(BigDecimal maxQty) {
		this.maxQty = maxQty;
	}

	public String getRequireOrgUnitNo() {
		return requireOrgUnitNo;
	}

	public void setRequireOrgUnitNo(String requireOrgUnitNo) {
		this.requireOrgUnitNo = requireOrgUnitNo;
	}

	public long getCostUseTypeId() {
		return costUseTypeId;
	}

	public void setCostUseTypeId(long costUseTypeId) {
		this.costUseTypeId = costUseTypeId;
	}

	public String getPieUnitName() {
		return pieUnitName;
	}

	public void setPieUnitName(String pieUnitName) {
		this.pieUnitName = pieUnitName;
	}

	public ScmPurReturnsEntry2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue) {
			this.convrate=BigDecimal.ZERO;
			this.maxQty=BigDecimal.ZERO;
			this.taxRateStr="0%";
		}
	}
	
	public ScmPurReturnsEntry2() {
		super();
	}
}