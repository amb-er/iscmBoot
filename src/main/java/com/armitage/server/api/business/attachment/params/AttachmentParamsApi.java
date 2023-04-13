package com.armitage.server.api.business.attachment.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="AttachmentParamsApi")
public class AttachmentParamsApi extends RequestParams {

	@ApiModelProperty(required = true)
	private AttachmentParams params;

	public AttachmentParams getParams() {
		return params;
	}

	public void setParams(AttachmentParams params) {
		this.params = params;
	}
	
}
