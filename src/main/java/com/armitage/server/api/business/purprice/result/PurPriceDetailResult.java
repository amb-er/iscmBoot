package com.armitage.server.api.business.purprice.result;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurPriceDetailResult",description="返回结果集resultList")
public class PurPriceDetailResult {
	@ApiModelProperty(value="行号",dataType="Int")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private int lineId;

	@ApiModelProperty(value="物资编码",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String itemNo;

	@ApiModelProperty(value="物资名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String itemName;

	@ApiModelProperty(value="物资规格",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String spec;

	@ApiModelProperty(value="计量单位",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String purUnit;

	@ApiModelProperty(value="询价1",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal inquiryPrice1;
	
	@ApiModelProperty(value="询价2",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal inquiryPrice2;
	
	@ApiModelProperty(value="询价3",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal inquiryPrice3;

	@ApiModelProperty(value="报价1",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal price1;
	
	@ApiModelProperty(value="报价2",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal price2;
	
	@ApiModelProperty(value="报价3",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal price3;

	@ApiModelProperty(value="上期价",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal prePrice;
	
	@ApiModelProperty(value="本期含税定价",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal price;
	
	@ApiModelProperty(value="税率",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String taxRateStr;

	@ApiModelProperty(value="单据状态",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String rowStatus;

	@ApiModelProperty(value="单据状态名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String rowStatusName;
	
	@ApiModelProperty(value="审核人",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String checker;

	@ApiModelProperty(value="审核人名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String checkerName;

	@ApiModelProperty(value="审核日期",dataType="Date",example="yyyy-MM-dd 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Date checkDate;

	@ApiModelProperty(value="备注",dataType="String",example="备注信息",required=true)
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
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
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getPurUnit() {
		return purUnit;
	}
	public void setPurUnit(String purUnit) {
		this.purUnit = purUnit;
	}
	public BigDecimal getInquiryPrice1() {
		return inquiryPrice1;
	}
	public void setInquiryPrice1(BigDecimal inquiryPrice1) {
		this.inquiryPrice1 = inquiryPrice1;
	}
	public BigDecimal getInquiryPrice2() {
		return inquiryPrice2;
	}
	public void setInquiryPrice2(BigDecimal inquiryPrice2) {
		this.inquiryPrice2 = inquiryPrice2;
	}
	public BigDecimal getInquiryPrice3() {
		return inquiryPrice3;
	}
	public void setInquiryPrice3(BigDecimal inquiryPrice3) {
		this.inquiryPrice3 = inquiryPrice3;
	}
	public BigDecimal getPrice1() {
		return price1;
	}
	public void setPrice1(BigDecimal price1) {
		this.price1 = price1;
	}
	public BigDecimal getPrice2() {
		return price2;
	}
	public void setPrice2(BigDecimal price2) {
		this.price2 = price2;
	}
	public BigDecimal getPrice3() {
		return price3;
	}
	public void setPrice3(BigDecimal price3) {
		this.price3 = price3;
	}
	public BigDecimal getPrePrice() {
		return prePrice;
	}
	public void setPrePrice(BigDecimal prePrice) {
		this.prePrice = prePrice;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getTaxRateStr() {
		return taxRateStr;
	}
	public void setTaxRateStr(String taxRateStr) {
		this.taxRateStr = taxRateStr;
	}
	public String getRowStatus() {
		return rowStatus;
	}
	public void setRowStatus(String rowStatus) {
		this.rowStatus = rowStatus;
	}
	public String getRowStatusName() {
		return rowStatusName;
	}
	public void setRowStatusName(String rowStatusName) {
		this.rowStatusName = rowStatusName;
	}
	public String getChecker() {
		return checker;
	}
	public void setChecker(String checker) {
		this.checker = checker;
	}
	public String getCheckerName() {
		return checkerName;
	}
	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
