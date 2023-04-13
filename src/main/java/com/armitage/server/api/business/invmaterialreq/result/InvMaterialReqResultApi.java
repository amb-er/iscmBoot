package com.armitage.server.api.business.invmaterialreq.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="InvMaterialReqResultApi")
public class InvMaterialReqResultApi extends ResultApi {
	private InvMaterialReqResult result;

	public InvMaterialReqResult getResult() {
		return result;
	}

	public void setResult(InvMaterialReqResult result) {
		this.result = result;
	}
}
