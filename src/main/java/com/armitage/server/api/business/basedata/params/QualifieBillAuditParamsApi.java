package com.armitage.server.api.business.basedata.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="QualifieBillAuditParamsApi")
public class QualifieBillAuditParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private QualifieBillAuditParams params;
	public QualifieBillAuditParams getParams() {
		return params;
	}

	public void setParams(QualifieBillAuditParams params) {
		this.params = params;
	}
	
}
