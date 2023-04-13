package com.armitage.server.api.business.datasync.params;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ScmPurRequireDetailParams",description="请购单明细写入明细")
public class ScmPurRequireDetailParams {
	
	@ApiModelProperty(value="物资编码",dataType="String",example="10004002",required=false)
	private String itemNo;
	@ApiModelProperty(value="申购数量",dataType="String",example="10",required=false)
	private BigDecimal qty;
	@ApiModelProperty(value="含税单价",dataType="String",example="1",required=false)
	private BigDecimal price;
	@ApiModelProperty(value="含税金额",dataType="String",example="10",required=false)
	private BigDecimal amt;
	@ApiModelProperty(value="供应商编号",dataType="String",example="00090",required=false)
	private String vendorNo;
	@ApiModelProperty(value="备注",dataType="String",example="备注",required=false)
	private String remarks; 
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
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	public String getVendorNo() {
		return vendorNo;
	}
	public void setVendorNo(String vendorNo) {
		this.vendorNo = vendorNo;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	
	
	
}


