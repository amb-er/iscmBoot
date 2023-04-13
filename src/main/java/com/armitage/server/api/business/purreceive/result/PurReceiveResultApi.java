package com.armitage.server.api.business.purreceive.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="PurReceiveResultApi",description="返回结果集")
public class PurReceiveResultApi extends ResultApi {
	private PurReceiveResult result;

	public PurReceiveResult getResult() {
		return result;
	}

	public void setResult(PurReceiveResult result) {
		this.result = result;
	}
	
}
