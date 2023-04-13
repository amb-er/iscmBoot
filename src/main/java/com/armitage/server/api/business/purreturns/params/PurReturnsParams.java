package com.armitage.server.api.business.purreturns.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurReturnsParams",description="退货申请单详情查询参数")
public class PurReturnsParams {
	@ApiModelProperty(value="退货申请号",dataType="String",example="123",required=true)
	private String rtNo;

	public String getRtNo() {
		return rtNo;
	}

	public void setRtNo(String rtNo) {
		this.rtNo = rtNo;
	}

}
