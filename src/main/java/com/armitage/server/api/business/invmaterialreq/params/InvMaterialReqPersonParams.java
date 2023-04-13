package com.armitage.server.api.business.invmaterialreq.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqPersonParams",description="申请人查询参数")
public class InvMaterialReqPersonParams {
	@ApiModelProperty(value="申请人名称",dataType="String",example="张三",required=false)
	private String requestPersonName;

	public String getRequestPersonName() {
		return requestPersonName;
	}

	public void setRequestPersonName(String requestPersonName) {
		this.requestPersonName = requestPersonName;
	}

}
