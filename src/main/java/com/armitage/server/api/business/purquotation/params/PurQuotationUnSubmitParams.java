package com.armitage.server.api.business.purquotation.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurQuotationUnSubmitParams",description="取消提交报价单参数对象")
public class PurQuotationUnSubmitParams {
	@ApiModelProperty(value="报价单号",dataType="String",example="123",required=true)
	private String pqNo;

	public String getPqNo() {
		return pqNo;
	}

	public void setPqNo(String pqNo) {
		this.pqNo = pqNo;
	}
}
