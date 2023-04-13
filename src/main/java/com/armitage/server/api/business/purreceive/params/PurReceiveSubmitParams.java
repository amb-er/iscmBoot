package com.armitage.server.api.business.purreceive.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurReceiveSubmitParams",description="提交参数对象")
public class PurReceiveSubmitParams {
	@ApiModelProperty(value="收货单号",dataType="String",example="123",required=true)
	private String pvNo;

	public String getPvNo() {
		return pvNo;
	}

	public void setPvNo(String pvNo) {
		this.pvNo = pvNo;
	}
	
}
