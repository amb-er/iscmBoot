package com.armitage.server.api.business.purorder.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="PurOrderResultApi",description="返回结果集")
public class PurOrderResultApi extends ResultApi {
	private PurOrderResult result;

	public PurOrderResult getResult() {
		return result;
	}

	public void setResult(PurOrderResult result) {
		this.result = result;
	}
	
}
