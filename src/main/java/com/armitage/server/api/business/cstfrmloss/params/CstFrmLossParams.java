package com.armitage.server.api.business.cstfrmloss.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="CstFrmLossParams",description="成本中心报损单详情查询参数")
public class CstFrmLossParams {
	@ApiModelProperty(value="报损单号",dataType="String",example="123",required=false)
	private String billNo;

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

}
