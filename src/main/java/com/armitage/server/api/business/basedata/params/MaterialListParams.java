package com.armitage.server.api.business.basedata.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="MaterialListParams",description="查询列表参数对象")
public class MaterialListParams {
	@ApiModelProperty(value="混合查询条件",dataType="String",example="123",required=false)
	private String mixParam;

	public String getMixParam() {
		return mixParam;
	}

	public void setMixParam(String mixParam) {
		this.mixParam = mixParam;
	}
	
	
}
