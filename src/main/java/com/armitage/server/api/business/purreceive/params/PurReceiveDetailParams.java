package com.armitage.server.api.business.purreceive.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(value="PurReceiveDetailParams",description="明细表参数对象")
public class PurReceiveDetailParams {
	@ApiModelProperty(value="行号",dataType="Integer",example="1",required=false)
	private int lineId;
	
	@ApiModelProperty(value="物资编码",dataType="String",example="123",required=true)
	private String itemNo;
	
	@ApiModelProperty(value="本次数量",dataType="BigDecimal",example="10",required=true)
	private BigDecimal qty;
	
	@ApiModelProperty(value="配送数量",dataType="BigDecimal",example="10",required=true)
	private BigDecimal deliveryQty;
	
	@ApiModelProperty(value="不合格数量",dataType="BigDecimal",example="10",required=true)
	private BigDecimal unqualifiedQty;
	
	@ApiModelProperty(value="让步接收率",dataType="BigDecimal",example="10",required=true)
	private BigDecimal concessiveRecRate;
	
	@ApiModelProperty(value="让步接收数量",dataType="BigDecimal",example="10",required=true)
	private BigDecimal concessiveRecQty;

	@ApiModelProperty(value="单价",dataType="BigDecimal",example="13.00",required=true)
	private BigDecimal price;
	
	@ApiModelProperty(value="金额",dataType="BigDecimal",example="130.00",required=true)
	private BigDecimal amt;
	
	@ApiModelProperty(value="税率",dataType="BigDecimal",example="0.13",required=true)
	private BigDecimal taxRate;

	@ApiModelProperty(value="含税价",dataType="BigDecimal",example="14.69",required=true)
	private BigDecimal taxPrice;
	
	@ApiModelProperty(value="含税金额",dataType="BigDecimal",example="146.90",required=true)
	private BigDecimal taxAmt;
	
	@ApiModelProperty(value="辅助数量",dataType="BigDecimal",example="5",required=true)
	private BigDecimal pieQty;

	@ApiModelProperty(value="收货部门",dataType="String",example="00000540",required=false)
	private String useOrgUnitNo;
	
	@ApiModelProperty(value="收货仓库",dataType="String",example="004",required=false)
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

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getDeliveryQty() {
		return deliveryQty;
	}

	public void setDeliveryQty(BigDecimal deliveryQty) {
		this.deliveryQty = deliveryQty;
	}

	public BigDecimal getUnqualifiedQty() {
		return unqualifiedQty;
	}

	public void setUnqualifiedQty(BigDecimal unqualifiedQty) {
		this.unqualifiedQty = unqualifiedQty;
	}

	public BigDecimal getConcessiveRecRate() {
		return concessiveRecRate;
	}

	public void setConcessiveRecRate(BigDecimal concessiveRecRate) {
		this.concessiveRecRate = concessiveRecRate;
	}

	public BigDecimal getConcessiveRecQty() {
		return concessiveRecQty;
	}

	public void setConcessiveRecQty(BigDecimal concessiveRecQty) {
		this.concessiveRecQty = concessiveRecQty;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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

	public BigDecimal getPieQty() {
		return pieQty;
	}

	public void setPieQty(BigDecimal pieQty) {
		this.pieQty = pieQty;
	}

	public String getUseOrgUnitNo() {
		return useOrgUnitNo;
	}

	public void setUseOrgUnitNo(String useOrgUnitNo) {
		this.useOrgUnitNo = useOrgUnitNo;
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
