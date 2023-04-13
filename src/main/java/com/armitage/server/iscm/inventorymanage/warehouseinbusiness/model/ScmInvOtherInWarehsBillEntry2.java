package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model;

import java.math.BigDecimal;
import java.util.Date;

public class ScmInvOtherInWarehsBillEntry2 extends ScmInvOtherInWarehsBillEntry{
	public static final String FN_ITEMNO = "itemNo";
	public static final String FN_ITEMNAME = "itemName";
	public static final String FN_SPEC = "spec";
	public static final String FN_UNITNAME = "unitName";
	public static final String FN_CHOOSED = "choosed";
	public static final String FN_CONVRATE = "convrate";
	public static final String FN_TAXRATESTR = "taxRateStr";
	public static final String FN_BASEUNITNAME = "baseUnitName";
	public static final String FN_BIZTYPE = "bizType";
	public static final String FN_CREATOR = "creator";
	public static final String FN_WHNAME = "whName";
	private String wrNo;
	private String bizType;
	private Date bizDate;
	private Date postDate;
	private String periodValid;
	private int periodValidDays;
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	private String status;
	private String creator;
	public String getWhName() {
		return whName;
	}
	public void setWhName(String whName) {
		this.whName = whName;
	}
	

	private String itemNo;
	private String itemName;
	private String spec;
	private String unitName;
	private boolean choosed;
	private BigDecimal convrate;
	private String taxRateStr;
	private String baseUnitName;
	private String pieUnitName;
	private BigDecimal invQty;
	private BigDecimal invPieQty;
	private String finOrgUnitNo;
	private String whName;
	private String groupName;
	public String getWrNo() {
		return wrNo;
	}
	public void setWrNo(String wrNo) {
		this.wrNo = wrNo;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public Date getBizDate() {
		return bizDate;
	}
	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getBaseUnitName() {
		return baseUnitName;
	}
	public void setBaseUnitName(String baseUnitName) {
		this.baseUnitName = baseUnitName;
	}
	public String getTaxRateStr() {
		return taxRateStr;
	}
	public void setTaxRateStr(String taxRateStr) {
		this.taxRateStr = taxRateStr;
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
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	public String getPieUnitName() {
		return pieUnitName;
	}
	public void setPieUnitName(String pieUnitName) {
		this.pieUnitName = pieUnitName;
	}
	public BigDecimal getInvQty() {
		return invQty;
	}
	public void setInvQty(BigDecimal invQty) {
		this.invQty = invQty;
	}
	public BigDecimal getInvPieQty() {
		return invPieQty;
	}
	public void setInvPieQty(BigDecimal invPieQty) {
		this.invPieQty = invPieQty;
	}
	public String getFinOrgUnitNo() {
		return finOrgUnitNo;
	}
	public void setFinOrgUnitNo(String finOrgUnitNo) {
		this.finOrgUnitNo = finOrgUnitNo;
	}
	public String getPeriodValid() {
		return periodValid;
	}
	public void setPeriodValid(String periodValid) {
		this.periodValid = periodValid;
	}
	public int getPeriodValidDays() {
		return periodValidDays;
	}
	public void setPeriodValidDays(int periodValidDays) {
		this.periodValidDays = periodValidDays;
	}
	public ScmInvOtherInWarehsBillEntry2(boolean defaultValue) {
		super(defaultValue);
		if (defaultValue) {
			this.convrate=BigDecimal.ZERO;
			this.taxRateStr="0%";
			this.invQty=BigDecimal.ZERO;
		}
	}

	public ScmInvOtherInWarehsBillEntry2() {
		super();
	}
}
