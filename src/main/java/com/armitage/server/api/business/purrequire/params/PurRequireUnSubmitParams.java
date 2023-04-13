package com.armitage.server.api.business.purrequire.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurRequireUnSubmitParams",description="取消提交参数对象")
public class PurRequireUnSubmitParams {
	@ApiModelProperty(value="请购单号",dataType="String",example="123",required=true)
	private String prNo;

	public String getPrNo() {
		return prNo;
	}

	public void setPrNo(String prNo) {
		this.prNo = prNo;
	}
}
