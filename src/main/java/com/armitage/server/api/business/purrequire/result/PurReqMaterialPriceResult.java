package com.armitage.server.api.business.purrequire.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@ApiModel(value="PurReqMaterialPriceResult",description="返回结果集")
public class PurReqMaterialPriceResult {
	@ApiModelProperty(value="物资编码",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String itemNo;

	@ApiModelProperty(value="价格",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal price;

	@ApiModelProperty(value="税率",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal taxRate;

	@ApiModelProperty(value="含税价",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal taxPrice;

	@ApiModelProperty(value="价格来源Id",dataType="Long")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private long priceBillId;

	@ApiModelProperty(value="供应商名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String vendorName;
	
	@ApiModelProperty(value="最近入库单价",dataType="BigDecimal")
	private BigDecimal recentPrice;
	
	@ApiModelProperty(value="库存量",dataType="BigDecimal")
	private BigDecimal stockQty;
	
	@ApiModelProperty(value="供应商是否可修改（1：可修改0不可修改）",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String vendorEditType;
	
	@ApiModelProperty(value="价格是否可修改（1：可修改0不可修改）",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String priceEditType;
	
	
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

	public long getPriceBillId() {
		return priceBillId;
	}

	public void setPriceBillId(long priceBillId) {
		this.priceBillId = priceBillId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getVendorEditType() {
		return vendorEditType;
	}
	public void setVendorEditType(String vendorEditType) {
		this.vendorEditType = vendorEditType;
	}
	public String getPriceEditType() {
		return priceEditType;
	}
	public void setPriceEditType(String priceEditType) {
		this.priceEditType = priceEditType;
	}
	

}
