package com.armitage.server.api.business.pursuppliersource.params;

import com.armitage.server.api.common.RequestParams;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="SupplierSourceAuditParamsApi")
public class SupplierSourceAuditParamsApi extends RequestParams{
	@ApiModelProperty(required = true)
	private SupplierSourceAuditParams params;

	public SupplierSourceAuditParams getParams() {
		return params;
	}

	public void setParams(SupplierSourceAuditParams params) {
		this.params = params;
	}

}
