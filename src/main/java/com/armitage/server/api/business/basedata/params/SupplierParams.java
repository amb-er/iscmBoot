package com.armitage.server.api.business.basedata.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="SupplierParams",description="查询列表参数对象")
public class SupplierParams {
	@ApiModelProperty(value="供应商编码",dataType="String",example="123",required=true)
	private String vendorNo;

	public String getVendorNo() {
		return vendorNo;
	}

	public void setVendorNo(String vendorNo) {
		this.vendorNo = vendorNo;
	}

}
