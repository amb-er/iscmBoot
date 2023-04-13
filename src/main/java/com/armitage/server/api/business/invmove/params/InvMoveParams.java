package com.armitage.server.api.business.invmove.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMoveParams",description="成本转移单详情查询参数")
public class InvMoveParams {
	@ApiModelProperty(value="移库单号",dataType="String",example="123",required=false)
	private String wtNo;

	public String getWtNo() {
		return wtNo;
	}

	public void setWtNo(String wtNo) {
		this.wtNo = wtNo;
	}

}
