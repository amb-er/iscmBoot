package com.armitage.server.api.business.cstfrmloss.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="CstFrmLossParamsApi")
public class CstFrmLossParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private CstFrmLossParams params;

	public CstFrmLossParams getParams() {
		return params;
	}

	public void setParams(CstFrmLossParams params) {
		this.params = params;
	}

}
