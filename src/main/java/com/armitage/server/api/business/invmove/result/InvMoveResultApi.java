package com.armitage.server.api.business.invmove.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="InvMoveResultApi",description="返回结果集")
public class InvMoveResultApi extends ResultApi {
	private InvMoveResult result;

	public InvMoveResult getResult() {
		return result;
	}

	public void setResult(InvMoveResult result) {
		this.result = result;
	}
	
}
