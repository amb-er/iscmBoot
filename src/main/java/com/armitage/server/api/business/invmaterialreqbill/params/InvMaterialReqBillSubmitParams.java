package com.armitage.server.api.business.invmaterialreqbill.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvMaterialReqBillSubmitParams",description="参数对象")
public class InvMaterialReqBillSubmitParams {

	@ApiModelProperty(value="领料出库单号",dataType="String",example="123",required=true)
	private String reqBillNo;

	public String getReqBillNo() {
		return reqBillNo;
	}

	public void setReqBillNo(String reqBillNo) {
		this.reqBillNo = reqBillNo;
	}
	
}
