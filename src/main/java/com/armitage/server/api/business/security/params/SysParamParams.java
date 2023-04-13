package com.armitage.server.api.business.security.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="SysParamParams",description="系统参数查询对象")
public class SysParamParams {
	@ApiModelProperty(value="参数代码",dataType="String",required=true)
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
