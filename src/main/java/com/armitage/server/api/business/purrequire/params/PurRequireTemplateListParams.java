package com.armitage.server.api.business.purrequire.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurRequireTemplateListParams",description="查询模板列表参数对象")
public class PurRequireTemplateListParams {
	@ApiModelProperty(value="混合查询",dataType="String",example="0001",required=false)
    private String mixParam;

	public String getMixParam() {
		return mixParam;
	}

	public void setMixParam(String mixParam) {
		this.mixParam = mixParam;
	}

}
