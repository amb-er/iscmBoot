package com.armitage.server.api.business.invsaleorder.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvSaleOrderParams",description="销售订单详情查询参数")
public class InvSaleOrderParams {
	@ApiModelProperty(value="订单号",dataType="String",example="123",required=false)
	private String otNo;

	public String getOtNo() {
		return otNo;
	}

	public void setOtNo(String otNo) {
		this.otNo = otNo;
	}

}
