package com.armitage.server.api.business.purmarketprice.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurMarketPriceParams",description="市调单详情查询参数")
public class PurMarketPriceParams {
	@ApiModelProperty(value="市调单号",dataType="String",example="123",required=false)
	private String piNo;

	public String getPiNo() {
		return piNo;
	}

	public void setPiNo(String piNo) {
		this.piNo = piNo;
	}
}
