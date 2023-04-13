package com.armitage.server.api.business.purprice.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurPriceParams",description="定价单详情查询参数")
public class PurPriceParams {
	@ApiModelProperty(value="定价单号",dataType="String",example="123",required=false)
	private String pmNo;

	public String getPmNo() {
		return pmNo;
	}

	public void setPmNo(String pmNo) {
		this.pmNo = pmNo;
	}

}
