package com.armitage.server.api.business.invotherissue.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvOtherIssueUnAuditParamsApi")
public class InvOtherIssueUnAuditParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private InvOtherIssueUnAuditParams params;

	public InvOtherIssueUnAuditParams getParams() {
		return params;
	}

	public void setParams(InvOtherIssueUnAuditParams params) {
		this.params = params;
	}
	
}
