package com.armitage.server.api.business.purrequire.params;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurRequireDetailParams",description="明细表参数对象")
public class PurRequireDetailParams {
	@ApiModelProperty(value="行号",dataType="Integer",example="1",required=true)
	private int lineId;
	
	@ApiModelProperty(value="物资编码",dataType="String",example="123",required=true)
	private String itemNo;
	
	@ApiModelProperty(value="申购数量",dataType="BigDecimal",example="10",required=true)
	private BigDecimal qty;
	
	@ApiModelProperty(value="辅助数量",dataType="BigDecimal",example="10",required=false)
	private BigDecimal pieQty;

	@ApiModelProperty(value="单价",dataType="BigDecimal",example="123",required=true)
	private BigDecimal price;
	
	@ApiModelProperty(value="金额",dataType="BigDecimal",example="123",required=true)
	private BigDecimal amt;
	
	@ApiModelProperty(value="建议订货日期",dataType="Date",example="2019-05-22 00:00:00",required=false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date orderDate;
	
	@ApiModelProperty(value="价格来源Id",dataType="Long",example="",required=true)
	private long priceBillId;

	@ApiModelProperty(value="备注",dataType="String",example="备注信息",required=true)
	private String remarks;
	
	@ApiModelProperty(value="最近入库单价",dataType="BigDecimal")
	private BigDecimal recentPrice;
	
	@ApiModelProperty(value="库存量",dataType="BigDecimal")
	private BigDecimal stockQty;
	
	@ApiModelProperty(value="供应商编码",dataType="String",example="001",required=true)
	private String vendorCode;
	
	public BigDecimal getRecentPrice() {
		return recentPrice;
	}
	public void setRecentPrice(BigDecimal recentPrice) {
		this.recentPrice = recentPrice;
	}
	public BigDecimal getStockQty() {
		return stockQty;
	}
	public void setStockQty(BigDecimal stockQty) {
		this.stockQty = stockQty;
	}

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

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public long getPriceBillId() {
		return priceBillId;
	}
	public void setPriceBillId(long priceBillId) {
		this.priceBillId = priceBillId;
	}
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	
}
