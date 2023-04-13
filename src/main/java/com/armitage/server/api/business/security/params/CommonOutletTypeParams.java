package com.armitage.server.api.business.security.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="CommonOutletTypeParams",description="组织可用产品查询参数对象")
public class CommonOutletTypeParams {
	@ApiModelProperty(value="组织编号",dataType="String",example="00000008",required=true)
	private String orgUnitNo;

	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}
	
}
