package com.armitage.server.api.business.purrequire.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PurReqOrgUnitParams",description="申购组织查询参数")
public class PurReqOrgUnitParams {
	@ApiModelProperty(value="组织名称",dataType="String",example="中餐部",required=false)
	private String orgUnitName;

	public String getOrgUnitName() {
		return orgUnitName;
	}

	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}

	
}
