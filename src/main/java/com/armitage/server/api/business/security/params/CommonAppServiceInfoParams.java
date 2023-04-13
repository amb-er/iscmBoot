package com.armitage.server.api.business.security.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="CommonAppServiceInfoParams",description="应用服务查询参数对象")
public class CommonAppServiceInfoParams {
	@ApiModelProperty(value="应用服务代码",dataType="String",example="iSCM",required=true)
	private String appServiceCode;

	@ApiModelProperty(value="系统Id",dataType="Long",example="170",required=true)
	private long systemId;

	public String getAppServiceCode() {
		return appServiceCode;
	}

	public void setAppServiceCode(String appServiceCode) {
		this.appServiceCode = appServiceCode;
	}

	public long getSystemId() {
		return systemId;
	}

	public void setSystemId(long systemId) {
		this.systemId = systemId;
	}

	
}
