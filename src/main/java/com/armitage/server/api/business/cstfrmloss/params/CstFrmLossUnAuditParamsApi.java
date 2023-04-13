package com.armitage.server.api.business.cstfrmloss.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="CstFrmLossUnAuditParamsApi")
public class CstFrmLossUnAuditParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private CstFrmLossUnAuditParams params;

	public CstFrmLossUnAuditParams getParams() {
		return params;
	}

	public void setParams(CstFrmLossUnAuditParams params) {
		this.params = params;
	}
	
}
