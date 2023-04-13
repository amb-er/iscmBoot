package com.armitage.server.api.business.invmaterialreq.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqUnReleaseParams",description="取消下达参数对象")
public class InvMaterialReqUnReleaseParams {
	@ApiModelProperty(value="领料申请单号",dataType="String",example="123",required=true)
	private String reqNo;

	public String getReqNo() {
		return reqNo;
	}

	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}
}
