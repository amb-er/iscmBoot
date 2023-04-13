package com.armitage.server.api.business.invpurinwarehs.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvPurInWarehsParams",description="采购入库单详情查询参数")
public class InvPurInWarehsParams {
	@ApiModelProperty(value="入库单号",dataType="String",example="123",required=false)
	private String wrNo;

	public String getWrNo() {
		return wrNo;
	}

	public void setWrNo(String wrNo) {
		this.wrNo = wrNo;
	}

}
