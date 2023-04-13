package com.armitage.server.api.business.purreceive.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurReceiveGenerateWrReceiptsParamsApi")
public class PurReceiveGenerateWrReceiptsParamsApi extends RequestParams {
	
	@ApiModelProperty(required = true)
	private PurReceiveGenerateWrReceiptsParams params;

	public PurReceiveGenerateWrReceiptsParams getParams() {
		return params;
	}

	public void setParams(PurReceiveGenerateWrReceiptsParams params) {
		this.params = params;
	}
	
}
