package com.armitage.server.api.business.costconsume.result;

import com.armitage.server.api.common.ResultApi;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "CostConsumeAddResultApi", description = "返回结果集")
public class CostConsumeAddResultApi extends ResultApi {
	@ApiModelProperty(value = "耗用单号", dataType = "String")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String dcNo;

	public String getDcNo() {
		return dcNo;
	}

	public void setDcNo(String dcNo) {
		this.dcNo = dcNo;
	}
	
}
