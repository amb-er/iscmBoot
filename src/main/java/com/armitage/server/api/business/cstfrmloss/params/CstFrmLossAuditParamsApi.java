package com.armitage.server.api.business.cstfrmloss.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="CstFrmLossAuditParamsApi")
public class CstFrmLossAuditParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private CstFrmLossAuditParams params;

	public CstFrmLossAuditParams getParams() {
		return params;
	}

	public void setParams(CstFrmLossAuditParams params) {
		this.params = params;
	}

}
