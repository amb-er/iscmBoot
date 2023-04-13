package com.armitage.server.api.business.basedata.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="QualifieBillUnAuditParamsApi")
public class QualifieBillUnAuditParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private QualifieBillUnAuditParams params;

	public QualifieBillUnAuditParams getParams() {
		return params;
	}

	public void setParams(QualifieBillUnAuditParams params) {
		this.params = params;
	}
	
}
