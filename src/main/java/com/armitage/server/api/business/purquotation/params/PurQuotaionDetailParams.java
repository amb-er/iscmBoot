package com.armitage.server.api.business.purquotation.params;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurQuotaionDetailParams",description="新增参数对象")
public class PurQuotaionDetailParams {
	@ApiModelProperty(value="行号",dataType="Int",example="123",required=true)
	private int lineId;
	
	@ApiModelProperty(value="物资编码",dataType="String",example="123",required=true)
	private String itemNo;
	
	@ApiModelProperty(value="计量单位",dataType="String",example="123",required=true)
	private String purUnit;
	
	@ApiModelProperty(value="本期报价",dataType="BigDecimal",example="123",required=true)
	private BigDecimal price;
	
	@ApiModelProperty(value="税率",dataType="BigDecimal",example="123",required=true)
	private BigDecimal taxRate;
	
	@ApiModelProperty(value="本期含税报价",dataType="BigDecimal",example="123",required=true)
	private BigDecimal taxPrice;
	
	@ApiModelProperty(value="备注",dataType="String",example="123",required=true)
	private String remarks;

	public int getLineId() {
		return lineId;
	}

	public void setLineId(int lineId) {
		this.lineId = lineId;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getPurUnit() {
		return purUnit;
	}

	public void setPurUnit(String purUnit) {
		this.purUnit = purUnit;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
