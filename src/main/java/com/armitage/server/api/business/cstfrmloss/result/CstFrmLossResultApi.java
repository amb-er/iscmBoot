package com.armitage.server.api.business.cstfrmloss.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="CstFrmLossResultApi",description="返回结果集")
public class CstFrmLossResultApi extends ResultApi {
	private CstFrmLossResult result;

	public CstFrmLossResult getResult() {
		return result;
	}

	public void setResult(CstFrmLossResult result) {
		this.result = result;
	}
	
}
