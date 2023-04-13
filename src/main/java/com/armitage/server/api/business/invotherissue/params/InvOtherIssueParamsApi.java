package com.armitage.server.api.business.invotherissue.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvOtherIssueParamsApi")
public class InvOtherIssueParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private InvOtherIssueParams params;

	public InvOtherIssueParams getParams() {
		return params;
	}

	public void setParams(InvOtherIssueParams params) {
		this.params = params;
	}

}
