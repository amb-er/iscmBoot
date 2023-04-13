package com.armitage.server.api.business.purreceive.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurReceiverListParams",description="查询列表参数对象")
public class PurReceiverListParams {
	@ApiModelProperty(value="收货人姓名",dataType="String",example="张",required=false)
	private String receiverName;

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	
}
