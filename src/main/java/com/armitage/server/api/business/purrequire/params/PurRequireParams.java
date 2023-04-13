package com.armitage.server.api.business.purrequire.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurRequireParams",description="请购单详情查询参数")
public class PurRequireParams {
	@ApiModelProperty(value="请购单号",dataType="String",example="123",required=false)
	private String prNo;
	public String getPrNo() {
		return prNo;
	}

	public void setPrNo(String prNo) {
		this.prNo = prNo;
	}
	
}
