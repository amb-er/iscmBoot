package com.armitage.server.api.business.invSaleIssueBill.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="InvSaleIssueBillParams",description="销售出库单详情查询参数")
public class InvSaleIssueBillParams {
	@ApiModelProperty(value="第三方单号",dataType="String",example="123",required=true)
	private String otherNo;

	public String getOtherNo() {
		return otherNo;
	}

	public void setOtherNo(String otherNo) {
		this.otherNo = otherNo;
	}

}
