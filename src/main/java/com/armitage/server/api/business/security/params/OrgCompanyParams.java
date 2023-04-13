package com.armitage.server.api.business.security.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="OrgCompanyParams",description="查询列表参数对象")
public class OrgCompanyParams {
	@ApiModelProperty(value="登录组织",dataType="String",example="123",required=true)
	private String orgUnitNo;

	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}

}
