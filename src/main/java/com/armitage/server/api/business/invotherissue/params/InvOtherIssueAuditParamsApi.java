package com.armitage.server.api.business.invotherissue.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvOtherIssueAuditParamsApi")
public class InvOtherIssueAuditParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private InvOtherIssueAuditParams params;

	public InvOtherIssueAuditParams getParams() {
		return params;
	}

	public void setParams(InvOtherIssueAuditParams params) {
		this.params = params;
	}

}
