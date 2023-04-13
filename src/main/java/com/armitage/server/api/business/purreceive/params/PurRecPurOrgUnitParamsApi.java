package com.armitage.server.api.business.purreceive.params;

import com.armitage.server.api.common.RequestParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurRecPurOrgUnitParamsApi")
public class PurRecPurOrgUnitParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurRecPurOrgUnitParams params;

	public PurRecPurOrgUnitParams getParams() {
		return params;
	}
	public void setParams(PurRecPurOrgUnitParams params) {
		this.params = params;
	}
}
