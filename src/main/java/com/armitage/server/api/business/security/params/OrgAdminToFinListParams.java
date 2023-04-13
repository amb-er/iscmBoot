package com.armitage.server.api.business.security.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="OrgAdminToFinListParams",description="查询列表参数对象")
public class OrgAdminToFinListParams {
	@ApiModelProperty(value="混合查询条件",dataType="String",example="123",required=false)
	private String mixParam;
	
	@ApiModelProperty(value="财务组织",dataType="String",example="123",required=true)
	private String finOrgUnitNo;

	public String getMixParam() {
		return mixParam;
	}

	public void setMixParam(String mixParam) {
		this.mixParam = mixParam;
	}

	public String getFinOrgUnitNo() {
		return finOrgUnitNo;
	}

	public void setFinOrgUnitNo(String finOrgUnitNo) {
		this.finOrgUnitNo = finOrgUnitNo;
	}
	
	
}
