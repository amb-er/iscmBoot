package com.armitage.server.api.business.invotherissue.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvOtherIssueParams",description="其他出库单详情查询参数")
public class InvOtherIssueParams {
	@ApiModelProperty(value="出库单号",dataType="String",example="123",required=false)
	private String otNo;

	public String getOtNo() {
		return otNo;
	}

	public void setOtNo(String otNo) {
		this.otNo = otNo;
	}

}
