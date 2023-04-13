package com.armitage.server.api.business.purrequire.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurchaseTypeParams",description="采购类型查询参数")
public class PurchaseTypeParams {
	@ApiModelProperty(value="采购组织",dataType="String",example="00000531",required=false)
	private String purOrgUnitNo;

	public String getPurOrgUnitNo() {
		return purOrgUnitNo;
	}

	public void setPurOrgUnitNo(String purOrgUnitNo) {
		this.purOrgUnitNo = purOrgUnitNo;
	}

	
}
