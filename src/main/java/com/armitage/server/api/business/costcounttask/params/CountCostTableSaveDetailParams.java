package com.armitage.server.api.business.costcounttask.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;

@ApiModel(value="CountCostTableSaveDetailParams",description="盘存表明细对象")
public class CountCostTableSaveDetailParams {
	@ApiModelProperty(value="行号",dataType="Int",example="0",required=true)
	private int lineId;
	@ApiModelProperty(value="物资编码",dataType="String",example="100103001",required=true)
	private String itemNo;
	@ApiModelProperty(value="实存数量",dataType="BigDecimal",example="10.00",required=true)
	private BigDecimal qty;
	@ApiModelProperty(value="单价",dataType="BigDecimal",example="13.00",required=true)
	private BigDecimal price;
	@ApiModelProperty(value="税率",dataType="BigDecimal",example="0.13",required=true)
	private BigDecimal taxRate;
	@ApiModelProperty(value="含税价",dataType="BigDecimal",example="14.69",required=true)
	private BigDecimal taxPrice;
	@ApiModelProperty(value="辅助数量",dataType="BigDecimal",example="5.00",required=false)
	private BigDecimal pieQty;
	@ApiModelProperty(value="是否以盘",dataType="Boolean",required=false)
	private boolean counted;
	
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
	public BigDecimal getQty() {
		return qty;
	}
	public void setQty(BigDecimal qty) {
		this.qty = qty;
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
	public BigDecimal getPieQty() {
		return pieQty;
	}
	public void setPieQty(BigDecimal pieQty) {
		this.pieQty = pieQty;
	}
	public boolean isCounted() {
		return counted;
	}
	public void setCounted(boolean counted) {
		this.counted = counted;
	}
	
}
