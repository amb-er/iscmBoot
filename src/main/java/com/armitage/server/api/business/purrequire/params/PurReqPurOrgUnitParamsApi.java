package com.armitage.server.api.business.purrequire.params;

import com.armitage.server.api.common.RequestParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurReqPurOrgUnitParamsApi")
public class PurReqPurOrgUnitParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private PurReqPurOrgUnitParams params;

	public PurReqPurOrgUnitParams getParams() {
		return params;
	}
	public void setParams(PurReqPurOrgUnitParams params) {
		this.params = params;
	}
}
