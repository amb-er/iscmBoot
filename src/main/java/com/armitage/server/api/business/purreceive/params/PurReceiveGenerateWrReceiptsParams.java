package com.armitage.server.api.business.purreceive.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurReceiveGenerateWrReceiptsParams",description="生成入库单参数对象")
public class PurReceiveGenerateWrReceiptsParams {

	@ApiModelProperty(value="收货单号",dataType="String",example="123",required=true)
	private String pvNo;

	public String getPvNo() {
		return pvNo;
	}

	public void setPvNo(String pvNo) {
		this.pvNo = pvNo;
	}

}
