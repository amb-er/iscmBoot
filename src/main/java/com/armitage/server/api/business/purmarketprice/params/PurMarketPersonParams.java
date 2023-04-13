package com.armitage.server.api.business.purmarketprice.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurMarketPersonParams",description="市调员查询参数")
public class PurMarketPersonParams {
    @ApiModelProperty(value="市调员名称",dataType="String",example="张三",required=false)
	private String enquiryName;

	public String getEnquiryName() {
		return enquiryName;
	}

	public void setEnquiryName(String enquiryName) {
		this.enquiryName = enquiryName;
	}

}
