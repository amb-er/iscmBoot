package com.armitage.server.api.business.basedata.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="SupplierUpdateSendOAParams",description="修改参数对象")
public class SupplierUpdateSendOAParams {
	
	@ApiModelProperty(value="单据状态",dataType="String",example="A",required=false)
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
