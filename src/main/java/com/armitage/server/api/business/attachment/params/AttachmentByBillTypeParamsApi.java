package com.armitage.server.api.business.attachment.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="AttachmentByBillTypeParamsApi")
public class AttachmentByBillTypeParamsApi extends RequestParams {
	
	@ApiModelProperty(required = true)
	private AttachmentByBillTypeParams params;

	public AttachmentByBillTypeParams getParams() {
		return params;
	}

	public void setParams(AttachmentByBillTypeParams params) {
		this.params = params;
	}
}
