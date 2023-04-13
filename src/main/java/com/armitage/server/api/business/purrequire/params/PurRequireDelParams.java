package com.armitage.server.api.business.purrequire.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurRequireDelParams",description="删除参数对象")
public class PurRequireDelParams {
	@ApiModelProperty(value="请购单号",dataType="String",example="123",required=true)
	private String prNo;

	public String getPrNo() {
		return prNo;
	}

	public void setPrNo(String prNo) {
		this.prNo = prNo;
	}
	
}
