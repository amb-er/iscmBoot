package com.armitage.server.api.business.purmarketprice.result;

import com.armitage.server.api.common.ResultApi;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurMarketPriceEditResultApi",description="修改返回结果")
public class PurMarketPriceEditResultApi extends ResultApi {
	@ApiModelProperty(value="市调单号",dataType="String")
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private String piNo;

	public String getPiNo() {
		return piNo;
	}

	public void setPiNo(String piNo) {
		this.piNo = piNo;
	}

}