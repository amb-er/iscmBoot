package com.armitage.server.api.business.purreceive.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurRecPurOrgUnitParams",description="采购组织查询参数")
public class PurRecPurOrgUnitParams {
	@ApiModelProperty(value="组织名称",dataType="String",example="采购中心",required=false)
	private String orgUnitName;

	public String getOrgUnitName() {
		return orgUnitName;
	}

	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}

}
