package com.armitage.server.api.business.invmaterialreq.result;

import com.armitage.server.api.common.ResultApi;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "InvMaterialReqAddResultApi", description = "返回结果集")
public class InvMaterialReqAddResultApi extends ResultApi {
	@ApiModelProperty(value = "申请单号", dataType = "String")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String reqNo;

	public String getReqNo() {
		return reqNo;
	}

	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}
}
