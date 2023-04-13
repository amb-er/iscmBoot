package com.armitage.server.api.business.invmaterialreqbill.params;

import io.swagger.annotations.ApiModelProperty;

public class InvMaterialReqBillPersonParams {
	@ApiModelProperty(value="领料人名称",dataType="String",example="123",required=false)
	private String requestPersonName;

	public String getRequestPersonName() {
		return requestPersonName;
	}

	public void setRequestPersonName(String requestPersonName) {
		this.requestPersonName = requestPersonName;
	}

}
