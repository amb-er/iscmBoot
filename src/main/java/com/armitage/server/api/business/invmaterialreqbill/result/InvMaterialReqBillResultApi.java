package com.armitage.server.api.business.invmaterialreqbill.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="InvMaterialReqBillResultApi",description="返回结果集")
public class InvMaterialReqBillResultApi extends ResultApi{
	private InvMaterialReqBillResult result;

	public InvMaterialReqBillResult getResult() {
		return result;
	}

	public void setResult(InvMaterialReqBillResult result) {
		this.result = result;
	}
	
}
