package com.armitage.server.api.business.basedata.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="SupplierListParams",description="查询列表参数对象")
public class SupplierListParams {
	@ApiModelProperty(value="供应商名称",dataType="String",example="XX商行",required=false)
	private String vendorName;
	
	@ApiModelProperty(value="混合查询条件",dataType="String",example="123",required=false)
	private String mixParam;

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getMixParam() {
		return mixParam;
	}

	public void setMixParam(String mixParam) {
		this.mixParam = mixParam;
	}
	

}
