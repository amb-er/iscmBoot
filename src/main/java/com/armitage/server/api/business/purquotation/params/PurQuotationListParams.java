package com.armitage.server.api.business.purquotation.params;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurQuotationListParams",description="获取列表参数对象")
public class PurQuotationListParams {
	@ApiModelProperty(value="报价单号",dataType="String",example="123",required=false)
	private String pqNo;

	@ApiModelProperty(value="供应商编号",dataType="String",example="123",required=false)
	private String vendorCode;
	
	@ApiModelProperty(value="采购员编号",dataType="String",example="123",required=false)
	private String buyerCode;
	
	@ApiModelProperty(value="报价开始日期",dataType="Date",example="2019-05-22 00:00:00",required=true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date pqDateFrom;
	
	@ApiModelProperty(value="报价结束日期",dataType="Date",example="2019-05-22 00:00:00",required=true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date pqDateTo;

	public String getPqNo() {
		return pqNo;
	}

	public void setPqNo(String pqNo) {
		this.pqNo = pqNo;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getBuyerCode() {
		return buyerCode;
	}

	public void setBuyerCode(String buyerCode) {
		this.buyerCode = buyerCode;
	}

	public Date getPqDateFrom() {
		return pqDateFrom;
	}

	public void setPqDateFrom(Date pqDateFrom) {
		this.pqDateFrom = pqDateFrom;
	}

	public Date getPqDateTo() {
		return pqDateTo;
	}

	public void setPqDateTo(Date pqDateTo) {
		this.pqDateTo = pqDateTo;
	}
}
