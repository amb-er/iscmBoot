package com.armitage.server.api.business.attachment.result;


import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;

@ApiModel(value="AttachmentResultApi",description="返回结果集")
public class AttachmentResultApi extends ResultApi {

	private AttachmentResult result;

	public AttachmentResult getResult() {
		return result;
	}

	public void setResult(AttachmentResult result) {
		this.result = result;
	}
	
}
