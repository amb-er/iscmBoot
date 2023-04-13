package com.armitage.server.api.business.datasync.params;

import java.util.List;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModelProperty;

public class InvOtherIssueBillListParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private List<InvOtherIssueBillListParams> params;

	public List<InvOtherIssueBillListParams> getParams() {
		return params;
	}

	public void setParams(List<InvOtherIssueBillListParams> params) {
		this.params = params;
	}

}
