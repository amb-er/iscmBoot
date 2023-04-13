package com.armitage.server.api.business.security.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="OrgAdminListParams",description="查询列表参数对象")
public class OrgAdminListParams {
	@ApiModelProperty(value="混合查询条件",dataType="String",example="123",required=false)
	private String mixParam;

	public String getMixParam() {
		return mixParam;
	}

	public void setMixParam(String mixParam) {
		this.mixParam = mixParam;
	}
	
	
}
