package com.armitage.server.api.business.datasync.params;

import java.math.BigDecimal;
import java.util.Date;

import com.armitage.server.api.config.StringDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.swagger.annotations.ApiModelProperty;


public class InvStockListParams {
	
	@JsonDeserialize(using = StringDeserializer.class)
	@ApiModelProperty(value="结存单号",dataType="String",example="MT20200606001",required=true)
	private String invNo;
	
	@JsonDeserialize(using = StringDeserializer.class)
	@ApiModelProperty(value="部门或仓库编码",dataType="String",example="00002125",required=true)
	private String orgUnitNo;
	
	@ApiModelProperty(value="结存日期",dataType="Date",example="2022-03-02 11:32:45",required=true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date invDate;
	
	@JsonDeserialize(using = StringDeserializer.class)
	@ApiModelProperty(value="物资编码",dataType="String",example="0000000",required=true)
	private String itemNo;
	
	@JsonDeserialize(using = StringDeserializer.class)
	@ApiModelProperty(value="批次",dataType="String",example="Y",required=true)
	private String lot;
	
	@ApiModelProperty(value="单价",dataType="BigDecimal",example="0000",required=false)
	private BigDecimal price;
	
	@ApiModelProperty(value="数量",dataType="BigDecimal",example="0000",required=false)
	private BigDecimal qty;
	
	@ApiModelProperty(value="金额",dataType="BigDecimal",example="0000",required=false)
	private BigDecimal amt;
	
	@ApiModelProperty(value="税率",dataType="BigDecimal",example="0000",required=false)
	private BigDecimal taxRate;
	
	@ApiModelProperty(value="含税单价",dataType="BigDecimal",example="0000",required=false)
	private BigDecimal taxPrice;
	
	@ApiModelProperty(value="含税金额",dataType="BigDecimal",example="0000",required=false)
	private BigDecimal taxAmt;
	
	@JsonDeserialize(using = StringDeserializer.class)
	@ApiModelProperty(value="是否删除",dataType="String",example="Y",required=true)
	private String delete;
	

	public String getDelete() {
		return delete;
	}

	public void setDelete(String delete) {
		this.delete = delete;
	}

	public String getInvNo() {
		return invNo;
	}

	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}

	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}

	public Date getInvDate() {
		return invDate;
	}

	public void setInvDate(Date invDate) {
		this.invDate = invDate;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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
	
}
