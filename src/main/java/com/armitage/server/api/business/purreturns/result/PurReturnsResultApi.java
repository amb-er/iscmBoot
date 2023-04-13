package com.armitage.server.api.business.purreturns.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="PurReturnsResultApi",description="返回结果集")
public class PurReturnsResultApi extends ResultApi {
	private PurReturnsResult result;

	public PurReturnsResult getResult() {
		return result;
	}

	public void setResult(PurReturnsResult result) {
		this.result = result;
	}
	
}
