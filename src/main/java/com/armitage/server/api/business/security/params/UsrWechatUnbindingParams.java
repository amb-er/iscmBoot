package com.armitage.server.api.business.security.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="UsrWechatUnbindingParams",description="公众号解绑参数对象")
public class UsrWechatUnbindingParams {
	@ApiModelProperty(value="用户编号",dataType="String",example="001",required=true)
	private String usrCode;

	@ApiModelProperty(value="微信公众号码",dataType="String",example="",required=true)
	private String wechatAccount;

	public String getUsrCode() {
		return usrCode;
	}

	public void setUsrCode(String usrCode) {
		this.usrCode = usrCode;
	}

	public String getWechatAccount() {
		return wechatAccount;
	}

	public void setWechatAccount(String wechatAccount) {
		this.wechatAccount = wechatAccount;
	}

}
