package com.armitage.server.api.business.purorder.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurOrderParams",description="订货单详情查询参数")
public class PurOrderParams {
	@ApiModelProperty(value="订货单号",dataType="String",example="123",required=false)
	private String poNo;

	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

}
