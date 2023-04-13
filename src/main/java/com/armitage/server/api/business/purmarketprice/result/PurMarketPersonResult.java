package com.armitage.server.api.business.purmarketprice.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@ApiModel(value="PurMarketPersonResult",description="市调员查询返回结果")
public class PurMarketPersonResult {

	@ApiModelProperty(value="市调员编号",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String enquiryCode;
	
	@ApiModelProperty(value="市调员名称",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String enquiryName;

	public String getEnquiryCode() {
		return enquiryCode;
	}

	public void setEnquiryCode(String enquiryCode) {
		this.enquiryCode = enquiryCode;
	}

	public String getEnquiryName() {
		return enquiryName;
	}

	public void setEnquiryName(String enquiryName) {
		this.enquiryName = enquiryName;
	}
	
}
