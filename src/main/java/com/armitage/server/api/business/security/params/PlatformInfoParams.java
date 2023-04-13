package com.armitage.server.api.business.security.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PlatformInfoParams",description="平台信息查询对象")
public class PlatformInfoParams {
	@ApiModelProperty(value="组织编号",dataType="String",example="00000027",required=true)
	private String orgUnitNo;

	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}

}
