package com.armitage.server.api.business.pursuppliersource.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="SupplierSourceParams",description="供应商寻源单详情查询参数")
public class SupplierSourceParams {
	@ApiModelProperty(value="寻源单号",dataType="String",example="123",required=true)
	private String billNo;

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}


}
