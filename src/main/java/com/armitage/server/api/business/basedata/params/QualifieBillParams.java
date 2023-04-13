package com.armitage.server.api.business.basedata.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="QualifieBillParams",description="资质申请单详情查询参数")
public class QualifieBillParams {
	@ApiModelProperty(value="申请单号",dataType="String",example="123",required=false)
	private String billNo;

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

}
