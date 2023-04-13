package com.armitage.server.api.business.purquotation.result;

import com.armitage.server.api.common.ResultApi;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "PurQuotationDelResultApi", description = "删除返回结果")
public class PurQuotationDelResultApi extends ResultApi {
	@ApiModelProperty(value = "报价单号", dataType = "String")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String pqNo;

	public String getPqNo() {
		return pqNo;
	}

	public void setPqNo(String pqNo) {
		this.pqNo = pqNo;
	}
}
