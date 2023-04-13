package com.armitage.server.api.business.purreceive.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurRecSupplierListParams",description="查询列表参数对象")
public class PurRecSupplierListParams {
	@ApiModelProperty(value="供应商名称",dataType="String",example="XX商行",required=false)
	private String vendorName;

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	

}
