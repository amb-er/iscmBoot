package com.armitage.server.api.business.purquotation.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurQuotationSubmitParams",description="提交参数对象")
public class PurQuotationSubmitParams {
	@ApiModelProperty(value="报价单号",dataType="String",example="123",required=true)
	private String pqNo;

	public String getPqNo() {
		return pqNo;
	}

	public void setPqNo(String pqNo) {
		this.pqNo = pqNo;
	}
}
