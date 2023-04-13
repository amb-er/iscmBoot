package com.armitage.server.iscm.report.inventory.model;

import java.math.BigDecimal;

public class ScmInvPurSalePrice {
	//ds1.select(itemClassName)
	//物资类别
	private String itemClassId;
	private String itemClassName;
	private String itemClassNo;
	//物资
	private String itemName;
	private String itemNo;
	private Long itemId;
	private String spec;
	
	//销售数据
	private BigDecimal saleTaxPrice;//销售含税单价
	private Long saleUnitId;//销售单位ID
	private BigDecimal saleConvrate;//销售与基本单位的换算率
	private String saleUnitName;//销售单位名称
	
	//采购数据
	private BigDecimal putTaxPrice;//采购含税单价
	private Long purUnitId;//采购单位ID
	private BigDecimal purConvrate;//采购与基本单位的换算率
	private String purUnitName;//采购单位名称
	
	
	
	
	
	

	@Override
	public String toString() {
		return "ScmInvPurSalePrice [itemClassId=" + itemClassId + ", itemClassName=" + itemClassName + ", itemClassNo="
				+ itemClassNo + ", itemName=" + itemName + ", itemNo=" + itemNo + ", itemId=" + itemId + ", spec="
				+ spec + ", saleTaxPrice=" + saleTaxPrice + ", saleUnitId=" + saleUnitId + ", saleConvrate="
				+ saleConvrate + ", saleUnitName=" + saleUnitName + ", putTaxPrice=" + putTaxPrice + ", purUnitId="
				+ purUnitId + ", purConvrate=" + purConvrate + ", purUnitName=" + purUnitName + "]";
	}
	public String getItemClassName() {
		return itemClassName;
	}
	public void setItemClassName(String itemClassName) {
		this.itemClassName = itemClassName;
	}
	public String getItemClassId() {
		return itemClassId;
	}
	public void setItemClassId(String itemClassId) {
		this.itemClassId = itemClassId;
	}
	public String getItemClassNo() {
		return itemClassNo;
	}
	public void setItemClassNo(String itemClassNo) {
		this.itemClassNo = itemClassNo;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public BigDecimal getSaleTaxPrice() {
		return saleTaxPrice;
	}
	public void setSaleTaxPrice(BigDecimal saleTaxPrice) {
		this.saleTaxPrice = saleTaxPrice;
	}
	public Long getSaleUnitId() {
		return saleUnitId;
	}
	public void setSaleUnitId(Long saleUnitId) {
		this.saleUnitId = saleUnitId;
	}
	public BigDecimal getSaleConvrate() {
		return saleConvrate;
	}
	public void setSaleConvrate(BigDecimal saleConvrate) {
		this.saleConvrate = saleConvrate;
	}
	public String getSaleUnitName() {
		return saleUnitName;
	}
	public void setSaleUnitName(String saleUnitName) {
		this.saleUnitName = saleUnitName;
	}
	public BigDecimal getPutTaxPrice() {
		return putTaxPrice;
	}
	public void setPutTaxPrice(BigDecimal putTaxPrice) {
		this.putTaxPrice = putTaxPrice;
	}
	public Long getPurUnitId() {
		return purUnitId;
	}
	public void setPurUnitId(Long purUnitId) {
		this.purUnitId = purUnitId;
	}
	public BigDecimal getPurConvrate() {
		return purConvrate;
	}
	public void setPurConvrate(BigDecimal purConvrate) {
		this.purConvrate = purConvrate;
	}
	public String getPurUnitName() {
		return purUnitName;
	}
	public void setPurUnitName(String purUnitName) {
		this.purUnitName = purUnitName;
	}
	
}





