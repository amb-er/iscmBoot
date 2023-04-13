package com.armitage.server.api.business.datareport.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@ApiModel(value="InvRealTimeStockResult",description="盘存任务查询返回结果")
public class InvRealTimeStockResult {
	
	@ApiModelProperty(value="物资编码",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String itemNo;
	
	@ApiModelProperty(value="物资名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String itemName;
	
	@ApiModelProperty(value="规格",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String spec;
	
	@ApiModelProperty(value="计量单位名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String unitName;
	
	@ApiModelProperty(value="批次",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String lot;

	@ApiModelProperty(value="结存数量",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal qty;
	
	@ApiModelProperty(value="结存金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal amt;

	@ApiModelProperty(value="税率",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal taxRate;
	
	@ApiModelProperty(value="结存含税金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal taxAmt;

	@ApiModelProperty(value="辅助计量单位名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String pieUnitName;
	
	@ApiModelProperty(value="辅助数量",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal pieQty;
	
	@ApiModelProperty(value="库存组织名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String orgUnitName;
	
	@ApiModelProperty(value="仓库名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String wareHouseName;
	
	@ApiModelProperty(value="供应商名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String vendorName;

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

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}

	public String getPieUnitName() {
		return pieUnitName;
	}

	public void setPieUnitName(String pieUnitName) {
		this.pieUnitName = pieUnitName;
	}

	public BigDecimal getPieQty() {
		return pieQty;
	}

	public void setPieQty(BigDecimal pieQty) {
		this.pieQty = pieQty;
	}

	public String getOrgUnitName() {
		return orgUnitName;
	}

	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}

	public String getWareHouseName() {
		return wareHouseName;
	}

	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
			
}
