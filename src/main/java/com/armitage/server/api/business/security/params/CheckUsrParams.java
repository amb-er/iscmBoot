package com.armitage.server.api.business.security.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="CheckUsrParams",description="用户检查参数对象")
public class CheckUsrParams {
	@ApiModelProperty(value="用户编号",dataType="String",example="001",required=true)
	private String usrCode;
	@ApiModelProperty(value="密码",dataType="String",example="",required=true)
	private String pass;

	public String getUsrCode() {
		return usrCode;
	}

	public void setUsrCode(String usrCode) {
		this.usrCode = usrCode;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
