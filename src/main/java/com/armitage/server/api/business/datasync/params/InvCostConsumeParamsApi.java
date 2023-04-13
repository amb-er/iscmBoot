package com.armitage.server.api.business.datasync.params;

import java.util.List;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModelProperty;

public class InvCostConsumeParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	public List<InvCostConsumeListParams> params;

	public List<InvCostConsumeListParams> getParams() {
		return params;
	}

	public void setParams(List<InvCostConsumeListParams> params) {
		this.params = params;
	}

}
