package com.armitage.server.api.business.purrequire.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurReqMaterialPriceParamsApi")
public class PurReqMaterialPriceParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurReqMaterialPriceParams params;

	public PurReqMaterialPriceParams getParams() {
		return params;
	}

	public void setParams(PurReqMaterialPriceParams params) {
		this.params = params;
	}
	
	
}
