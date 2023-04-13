package com.armitage.server.api.business.invmaterialreq.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqParams",description="领料详情查询参数")
public class InvMaterialReqParams {
	@ApiModelProperty(value="领料单号",dataType="String",example="123",required=false)
	private String reqNo;

	public String getReqNo() {
		return reqNo;
	}

	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}

}
