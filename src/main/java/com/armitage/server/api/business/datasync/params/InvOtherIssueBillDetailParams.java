package com.armitage.server.api.business.datasync.params;

import java.math.BigDecimal;

import com.armitage.server.api.config.StringDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvCostConsumeDetailParams",description="其他入库单明细对象")
public class InvOtherIssueBillDetailParams {
	
	@JsonDeserialize(using = StringDeserializer.class)
	@ApiModelProperty(value="物资编码",dataType="String",example="10009014",required=true)
	private String itemNo;
	
	@ApiModelProperty(value="单价",dataType="BigDecimal",example="9.653465",required=true)
	private BigDecimal price;
	
	@JsonDeserialize(using = StringDeserializer.class)
	@ApiModelProperty(value="批次",dataType="String",example="WR20220105056-01",required=true)
	private String lot;
	
	@ApiModelProperty(value="数量",dataType="BigDecimal",example="8.0000",required=true)
	private BigDecimal qty;
	
	@ApiModelProperty(value="金额",dataType="BigDecimal",example="77.23",required=true)
	private BigDecimal amt;
	
	@ApiModelProperty(value="税率",dataType="BigDecimal",example="0.030000",required=true)
	private BigDecimal taxRate;
	
	@ApiModelProperty(value="含税单价",dataType="BigDecimal",example="2.943069",required=true)
	private BigDecimal taxPrice;
	
	@ApiModelProperty(value="含税金额",dataType="BigDecimal",example="9.943069",required=true)
	private BigDecimal taxAmt;
	
	@ApiModelProperty(value="备注",dataType="String",example="123")
	private String remarks;

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

	public BigDecimal getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}

	public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	

}
