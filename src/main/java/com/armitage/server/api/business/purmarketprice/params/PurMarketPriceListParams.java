package com.armitage.server.api.business.purmarketprice.params;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurMarketPriceListParams",description="市调单列表查询参数")
public class PurMarketPriceListParams {
	@ApiModelProperty(value="市调单号",dataType="String",example="123",required=false)
	private String piNo;

	@ApiModelProperty(value="市调员编号",dataType="String",example="001",required=false)
	private String enquiryCode;
    
	@ApiModelProperty(value="市调开始日期",dataType="Date",example="2019-05-22 00:00:00",required=false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date bizDateFrom;
    
	@ApiModelProperty(value="市调结束日期",dataType="Date",example="2019-05-22 00:00:00",required=false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date bizDateTo;
	
	@ApiModelProperty(value="单据状态",dataType="String",example="I：新建，D：待审，P：审核中，A：通过，B：部分通过，N：未通过，S：部分下达，E：下达，F：部分关闭，C：关闭，多个状态如：I,D,A",required=false)
	private String status;

	public String getPiNo() {
		return piNo;
	}
	public void setPiNo(String piNo) {
		this.piNo = piNo;
	}
	public String getEnquiryCode() {
		return enquiryCode;
	}
	public void setEnquiryCode(String enquiryCode) {
		this.enquiryCode = enquiryCode;
	}
	public Date getBizDateFrom() {
		return bizDateFrom;
	}
	public void setBizDateFrom(Date bizDateFrom) {
		this.bizDateFrom = bizDateFrom;
	}
	public Date getBizDateTo() {
		return bizDateTo;
	}
	public void setBizDateTo(Date bizDateTo) {
		this.bizDateTo = bizDateTo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
