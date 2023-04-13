package com.armitage.server.api.business.purquotation.result;

import com.armitage.server.api.common.ResultApi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurQuotationResultApi",description="返回结果集")
public class PurQuotationResultApi extends ResultApi {
	@ApiModelProperty(value="查询返回结果集",dataType="List")
    private PurQuotationResult result;

	public PurQuotationResult getResult() {
		return result;
	}

	public void setResult(PurQuotationResult result) {
		this.result = result;
	}
	
}
