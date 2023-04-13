package com.armitage.server.api.business.purrequire.result;

import com.armitage.server.api.common.ResultApi;
import io.swagger.annotations.ApiModel;

@ApiModel(value="PurReqMaterialPriceResultApi",description="返回结果集")
public class PurReqMaterialPriceResultApi extends ResultApi {
	private PurReqMaterialPriceResult result;

	public PurReqMaterialPriceResult getResult() {
		return result;
	}

	public void setResult(PurReqMaterialPriceResult result) {
		this.result = result;
	}
	
}
