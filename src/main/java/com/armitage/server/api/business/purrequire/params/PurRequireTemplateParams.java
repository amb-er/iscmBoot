package com.armitage.server.api.business.purrequire.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurRequireTemplateParams",description="请购模板详情查询参数")
public class PurRequireTemplateParams {
	@ApiModelProperty(value="模板编号",dataType="String",example="123",required=false)
	private String prNo;

	public String getPrNo() {
		return prNo;
	}

	public void setPrNo(String prNo) {
		this.prNo = prNo;
	}
	
}
