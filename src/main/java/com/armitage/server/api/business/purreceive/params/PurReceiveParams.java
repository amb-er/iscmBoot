package com.armitage.server.api.business.purreceive.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurReceiveParams",description="请购单详情查询参数")
public class PurReceiveParams {
	@ApiModelProperty(value="收货单号",dataType="String",example="123",required=false)
	private String pvNo;

	public String getPvNo() {
		return pvNo;
	}

	public void setPvNo(String pvNo) {
		this.pvNo = pvNo;
	}

}
