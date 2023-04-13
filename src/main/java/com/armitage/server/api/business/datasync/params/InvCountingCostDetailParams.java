package com.armitage.server.api.business.datasync.params;

import java.math.BigDecimal;

import com.armitage.server.api.config.StringDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvCountingCostDetailParams",description="成本中心盘存明细对象")
public class InvCountingCostDetailParams {
	
	@JsonDeserialize(using = StringDeserializer.class)
	@ApiModelProperty(value="物资编码",dataType="String",example="10009014",required=true)
	private String itemNo;
	
	@ApiModelProperty(value="单价",dataType="BigDecimal",example="9.653465",required=true)
	private BigDecimal price;
	
	@ApiModelProperty(value="含税单价",dataType="BigDecimal",example="2.943069",required=true)
	private BigDecimal taxPrice;
	
	@JsonDeserialize(using = StringDeserializer.class)
	@ApiModelProperty(value="批次",dataType="String",example="WR20220105056-01",required=true)
	private String lot;
	
	@ApiModelProperty(value="实存数量",dataType="BigDecimal",example="8.0000",required=true)
	private BigDecimal qty;
	
//	@ApiModelProperty(value="实存金额",dataType="BigDecimal",example="77.23",required=true)
//	private BigDecimal amt;
//	
//	@ApiModelProperty(value="实存含税金额",dataType="BigDecimal",example="9.943069",required=true)
//	private BigDecimal taxAmt;
	
	@ApiModelProperty(value="帐存数量",dataType="BigDecimal",example="8.0000",required=true)
	private BigDecimal accountQty;
	
//	@ApiModelProperty(value="差异数量",dataType="BigDecimal",example="8.0000",required=true)
//	private BigDecimal differQty;
//	
//	@ApiModelProperty(value="差异金额",dataType="BigDecimal",example="77.23",required=true)
//	private BigDecimal differAmt;
//	
//	@ApiModelProperty(value="差异含税金额",dataType="BigDecimal",example="77.23",required=true)
//	private BigDecimal differTaxAmt;
	
	@ApiModelProperty(value="税率",dataType="BigDecimal",example="0.030000",required=true)
	private BigDecimal taxRate;

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
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

//	public BigDecimal getAmt() {
//		return amt;
//	}
//
//	public void setAmt(BigDecimal amt) {
//		this.amt = amt;
//	}
//
//	public BigDecimal getTaxAmt() {
//		return taxAmt;
//	}
//
//	public void setTaxAmt(BigDecimal taxAmt) {
//		this.taxAmt = taxAmt;
//	}

	public BigDecimal getAccountQty() {
		return accountQty;
	}

	public void setAccountQty(BigDecimal accountQty) {
		this.accountQty = accountQty;
	}

//	public BigDecimal getDifferQty() {
//		return differQty;
//	}
//
//	public void setDifferQty(BigDecimal differQty) {
//		this.differQty = differQty;
//	}
//
//	public BigDecimal getDifferAmt() {
//		return differAmt;
//	}
//
//	public void setDifferAmt(BigDecimal differAmt) {
//		this.differAmt = differAmt;
//	}
//
//	public BigDecimal getDifferTaxAmt() {
//		return differTaxAmt;
//	}
//
//	public void setDifferTaxAmt(BigDecimal differTaxAmt) {
//		this.differTaxAmt = differTaxAmt;
//	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}
	
	

}
