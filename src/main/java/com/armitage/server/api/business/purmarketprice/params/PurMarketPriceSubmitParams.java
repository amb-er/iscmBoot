package com.armitage.server.api.business.purmarketprice.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurMarketPriceSubmitParams",description="提交市调单参数对象")
public class PurMarketPriceSubmitParams {
	@ApiModelProperty(value="市调单号",dataType="String",example="123",required=true)
	private String piNo;

	public String getPiNo() {
		return piNo;
	}

	public void setPiNo(String piNo) {
		this.piNo = piNo;
	}

}
