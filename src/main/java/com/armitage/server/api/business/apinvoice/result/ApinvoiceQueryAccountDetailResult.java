package com.armitage.server.api.business.apinvoice.result;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ApinvoiceQueryAccountDetailResult",description="返回结果集resultList")
public class ApinvoiceQueryAccountDetailResult {
	
	@ApiModelProperty(value="供应商编号",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String vendorNo;
	
	@ApiModelProperty(value="供应商名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String vendorName;
	
	@ApiModelProperty(value="单据日期",dataType="Date",example="yyyy-MM-dd 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Date billDate;
	
	@ApiModelProperty(value="单据编号",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String billNo;
	
	@ApiModelProperty(value="单据类型",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String billType;
	
	@ApiModelProperty(value="摘要",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String comment;
	
	@ApiModelProperty(value="应付金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal amt;
	
	@ApiModelProperty(value="实付金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal actualAmt;
	
	@ApiModelProperty(value="折扣金额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal discountAmt;
	
	@ApiModelProperty(value="余额",dataType="BigDecimal")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private BigDecimal balance;

	public String getVendorNo() {
		return vendorNo;
	}

	public void setVendorNo(String vendorNo) {
		this.vendorNo = vendorNo;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public BigDecimal getActualAmt() {
		return actualAmt;
	}

	public void setActualAmt(BigDecimal actualAmt) {
		this.actualAmt = actualAmt;
	}

	public BigDecimal getDiscountAmt() {
		return discountAmt;
	}

	public void setDiscountAmt(BigDecimal discountAmt) {
		this.discountAmt = discountAmt;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}
