package com.armitage.server.api.business.invSaleIssueBill.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;

@ApiModel(value="InvSaleIssueBillDetailParams",description="明细表参数对象")
public class InvSaleIssueBillDetailParams {
	@ApiModelProperty(value="行号",dataType="Integer",example="1",required=true)
	private int lineId;
	
	@ApiModelProperty(value="物资编码",dataType="String",example="123",required=true)
	private String itemNo;
	
	@ApiModelProperty(value="批次",dataType="String",example="3",required=false)
	private String lot;
	
	@ApiModelProperty(value="数量",dataType="BigDecimal",example="10",required=true)
	private BigDecimal qty;
	
	@ApiModelProperty(value="销售含税价",dataType="BigDecimal",example="10",required=false)
	private BigDecimal saleTaxPrice;
	
	@ApiModelProperty(value="仓库",dataType="String",example="00001",required=true)
	private String wareHouseNo;
	
	@ApiModelProperty(value="备注",dataType="String",example="备注信息",required=false)
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

	public BigDecimal getSaleTaxPrice() {
		return saleTaxPrice;
	}

	public void setSaleTaxPrice(BigDecimal saleTaxPrice) {
		this.saleTaxPrice = saleTaxPrice;
	}

	public String getWareHouseNo() {
		return wareHouseNo;
	}

	public void setWareHouseNo(String wareHouseNo) {
		this.wareHouseNo = wareHouseNo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}

