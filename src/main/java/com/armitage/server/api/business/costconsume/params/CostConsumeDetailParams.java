package com.armitage.server.api.business.costconsume.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;

@ApiModel(value="CostConsumeDetailParams",description="明细表参数对象")
public class CostConsumeDetailParams {
	@ApiModelProperty(value="行号",dataType="Integer",example="1",required=true)
	private int lineId;
	
	@ApiModelProperty(value="物资编码",dataType="String",example="123",required=true)
	private String itemNo;
	
	@ApiModelProperty(value="耗用数量",dataType="BigDecimal",example="10",required=true)
	private BigDecimal qty;
	
	@ApiModelProperty(value="辅助数量",dataType="BigDecimal",example="3",required=false)
	private BigDecimal pieQty;
	
	@ApiModelProperty(value="备注",dataType="String",example="备注信息",required=true)
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

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getPieQty() {
		return pieQty;
	}

	public void setPieQty(BigDecimal pieQty) {
		this.pieQty = pieQty;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
