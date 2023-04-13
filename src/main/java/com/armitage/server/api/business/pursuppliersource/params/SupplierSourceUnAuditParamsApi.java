package com.armitage.server.api.business.pursuppliersource.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="SupplierSourceUnAuditParamsApi")
public class SupplierSourceUnAuditParamsApi extends RequestParams{
	@ApiModelProperty(required = true)
	private SupplierSourceUnAuditParams params;

	public SupplierSourceUnAuditParams getParams() {
		return params;
	}

	public void setParams(SupplierSourceUnAuditParams params) {
		this.params = params;
	}
	
}
