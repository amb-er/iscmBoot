package com.armitage.server.api.business.purmarketprice.params;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurMarketPriceDetailParams",description="定价单列表查询参数")
public class PurMarketPriceDetailParams{
	@ApiModelProperty(value="行号",dataType="Integer",example="1",required=true)
	private int lineId;
	
	@ApiModelProperty(value="物资编码",dataType="String",example="123",required=true)
	private String itemNo;
	
	@ApiModelProperty(value="市调价",dataType="BigDecimal",example="123",required=true)
	private BigDecimal price;
	
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
