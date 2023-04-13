package com.armitage.server.api.business.basedata.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="QualifieBillParamsApi")
public class QualifieBillParamsApi extends RequestParams {
	@ApiModelProperty(required = true)
	private QualifieBillParams params;

	public QualifieBillParams getParams() {
		return params;
	}

	public void setParams(QualifieBillParams params) {
		this.params = params;
	}

}
