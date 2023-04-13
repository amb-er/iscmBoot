package com.armitage.server.api.business.purrequire.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurRequireTemplateParamsApi")
public class PurRequireTemplateParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurRequireTemplateParams params;

	public PurRequireTemplateParams getParams() {
		return params;
	}

	public void setParams(PurRequireTemplateParams params) {
		this.params = params;
	}
	
	
}
